package com.nt.uploadutil.service.file;


import com.nt.uploadutil.domain.FileChunkDto;
import com.nt.uploadutil.domain.resp.DataResp;
import com.nt.uploadutil.domain.resp.Resp;
import com.nt.uploadutil.enums.file.save.SaveTypeEnum;
import com.nt.uploadutil.utils.MultiImplServiceInvoker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * 基础文件服务
 * @author arvin
 * @date 2023/01/08
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CommonFileService {

	private final MultiImplServiceInvoker<SaveFileService, SaveTypeEnum> invoker;

	/**
	 * 默认切片大小
	 */
	@Value("${natu.save-file.chunk-size}")
	private Long defaultChunkSize;

	/**
	 * 上传缓存地址
	 */
	@Value("${natu.save-file.temp-path}")
	private String baseFileTempPath;

	/**
	 * 存储方式
	 */
	@Value("${natu.save-file.save-type}")
	private SaveTypeEnum saveType;

	/**
	 * 获取当前所选定的存储方式
	 * @return service子类
	 */
	private SaveFileService saveFileService() {
		return invoker.getMultiService(saveType);
	}

	/**
	 * 查询数据是否已存在
	 * @param dto 文件切片信息
	 * @return 请求返回信息
	 */
	public Resp check(FileChunkDto dto) {
		// TODO 此处需改为查询数据库或Redis缓存（存）
		return DataResp.succeed(Boolean.FALSE);
	}

	/**
	 * 文件上传
	 * @param dto 文件切片信息
	 * @return 请求返回信息
	 */
	public Resp upload(FileChunkDto dto) {
		// TODO 需根据实际情况配置
		final String currentUser = "test";
		// 参数的判空和各数据格式的验证
		if (dto.getFile() == null) {
			log.warn("用户：{} 上传文件为空！", currentUser);
			return Resp.badRequest();
		} else if (!dto.getChunkSize().equals(defaultChunkSize)
				|| !dto.getCurrentChunkSize().equals(dto.getFile().getSize())) {
			log.warn("用户：{} 切片大小不符！", currentUser);
			return Resp.badRequest();
		}

		// 查询缓存查看是否存在
		// TODO 此处默认不存在，需根据实际情况具体配置。若存在直接使用秒传逻辑即可

		// 进入保存流程
		Boolean uploadResult = Boolean.FALSE;
		if (dto.getTotalChunks() == 1) {
			uploadResult = saveFileService().uploadSingleFile(dto);
		} else {
			try {
				uploadResult = saveFileService().uploadSharding(dto, getTempAddr(dto.getFilename()));
			} catch (IOException e) {
				log.error("文件：{} 缓存失败！:", dto.getIdentifier(), e);
			}
		}

		if (!uploadResult) {
			log.error("用户：{} 上传的文件：{} 保存失败！", currentUser, dto.getFilename());
			return Resp.serverError();
		}

		return Resp.succeed();
	}

	/**
	 * 将文件从缓存区移至存储区
	 * @param fileName 文件名
	 * @param fileIdentifier 文件唯一标识符
	 * @return 请求返回信息
	 */
	public Resp confirmUpload(String fileName, String fileIdentifier) {
		// TODO 通过redis确认块完整性
		if (saveFileService().confirmUpload(new File(getTempAddr(fileName)), fileIdentifier)) {
			return Resp.succeed();
		} else {
			return Resp.serverError();
		}
	}

	/**
	 * 获取缓存文件的绝对地址
	 * @param fileName 缓存文件名
	 * @return 缓存文件的绝对地址
	 */
	private String getTempAddr(String fileName) {
		return baseFileTempPath + File.separator + fileName;
	}

}

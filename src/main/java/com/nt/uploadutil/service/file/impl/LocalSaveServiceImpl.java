package com.nt.uploadutil.service.file.impl;


import com.nt.uploadutil.domain.FileChunkDto;
import com.nt.uploadutil.service.file.SaveFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 文件的本地存储实现类
 *
 * @author arvin
 * @date 2023/01/08
 */
@Service
@Slf4j
public class LocalSaveServiceImpl implements SaveFileService {

	/**
	 * 上传保存地址
	 */
	@Value("${natu.save-file.save-path}")
	private String baseFileSavePath;

	@Override
	public Boolean uploadSingleFile(FileChunkDto dto) {
		File saveFile = getSaveFilePath(dto.getIdentifier());
		try {
			dto.getFile().transferTo(saveFile);
			return Boolean.TRUE;
		} catch (IOException e) {
			log.error("文件：{} 保存失败！", dto.getFilename());
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean confirmUpload(File source, String fileIdentifier) {
		try {
			Files.copy(source.toPath(), getSaveFilePath(fileIdentifier).toPath());
			// TODO 将文件信息存入数据库
			return Boolean.TRUE;
		} catch (IOException e) {
			log.error("文件：{} 保存失败！", fileIdentifier);
			return Boolean.FALSE;
		}
	}

	private File getSaveFilePath(String identifier) {
		final String savePath = baseFileSavePath + File.separator + identifier;
		return new File(savePath);
	}

}

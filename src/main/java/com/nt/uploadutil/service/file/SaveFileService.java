package com.nt.uploadutil.service.file;



import com.nt.uploadutil.domain.FileChunkDto;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 文件保存服务
 * TODO 通过AOP将抽象方法中文件的相关信息存入数据库并写入Redis缓存（存）
 * @author arvin
 * @date 2023/01/08
 */
public interface SaveFileService extends CommonMultiImplService {


	/**
	 * 单文件上传
	 * @param dto 文件切片信息
	 * @return 完成结果
	 */
	Boolean uploadSingleFile(FileChunkDto dto);

	/**
	 * 将文件存入储存区
	 * TODO 通过AOP删除Redis缓存（缓）相关信息
	 * @param source 缓存区文件目录地址
	 * @param fileIdentifier 文件唯一标识
	 * @return 完成结果
	 */
	Boolean confirmUpload(File source, String fileIdentifier);

	/**
	 * 大文件切片上传至缓存区
	 * @param dto 文件切片信息
	 * @param tempAddr 文件缓存地址
	 * @return 完成结果
	 * @throws IOException IO异常
	 */
	default Boolean uploadSharding(FileChunkDto dto, String tempAddr) throws IOException {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(tempAddr, "rw")) {
			// 块大小
			final long chunkSize = dto.getChunkSize();
			// 偏移量
			final long offset = chunkSize * (dto.getChunkNumber() - 1);
			// 定位到当前切片的偏移量
			randomAccessFile.seek(offset);
			// 写入
			randomAccessFile.write(dto.getFile().getBytes());
			// TODO 将块信息存入Redis缓存（缓）方便后期确认块完整性
			return Boolean.TRUE;
		}
	}
}

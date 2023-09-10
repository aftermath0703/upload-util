package com.nt.uploadutil.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 文件切片信息
 * @author arvin
 * @date 2023/01/08
 */
@Data
public class FileChunkDto implements Serializable {

	private static final long serialVersionUID = 6712868193468918716L;

	/**
	 * 当前块的次序，第一个块是 1，注意不是从 0 开始的
	 */
	private Integer chunkNumber;

	/**
	 * 文件被分成块的总数
	 */
	private Integer totalChunks;

	/**
	 * 分块大小，根据 totalSize 和这个值你就可以计算出总共的块数。注意最后一块的大小可能会比这个要大
	 */
	private Long chunkSize;

	/**
	 * 当前块的大小，实际大小
	 */
	private Long currentChunkSize;

	/**
	 * 文件总大小
	 */
	private Long totalSize;

	/**
	 * 这个就是每个文件的唯一标识，即文件MD5值
	 */
	private String identifier;

	/**
	 * 文件名
	 */
	private String filename;

	/**
	 * 文件
	 */
	private MultipartFile file;
}

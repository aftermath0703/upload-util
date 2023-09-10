package com.nt.uploadutil.controller;

import com.nt.uploadutil.domain.FileChunkDto;
import com.nt.uploadutil.domain.resp.Resp;
import com.nt.uploadutil.service.file.CommonFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 通用请求处理
 *
 * @author arvin
 * @date 2023/02/16
 */
@RestController
@RequestMapping("/common")
@RequiredArgsConstructor
public class CommonController {

	private final CommonFileService commonFileService;

	/**
	 * 查询数据是否存在
	 * @param dto 文件切片信息
	 * @return 请求返回信息
	 */
	@GetMapping("/upload")
	public Resp check(@ModelAttribute FileChunkDto dto) {
		return commonFileService.check(dto);
	}

	/**
	 * 文件上传
	 * @param dto 文件切片信息
	 * @return 请求返回信息
	 */
	@PostMapping("/upload")
	public Resp uploadFile(@RequestBody FileChunkDto dto) {
		return commonFileService.upload(dto);
	}

	/**
	 * 将文件从缓存区移至存储区
	 * @param filename 文件名
	 * @param identifier 文件唯一标识符
	 * @return 请求返回信息
	 */
	@PostMapping("/confirm/{filename}")
	public Resp confirm(@PathVariable("filename") String filename, @RequestBody String identifier) {
		return commonFileService.confirmUpload(filename, identifier);
	}

}

package com.nt.uploadutil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 * TODO 需添加定时任务清空Redis缓存（缓）中不存在的文件
 *
 * @author arvin
 * @date 2023/02/17
 */
@SpringBootApplication
public class UploadUtilApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadUtilApplication.class, args);
	}

}

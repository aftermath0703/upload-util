package com.nt.uploadutil.config;

import com.nt.uploadutil.enums.file.save.SaveTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.File;
import java.util.Arrays;

/**
 * 自定义属性
 * @author arvin
 * @date 2023/01/08
 */
@Validated
@Configuration
@ConfigurationProperties(prefix = "natu")
public class AppProperties {

	@Getter
	@Setter
	private SaveFile saveFile = new SaveFile();

	@PostConstruct
	public void init() {
		final File tempFile = new File(this.saveFile.tempPath);
		final File saveFile = new File(this.saveFile.savePath);
		Arrays.asList(tempFile, saveFile)
				.forEach(file -> {
					if (!file.exists()) {
						file.mkdir();
					}
				});
	}

	/**
	 * 文件上传相关
	 */
	@Getter
	@Setter
	public static class SaveFile {
		/**
		 * 上传文件缓存路径
		 */
		@NotEmpty
		private String tempPath;

		/**
		 * 上传文件本地保存路径
		 */
		@NotEmpty
		private String savePath;

		/**
		 * 大文件切片大小
		 * 默认5MB，即5 * 1024 * 1024
		 */
		@Positive
		@NotNull
		private Long chunkSize = 5242880L;

		/**
		 * 存储方式
		 */
		@NotNull
		private SaveTypeEnum saveType = SaveTypeEnum.LOCAL;
	}

}

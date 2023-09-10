package com.nt.uploadutil.enums.file.save;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 存储方式的枚举类
 * @author arvin
 * @date 2023/01/29
 */
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SaveTypeEnum implements MultiImplServiceSelector {

	/**
	 * 本地存储
	 */
	LOCAL("1", "localSaveServiceImpl", "本地存储");

	/**
	 * 编号
	 */
	private String code;

	/**
	 * 接口的实现类名
	 */
	private String implement;

	/**
	 * 备注
	 */
	private String comment;

}

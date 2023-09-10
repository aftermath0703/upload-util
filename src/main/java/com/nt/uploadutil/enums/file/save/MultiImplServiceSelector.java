package com.nt.uploadutil.enums.file.save;

/**
 * 多实现service枚举的顶级父类
 * 注意：子类必须为枚举类
 * @author arvin
 * @date 2023/01/29
 */
public interface MultiImplServiceSelector {

	/**
	 * 获得实现类的名称
	 * 注意：出参需以小写字母开头
	 * @return 实现类的名称
	 */
	String getImplement();

}

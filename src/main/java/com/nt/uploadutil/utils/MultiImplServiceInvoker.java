package com.nt.uploadutil.utils;


import com.nt.uploadutil.enums.file.save.MultiImplServiceSelector;
import com.nt.uploadutil.service.file.CommonMultiImplService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 多实现服务的调用器
 * @param <S> service接口
 * @param <E> 多实现service的枚举类
 *
 * @author arvin
 * @date 2023/01/29
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MultiImplServiceInvoker<S extends CommonMultiImplService,E extends MultiImplServiceSelector> {

	private final Map<String, S> multiService;

	private final S singleService;

	/**
	 * 获取目标服务
	 * 注意：调用方需做判空处理
	 * @param selector 枚举选择器
	 * @return 指定的service实现类
	 */
	public S getMultiService(E selector) {
		// 使用指定实现类
		S service = multiService.get(selector.getImplement());

		//找不到指定实现类时的处理方法
		if (service == null) {
			log.error("无法定位 {} 的对应实现类！", selector);
			// 通过反射查找枚举类中的第一个枚举参数
			final Class<? extends MultiImplServiceSelector> enumClass = selector.getClass();
			if (enumClass.isEnum()) {
				// 成功定位枚举类
				final MultiImplServiceSelector defaultSelector = enumClass.getEnumConstants()[0];
				service = multiService.get(defaultSelector.getImplement());
				if (service == null) {
					log.error("无法定位 {} 对应的默认实现类,已使用Spring默认加载器！", defaultSelector);
					service = singleService;
				}
			} else {
				// <E>为非枚举类时使用Spring默认的IOC调用方式
				log.error("{} 并非为MultiImplServiceSelector的子类！", selector.getClass().getCanonicalName());
				service = singleService;
			}
		}
		return service;
	}

}

package com.nt.uploadutil.utils;

import com.nt.uploadutil.UploadUtilApplication;
import com.nt.uploadutil.enums.file.save.SaveTypeEnum;
import com.nt.uploadutil.service.file.SaveFileService;
import com.nt.uploadutil.service.file.impl.LocalSaveServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * 多实现服务的调用器的测试类
 *
 * @author arvin
 * @date 2023/02/20
 */
@SpringBootTest(classes = UploadUtilApplication.class)
public class MultiImplServiceInvokerTests {

	@Autowired
	private MultiImplServiceInvoker<SaveFileService, SaveTypeEnum> invoker;

	@Test
	void testGetMultiService() {
		final SaveFileService service = invoker.getMultiService(SaveTypeEnum.LOCAL);

		assertThat(service).isInstanceOf(LocalSaveServiceImpl.class);
	}

}

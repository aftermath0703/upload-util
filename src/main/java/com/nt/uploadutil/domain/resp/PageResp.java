package com.nt.uploadutil.domain.resp;

import com.nt.uploadutil.enums.ResultCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 含总数据条数的返回体类
 * 主要为分页数据服务
 *
 * @author arvin
 * @date 2022/12/16
 */
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageResp<T> extends DataResp<T> {

	private static final long serialVersionUID = -659150874511168656L;

	/**
	 * 总数据条数
	 */
	private Long total;

	public static <T> PageResp<T> succeed(T data, Long total) {
		final PageResp<T> resp = new PageResp<>();
		resp.setByResultCode(ResultCode.OK);
		resp.setData(data);
		resp.setTotal(total);
		return resp;
	}

}

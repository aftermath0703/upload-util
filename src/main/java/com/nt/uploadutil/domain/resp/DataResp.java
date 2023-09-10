package com.nt.uploadutil.domain.resp;

import com.nt.uploadutil.enums.ResultCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 含数据的返回体类
 * 此类为包含具体数据的返回体类，若有特殊参数需求请参照{@code PageResp}
 * @see PageResp
 *
 * @author arvin
 * @date 2022/12/15
 */
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DataResp<T> extends Resp {
	private static final long serialVersionUID = 5218023559819410108L;

	/**
	 * 返回的详细数据
	 */
	private T data;

	public static <T> DataResp<T> succeed(T data) {
		final DataResp<T> resp = new DataResp<>();
		resp.setByResultCode(ResultCode.OK);
		resp.setData(data);
		return resp;
	}

}

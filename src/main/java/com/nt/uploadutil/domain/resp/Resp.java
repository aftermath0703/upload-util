package com.nt.uploadutil.domain.resp;


import com.nt.uploadutil.enums.ResultCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回体的总父类
 * 此为所有API接口的返回类型，Controller层的所有方法的返回体务必使用此类
 * @see DataResp
 * @see PageResp
 *
 * @author arvin
 * @date 2022/12/14
 */
@Getter
@NoArgsConstructor
public class Resp implements Serializable {

	private static final long serialVersionUID = -8509310293471386003L;

	/**
	 * 状态码
	 */
	private Integer code;

	/**
	 * 状态信息
	 */
	private String msg;

	protected void setByResultCode(ResultCode resultCode) {
		this.code = resultCode.getCode();
		this.msg = resultCode.getMessage();
	}

	protected static Resp status(ResultCode resultCode) {
		final Resp resp = new Resp();
		resp.setByResultCode(resultCode);
		return resp;
	}

	/* 成功的返回体 */

	public static Resp succeed() {
		return status(ResultCode.OK);
	}

	public static Resp created() {
		return status(ResultCode.CREATED);
	}

	public static Resp updated() {
		return status(ResultCode.UPDATED);
	}

	public static Resp deleted() {
		return status(ResultCode.DELETED);
	}

	/* 失败的返回体 */

	public static Resp badRequest() {
		return status(ResultCode.BAD_REQUEST);
	}

	public static Resp unauthorized() {
		return status(ResultCode.UNAUTHORIZED);
	}

	public static Resp forbidden() {
		return status(ResultCode.FORBIDDEN);
	}

	public static Resp notFound() {
		return status(ResultCode.NOT_FOUND);
	}

	public static Resp unprocessableEntity() {
		return status(ResultCode.UNPROCESSABLE_ENTITY);
	}

	public static Resp serverError() {
		return status(ResultCode.INTERNAL_SERVER_ERROR);
	}

}

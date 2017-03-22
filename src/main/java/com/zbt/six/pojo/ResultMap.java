package com.zbt.six.pojo;

import java.util.Map;
/**
 * 公共实体类<MAP>
 */
public class ResultMap<T1, T2> {
	private String message;
	private int status;
	private boolean isSuccess;
	private Map<T1, T2> map;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Map<T1, T2> getMap() {
		return map;
	}
	public void setMap(Map<T1, T2> map) {
		this.map = map;
	} 
}

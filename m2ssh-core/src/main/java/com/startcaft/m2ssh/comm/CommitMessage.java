package com.startcaft.m2ssh.comm;

/**
 * insert,delete,update操作返回的数据对象
 * @author startcaft
 *
 */
public class CommitMessage {
	
	private String msg;
	private boolean result;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public CommitMessage(String msg, boolean result) {
		this.msg = msg;
		this.result = result;
	}
	@Override
	public String toString() {
		return "CommitMessage [msg=" + msg + ", result=" + result + "]";
	}
}

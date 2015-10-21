package com.startcaft.ssm.po.extension;

/**增，删，改动作的返回结果**/
public class CommitMessage {
	
	private boolean result;
	private String msg;
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public CommitMessage(boolean result, String msg) {
		super();
		this.result = result;
		this.msg = msg;
	}
	
	public CommitMessage() {
	}
	
	@Override
	public String toString() {
		return "CommitMessage [result=" + result + ", msg=" + msg + "]";
	}
}

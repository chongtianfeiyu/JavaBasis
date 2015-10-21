package com.startcaft.ssm.po.extension;

/**用户查询对象**/
public class UserQueryVo {
	
	private Integer start;				
	private Integer end;
	private Integer page;				//easyui传递进来的起始页
	private Integer rows;				//easyui传递进来的页码
	private UserCustom userCustom;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public UserCustom getUserCustom() {
		return userCustom;
	}
	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}
	
	
	public UserQueryVo() {
	}
}

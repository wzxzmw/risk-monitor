package com.beebank.monitor.entity;

/**
 * 渠道信息表
 */
public class ChannelInfo {

	/**
	 * @备注:渠道简写
	 * @字段:OWN VARCHAR(20)
	 */
	private String own;
	/**
	 * @备注:渠道名称
	 * @字段:CHANNEL_NAME VARCHAR(50)
	 */
	private String channelName;
	/**
	 * @备注:检索目标方案
	 * @字段:TARGET VARCHAR(20)
	 */
	private String target;
	/**
	 * @备注:检索参数方案
	 * @字段:CONFIG VARCHAR(20)
	 */
	private String config;
	/**
	 * @备注:机构号
	 * @字段:ORGID VARCHAR(10)
	 */
	private String orgid;
	/**
	 * @备注:柜员号
	 * @字段:USERID VARCHAR(10)
	 */
	private String userid;
	/**
	 * @备注:插入数据时间
	 * @字段:REGDATE
	 */
	private java.sql.Timestamp regdate;



	public void setOwn(String own) {
		this.own = own;
	}
	public String getOwn() {
		return this.own;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelName() {
		return this.channelName;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getTarget() {
		return this.target;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public String getConfig() {
		return this.config;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOrgid() {
		return this.orgid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserid() {
		return this.userid;
	}
	public void setRegdate(java.sql.Timestamp regdate) {
		this.regdate = regdate;
	}
	public java.sql.Timestamp getRegdate() {
		return this.regdate;
	}
}


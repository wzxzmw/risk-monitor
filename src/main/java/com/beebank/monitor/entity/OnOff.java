package com.beebank.monitor.entity;

public class OnOff{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
     * @备注:渠道名简称     
     * @字段:CHANNEL_NAME VARCHAR(20)  
     */	
	private String channelName;

	/**
     * @备注:渠道名全称     
     * @字段:CHANNEL_FULL_NAME VARCHAR(50)  
     */	
	private String channelFullName;

	/**
     * @备注:渠道开关状态 0：关 1：开     
     * @字段:CHANNEL_STATUS VARCHAR(2)  
     */	
	private String channelStatus;

	/**
     * @备注:修改时间，默认为当前时间     
     * @字段:UPDATE_TIME TIMESTAMP(26)  
     */	
	private java.sql.Timestamp updateTime;
	
	 

	/**
     * @备注:修改原因     
     * @字段:UPDATE_REASON VARCHAR(100)  
     */	
	private String updateReason;
	public OnOff(){
	}


	
	/**
	 * @param channelName 渠道名简称
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	/**
	 * @return 渠道名简称
	 */
	public String getChannelName() {
		return this.channelName;
	}
	
	/**
	 * @param channelFullName 渠道名全称
	 */
	public void setChannelFullName(String channelFullName) {
		this.channelFullName = channelFullName;
	}
	
	/**
	 * @return 渠道名全称
	 */
	public String getChannelFullName() {
		return this.channelFullName;
	}
	
	/**
	 * @param channelStatus 渠道开关状态 0：关 1：开
	 */
	public void setChannelStatus(String channelStatus) {
		this.channelStatus = channelStatus;
	}
	
	/**
	 * @return 渠道开关状态 0：关 1：开
	 */
	public String getChannelStatus() {
		return this.channelStatus;
	}
	
	/**
	 * @param updateTime 修改时间，默认为当前时间
	 */
	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * @return 修改时间，默认为当前时间
	 */
	public java.sql.Timestamp getUpdateTime() {
		return this.updateTime;
	}
	 
	
	/**
	 * @param updateReason 修改原因
	 */
	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
	}
	
	/**
	 * @return 修改原因
	 */
	public String getUpdateReason() {
		return this.updateReason;
	}
}

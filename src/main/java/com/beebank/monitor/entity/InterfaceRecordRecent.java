package com.beebank.monitor.entity;

public class InterfaceRecordRecent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @备注:主键     
     * @字段:ID BIGINT(19)  
     */	
	private Long id;

	/**
     * @备注:渠道机构号     
     * @字段:BRANCHID VARCHAR(10)  
     */	
	private String branchid;

	/**
     * @备注:渠道交易流水     
     * @字段:OWNREF VARCHAR(32)  
     */	
	private String ownref;

	/**
     * @备注:外围系统缩写     
     * @字段:OWN VARCHAR(10)  
     */	
	private String own;

	/**
     * @备注:请求报文     
     * @字段:REQUESTMESSAGE VARCHAR(2000)  
     */	
	private String requestmessage;

	/**
     * @备注:返回报文     
     * @字段:RESPONSEMESSAGE VARCHAR(1000)  
     */	
	private String responsemessage;

	/**
     * @备注:请求时间     
     * @字段:REQUESTTIME TIMESTAMP(26)  
     */	
	private java.sql.Timestamp requesttime;
	
	 

	/**
     * @备注:检索状态（0：成功 1：失败）     
     * @字段:STATUS VARCHAR(1)  
     */	
	private String status;

	/**
     * @备注:是否命中（命中为：H，未命中为：L ）     
     * @字段:RLEVEL VARCHAR(1)  
     */	
	private String rlevel;

	/**
     * @备注:耗时（毫秒）     
     * @字段:USETIME INTEGER(10)  
     */	
	private Integer usetime;

	/**
     * @备注:插入表时间     
     * @字段:REGDATE TIMESTAMP(26)  
     */	
	private java.sql.Timestamp regdate;
	
	 

	/**
     * @备注:转移时间     
     * @字段:TRANSFERDATE DATE(10)  
     */	
	private java.sql.Date transferdate;
	
	 
	public InterfaceRecordRecent(){
	}

	public InterfaceRecordRecent(
		Long id
	){
		this.id = id;
	}

	
	/**
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return 主键
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * @param branchid 渠道机构号
	 */
	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}
	
	/**
	 * @return 渠道机构号
	 */
	public String getBranchid() {
		return this.branchid;
	}
	
	/**
	 * @param ownref 渠道交易流水
	 */
	public void setOwnref(String ownref) {
		this.ownref = ownref;
	}
	
	/**
	 * @return 渠道交易流水
	 */
	public String getOwnref() {
		return this.ownref;
	}
	
	/**
	 * @param own 外围系统缩写
	 */
	public void setOwn(String own) {
		this.own = own;
	}
	
	/**
	 * @return 外围系统缩写
	 */
	public String getOwn() {
		return this.own;
	}
	
	/**
	 * @param requestmessage 请求报文
	 */
	public void setRequestmessage(String requestmessage) {
		this.requestmessage = requestmessage;
	}
	
	/**
	 * @return 请求报文
	 */
	public String getRequestmessage() {
		return this.requestmessage;
	}
	
	/**
	 * @param responsemessage 返回报文
	 */
	public void setResponsemessage(String responsemessage) {
		this.responsemessage = responsemessage;
	}
	
	/**
	 * @return 返回报文
	 */
	public String getResponsemessage() {
		return this.responsemessage;
	}
	
	/**
	 * @param requesttime 请求时间
	 */
	public void setRequesttime(java.sql.Timestamp requesttime) {
		this.requesttime = requesttime;
	}
	
	/**
	 * @return 请求时间
	 */
	public java.sql.Timestamp getRequesttime() {
		return this.requesttime;
	}
	 
	
	/**
	 * @param status 检索状态（0：成功 1：失败）
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return 检索状态（0：成功 1：失败）
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * @param rlevel 是否命中（命中为：H，未命中为：L ）
	 */
	public void setRlevel(String rlevel) {
		this.rlevel = rlevel;
	}
	
	/**
	 * @return 是否命中（命中为：H，未命中为：L ）
	 */
	public String getRlevel() {
		return this.rlevel;
	}
	
	/**
	 * @param usetime 耗时（毫秒）
	 */
	public void setUsetime(Integer usetime) {
		this.usetime = usetime;
	}
	
	/**
	 * @return 耗时（毫秒）
	 */
	public Integer getUsetime() {
		return this.usetime;
	}
	
	/**
	 * @param regdate 插入表时间
	 */
	public void setRegdate(java.sql.Timestamp regdate) {
		this.regdate = regdate;
	}
	
	/**
	 * @return 插入表时间
	 */
	public java.sql.Timestamp getRegdate() {
		return this.regdate;
	}
	 
	
	/**
	 * @param transferdate 转移时间
	 */
	public void setTransferdate(java.sql.Date transferdate) {
		this.transferdate = transferdate;
	}
	
	/**
	 * @return 转移时间
	 */
	public java.sql.Date getTransferdate() {
		return this.transferdate;
	}
	 
}

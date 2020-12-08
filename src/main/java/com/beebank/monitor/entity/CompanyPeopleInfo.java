package com.beebank.monitor.entity;

/**
 * 公司人员信息
 */
public class CompanyPeopleInfo {


	private Integer id;

	private String custNo;

	private String desc;

	private String ids;

	private String idType;

	private String gender;

	private String name;

	private String address;

	private String country;

	private java.sql.Timestamp regdate;



	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustNo() {
		return this.custNo;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return this.desc;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getIds() {
		return this.ids;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdType() {
		return this.idType;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return this.gender;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return this.address;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountry() {
		return this.country;
	}
	public void setRegdate(java.sql.Timestamp regdate) {
		this.regdate = regdate;
	}
	public java.sql.Timestamp getRegdate() {
		return this.regdate;
	}

	public CompanyPeopleInfo() {
	}

	public CompanyPeopleInfo(String custNo) {
		this.custNo = custNo;
	}
}


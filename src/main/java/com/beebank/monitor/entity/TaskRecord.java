package com.beebank.monitor.entity;

import java.util.Date;

/**
 * 定时任务执行记录表
 */
public class TaskRecord {


	private Integer id;

	private String taskName;

	private Date taskDate;

	private String status;

	private String statusName;

	private java.sql.Timestamp regTime;

	private java.sql.Timestamp updateTime;

	private String remark;



	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskName() {
		return this.taskName;
	}
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}
	public Date getTaskDate() {
		return this.taskDate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getStatusName() {
		return this.statusName;
	}
	public void setRegTime(java.sql.Timestamp regTime) {
		this.regTime = regTime;
	}
	public java.sql.Timestamp getRegTime() {
		return this.regTime;
	}
	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public java.sql.Timestamp getUpdateTime() {
		return this.updateTime;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return this.remark;
	}
}


package com.beebank.monitor.entity;

public class InterfaceRecord {
    private Long id;
    private String branchId;
    private String ownref;
    private String own;
    private String requestMessage;
    private String responseMessage;
    private String requestTime;
    private String status;
    private String rlevel;
    private int useTime;
    private String regDate;

    public InterfaceRecord(String branchId, String ownref,String own, String requestMessage, String responseMessage, String requestTime, String status, String rlevel, int useTime) {
        this.branchId = branchId;
        this.own = own;
        this.ownref = ownref;
        this.requestMessage = requestMessage;
        this.responseMessage = responseMessage;
        this.requestTime = requestTime;
        this.status = status;
        this.rlevel = rlevel;
        this.useTime = useTime;
    }

    public InterfaceRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getOwnref() {
        return ownref;
    }

    public void setOwnref(String ownref) {
        this.ownref = ownref;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRlevel() {
        return rlevel;
    }

    public void setRlevel(String rlevel) {
        this.rlevel = rlevel;
    }

    public int getUseTime() {
        return useTime;
    }

    public void setUseTime(int useTime) {
        this.useTime = useTime;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }
}

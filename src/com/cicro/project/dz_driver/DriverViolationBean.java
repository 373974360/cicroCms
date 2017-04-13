package com.cicro.project.dz_driver;
 
import java.io.Serializable;

public class DriverViolationBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id = 0;
	private int companyId = 0;
	private String name = "";
	private String carNo = "";
	private String reason = "";
	private String advice = "";
	private String violationTime = "";
	private String addTime = "";
    private String status = "";

    private String simpleName = "";
    private String allName = "";
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getViolationTime() {
		return violationTime;
	}
	public void setViolationTime(String violationTime) {
		this.violationTime = violationTime;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSimpleName() {
		return simpleName;
	}
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	public String getAllName() {
		return allName;
	}
	public void setAllName(String allName) {
		this.allName = allName;
	}
    
}
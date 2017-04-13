package com.cicro.project.dz_driver;
 
import java.io.Serializable;

public class DriverCompanyBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id = 0;
	private String simpleName = "";
    private String allName = "";
    private String tel = "";
    private String address = "";
	private String addTime = "";
    private String status = "";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
    
}
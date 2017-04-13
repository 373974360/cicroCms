package com.cicro.project.dz_memberAction;
 
import java.io.Serializable;
 
public class MemberInfoBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String id;
	private String memberId = "";
	private String infoId = "";
	private String type = "";
	private String addTime = "";
	private String ipAddr = "";
	private String byzd = "";
	private String byzd1 = "";
	private String byzd2 = "";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getByzd() {
		return byzd;
	}
	public void setByzd(String byzd) {
		this.byzd = byzd;
	}
	public String getByzd1() {
		return byzd1;
	}
	public void setByzd1(String byzd1) {
		this.byzd1 = byzd1;
	}
	public String getByzd2() {
		return byzd2;
	}
	public void setByzd2(String byzd2) {
		this.byzd2 = byzd2;
	}
	
}
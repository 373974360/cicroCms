package com.cicro.project.dz_siteError;
 
import java.io.Serializable;

public class EchartsBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String siteName ="";
	private String wzg = "";
	private String yzg = "";
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getWzg() {
		return wzg;
	}
	public void setWzg(String wzg) {
		this.wzg = wzg;
	}
	public String getYzg() {
		return yzg;
	}
	public void setYzg(String yzg) {
		this.yzg = yzg;
	}
	
}
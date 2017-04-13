package com.cicro.project.dz_siteError;
 
import java.io.Serializable;

public class SiteErrorTopBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String siteId ="";
	private String totle = "";
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getTotle() {
		return totle;
	}
	public void setTotle(String totle) {
		this.totle = totle;
	}
}
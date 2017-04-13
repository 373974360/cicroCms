/*    */ package com.cicro.wcm.services.extendfunction.zdfwly;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ZdfwlyCateBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 7784059694818824361L;
/* 12 */   private int id = 0;
/* 13 */   private String zdcat_id = "";
/* 14 */   private String zdparent_id = "";
/* 15 */   private String zdcat_name = "";
/* 16 */   private String zdcat_position = "";
/* 17 */   private int zdcat_level = 1;
/* 18 */   private String zdcat_memo = "";
/* 19 */   private int sort_id = 999;
/* 20 */   private String app_id = "";
/* 21 */   private String site_id = "";
/*    */ 
			/*    */   
			/*    */
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
			public String getZdcat_id() {
				return zdcat_id;
			}
			public void setZdcat_id(String zdcat_id) {
				this.zdcat_id = zdcat_id;
			}
			public String getZdparent_id() {
				return zdparent_id;
			}
			public void setZdparent_id(String zdparent_id) {
				this.zdparent_id = zdparent_id;
			}
			public String getZdcat_name() {
				return zdcat_name;
			}
			public void setZdcat_name(String zdcat_name) {
				this.zdcat_name = zdcat_name;
			}
			public String getZdcat_position() {
				return zdcat_position;
			}
			public void setZdcat_position(String zdcat_position) {
				this.zdcat_position = zdcat_position;
			}
			public int getZdcat_level() {
				return zdcat_level;
			}
			public void setZdcat_level(int zdcat_level) {
				this.zdcat_level = zdcat_level;
			}
			public String getZdcat_memo() {
				return zdcat_memo;
			}
			public void setZdcat_memo(String zdcat_memo) {
				this.zdcat_memo = zdcat_memo;
			}
			public int getSort_id() {
				return sort_id;
			}
			public void setSort_id(int sort_id) {
				this.sort_id = sort_id;
			}
			public String getApp_id() {
				return app_id;
			}
			public void setApp_id(String app_id) {
				this.app_id = app_id;
			}
			public String getSite_id() {
				return site_id;
			}
			public void setSite_id(String site_id) {
				this.site_id = site_id;
			} 
		}

/* Location:           E:\Xshell\61.150.72.149(渭南96)\com.zip
 * Qualified Name:     com.cicro.wcm.services.extendfunction.knowledgeTab.KnowledgeCateBean
 * JD-Core Version:    0.6.2
 */
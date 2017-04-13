/*    */ package com.cicro.project.dz_pxxx;
/*    */ 
/*    */ import com.cicro.util.DateUtil;
		import com.cicro.util.FormatUtil;
		import com.cicro.util.OutExcel;
		import com.cicro.util.jconfig.JconfigFactory;
		import com.cicro.util.jconfig.JconfigUtil;
		import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.services.Log.LogManager;

		import java.io.File;
/*    */ import java.util.List;
/*    */ import java.util.Map;

		import javax.servlet.http.HttpServletRequest;

/*    */ 
/*    */ public class PxxxBmRPC
/*    */ {
/*    */   public static String getPxxxBmCount(Map<String, String> m)
/*    */   {
/* 14 */     return PxxxBmManager.getPxxxBmCount(m);
/*    */   }
/*    */                                                 
/*    */   public static List<PxxxBmBean> getPxxxBmList(Map<String, String> m)
/*    */   {
/* 19 */     return PxxxBmManager.getPxxxBmList(m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxBmBean> getAllPxxxBmList()
/*    */   {
/* 24 */     return PxxxBmManager.getAllPxxxBmList();
/*    */   }

/*    */   public static List<PxxxBmBean> getAllPxxxBmByPxID(Map<String, String> m)
/*    */   {
/* 24 */     return PxxxBmManager.getAllPxxxBmByPxID(m);
/*    */   }
/*    */ 
/*    */   public static PxxxBmBean getPxxxBmBean(String gq_id)
/*    */   {
/* 30 */     return PxxxBmManager.getPxxxBmBean(gq_id);
/*    */   }
/*    */ 
/*    */   public static boolean updatePxxxBm(PxxxBmBean hb, HttpServletRequest request)
/*    */   {
/* 35 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 36 */     if (stl != null) {
/* 37 */       return PxxxBmManager.updatePxxxBm(hb, stl);
/*    */     }
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean insertPxxxBm(PxxxBmBean hb, HttpServletRequest request)
/*    */   {
/* 44 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 45 */     if (stl != null) {
/* 46 */       return PxxxBmManager.insertPxxxBm(hb, stl);
/*    */     }
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deletePxxxBm(Map<String, String> m, HttpServletRequest request)
/*    */   {
/* 62 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 63 */     if (stl != null) {
/* 64 */       return PxxxBmManager.deletePxxxBm(m, stl);
/*    */     }
/* 66 */     return false;
/*    */   }
			public static String exportPxxxBm(Map<String, String> m)
			{
				List<PxxxBmBean> pxxxBmList = getPxxxBmList(m);
				if(pxxxBmList != null && pxxxBmList.size() > 0)
				{
					String[][] data = new String[pxxxBmList.size() + 1][12];
					data[0][0] = "培训名称";
					data[0][1] = "报名ID";
					data[0][2] = "姓名";
					data[0][3] = "性别";
					data[0][4] = "身份证号";
					data[0][5] = "工作单位";
					data[0][6] = "职务";
					data[0][7] = "联系方式";
					data[0][8] = "QQ";
					data[0][9] = "报名时间";
					data[0][10] = "是否住宿";
					data[0][11] = "备注";
					for(int i = 0; i < pxxxBmList.size(); i++)
					{
						PxxxBmBean pxxxBmBean = pxxxBmList.get(i);
						data[i+1][0] = pxxxBmBean.getPxmc();
						data[i+1][1] = pxxxBmBean.getBmid();
						data[i+1][2] = pxxxBmBean.getXm();
						data[i+1][3] = pxxxBmBean.getXb();
						data[i+1][4] = pxxxBmBean.getSfzh();
						data[i+1][5] = pxxxBmBean.getGzdw();
						data[i+1][6] = pxxxBmBean.getZw();
						data[i+1][7] = pxxxBmBean.getLxfs();
						data[i+1][8] = pxxxBmBean.getQq();
						data[i+1][9] = pxxxBmBean.getBmsj();
						data[i+1][10] = pxxxBmBean.getSfzs();
						data[i+1][11] = "";
					}
				    String file_name = DateUtil.getCurrentDateTime("yyyyMMddHHmmss");
				    JconfigUtil bc = JconfigFactory.getJconfigUtilInstance("bashConfig");
				    String path = bc.getProperty("path", "", "manager_path").trim() + "/pxxxBm";
				    String tempPath = path + "/" + DateUtil.getCurrentDate();
				    File file2 = new File(FormatUtil.formatPath(tempPath));
				    if (!file2.exists()) {
				    	file2.mkdirs();
				    }
				    String xFile = FormatUtil.formatPath(tempPath + "/" + file_name + ".xls");
	
				    //OutExcel.deleteFile(path);
	
				    OutExcel oe = new OutExcel("培训信息报名表");
	
				    oe.doOut(xFile, data);
				    return (tempPath + "/" + file_name + ".xls").replace("/cicro/wcm/vhosts/common", "");
				}
				return "";
			}
		}

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglRPC
 * JD-Core Version:    0.6.2
 */
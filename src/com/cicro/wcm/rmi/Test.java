/*    */ package com.cicro.wcm.rmi;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.info.GKFtygsBean;
/*    */ import java.io.PrintStream;
/*    */ import javax.naming.Context;
/*    */ import javax.naming.InitialContext;
/*    */ 
/*    */ public class Test
/*    */ {
/*    */   public static final String URL = "rmi://192.168.12.18:1102/customRmi";
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 17 */     Context namingContext = null;
/*    */     try {
/* 19 */       namingContext = new InitialContext();
/* 20 */       ICustomFormRMI rmi = (ICustomFormRMI)namingContext.lookup("rmi://192.168.12.18:1102/customRmi");
/*    */ 
/* 119 */       String from_id = "1110";
/* 120 */       String model_name = "gkftygs";
/* 121 */       GKFtygsBean gkFtygsBean = new GKFtygsBean();
/* 122 */       gkFtygsBean.setEffect_dtime("2013-02-28");
/* 123 */       gkFtygsBean.setAboli_dtime("2013-02-28");
/* 124 */       gkFtygsBean.setAuthor("系统管理员");
/* 125 */       gkFtygsBean.setCarrier_type("载体类型");
/* 126 */       gkFtygsBean.setCat_id(4807);
/* 127 */       gkFtygsBean.setDescription("内容摘要");
/* 128 */       gkFtygsBean.setDoc_no("文号");
/* 129 */       gkFtygsBean.setEditor("");
/* 130 */       gkFtygsBean.setGenerate_dtime("2013-02-28");
/* 131 */       gkFtygsBean.setGk_content("信息内容");
/* 132 */       gkFtygsBean.setGk_duty_dept("责任部门/科室");
/*    */ 
/* 134 */       gkFtygsBean.setGk_index("A0300-010000-2013-000072");
/* 135 */       gkFtygsBean.setGk_input_dept("发布机构");
/* 136 */       gkFtygsBean.setGk_proc("审核程序");
/* 137 */       gkFtygsBean.setGk_range("面向社会");
/* 138 */       gkFtygsBean.setGk_signer("签发人");
/* 139 */       gkFtygsBean.setGk_time_limit("长期公开");
/* 140 */       gkFtygsBean.setGk_validity("有效");
/* 141 */       gkFtygsBean.setGk_way("政府网站");
/* 142 */       gkFtygsBean.setGk_time_limit("长期公开");
/* 143 */       gkFtygsBean.setInput_dtime("2013-02-20 22:22:22");
/* 144 */       gkFtygsBean.setInput_user(1);
/* 145 */       gkFtygsBean.setLanguage("汉语");
/* 146 */       gkFtygsBean.setPlace_key("位置关键词");
/* 147 */       gkFtygsBean.setServe_id(384);
/* 148 */       gkFtygsBean.setServe_name("行政机关");
/* 149 */       gkFtygsBean.setSite_id("GKzfb");
/* 150 */       gkFtygsBean.setSource("政务门户演示网站");
/* 151 */       gkFtygsBean.setTheme_id(370);
/* 152 */       gkFtygsBean.setTheme_name("命令");
/* 153 */       gkFtygsBean.setThumb_url("");
/* 154 */       gkFtygsBean.setTitle("信息标题公开333333");
/* 155 */       gkFtygsBean.setTop_title("");
/* 156 */       gkFtygsBean.setSubtitle("");
/* 157 */       gkFtygsBean.setTopic_id(404);
/* 158 */       gkFtygsBean.setTopic_name("省政府");
/* 159 */       boolean result = rmi.insertForm(gkFtygsBean, model_name, from_id);
/*    */ 
/* 162 */       System.out.println(result);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 312 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.rmi.Test
 * JD-Core Version:    0.6.2
 */
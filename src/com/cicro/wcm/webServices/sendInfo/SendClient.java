/*     */ package com.cicro.wcm.webServices.sendInfo;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.MalformedURLException;
/*     */ import org.codehaus.xfire.XFireFactory;
/*     */ import org.codehaus.xfire.client.XFireProxyFactory;
/*     */ import org.codehaus.xfire.service.Service;
/*     */ import org.codehaus.xfire.service.binding.ObjectServiceFactory;
/*     */ import sun.misc.BASE64Encoder;
/*     */ 
/*     */ public class SendClient
/*     */ {
/*     */   public static String encodeBase64File(String path)
/*     */   {
/*  25 */     String str = "";
/*     */     try {
/*  27 */       File file = new File(path);
/*     */ 
/*  29 */       FileInputStream inputFile = new FileInputStream(file);
/*  30 */       byte[] buffer = new byte[(int)file.length()];
/*  31 */       inputFile.read(buffer);
/*  32 */       inputFile.close();
/*  33 */       str = new BASE64Encoder().encode(buffer);
/*     */     } catch (FileNotFoundException e) {
/*  35 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/*  37 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  40 */     return str;
/*     */   }
/*     */ 
/*     */   public static ISendInfo getServicesObj(String site_domain)
/*     */   {
/*  46 */     Service srvcModel = new ObjectServiceFactory().create(ISendInfo.class);
/*  47 */     XFireProxyFactory factory = new XFireProxyFactory(
/*  48 */       XFireFactory.newInstance().getXFire());
/*     */ 
/*  50 */     String servicesURL = "http://" + site_domain + "/services/SendInfo";
/*     */     try {
/*  52 */       return (ISendInfo)factory.create(srvcModel, servicesURL);
/*     */     }
/*     */     catch (MalformedURLException e) {
/*  55 */       e.printStackTrace();
/*  56 */     }return null;
/*     */   }
/*     */ 
/*     */   public static String getXML()
/*     */   {
/*  61 */     String username = "system";
/*  62 */     String password = "=#=88OWKd587QY=";
/*  63 */     String t_site_id = "CMSWCM16";
/*  64 */     String s_site_domain = "www.16.com";
/*  65 */     String s_site_name = "政务门户演示网站";
/*  66 */     String s_user_name = "系统管理员";
/*  67 */     String s_send_dtime = "2013-03-27 09:00:08";
/*     */ 
/*  69 */     String r_cat_id = "10001";
/*  70 */     String r_cat_name = "测试目录三ydc";
/*  71 */     String send_record_id = "87";
/*  72 */     String info_content = "<p><br />&nbsp;&nbsp;&nbsp; 在伟大的中国共产党成立90周年之际，我州各地的广大党员干部和普通百姓，都在以感恩的心情，学习和了解我们国家的执政党&mdash;&mdash;中国共产党的奋斗史、创业史和改革开放史。这是一件非常有意义的事。<br />&nbsp;&nbsp;&nbsp; 一位学者说，&ldquo;不懂历史的人没有根，淡忘历史的民族没有魂&rdquo;。我们应该记住这句名言。我们每个人都有了解党史、读懂党史、运用党史的责任。只有用党的&ldquo;三史&rdquo;来洗礼自己的思想，才能为&ldquo;做懂根的人，做有魂的民族&rdquo;打下思想基础。<br />&nbsp;&nbsp;&nbsp; &ldquo;以铜为镜可以正衣冠，以人为镜可以知得失，以史为镜可以知古今&rdquo;。学过党史的人知道，我们的党在已经走过的90年光辉历程中，真正做到了从无到有、由弱变强的转变，它带领全国各族人民战胜重重艰难险阻，成功地领导了两次革命，干了三件大事，实现了两次飞跃&hellip;&hellip;中国共产党的诞生和发展，扭转了近现代中国历史的走向，改变了中国人民的命运，对整个世界的格局和面貌也产生了广泛而深远的影响。<br />&nbsp;&nbsp;&nbsp; 了解了党史、学习了党史，可以得出这样的结沦：共产党没有自身的特殊利益，共产党的职能就是为人民服务。过去，没有共产党就没有新中国；今天，没有共产党就没有和谐发展的中国；未来，我们应该更加坚定一个信念：没有共产党就不会有未来屹立于世界强国之列的中国和中华民族。</p>";
/*  73 */     String info_keywords = "关键字,测试";
/*  74 */     String info_description = "简介，描述";
/*  75 */     String title = "第三方报送信息测试20130516";
/*  76 */     String model_id = "11";
/*  77 */     String author = "作者";
/*  78 */     String source = "西安日报";
/*     */ 
/*  81 */     String file_name = "local.gif";
/*  82 */     String file_url = "/browser/images/local.gif";
/*  83 */     String file_code = "";
/*  84 */     file_code = encodeBase64File("D://ydc/local.gif");
/*     */ 
/*  86 */     System.out.println(file_code);
/*  87 */     StringBuffer sbf = new StringBuffer();
/*  88 */     sbf.append("<cicrowcm>");
/*  89 */     sbf.append("<username><![CDATA[" + username + "]]></username>");
/*  90 */     sbf.append("<password><![CDATA[" + password + "]]></password>");
/*  91 */     sbf.append("<t_site_id><![CDATA[" + t_site_id + "]]></t_site_id>");
/*  92 */     sbf.append("<s_site_domain><![CDATA[" + s_site_domain + "]]></s_site_domain>");
/*  93 */     sbf.append("<s_site_name><![CDATA[" + s_site_name + "]]></s_site_name>");
/*  94 */     sbf.append("<s_user_name><![CDATA[" + s_user_name + "]]></s_user_name>");
/*  95 */     sbf.append("<s_send_dtime><![CDATA[" + s_send_dtime + "]]></s_send_dtime>");
/*  96 */     sbf.append("<infoList>");
/*  97 */     sbf.append("<info>");
/*  98 */     sbf.append("<r_cat_id>" + r_cat_id + "</r_cat_id>");
/*  99 */     sbf.append("<r_cat_name>" + r_cat_name + "</r_cat_name>");
/* 100 */     sbf.append("<send_record_id>" + send_record_id + "</send_record_id>");
/* 101 */     sbf.append("<r_content>");
/* 102 */     sbf.append("<info_content><![CDATA[" + info_content + "]]></info_content>");
/* 103 */     sbf.append("<info_keywords><![CDATA[" + info_keywords + "]]></info_keywords>");
/* 104 */     sbf.append("<info_description><![CDATA[" + info_description + "]]></info_description>");
/* 105 */     sbf.append("<title><![CDATA[" + title + "]]></title>");
/* 106 */     sbf.append("<model_id><![CDATA[" + model_id + "]]></model_id>");
/* 107 */     sbf.append("<author><![CDATA[" + author + "]]></author>");
/* 108 */     sbf.append("<source><![CDATA[" + source + "]]></source>");
/* 109 */     sbf.append("</r_content>");
/*     */ 
/* 112 */     sbf.append("<fileList>");
/*     */ 
/* 114 */     sbf.append("<files>");
/* 115 */     sbf.append("<f_content>");
/* 116 */     sbf.append("<file_name><![CDATA[" + file_name + "]]></file_name>");
/* 117 */     sbf.append("<file_url><![CDATA[" + file_url + "]]></file_url>");
/* 118 */     sbf.append("<file_code><![CDATA[" + file_code + "]]></file_code>");
/* 119 */     sbf.append("</f_content>");
/* 120 */     sbf.append("</files>");
/*     */ 
/* 122 */     sbf.append("</fileList>");
/*     */ 
/* 125 */     sbf.append("</info>");
/* 126 */     sbf.append("</infoList>");
/* 127 */     sbf.append("</cicrowcm>");
/* 128 */     System.out.println("");
/*     */ 
/* 130 */     return sbf.toString();
/*     */   }
/*     */ 
/*     */   public static void init()
/*     */   {
/* 139 */     String xml = getXML();
/*     */ 
/* 142 */     String servicesURL = "http://www.hbly.gov.cn/services/SendInfo";
/*     */ 
/* 145 */     Service srvcModel = new ObjectServiceFactory().create(ISendInfo.class);
/* 146 */     XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
/*     */ 
/* 148 */     ISendInfo service = null;
/*     */     try {
/* 150 */       service = (ISendInfo)factory.create(srvcModel, servicesURL);
/*     */     }
/*     */     catch (Exception e) {
/* 153 */       e.printStackTrace();
/*     */     }
/* 155 */     if (service != null)
/*     */     {
/* 157 */       boolean isCorrect = service.sendInfoFormThirdParty(xml, "", "");
/*     */ 
/* 159 */       if (!isCorrect) {
/* 160 */         System.out.println("webService失败！！！");
/* 161 */         return;
/*     */       }
/* 163 */       System.out.println("成功！！！");
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 170 */     init();
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.webServices.sendInfo.SendClient
 * JD-Core Version:    0.6.2
 */
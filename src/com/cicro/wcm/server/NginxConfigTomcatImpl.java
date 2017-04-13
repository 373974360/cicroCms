/*     */ package com.cicro.wcm.server;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.control.SiteDomainBean;
/*     */ import com.cicro.wcm.services.control.domain.SiteDomainManager;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class NginxConfigTomcatImpl
/*     */   implements IApacheConfig
/*     */ {
/*  22 */   private static String LINE_SEPARATOR = "\n";
/*     */ 
/*  24 */   static { if (ServerManager.isWindows())
/*  25 */       LINE_SEPARATOR = "\r\n";
/*     */   }
/*     */ 
/*     */   public String getType()
/*     */   {
/*  34 */     return "tomcat";
/*     */   }
/*     */ 
/*     */   public String addSite(Map info, String nginxConfig_name)
/*     */   {
/*  45 */     return addVhost(info, nginxConfig_name);
/*     */   }
/*     */ 
/*     */   public String addVhost(Map info, String config_name)
/*     */   {
/*  57 */     String domain = (String)info.get("site_domain");
/*  58 */     String site_path = (String)info.get("site_path");
/*  59 */     String site_id = (String)info.get("site_id");
/*     */ 
/*  62 */     if ((domain == null) || ((domain = domain.trim()).length() == 0)) {
/*  63 */       return "ERROR";
/*     */     }
/*  65 */     if ((site_path == null) || ((site_path = site_path.trim()).length() == 0)) {
/*  66 */       return "ERROR";
/*     */     }
/*  68 */     site_path = site_path.replace('\\', '/');
/*     */ 
/*  71 */     String root_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path");
/*     */ 
/*  74 */     String nginxRoot = JconfigUtilContainer.bashConfig().getProperty("path", "", "nginx_path");
/*  75 */     String nginxConfFilePath = FormatUtil.formatPath(nginxRoot + "/conf/nginx.conf");
/*  76 */     File nginxConfFile = new File(nginxConfFilePath);
/*  77 */     if (!nginxConfFile.exists()) {
/*  78 */       return "ERROR";
/*     */     }
/*     */ 
/*  81 */     String nginx_Content = "";
/*     */ 
/*  84 */     String backupConfFilePath = nginxConfFilePath + 
/*  85 */       DateUtil.getString(new Date(), "yyyyMMddHHmmss");
/*     */     try {
/*  87 */       nginx_Content = FileOperation.readFileToString(nginxConfFile);
/*  88 */       FileOperation.copyFile(nginxConfFilePath, backupConfFilePath, true);
/*     */     } catch (Exception e) {
/*  90 */       e.printStackTrace(System.out);
/*  91 */       return "ERROR";
/*     */     }
/*     */ 
/*  95 */     String appendContent = JconfigUtilContainer.apacheConfig().getProperty("value", "", ServerManager.getProxyServer() + config_name).replaceAll("LINE_SEPARATOR", LINE_SEPARATOR);
/*  96 */     appendContent = appendContent.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("'", "\"");
/*  97 */     appendContent = appendContent.replaceAll("\\$domain\\$", domain).replaceAll("\\$site_path\\$", site_path).replaceAll("\\$site_id\\$", site_id);
/*  98 */     appendContent = appendContent.replaceAll("\\$nginx_path\\$", nginxRoot).replaceAll("\\$root_path\\$", root_path).replaceAll("\\$manager_path\\$", FormatUtil.formatPath(JconfigUtilContainer.bashConfig().getProperty("path", "", "manager_path")));
/*     */ 
/* 100 */     nginx_Content = nginx_Content.replace("#replace_content_flag#", appendContent);
/*     */     try
/*     */     {
/* 103 */       FileOperation.writeStringToFile(nginxConfFilePath, nginx_Content, false);
/*     */     } catch (Exception e) {
/* 105 */       e.printStackTrace(System.out);
/*     */       try
/*     */       {
/* 108 */         FileOperation.copyFile(backupConfFilePath, nginxConfFilePath, false);
/*     */       } catch (Exception ex) {
/* 110 */         ex.printStackTrace(System.out);
/*     */       }
/* 112 */       return "ERROR";
/*     */     }
/*     */ 
/* 116 */     return "NO_ERROR";
/*     */   }
/*     */ 
/*     */   public String updateSite(Map infos)
/*     */   {
/* 127 */     updateVhost(infos);
/* 128 */     return "";
/*     */   }
/*     */ 
/*     */   public String delSite(Map infos)
/*     */   {
/* 138 */     delVhost(infos);
/* 139 */     return "";
/*     */   }
/*     */ 
/*     */   public String delMultiVhost(String site_id)
/*     */   {
/* 150 */     String nginxRoot = JconfigUtilContainer.bashConfig().getProperty("path", "", "nginx_path");
/* 151 */     String nginxConfFilePath = FormatUtil.formatPath(nginxRoot + "/conf/nginx.conf");
/* 152 */     nginxConfFilePath = FormatUtil.formatPath(nginxConfFilePath);
/* 153 */     File nginxConfFile = new File(nginxConfFilePath);
/* 154 */     if (!nginxConfFile.exists()) {
/* 155 */       return "ERROR";
/*     */     }
/*     */ 
/* 159 */     String backupConfFilePath = nginxConfFilePath + 
/* 160 */       DateUtil.getString(new Date(), "yyyyMMddHHmmss");
/*     */     try {
/* 162 */       FileOperation.copyFile(nginxConfFilePath, backupConfFilePath, true);
/*     */     } catch (Exception e) {
/* 164 */       e.printStackTrace(System.out);
/* 165 */       return "ERROR";
/*     */     }
/*     */ 
/* 169 */     StringBuffer content = new StringBuffer();
/* 170 */     BufferedReader reader = null;
/* 171 */     String line = "";
/* 172 */     StringBuffer vhost = new StringBuffer();
/*     */     try {
/* 174 */       reader = new BufferedReader(new FileReader(nginxConfFile));
/* 175 */       while ((line = reader.readLine()) != null) {
/* 176 */         if (line.indexOf("server {") != -1) {
/* 177 */           vhost.append(line + "\n");
/* 178 */           while (((line = reader.readLine()) != null) && 
/* 179 */             (line.indexOf("#end") == -1)) {
/* 180 */             vhost.append(line + "\n");
/*     */           }
/* 182 */           vhost.append(line + "\n");
/* 183 */           if (vhost.toString().indexOf(site_id) == -1)
/*     */           {
/* 185 */             content.append(vhost);
/*     */           }
/* 187 */           vhost = new StringBuffer();
/*     */         }
/*     */         else {
/* 190 */           content.append(line + "\n");
/*     */         }
/*     */       }
/* 193 */       FileOperation.writeStringToFile(nginxConfFile, content.toString(), false);
/*     */     } catch (Exception e) {
/* 195 */       e.printStackTrace(System.out);
/*     */       try
/*     */       {
/* 198 */         FileOperation.copyFile(backupConfFilePath, nginxConfFilePath, true);
/*     */       } catch (Exception ex) {
/* 200 */         ex.printStackTrace(System.out);
/*     */       }
/* 202 */       return "ERROR";
/*     */     } finally {
/*     */       try {
/* 205 */         if (reader != null)
/* 206 */           reader.close();
/*     */       }
/*     */       catch (Exception ex) {
/* 209 */         ex.printStackTrace(System.out);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 214 */     return "NO_ERROR";
/*     */   }
/*     */ 
/*     */   public boolean domainIsExist(String str, String site_id)
/*     */   {
/* 220 */     List dl = SiteDomainManager.getDomainListBySiteID(site_id);
/* 221 */     if ((dl != null) && (dl.size() > 0))
/*     */     {
/* 223 */       for (SiteDomainBean sdb : dl)
/*     */       {
/* 225 */         if (str.indexOf(sdb.getSite_domain()) > -1)
/*     */         {
/* 227 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 231 */     return false;
/*     */   }
/*     */ 
/*     */   private String delVhost(String domain)
/*     */   {
/* 242 */     if ((domain == null) || ((domain = domain.trim()).length() == 0)) {
/* 243 */       return "ERROR";
/*     */     }
/*     */ 
/* 247 */     String nginxRoot = JconfigUtilContainer.bashConfig().getProperty("path", "", "nginx_path");
/* 248 */     String nginxConfFilePath = FormatUtil.formatPath(nginxRoot + "/conf/nginx.conf");
/* 249 */     nginxConfFilePath = FormatUtil.formatPath(nginxConfFilePath);
/* 250 */     File nginxConfFile = new File(nginxConfFilePath);
/* 251 */     if (!nginxConfFile.exists()) {
/* 252 */       return "ERROR";
/*     */     }
/*     */ 
/* 256 */     String backupConfFilePath = nginxConfFilePath + 
/* 257 */       DateUtil.getString(new Date(), "yyyyMMddHHmmss");
/*     */     try {
/* 259 */       FileOperation.copyFile(nginxConfFilePath, backupConfFilePath, true);
/*     */     } catch (Exception e) {
/* 261 */       e.printStackTrace(System.out);
/* 262 */       return "ERROR";
/*     */     }
/*     */ 
/* 266 */     StringBuffer content = new StringBuffer();
/* 267 */     BufferedReader reader = null;
/* 268 */     String line = "";
/* 269 */     StringBuffer vhost = new StringBuffer();
/*     */     try {
/* 271 */       reader = new BufferedReader(new FileReader(nginxConfFile));
/*     */ 
/* 273 */       while ((line = reader.readLine()) != null) {
/* 274 */         if (line.indexOf("server {") != -1) {
/* 275 */           vhost.append(line + "\n");
/* 276 */           while (((line = reader.readLine()) != null) && 
/* 277 */             (line.indexOf("#end") == -1)) {
/* 278 */             vhost.append(line + "\n");
/*     */           }
/* 280 */           vhost.append(line + "\n");
/* 281 */           if (vhost.toString().indexOf(domain) == -1)
/*     */           {
/* 283 */             content.append(vhost);
/*     */           }
/* 285 */           vhost = new StringBuffer();
/*     */         }
/*     */         else {
/* 288 */           content.append(line + "\n");
/*     */         }
/*     */       }
/* 291 */       FileOperation.writeStringToFile(nginxConfFile, content.toString(), false);
/*     */     } catch (Exception e) {
/* 293 */       e.printStackTrace(System.out);
/*     */       try
/*     */       {
/* 296 */         FileOperation.copyFile(backupConfFilePath, nginxConfFilePath, true);
/*     */       } catch (Exception ex) {
/* 298 */         ex.printStackTrace(System.out);
/*     */       }
/* 300 */       return "ERROR";
/*     */     } finally {
/*     */       try {
/* 303 */         if (reader != null)
/* 304 */           reader.close();
/*     */       }
/*     */       catch (Exception ex) {
/* 307 */         ex.printStackTrace(System.out);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 312 */     return "NO_ERROR";
/*     */   }
/*     */ 
/*     */   public void reset()
/*     */   {
/* 322 */     String root_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path");
/* 323 */     String[] windowsCommand = (String[])null;
/* 324 */     String[] LinuxCommand = { root_path + "/bin/restartnginx.sh", "" };
/*     */     try {
/* 326 */       ExecuteCommand.executeCommandHandl(windowsCommand, LinuxCommand);
/*     */     } catch (Throwable t) {
/* 328 */       t.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String addMultiVhost(Map info, String config_name)
/*     */   {
/* 340 */     String site_id = (String)info.get("site_id");
/* 341 */     String site_path = (String)info.get("site_path");
/* 342 */     String appendContent = "";
/*     */ 
/* 344 */     String nginx_content = "";
/* 345 */     List dl = SiteDomainManager.getDomainListBySiteID(site_id);
/* 346 */     if ((dl != null) && (dl.size() > 0))
/*     */     {
/* 348 */       site_path = site_path.replace('\\', '/');
/*     */ 
/* 351 */       String root_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path");
/*     */ 
/* 354 */       String nginxRoot = JconfigUtilContainer.bashConfig().getProperty("path", "", "nginx_path");
/* 355 */       String nginxConfFilePath = FormatUtil.formatPath(nginxRoot + "/conf/nginx.conf");
/* 356 */       File nginxConfFile = new File(nginxConfFilePath);
/* 357 */       if (!nginxConfFile.exists()) {
/* 358 */         return "ERROR";
/*     */       }
/*     */ 
/* 362 */       String backupConfFilePath = nginxConfFilePath + 
/* 363 */         DateUtil.getString(new Date(), "yyyyMMddHHmmss");
/*     */       try {
/* 365 */         nginx_content = FileOperation.readFileToString(nginxConfFile);
/* 366 */         FileOperation.copyFile(nginxConfFilePath, backupConfFilePath, true);
/*     */       } catch (Exception e) {
/* 368 */         e.printStackTrace(System.out);
/* 369 */         return "ERROR";
/*     */       }
/* 371 */       for (SiteDomainBean sdb : dl)
/*     */       {
/* 373 */         String domain = sdb.getSite_domain();
/*     */ 
/* 375 */         if ((domain == null) || ((domain = domain.trim()).length() == 0)) {
/* 376 */           return "ERROR";
/*     */         }
/* 378 */         if ((site_path == null) || ((site_path = site_path.trim()).length() == 0)) {
/* 379 */           return "ERROR";
/*     */         }
/*     */ 
/* 383 */         appendContent = JconfigUtilContainer.apacheConfig().getProperty("value", "", ServerManager.getProxyServer() + config_name).replaceAll("LINE_SEPARATOR", LINE_SEPARATOR);
/* 384 */         appendContent = appendContent.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("'", "\"");
/* 385 */         appendContent = appendContent.replaceAll("\\$domain\\$", domain).replaceAll("\\$site_path\\$", site_path).replaceAll("\\$site_id\\$", site_id);
/* 386 */         appendContent = appendContent.replaceAll("\\$nginx_path\\$", nginxRoot).replaceAll("\\$root_path\\$", root_path).replaceAll("\\$manager_path\\$", FormatUtil.formatPath(JconfigUtilContainer.bashConfig().getProperty("path", "", "manager_path")));
/*     */ 
/* 389 */         nginx_content = nginx_content.replace("#replace_content_flag#", appendContent);
/*     */       }
/*     */ 
/*     */       try
/*     */       {
/* 394 */         FileOperation.writeStringToFile(nginxConfFilePath, nginx_content, false);
/*     */       } catch (Exception e) {
/* 396 */         e.printStackTrace(System.out);
/*     */         try
/*     */         {
/* 399 */           FileOperation.copyFile(backupConfFilePath, nginxConfFilePath, false);
/*     */         } catch (Exception ex) {
/* 401 */           ex.printStackTrace(System.out);
/*     */         }
/* 403 */         return "ERROR";
/*     */       }
/*     */ 
/* 407 */       return "NO_ERROR";
/*     */     }
/* 409 */     return "ERROR";
/*     */   }
/*     */ 
/*     */   public String updateVhost(Map info)
/*     */   {
/* 416 */     if ("".equals((String)info.get("old_site_domain")))
/*     */     {
/* 418 */       return "ERROR";
/*     */     }
/* 420 */     String old_site_domain = "server_name  " + (String)info.get("old_site_domain");
/* 421 */     String new_site_domain = "server_name  " + (String)info.get("new_site_domain");
/*     */ 
/* 424 */     String nginxRoot = JconfigUtilContainer.bashConfig().getProperty("path", "", "nginx_path");
/* 425 */     String nginxConfFilePath = FormatUtil.formatPath(nginxRoot + "/conf/nginx.conf");
/* 426 */     File nginxConfFile = new File(nginxConfFilePath);
/* 427 */     if (!nginxConfFile.exists()) {
/* 428 */       return "ERROR";
/*     */     }
/*     */ 
/* 431 */     String nginx_content = "";
/*     */     try {
/* 433 */       nginx_content = FileOperation.readFileToString(nginxConfFile);
/* 434 */       nginx_content = nginx_content.replaceAll(old_site_domain, new_site_domain);
/*     */     }
/*     */     catch (Exception e) {
/* 437 */       e.printStackTrace(System.out);
/* 438 */       return "ERROR";
/*     */     }
/*     */ 
/* 442 */     String backupConfFilePath = nginxConfFilePath + 
/* 443 */       DateUtil.getString(new Date(), "yyyyMMddHHmmss");
/*     */     try {
/* 445 */       FileOperation.copyFile(nginxConfFilePath, backupConfFilePath, true);
/*     */     } catch (Exception e) {
/* 447 */       e.printStackTrace(System.out);
/* 448 */       return "ERROR";
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 453 */       FileOperation.writeStringToFile(nginxConfFilePath, nginx_content, false);
/*     */     } catch (Exception e) {
/* 455 */       e.printStackTrace(System.out);
/*     */       try
/*     */       {
/* 458 */         FileOperation.copyFile(backupConfFilePath, nginxConfFilePath, true);
/*     */       } catch (Exception ex) {
/* 460 */         ex.printStackTrace(System.out);
/*     */       }
/* 462 */       return "ERROR";
/*     */     }
/*     */ 
/* 466 */     return "NO_ERROR";
/*     */   }
/*     */ 
/*     */   public String delVhost(Map info)
/*     */   {
/* 478 */     String domain = (String)info.get("site_domain");
/* 479 */     return delVhost(domain);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 484 */     IApacheConfig i = new NginxConfigTomcatImpl();
/* 485 */     Map info = new HashMap();
/* 486 */     info.put("site_domain", "www.kisslan.com");
/* 487 */     i.delVhost(info);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.server.NginxConfigTomcatImpl
 * JD-Core Version:    0.6.2
 */
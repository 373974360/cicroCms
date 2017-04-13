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
/*     */ import java.io.PrintStream;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ApacheConfigTomcatImpl
/*     */   implements IApacheConfig
/*     */ {
/*  29 */   private static String LINE_SEPARATOR = "\n";
/*     */ 
/*  31 */   static { if (ServerManager.isWindows())
/*  32 */       LINE_SEPARATOR = "\r\n";
/*     */   }
/*     */ 
/*     */   public String getType()
/*     */   {
/*  41 */     return "tomcat";
/*     */   }
/*     */ 
/*     */   public String addSite(Map info, String apacheConfig_name)
/*     */   {
/*  52 */     return addVhost(info, apacheConfig_name);
/*     */   }
/*     */ 
/*     */   public String updateSite(Map infos)
/*     */   {
/*  62 */     updateVhost(infos);
/*  63 */     return "";
/*     */   }
/*     */ 
/*     */   public String delSite(Map infos)
/*     */   {
/*  73 */     delVhost(infos);
/*  74 */     return "";
/*     */   }
/*     */ 
/*     */   public String delMultiVhost(String site_id)
/*     */   {
/*  85 */     String apacheRoot = JconfigUtilContainer.bashConfig().getProperty("path", "", "apaceh_path");
/*  86 */     String apacheConfFilePath = FormatUtil.formatPath(apacheRoot + "/conf/httpd.conf");
/*  87 */     apacheConfFilePath = FormatUtil.formatPath(apacheConfFilePath);
/*  88 */     File apacheConfFile = new File(apacheConfFilePath);
/*  89 */     if (!apacheConfFile.exists()) {
/*  90 */       return "ERROR";
/*     */     }
/*     */ 
/*  94 */     String backupConfFilePath = apacheConfFilePath + 
/*  95 */       DateUtil.getString(new Date(), "yyyyMMddHHmmss");
/*     */     try {
/*  97 */       FileOperation.copyFile(apacheConfFilePath, backupConfFilePath, true);
/*     */     } catch (Exception e) {
/*  99 */       e.printStackTrace(System.out);
/* 100 */       return "ERROR";
/*     */     }
/*     */ 
/* 104 */     StringBuffer content = new StringBuffer();
/* 105 */     BufferedReader reader = null;
/* 106 */     String line = "";
/* 107 */     StringBuffer vhost = new StringBuffer();
/*     */     try {
/* 109 */       reader = new BufferedReader(new FileReader(apacheConfFile));
/* 110 */       while ((line = reader.readLine()) != null) {
/* 111 */         if (line.indexOf("<VirtualHost") != -1) {
/* 112 */           vhost.append(line + "\n");
/* 113 */           while (((line = reader.readLine()) != null) && 
/* 114 */             (line.indexOf("</VirtualHost>") == -1)) {
/* 115 */             vhost.append(line + "\n");
/*     */           }
/* 117 */           vhost.append(line + "\n");
/* 118 */           if (!domainIsExist(vhost.toString(), site_id))
/*     */           {
/* 120 */             content.append(vhost);
/*     */           }
/* 122 */           vhost = new StringBuffer();
/*     */         }
/*     */         else {
/* 125 */           content.append(line + "\n");
/*     */         }
/*     */       }
/* 128 */       FileOperation.writeStringToFile(apacheConfFile, content.toString(), false);
/*     */     } catch (Exception e) {
/* 130 */       e.printStackTrace(System.out);
/*     */       try
/*     */       {
/* 133 */         FileOperation.copyFile(backupConfFilePath, apacheConfFilePath, true);
/*     */       } catch (Exception ex) {
/* 135 */         ex.printStackTrace(System.out);
/*     */       }
/* 137 */       return "ERROR";
/*     */     } finally {
/*     */       try {
/* 140 */         if (reader != null)
/* 141 */           reader.close();
/*     */       }
/*     */       catch (Exception ex) {
/* 144 */         ex.printStackTrace(System.out);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 149 */     return "NO_ERROR";
/*     */   }
/*     */ 
/*     */   public boolean domainIsExist(String str, String site_id)
/*     */   {
/* 155 */     List dl = SiteDomainManager.getDomainListBySiteID(site_id);
/* 156 */     if ((dl != null) && (dl.size() > 0))
/*     */     {
/* 158 */       for (SiteDomainBean sdb : dl)
/*     */       {
/* 160 */         if (str.indexOf(sdb.getSite_domain()) > -1)
/*     */         {
/* 162 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 166 */     return false;
/*     */   }
/*     */ 
/*     */   private String delVhost(String domain)
/*     */   {
/* 177 */     if ((domain == null) || ((domain = domain.trim()).length() == 0)) {
/* 178 */       return "ERROR";
/*     */     }
/*     */ 
/* 182 */     String apacheRoot = JconfigUtilContainer.bashConfig().getProperty("path", "", "apaceh_path");
/* 183 */     String apacheConfFilePath = FormatUtil.formatPath(apacheRoot + "/conf/httpd.conf");
/* 184 */     apacheConfFilePath = FormatUtil.formatPath(apacheConfFilePath);
/* 185 */     File apacheConfFile = new File(apacheConfFilePath);
/* 186 */     if (!apacheConfFile.exists()) {
/* 187 */       return "ERROR";
/*     */     }
/*     */ 
/* 191 */     String backupConfFilePath = apacheConfFilePath + 
/* 192 */       DateUtil.getString(new Date(), "yyyyMMddHHmmss");
/*     */     try {
/* 194 */       FileOperation.copyFile(apacheConfFilePath, backupConfFilePath, true);
/*     */     } catch (Exception e) {
/* 196 */       e.printStackTrace(System.out);
/* 197 */       return "ERROR";
/*     */     }
/*     */ 
/* 201 */     StringBuffer content = new StringBuffer();
/* 202 */     BufferedReader reader = null;
/* 203 */     String line = "";
/* 204 */     StringBuffer vhost = new StringBuffer();
/*     */     try {
/* 206 */       reader = new BufferedReader(new FileReader(apacheConfFile));
/* 207 */       while ((line = reader.readLine()) != null) {
/* 208 */         if (line.indexOf("<VirtualHost") != -1) {
/* 209 */           vhost.append(line + "\n");
/* 210 */           while (((line = reader.readLine()) != null) && 
/* 211 */             (line.indexOf("</VirtualHost>") == -1)) {
/* 212 */             vhost.append(line + "\n");
/*     */           }
/* 214 */           vhost.append(line + "\n");
/* 215 */           if (vhost.toString().indexOf(domain) == -1)
/*     */           {
/* 217 */             content.append(vhost);
/*     */           }
/* 219 */           vhost = new StringBuffer();
/*     */         }
/*     */         else {
/* 222 */           content.append(line + "\n");
/*     */         }
/*     */       }
/* 225 */       FileOperation.writeStringToFile(apacheConfFile, content.toString(), false);
/*     */     } catch (Exception e) {
/* 227 */       e.printStackTrace(System.out);
/*     */       try
/*     */       {
/* 230 */         FileOperation.copyFile(backupConfFilePath, apacheConfFilePath, true);
/*     */       } catch (Exception ex) {
/* 232 */         ex.printStackTrace(System.out);
/*     */       }
/* 234 */       return "ERROR";
/*     */     } finally {
/*     */       try {
/* 237 */         if (reader != null)
/* 238 */           reader.close();
/*     */       }
/*     */       catch (Exception ex) {
/* 241 */         ex.printStackTrace(System.out);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 246 */     return "NO_ERROR";
/*     */   }
/*     */ 
/*     */   public void reset()
/*     */   {
/* 255 */     if (!ServerManager.isWindows())
/*     */     {
/* 258 */       String root_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path");
/* 259 */       String[] windowsCommand = (String[])null;
/* 260 */       String[] LinuxCommand = { root_path + "/bin/restartapache.sh", "" };
/*     */       try {
/* 262 */         ExecuteCommand.executeCommandHandl(windowsCommand, LinuxCommand);
/*     */       } catch (Throwable t) {
/* 264 */         t.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String addMultiVhost(Map info, String config_name)
/*     */   {
/* 277 */     String site_id = (String)info.get("site_id");
/* 278 */     String site_path = (String)info.get("site_path");
/* 279 */     String appendContent = "";
/* 280 */     String all_str = "";
/* 281 */     List dl = SiteDomainManager.getDomainListBySiteID(site_id);
/* 282 */     if ((dl != null) && (dl.size() > 0))
/*     */     {
/* 284 */       String manager_path = FormatUtil.formatPath(JconfigUtilContainer.bashConfig().getProperty("path", "", "manager_path"));
/*     */ 
/* 286 */       String root_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path");
/*     */ 
/* 289 */       String apacheRoot = JconfigUtilContainer.bashConfig().getProperty("path", "", "apaceh_path");
/* 290 */       String apacheConfFilePath = FormatUtil.formatPath(apacheRoot + "/conf/httpd.conf");
/* 291 */       File apacheConfFile = new File(apacheConfFilePath);
/* 292 */       if (!apacheConfFile.exists()) {
/* 293 */         return "ERROR";
/*     */       }
/*     */ 
/* 297 */       String backupConfFilePath = apacheConfFilePath + 
/* 298 */         DateUtil.getString(new Date(), "yyyyMMddHHmmss");
/*     */       try {
/* 300 */         FileOperation.copyFile(apacheConfFilePath, backupConfFilePath, true);
/*     */       } catch (Exception e) {
/* 302 */         e.printStackTrace(System.out);
/* 303 */         return "ERROR";
/*     */       }
/* 305 */       for (SiteDomainBean sdb : dl)
/*     */       {
/* 307 */         String domain = sdb.getSite_domain();
/*     */ 
/* 309 */         if ((domain == null) || ((domain = domain.trim()).length() == 0)) {
/* 310 */           return "ERROR";
/*     */         }
/* 312 */         if ((site_path == null) || ((site_path = site_path.trim()).length() == 0)) {
/* 313 */           return "ERROR";
/*     */         }
/* 315 */         if (ServerManager.isWindows())
/*     */         {
/* 317 */           site_path = site_path.replaceAll("\\\\", "\\\\\\\\");
/* 318 */           manager_path = manager_path.replaceAll("\\\\", "\\\\\\\\");
/* 319 */           apacheRoot = apacheRoot.replaceAll("\\\\", "\\\\\\\\");
/*     */         }
/*     */ 
/* 322 */         appendContent = JconfigUtilContainer.apacheConfig().getProperty("value", "", ServerManager.getProxyServer() + config_name).replaceAll("LINE_SEPARATOR", LINE_SEPARATOR);
/* 323 */         appendContent = appendContent.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("'", "\"");
/* 324 */         appendContent = appendContent.replaceAll("\\$domain\\$", domain).replaceAll("\\$site_path\\$", site_path);
/* 325 */         appendContent = appendContent.replaceAll("\\$apache_path\\$", apacheRoot).replaceAll("\\$root_path\\$", root_path).replaceAll("\\$manager_path\\$", manager_path);
/*     */ 
/* 327 */         all_str = all_str + appendContent;
/*     */       }
/*     */ 
/*     */       try
/*     */       {
/* 332 */         FileOperation.writeStringToFile(apacheConfFilePath, all_str, true);
/*     */       } catch (Exception e) {
/* 334 */         e.printStackTrace(System.out);
/*     */         try
/*     */         {
/* 337 */           FileOperation.copyFile(backupConfFilePath, apacheConfFilePath, true);
/*     */         } catch (Exception ex) {
/* 339 */           ex.printStackTrace(System.out);
/*     */         }
/* 341 */         return "ERROR";
/*     */       }
/*     */ 
/* 345 */       return "NO_ERROR";
/*     */     }
/* 347 */     return "ERROR";
/*     */   }
/*     */ 
/*     */   public String addVhost(Map info, String config_name)
/*     */   {
/* 359 */     String domain = (String)info.get("site_domain");
/* 360 */     String site_path = (String)info.get("site_path");
/* 361 */     String manager_path = FormatUtil.formatPath(JconfigUtilContainer.bashConfig().getProperty("path", "", "manager_path"));
/*     */ 
/* 364 */     if ((domain == null) || ((domain = domain.trim()).length() == 0)) {
/* 365 */       return "ERROR";
/*     */     }
/* 367 */     if ((site_path == null) || ((site_path = site_path.trim()).length() == 0)) {
/* 368 */       return "ERROR";
/*     */     }
/*     */ 
/* 372 */     String root_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path");
/*     */ 
/* 375 */     String apacheRoot = JconfigUtilContainer.bashConfig().getProperty("path", "", "apaceh_path");
/* 376 */     String apacheConfFilePath = FormatUtil.formatPath(apacheRoot + "/conf/httpd.conf");
/* 377 */     File apacheConfFile = new File(apacheConfFilePath);
/* 378 */     if (!apacheConfFile.exists()) {
/* 379 */       return "ERROR";
/*     */     }
/*     */ 
/* 383 */     String backupConfFilePath = apacheConfFilePath + 
/* 384 */       DateUtil.getString(new Date(), "yyyyMMddHHmmss");
/*     */     try {
/* 386 */       FileOperation.copyFile(apacheConfFilePath, backupConfFilePath, true);
/*     */     } catch (Exception e) {
/* 388 */       e.printStackTrace(System.out);
/* 389 */       return "ERROR";
/*     */     }
/*     */ 
/* 392 */     if (ServerManager.isWindows())
/*     */     {
/* 394 */       site_path = site_path.replaceAll("\\\\", "\\\\\\\\");
/* 395 */       manager_path = manager_path.replaceAll("\\\\", "\\\\\\\\");
/* 396 */       apacheRoot = apacheRoot.replaceAll("\\\\", "\\\\\\\\");
/*     */     }
/*     */ 
/* 400 */     String appendContent = JconfigUtilContainer.apacheConfig().getProperty("value", "", ServerManager.getProxyServer() + config_name).replaceAll("LINE_SEPARATOR", LINE_SEPARATOR);
/* 401 */     appendContent = appendContent.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("'", "\"");
/* 402 */     appendContent = appendContent.replaceAll("\\$domain\\$", domain).replaceAll("\\$site_path\\$", site_path);
/* 403 */     appendContent = appendContent.replaceAll("\\$apache_path\\$", apacheRoot).replaceAll("\\$root_path\\$", root_path).replaceAll("\\$manager_path\\$", manager_path);
/*     */     try
/*     */     {
/* 406 */       FileOperation.writeStringToFile(apacheConfFilePath, appendContent, true);
/*     */     } catch (Exception e) {
/* 408 */       e.printStackTrace(System.out);
/*     */       try
/*     */       {
/* 411 */         FileOperation.copyFile(backupConfFilePath, apacheConfFilePath, true);
/*     */       } catch (Exception ex) {
/* 413 */         ex.printStackTrace(System.out);
/*     */       }
/* 415 */       return "ERROR";
/*     */     }
/*     */ 
/* 419 */     return "NO_ERROR";
/*     */   }
/*     */ 
/*     */   public String updateVhost(Map info)
/*     */   {
/* 427 */     if ("".equals((String)info.get("old_site_domain")))
/*     */     {
/* 429 */       return "ERROR";
/*     */     }
/* 431 */     String old_site_domain = "ServerName " + (String)info.get("old_site_domain");
/* 432 */     String new_site_domain = "ServerName " + (String)info.get("new_site_domain");
/*     */ 
/* 435 */     String apacheRoot = JconfigUtilContainer.bashConfig().getProperty("path", "", "apaceh_path");
/* 436 */     String apacheConfFilePath = FormatUtil.formatPath(apacheRoot + "/conf/httpd.conf");
/* 437 */     File apacheConfFile = new File(apacheConfFilePath);
/* 438 */     if (!apacheConfFile.exists()) {
/* 439 */       return "ERROR";
/*     */     }
/*     */ 
/* 442 */     String apache_content = "";
/*     */     try {
/* 444 */       apache_content = FileOperation.readFileToString(apacheConfFile);
/* 445 */       apache_content = apache_content.replaceAll(old_site_domain, new_site_domain);
/* 446 */       System.out.println("old_site_domain----------------" + old_site_domain);
/* 447 */       System.out.println("new_site_domain----------------" + new_site_domain);
/*     */     } catch (Exception e) {
/* 449 */       e.printStackTrace(System.out);
/* 450 */       return "ERROR";
/*     */     }
/*     */ 
/* 454 */     String backupConfFilePath = apacheConfFilePath + 
/* 455 */       DateUtil.getString(new Date(), "yyyyMMddHHmmss");
/*     */     try {
/* 457 */       FileOperation.copyFile(apacheConfFilePath, backupConfFilePath, true);
/*     */     } catch (Exception e) {
/* 459 */       e.printStackTrace(System.out);
/* 460 */       return "ERROR";
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 465 */       FileOperation.writeStringToFile(apacheConfFilePath, apache_content, false);
/*     */     } catch (Exception e) {
/* 467 */       e.printStackTrace(System.out);
/*     */       try
/*     */       {
/* 470 */         FileOperation.copyFile(backupConfFilePath, apacheConfFilePath, true);
/*     */       } catch (Exception ex) {
/* 472 */         ex.printStackTrace(System.out);
/*     */       }
/* 474 */       return "ERROR";
/*     */     }
/*     */ 
/* 478 */     return "NO_ERROR";
/*     */   }
/*     */ 
/*     */   public String delVhost(Map info)
/*     */   {
/* 489 */     String domain = (String)info.get("site_domain");
/* 490 */     return delVhost(domain);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.server.ApacheConfigTomcatImpl
 * JD-Core Version:    0.6.2
 */
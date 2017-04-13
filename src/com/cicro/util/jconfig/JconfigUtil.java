/*     */ package com.cicro.util.jconfig;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.wcm.server.ServerManager;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Map;
/*     */ import org.jconfig.Configuration;
/*     */ import org.jconfig.ConfigurationManager;
/*     */ import org.jconfig.ConfigurationManagerException;
/*     */ import org.jconfig.handler.XMLFileHandler;
/*     */ 
/*     */ public class JconfigUtil
/*     */ {
/*  18 */   private Configuration configuration = null;
/*  19 */   private ConfigurationManager cm = null;
/*     */ 
/*     */   public JconfigUtil() {
/*     */   }
/*     */ 
/*     */   public JconfigUtil(String path) {
/*     */     try {
/*  26 */       loadPropertyFile(path);
/*     */     } catch (ConfigurationManagerException e) {
/*  28 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void loadPropertyFile(String path)
/*     */     throws ConfigurationManagerException
/*     */   {
/*     */     try
/*     */     {
/*  39 */       if ((ServerManager.isWindows()) && (path.startsWith("/"))) {
/*  40 */         path = path.substring(1);
/*     */       }
/*  42 */       path = path.replaceAll("%20", " ");
/*  43 */       path = FormatUtil.formatPath(path);
/*  44 */       System.out.println(path);
/*  45 */       File file = new File(path);
/*  46 */       if (!file.isFile()) {
/*  47 */         FileOperation.writeStringToFile(path, "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n<properties>\n</properties>", false);
/*     */       }
/*  49 */       XMLFileHandler handler = new XMLFileHandler();
/*  50 */       handler.setFile(file);
/*  51 */       this.cm = ConfigurationManager.getInstance();
/*     */ 
/*  53 */       this.cm.load(handler, "myConfig");
/*  54 */       this.configuration = ConfigurationManager.getConfiguration("myConfig");
/*     */     }
/*     */     catch (Exception e) {
/*  57 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void loadPropertyFile(String path, String fileName)
/*     */   {
/*     */     try
/*     */     {
/*  69 */       path = FormatUtil.formatPath(path);
/*  70 */       File file = new File(path);
/*  71 */       if (!file.isFile()) {
/*  72 */         FileOperation.writeStringToFile(path, "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n<properties>\n</properties>", false);
/*     */       }
/*  74 */       XMLFileHandler handler = new XMLFileHandler();
/*  75 */       handler.setFile(file);
/*  76 */       this.cm = ConfigurationManager.getInstance();
/*  77 */       this.cm.load(handler, fileName);
/*     */     } catch (ConfigurationManagerException e) {
/*  79 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/*  81 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setProperty(String key, String value, String category)
/*     */   {
/*     */     try
/*     */     {
/*  93 */       this.configuration.setProperty(key, value, category);
/*  94 */       this.cm.save("myConfig");
/*     */     } catch (ConfigurationManagerException e) {
/*  96 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setVariable(String key, String value)
/*     */   {
/*     */     try
/*     */     {
/* 107 */       this.configuration.setVariable(key, value);
/* 108 */       this.cm.save("myConfig");
/*     */     } catch (ConfigurationManagerException e) {
/* 110 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void choosePropertyFile(String propertyFile)
/*     */   {
/* 120 */     this.configuration = ConfigurationManager.getConfiguration(propertyFile);
/*     */   }
/*     */ 
/*     */   public void setProperty(String propertyFile, String key, String value, String category)
/*     */   {
/*     */     try
/*     */     {
/* 132 */       this.configuration.setProperty(key, value, category);
/* 133 */       this.cm.save(propertyFile);
/*     */     } catch (ConfigurationManagerException e) {
/* 135 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setVariable(String propertyFile, String key, String value)
/*     */   {
/*     */     try
/*     */     {
/* 148 */       this.configuration.setVariable(key, value);
/* 149 */       this.cm.save(propertyFile);
/*     */     } catch (ConfigurationManagerException e) {
/* 151 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getProperty(String key, String defaultValue, String category)
/*     */   {
/* 164 */     return this.configuration.getProperty(key, defaultValue, category);
/*     */   }
/*     */ 
/*     */   public void removeProperty(String key, String category)
/*     */   {
/* 173 */     this.configuration.removeProperty(key, category);
/*     */   }
/*     */ 
/*     */   public String[] getCategorys()
/*     */   {
/* 181 */     return this.configuration.getCategoryNames();
/*     */   }
/*     */ 
/*     */   public String[] getPropertyNamesByCategory(String categoryName)
/*     */   {
/* 190 */     return this.configuration.getPropertyNames(categoryName);
/*     */   }
/*     */ 
/*     */   public Map getVariables()
/*     */   {
/* 199 */     return this.configuration.getVariables();
/*     */   }
/*     */ 
/*     */   public void setParentConfig() {
/* 203 */     loadPropertyFile(JconfigFactory.getAllConfigPath(), "configV");
/* 204 */     this.configuration.setBaseConfiguration("configV");
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.jconfig.JconfigUtil
 * JD-Core Version:    0.6.2
 */
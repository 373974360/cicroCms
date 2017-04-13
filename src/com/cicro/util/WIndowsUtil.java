/*     */ package com.cicro.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public final class WIndowsUtil
/*     */ {
/*     */   public static String getNetVariableValue(String variable_name)
/*     */   {
/*  47 */     if ((variable_name == null) || ((variable_name = variable_name.trim()).length() == 0)) {
/*  48 */       return "";
/*     */     }
/*  50 */     String result = "";
/*  51 */     String os = System.getProperty("os.name").toLowerCase();
/*  52 */     if ((os != null) && (os.startsWith("windows")))
/*     */       try {
/*  54 */         String command = "cmd.exe /c ipconfig /all";
/*  55 */         Process p = Runtime.getRuntime().exec(command);
/*  56 */         BufferedReader br = 
/*  57 */           new BufferedReader(
/*  58 */           new InputStreamReader(p.getInputStream()));
/*     */         String line;
/*  60 */         while ((line = br.readLine()) != null)
/*     */         {
/*     */           String line;
/*  61 */           if (line.indexOf(variable_name) > 0) {
/*  62 */             int index = line.indexOf(":");
/*  63 */             index += 2;
/*  64 */             result = line.substring(index);
/*  65 */             break;
/*     */           }
/*     */         }
/*  68 */         br.close();
/*  69 */         return result.trim();
/*     */       } catch (IOException localIOException) {
/*     */       }
/*  72 */     return result;
/*     */   }
/*     */ 
/*     */   public static String getMACAddress()
/*     */   {
/*  80 */     return getNetVariableValue("Physical Address");
/*     */   }
/*     */ 
/*     */   public static String getHostName()
/*     */   {
/*  88 */     return getNetVariableValue("Host Name");
/*     */   }
/*     */ 
/*     */   public static String getIP()
/*     */   {
/*  96 */     return getNetVariableValue("IP Address");
/*     */   }
/*     */ 
/*     */   public static String getGateWayIP()
/*     */   {
/* 104 */     return getNetVariableValue("Default Gateway");
/*     */   }
/*     */ 
/*     */   public static String getDNSServer()
/*     */   {
/* 112 */     return getNetVariableValue("DNS Servers");
/*     */   }
/*     */ 
/*     */   public static String getSystemVariableValue(String variable_name)
/*     */   {
/* 120 */     if ((variable_name == null) || ((variable_name = variable_name.trim()).length() == 0)) {
/* 121 */       return "";
/*     */     }
/* 123 */     String result = "";
/* 124 */     String os = System.getProperty("os.name").toLowerCase();
/* 125 */     if ((os != null) && (os.startsWith("windows")))
/*     */       try {
/* 127 */         String command = "cmd.exe /c echo %" + variable_name + "%";
/* 128 */         Process p = Runtime.getRuntime().exec(command);
/* 129 */         BufferedReader br = 
/* 130 */           new BufferedReader(
/* 131 */           new InputStreamReader(p.getInputStream()));
/*     */         String line;
/* 133 */         if ((line = br.readLine()) != null) {
/* 134 */           result = line;
/*     */         }
/*     */ 
/* 137 */         br.close();
/* 138 */         return result.trim();
/*     */       } catch (IOException localIOException) {
/*     */       }
/* 141 */     return result;
/*     */   }
/*     */ 
/*     */   public static String getPath()
/*     */   {
/* 149 */     return getSystemVariableValue("PATH");
/*     */   }
/*     */ 
/*     */   public static String getSystemDir()
/*     */   {
/* 157 */     return getSystemVariableValue("windir");
/*     */   }
/*     */ 
/*     */   public static String getJavaHome()
/*     */   {
/* 165 */     return getSystemVariableValue("JAVA_HOME");
/*     */   }
/*     */ 
/*     */   public static String getClassPath()
/*     */   {
/* 173 */     return getSystemVariableValue("CLASSPATH");
/*     */   }
/*     */ 
/*     */   public static String getHostsPath()
/*     */   {
/* 181 */     String win_dir = getSystemDir();
/* 182 */     String hosts_path = win_dir + "\\system32\\drivers\\etc\\hosts";
/* 183 */     hosts_path = hosts_path.replace('\\', '/');
/* 184 */     File hosts_file = new File(hosts_path);
/* 185 */     if (!hosts_file.exists())
/*     */     {
/* 187 */       System.out.println("the hosts file not exist");
/*     */     }
/* 189 */     return hosts_path;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 197 */     System.out.println("MACAddress : " + getMACAddress());
/* 198 */     System.out.println("HostName : " + getHostName());
/* 199 */     System.out.println("IP : " + getIP());
/* 200 */     System.out.println("GateWay : " + getGateWayIP());
/* 201 */     System.out.println("DNS : " + getDNSServer());
/* 202 */     System.out.println("PATH : " + getPath());
/* 203 */     System.out.println("System dir : " + getSystemDir());
/* 204 */     System.out.println("JAVA_HOME : " + getJavaHome());
/* 205 */     System.out.println("CLASSPATH : " + getClassPath());
/* 206 */     System.out.println("SystemVariable(CLASSPATH) : " + 
/* 207 */       getSystemVariableValue("CLASSPATH"));
/* 208 */     System.out.println("getNetVariableValue(Physical Address) : " + 
/* 209 */       getNetVariableValue("Physical Address"));
/* 210 */     System.out.println("HostsPath : " + getHostsPath());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.WIndowsUtil
 * JD-Core Version:    0.6.2
 */
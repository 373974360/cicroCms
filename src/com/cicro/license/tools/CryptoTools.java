/*    */ package com.cicro.license.tools;
/*    */ 
/*    */ /*    */ import java.security.Key;
/*    */ import java.security.spec.AlgorithmParameterSpec;
import java.text.SimpleDateFormat;
import java.util.Date;

/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.SecretKeyFactory;
/*    */ import javax.crypto.spec.DESKeySpec;
/*    */ import javax.crypto.spec.IvParameterSpec;


import com.cicro.util.DateUtil;
import com.cicro.util.RandomStrg;
import com.cloopen.rest.sdk.utils.encoder.BASE64Decoder;
/*    */ 
/*    */ public class CryptoTools
/*    */ {
/* 20 */   private final byte[] DESkey = "cicioweb".getBytes();
/* 21 */   private final byte[] DESIV = { 18, 52, 86, 120, 1, 2, 3, 4 };
/*    */ 
/* 23 */   private AlgorithmParameterSpec iv = null;
/* 24 */   private Key key = null;
/*    */ 
/*    */   public CryptoTools()
/*    */   {
/*    */     try
/*    */     {
/* 30 */       DESKeySpec keySpec = new DESKeySpec(this.DESkey);
/* 31 */       this.iv = new IvParameterSpec(this.DESIV);
/* 32 */       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
/* 33 */       this.key = keyFactory.generateSecret(keySpec);
/*    */     }
/*    */     catch (Exception e) {
/* 36 */       e.printStackTrace(System.out);
/*    */     }
/*    */   }
/*    */ 
/*    */   public String encode(String data)
/*    */   {
/*    */     try
/*    */     {
/* 44 */       Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
/* 45 */       enCipher.init(1, this.key, this.iv);
/* 46 */       byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
/* 47 */       com.cloopen.rest.sdk.utils.encoder.BASE64Encoder base64Encoder = new com.cloopen.rest.sdk.utils.encoder.BASE64Encoder();
/* 48 */       return "=#=" + base64Encoder.encode(pasByte);
/*    */     }
/*    */     catch (Exception e) {
/* 51 */       e.printStackTrace(System.out);
/* 52 */     }return data;
/*    */   }
/*    */ 
/*    */   public String decode(String data)
/*    */   {
/*    */     try
/*    */     {
/* 59 */       if (data.startsWith("=#="))
/*    */       {
/* 61 */         Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
/* 62 */         deCipher.init(2, this.key, this.iv);
/* 63 */         BASE64Decoder base64Decoder = new BASE64Decoder();
/*    */ 
/* 65 */         byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data.substring(3)));
/*    */ 
/* 67 */         return new String(pasByte, "UTF-8");
/*    */       }
/*    */ 
/* 70 */       return data;
/*    */     }
/*    */     catch (Exception e) {
/* 73 */       e.printStackTrace(System.out);
/* 74 */     }return data;
/*    */   }

	  public String license(String value)
	  {
		  String cp_code = decode(value);
			 String[] codes = cp_code.split("[*]");
			 String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) ;
			 int site_num = 10;
			 String app_ids = "system,org,cms,control,appeal,zwgk,ggfw";
			 if(codes != null && codes.length > 0)
			 {
				 String cupid = codes[1].replaceAll("[$]","");
				 String mac = codes[2].replaceAll("[$]","");
				 //System.out.println(cupid);
				 //System.out.println(mac);
				 String xml = "<license><cpuid><![CDATA[" + cupid +"]]></cpuid>"+
						 "<mac><![CDATA[" + mac + "]]></mac>" +
						 "<wcm><start_time>" + date +"</start_time>" +
						 "<time_limit><![CDATA[]]></time_limit>" +
						 "<site_num>"+ site_num + "</site_num>" + 
						 "<app_ids>" + app_ids +"</app_ids>" + 
						 "</wcm></license>";
				 System.out.println(xml);
				 return encode(xml);
			 }	
		  return "";
	  }
	public static void main(String[] args)
	{
		CryptoTools ct = new CryptoTools();
		System.out.println(ct.license("=#=rU65f7rwo/gkzL0byi9sP44wPpsKCCGIwNivZY3tz+zLjt/oq2d2Kw40hh5l1BS/0L4LnSj3n9BQjJA9u2NZRw=="));
		//System.out.println(ct.decode("=#=KDDG+LKyz/A="));
		//System.out.println(ct.encode("<license><cpuid><![CDATA[640F0000]]></cpuid><mac><![CDATA[0016ECEFC89B]]></mac><wcm><start_time>2013-03-07</start_time><time_limit><![CDATA[]]></time_limit><site_num>10</site_num><app_ids>system,org,cms,control,appeal,zwgk,ggfw</app_ids></wcm></license>"));
		
		//System.out.println(ct.decode("=#=QCRhOMN6cKmmN/ZP+67zJA=="));
		//String key = RandomStrg.getRandomStr("", RandomStrg.getRandomStr("0-9", "1")) + "," + DateUtil.dateToTimestamp();
		//key = ct.encode(key).replace("+", "WcMrEPlAcE").substring(3);
		/*
		String key = "SLC3/Nyka4ADzbEaNH481jfxTwhCkWcMrEPlAcEM7";
		key = "=#=" + key;
		key = ct.decode(key.replace("WcMrEPlAcE", "+"));
		
		key = key.substring(key.indexOf(",") + 1);
		int times = 60 * 15;
		long timel = Long.parseLong(key);
		long time2 = DateUtil.dateToTimestamp();
		System.out.println(DateUtil.timestampToDate(timel, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(DateUtil.timestampToDate(time2, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(time2 - timel  < 1000 * times + 100);
		*/
	}
}

/*     */ package com.cicro.analyzer_bak_20151106.cfg;
/*     */ 
/*     */ import com.cicro.analyzer.dic.Dictionary;
/*     */ import com.cicro.analyzer.seg.CJKSegmenter;
/*     */ import com.cicro.analyzer.seg.ISegmenter;
/*     */ import com.cicro.analyzer.seg.LetterSegmenter;
/*     */ import com.cicro.analyzer.seg.QuantifierSegmenter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.InvalidPropertiesFormatException;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class Configuration
/*     */ {
/*     */   private static final String FILE_NAME = "/CICROIKAnalyzer.cfg.xml";
/*     */   private static final String EXT_DICT = "ext_dict";
/*     */   private static final String EXT_STOP = "ext_stopwords";
/*     */   private static final String EXT_STOP_WORDS = "ext_stop_words";
/*  37 */   private static final Configuration CFG = new Configuration();
/*     */   private Properties props;
/*     */ 
/*     */   private Configuration()
/*     */   {
/*  46 */     this.props = new Properties();
/*     */ 
/*  48 */     InputStream input = Configuration.class.getResourceAsStream("/CICROIKAnalyzer.cfg.xml");
/*  49 */     if (input != null)
/*     */       try {
/*  51 */         this.props.loadFromXML(input);
/*     */       } catch (InvalidPropertiesFormatException e) {
/*  53 */         e.printStackTrace();
/*     */       } catch (IOException e) {
/*  55 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public static List<String> getExtDictionarys()
/*     */   {
/*  65 */     List extDictFiles = new ArrayList(2);
/*  66 */     String extDictCfg = CFG.props.getProperty("ext_dict");
/*  67 */     if (extDictCfg != null)
/*     */     {
/*  69 */       String[] filePaths = extDictCfg.split(";");
/*  70 */       if (filePaths != null) {
/*  71 */         for (String filePath : filePaths) {
/*  72 */           if ((filePath != null) && (!"".equals(filePath.trim()))) {
/*  73 */             extDictFiles.add(filePath.trim());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  79 */     return extDictFiles;
/*     */   }
/*     */ 
/*     */   public static List<String> getExtStopWordDictionarys()
/*     */   {
/*  87 */     List extStopWordDictFiles = new ArrayList(2);
/*  88 */     String extStopWordDictCfg = CFG.props.getProperty("ext_stopwords");
/*  89 */     if (extStopWordDictCfg != null)
/*     */     {
/*  91 */       String[] filePaths = extStopWordDictCfg.split(";");
/*  92 */       if (filePaths != null) {
/*  93 */         for (String filePath : filePaths) {
/*  94 */           if ((filePath != null) && (!"".equals(filePath.trim()))) {
/*  95 */             extStopWordDictFiles.add(filePath.trim());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 101 */     return extStopWordDictFiles;
/*     */   }
/*     */ 
/*     */   public static List<String> getExtStopWordSDictionarysDIY()
/*     */   {
/* 110 */     List extStopWordDictFiles = new ArrayList(2);
/* 111 */     String extStopWordDictCfg = CFG.props.getProperty("ext_stop_words");
/* 112 */     if (extStopWordDictCfg != null)
/*     */     {
/* 114 */       String[] filePaths = extStopWordDictCfg.split(";");
/* 115 */       if (filePaths != null) {
/* 116 */         for (String filePath : filePaths) {
/* 117 */           if ((filePath != null) && (!"".equals(filePath.trim()))) {
/* 118 */             extStopWordDictFiles.add(filePath.trim());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 124 */     return extStopWordDictFiles;
/*     */   }
/*     */ 
/*     */   public static List<ISegmenter> loadSegmenter()
/*     */   {
/* 135 */     Dictionary.getInstance();
/* 136 */     List segmenters = new ArrayList(4);
/*     */ 
/* 138 */     segmenters.add(new QuantifierSegmenter());
/*     */ 
/* 140 */     segmenters.add(new CJKSegmenter());
/*     */ 
/* 142 */     segmenters.add(new LetterSegmenter());
/* 143 */     return segmenters;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.cfg.Configuration
 * JD-Core Version:    0.6.2
 */
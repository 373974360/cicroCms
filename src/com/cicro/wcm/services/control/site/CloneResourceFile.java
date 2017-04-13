/*    */ package com.cicro.wcm.services.control.site;
/*    */ 
/*    */ import com.cicro.util.FormatUtil;
/*    */ import com.cicro.util.JarManager;
/*    */ import com.cicro.util.io.FileOperation;
/*    */ import com.cicro.wcm.bean.control.SiteBean;
/*    */ import com.cicro.wcm.rmi.ISiteRmi;
/*    */ import java.io.File;
/*    */ 
/*    */ public class CloneResourceFile
/*    */   implements ICloneSite
/*    */ {
/*    */   public boolean cloneSite(String site_id, String s_site_id)
/*    */   {
/*    */     try
/*    */     {
/* 26 */       SiteBean sb = SiteManager.getSiteBeanBySiteID(site_id);
/* 27 */       SiteBean s_sb = SiteManager.getSiteBeanBySiteID(s_site_id);
/*    */ 
/* 29 */       if (sb.getServer_id() == s_sb.getServer_id())
/*    */       {
/* 32 */         compressResourceFile(sb, s_sb);
/*    */       }
/*    */       else
/*    */       {
/* 38 */         ISiteRmi siteRmi = SiteOperationFactory.getSiteRmigetSiteRMIForServerID(s_sb.getServer_id());
/* 39 */         byte[] b = siteRmi.copySiteResource(s_site_id);
/* 40 */         if (b != null)
/*    */         {
/* 42 */           FileOperation.writeBytesToFile(FormatUtil.formatPath(sb.getSite_path() + "/resource.jar"), b, true);
/* 43 */           decompressResourceFile(sb.getSite_path());
/*    */         }
/*    */       }
/* 46 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 49 */       e.printStackTrace();
/* 50 */     }return false;
/*    */   }
/*    */ 
/*    */   public static void compressResourceFile(SiteBean sb, SiteBean s_sb)
/*    */   {
/* 62 */     String copy_dir = FormatUtil.formatPath(new StringBuilder(String.valueOf(s_sb.getSite_path())).append("/images").toString()) + "," + FormatUtil.formatPath(new StringBuilder(String.valueOf(s_sb.getSite_path())).append("/js").toString()) + "," + FormatUtil.formatPath(new StringBuilder(String.valueOf(s_sb.getSite_path())).append("/styles").toString());
/* 63 */     JarManager.compress("resource.jar", copy_dir, FormatUtil.formatPath(s_sb.getSite_path()), sb.getSite_path());
/* 64 */     decompressResourceFile(sb.getSite_path());
/*    */   }
/*    */ 
/*    */   public static void decompressResourceFile(String site_path)
/*    */   {
/* 75 */     JarManager.decompress(FormatUtil.formatPath(site_path + "/resource.jar"), site_path);
/* 76 */     File f = new File(FormatUtil.formatPath(site_path + "/resource.jar"));
/* 77 */     f.delete();
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.site.CloneResourceFile
 * JD-Core Version:    0.6.2
 */
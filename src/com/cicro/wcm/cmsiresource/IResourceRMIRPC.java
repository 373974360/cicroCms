/*    */ package com.cicro.wcm.cmsiresource;
/*    */ 
/*    */ import com.cicro.iresource.service.resourceService.dto.TreeDTO;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class IResourceRMIRPC
/*    */ {
/*    */   public static List<IresourceInfoCount> getRootChildTreeCount(String id)
/*    */   {
/* 15 */     List list = new ArrayList();
/* 16 */     List TreeDTOList = IResourceRMIService.findTreeByPIdAndType(id);
/*    */     Iterator it;
/* 17 */     for (Iterator localIterator1 = TreeDTOList.iterator(); localIterator1.hasNext(); 
/* 21 */       it.hasNext())
/*    */     {
/* 17 */       TreeDTO treeDTO = (TreeDTO)localIterator1.next();
/* 18 */       Map mapInfo = IResourceRMIService.totalByGroupTreeId(treeDTO.getId());
/* 19 */       System.out.println("mapInfo === " + mapInfo);
/* 20 */       it = mapInfo.keySet().iterator();
/* 21 */       continue;
/* 22 */       String key = (String)it.next();
/* 23 */       String value = (String)mapInfo.get(key);
/* 24 */       String treeid = key.split("#")[0];
/* 25 */       String name = key.split("#")[1];
/* 26 */       IresourceInfoCount iresourceInfoCount = new IresourceInfoCount();
/* 27 */       iresourceInfoCount.setName(name);
/* 28 */       iresourceInfoCount.setTreeId(treeid);
/* 29 */       iresourceInfoCount.setCount(Integer.valueOf(value).intValue());
/* 30 */       list.add(iresourceInfoCount);
/*    */     }
/*    */ 
/* 33 */     return list;
/*    */   }
/*    */ 
/*    */   public static List<IresourceInfoCount> getRootChildTreeCountById(String id)
/*    */   {
/* 40 */     List list = new ArrayList();
/* 41 */     Map mapInfo = IResourceRMIService.totalByGroupTreeId(id);
/* 42 */     System.out.println("mapInfo === " + mapInfo);
/* 43 */     Iterator it = mapInfo.keySet().iterator();
/* 44 */     while (it.hasNext()) {
/* 45 */       String key = (String)it.next();
/* 46 */       String value = (String)mapInfo.get(key);
/* 47 */       String treeid = key.split("#")[0];
/* 48 */       String name = key.split("#")[1];
/* 49 */       IresourceInfoCount iresourceInfoCount = new IresourceInfoCount();
/* 50 */       iresourceInfoCount.setName(name);
/* 51 */       iresourceInfoCount.setTreeId(treeid);
/* 52 */       iresourceInfoCount.setCount(Integer.valueOf(value).intValue());
/* 53 */       list.add(iresourceInfoCount);
/*    */     }
/* 55 */     return list;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.cmsiresource.IResourceRMIRPC
 * JD-Core Version:    0.6.2
 */
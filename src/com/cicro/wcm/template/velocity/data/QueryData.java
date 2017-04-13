/*     */ package com.cicro.wcm.template.velocity.data;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.query.QueryDicBean;
/*     */ import com.cicro.wcm.bean.query.QueryItemBean;
/*     */ import com.cicro.wcm.bean.template.TurnPageBean;
/*     */ import com.cicro.wcm.services.query.QueryDicManager;
/*     */ import com.cicro.wcm.services.query.QueryItemManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ 
/*     */ public class QueryData
/*     */ {
/*  16 */   private static int cur_page = 1;
/*  17 */   private static int page_size = 15;
/*     */ 
/*     */   public static List<QueryItemBean> getQueryList(String requet_params, String params, String confid)
/*     */   {
/*  21 */     int current_page = 1;
/*  22 */     int page_sizes = Integer.parseInt(JconfigUtilContainer.velocityTemplateConfig().getProperty("num", "15", "show_list_num"));
/*  23 */     String con = getQuerySearchCon(requet_params, params, confid);
/*  24 */     System.out.println("getQueryList   getQuerySearchCon ============" + con);
/*  25 */     String[] tempParam = params.split(";");
/*  26 */     for (int i = 0; i < tempParam.length; i++)
/*     */     {
/*  28 */       if (tempParam[i].toLowerCase().startsWith("size="))
/*     */       {
/*  30 */         String ps = FormatUtil.formatNullString(tempParam[i].substring(tempParam[i].indexOf("=") + 1));
/*  31 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/*  32 */           page_sizes = Integer.parseInt(ps);
/*     */       }
/*  34 */       if (tempParam[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/*  36 */         String cp = FormatUtil.formatNullString(tempParam[i].substring(tempParam[i].indexOf("=") + 1));
/*  37 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/*  38 */           current_page = Integer.parseInt(cp);
/*     */       }
/*     */     }
/*  41 */     return QueryItemManager.getQueryListBrowser(con, confid, (current_page - 1) * page_sizes, page_sizes);
/*     */   }
/*     */ 
/*     */   public static TurnPageBean getQueryCount(String requet_params, String params, String conf_id)
/*     */   {
/*  46 */     TurnPageBean tpb = new TurnPageBean();
/*  47 */     tpb.setCount(Integer.parseInt(QueryItemManager.getQueryListCountBrowser(getQuerySearchCon(requet_params, params, conf_id))));
/*  48 */     tpb.setCur_page(cur_page);
/*  49 */     tpb.setPage_size(page_size);
/*  50 */     tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
/*  51 */     if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
/*  52 */       tpb.setPage_count(tpb.getPage_count() - 1);
/*     */     }
/*  54 */     if (cur_page > 1) {
/*  55 */       tpb.setPrev_num(cur_page - 1);
/*     */     }
/*  57 */     tpb.setNext_num(tpb.getPage_count());
/*  58 */     if (cur_page < tpb.getPage_count())
/*  59 */       tpb.setNext_num(cur_page + 1);
/*  60 */     return tpb;
/*     */   }
/*     */ 
/*     */   public static String getQuerySearchCon(String requet_params, String param, String conf_id)
/*     */   {
/*  65 */     String returnRS = "";
/*  66 */     String retult_item = "";
/*  67 */     String search = "";
/*  68 */     String table_view = "";
/*  69 */     if (!requet_params.trim().endsWith("conf_id=" + conf_id))
/*     */     {
/*  71 */       table_view = "from cs_dz_cx_" + conf_id + " where 1=1";
/*     */     }
/*  73 */     else table_view = "from cs_dz_cx_" + conf_id + " where item_id=0";
/*     */ 
/*  75 */     String[] temp = requet_params.split("&");
/*  76 */     List list = getTypeDicList("conf_id=" + conf_id + ";is_query=1");
/*  77 */     if (list != null)
/*     */     {
/*  79 */       for (int j = 0; j < list.size(); j++)
/*     */       {
/*  81 */         String is_query = "item_" + ((QueryDicBean)list.get(j)).getDic_id();
/*  82 */         for (int i = 0; i < temp.length; i++)
/*     */         {
/*  84 */           if (temp[i].toLowerCase().startsWith("item_"))
/*     */           {
/*  86 */             String[] tempB = temp[i].split("=");
/*  87 */             if (is_query.equals(tempB[0]))
/*     */             {
/*  89 */               String item = FormatUtil.formatNullString(temp[i].substring(temp[i].indexOf("=") + 1));
/*  90 */               if ((item != "") && (item != null) && (item != "null"))
/*     */               {
/*  93 */                 search = search + " and item_" + ((QueryDicBean)list.get(j)).getDic_id() + "='" + item.trim() + "'";
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  98 */     String[] tempA = param.split(";");
/*  99 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/* 101 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/* 103 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 104 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/* 105 */           page_size = Integer.parseInt(ps);
/*     */       }
/* 107 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/* 109 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 110 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/* 111 */           cur_page = Integer.parseInt(cp);
/*     */       }
/*     */     }
/* 114 */     if (search.length() > 0)
/*     */     {
/* 116 */       table_view = table_view.replace("item_id=0", "1=1");
/* 117 */       returnRS = table_view + search;
/*     */     } else {
/* 119 */       returnRS = table_view;
/*     */     }
/* 121 */     return returnRS;
/*     */   }
/*     */ 
/*     */   public static List<QueryDicBean> getTypeDicList(String par)
/*     */   {
/* 126 */     String contion = "";
/* 127 */     String[] tempPar = par.split(";");
/* 128 */     for (int i = 0; i < tempPar.length; i++)
/*     */     {
/* 130 */       if (tempPar[i].toLowerCase().startsWith("conf_id="))
/*     */       {
/* 132 */         String cid = FormatUtil.formatNullString(tempPar[i].substring(tempPar[i].indexOf("=") + 1));
/* 133 */         if (cid.toLowerCase().startsWith("conf_id="))
/*     */         {
/* 135 */           cid = cid.replace("conf_id=", "");
/* 136 */           contion = contion + " and conf_id =" + cid + " ";
/*     */         } else {
/* 138 */           contion = contion + " and conf_id =" + cid + " ";
/*     */         }
/*     */       }
/* 141 */       if (tempPar[i].toLowerCase().startsWith("is_query="))
/*     */       {
/* 143 */         String is_query = FormatUtil.formatNullString(tempPar[i].substring(tempPar[i].indexOf("=") + 1));
/* 144 */         if ((!"".equals(is_query)) && (!is_query.startsWith("$is_query")) && (FormatUtil.isValiditySQL(is_query)))
/*     */         {
/* 146 */           contion = contion + "and is_query='" + is_query + "' ";
/*     */         }
/*     */       }
/* 149 */       if (tempPar[i].toLowerCase().startsWith("is_result="))
/*     */       {
/* 151 */         String is_result = FormatUtil.formatNullString(tempPar[i].substring(tempPar[i].indexOf("=") + 1));
/* 152 */         if ((!"".equals(is_result)) && (!is_result.startsWith("$is_result")) && (FormatUtil.isValiditySQL(is_result)))
/*     */         {
/* 154 */           contion = contion + "and is_result='" + is_result + "' ";
/*     */         }
/*     */       }
/* 157 */       if (tempPar[i].toLowerCase().startsWith("is_selected="))
/*     */       {
/* 159 */         String is_selected = FormatUtil.formatNullString(tempPar[i].substring(tempPar[i].indexOf("=") + 1));
/* 160 */         if ((!"".equals(is_selected)) && (!is_selected.startsWith("$is_selected")) && (FormatUtil.isValiditySQL(is_selected)))
/*     */         {
/* 162 */           contion = contion + "and is_selected='" + is_selected + "' ";
/*     */         }
/*     */       }
/*     */     }
/* 164 */     return QueryDicManager.getTypeDicList(contion);
/*     */   }
/*     */ 
/*     */   public static boolean isValiditySQL(String str)
/*     */   {
/* 170 */     String inj_str = "'|!|@|#|~|$|%|^|*|%|&|(|)|truncate|char|declare| or |+";
/* 171 */     String[] inj_stra = inj_str.split("\\|");
/* 172 */     for (int i = 0; i < inj_stra.length; i++)
/*     */     {
/* 174 */       if (str.indexOf(inj_stra[i]) >= 0)
/*     */       {
/* 176 */         System.out.println("sql str is error --> " + str);
/* 177 */         return false;
/*     */       }
/*     */     }
/* 180 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 186 */     System.out.println(isValiditySQL("conf_id=81&item_4=&btnOK=确 定&item_3=&item_1=*"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.data.QueryData
 * JD-Core Version:    0.6.2
 */
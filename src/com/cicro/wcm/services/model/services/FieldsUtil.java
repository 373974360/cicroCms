/*     */ package com.cicro.wcm.services.model.services;
/*     */ 
/*     */ import com.cicro.util.xml.XmlManager;
/*     */ import com.cicro.wcm.services.model.Fields;
/*     */ import java.util.HashMap;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ public class FieldsUtil
/*     */ {
/*     */   public static String formatQuote(String s)
/*     */   {
/*  28 */     if (s == null) {
/*  29 */       return "";
/*     */     }
/*     */ 
/*  32 */     return s;
/*     */   }
/*     */ 
/*     */   public static String getFieldXml(HashMap map, String fieldType)
/*     */   {
/*     */     try
/*     */     {
/*  43 */       StringBuffer sb = new StringBuffer();
/*  44 */       if (fieldType.equals("0")) {
/*  45 */         String field_maxnum = (String)map.get("field_maxnum");
/*  46 */         String field_maxlength = (String)map.get("field_maxlength");
/*  47 */         String validator = (String)map.get("validator");
/*  48 */         String default_value = (String)map.get("default_value");
/*     */ 
/*  50 */         sb.append("<fieldInfo>");
/*  51 */         sb.append("<field_maxnum><![CDATA[");
/*  52 */         sb.append(field_maxnum);
/*  53 */         sb.append("]]></field_maxnum>");
/*  54 */         sb.append("<field_maxlength><![CDATA[");
/*  55 */         sb.append(field_maxlength);
/*  56 */         sb.append("]]></field_maxlength>");
/*  57 */         sb.append("<validator><![CDATA[");
/*  58 */         sb.append(validator);
/*  59 */         sb.append("]]></validator>");
/*  60 */         sb.append("<default_value><![CDATA[");
/*  61 */         sb.append(default_value);
/*  62 */         sb.append("]]></default_value>");
/*  63 */         sb.append("</fieldInfo>");
/*  64 */       } else if (fieldType.equals("1"))
/*     */       {
/*  66 */         String width = (String)map.get("width");
/*  67 */         String height = (String)map.get("height");
/*  68 */         String field_maxnum = (String)map.get("field_maxnum");
/*  69 */         String default_value = (String)map.get("default_value");
/*  70 */         sb.append("<fieldInfo>");
/*  71 */         sb.append("<width><![CDATA[");
/*  72 */         sb.append(width);
/*  73 */         sb.append("]]></width>");
/*  74 */         sb.append("<height><![CDATA[");
/*  75 */         sb.append(height);
/*  76 */         sb.append("]]></height>");
/*  77 */         sb.append("<field_maxnum><![CDATA[");
/*  78 */         sb.append(field_maxnum);
/*  79 */         sb.append("]]></field_maxnum>");
/*  80 */         sb.append("<default_value><![CDATA[");
/*  81 */         sb.append(default_value);
/*  82 */         sb.append("]]></default_value>");
/*  83 */         sb.append("</fieldInfo>");
/*     */       }
/*  85 */       else if (fieldType.equals("2"))
/*     */       {
/*  87 */         String width = (String)map.get("width");
/*  88 */         String height = (String)map.get("height");
/*  89 */         String field_maxnum = (String)map.get("field_maxnum");
/*  90 */         String default_value = (String)map.get("default_value");
/*  91 */         sb.append("<fieldInfo>");
/*  92 */         sb.append("<width><![CDATA[");
/*  93 */         sb.append(width);
/*  94 */         sb.append("]]></width>");
/*  95 */         sb.append("<height><![CDATA[");
/*  96 */         sb.append(height);
/*  97 */         sb.append("]]></height>");
/*  98 */         sb.append("<field_maxnum><![CDATA[");
/*  99 */         sb.append(field_maxnum);
/* 100 */         sb.append("]]></field_maxnum>");
/* 101 */         sb.append("<default_value><![CDATA[");
/* 102 */         sb.append(default_value);
/* 103 */         sb.append("]]></default_value>");
/* 104 */         sb.append("</fieldInfo>");
/*     */       }
/* 106 */       else if (fieldType.equals("3"))
/*     */       {
/* 109 */         String select_item = (String)map.get("select_item");
/* 110 */         String select_view = (String)map.get("select_view");
/* 111 */         String default_value = (String)map.get("default_value");
/* 112 */         String data_type = (String)map.get("data_type");
/* 113 */         String data_type_id = (String)map.get("data_type_id");
/* 114 */         sb.append("<fieldInfo>");
/* 115 */         sb.append("<data_type><![CDATA[");
/* 116 */         sb.append(data_type);
/* 117 */         sb.append("]]></data_type>");
/* 118 */         sb.append("<data_type_id><![CDATA[");
/* 119 */         sb.append(data_type_id);
/* 120 */         sb.append("]]></data_type_id>");
/* 121 */         sb.append("<select_item><![CDATA[");
/* 122 */         sb.append(select_item);
/* 123 */         sb.append("]]></select_item>");
/* 124 */         sb.append("<select_view><![CDATA[");
/* 125 */         sb.append(select_view);
/* 126 */         sb.append("]]></select_view>");
/* 127 */         sb.append("<default_value><![CDATA[");
/* 128 */         sb.append(default_value);
/* 129 */         sb.append("]]></default_value>");
/* 130 */         sb.append("</fieldInfo>");
/*     */       }
/* 132 */       else if (fieldType.equals("4"))
/*     */       {
/* 135 */         String min_num = (String)map.get("min_num");
/* 136 */         String max_num = (String)map.get("max_num");
/* 137 */         String default_value = (String)map.get("default_value");
/* 138 */         sb.append("<fieldInfo>");
/* 139 */         sb.append("<min_num><![CDATA[");
/* 140 */         sb.append(min_num);
/* 141 */         sb.append("]]></min_num>");
/* 142 */         sb.append("<max_num><![CDATA[");
/* 143 */         sb.append(max_num);
/* 144 */         sb.append("]]></max_num>");
/* 145 */         sb.append("<default_value><![CDATA[");
/* 146 */         sb.append(default_value);
/* 147 */         sb.append("]]></default_value>");
/* 148 */         sb.append("</fieldInfo>");
/*     */       }
/* 150 */       else if (fieldType.equals("5"))
/*     */       {
/* 152 */         String default_value = (String)map.get("default_value");
/* 153 */         sb.append("<fieldInfo>");
/* 154 */         sb.append("<default_value><![CDATA[");
/* 155 */         sb.append(default_value);
/* 156 */         sb.append("]]></default_value>");
/* 157 */         sb.append("</fieldInfo>");
/*     */       }
/* 159 */       else if (fieldType.equals("6"))
/*     */       {
/* 161 */         String default_value = (String)map.get("default_value");
/* 162 */         sb.append("<fieldInfo>");
/* 163 */         sb.append("<default_value><![CDATA[");
/* 164 */         sb.append(default_value);
/* 165 */         sb.append("]]></default_value>");
/* 166 */         sb.append("</fieldInfo>");
/*     */       }
/* 168 */       else if (fieldType.equals("9"))
/*     */       {
/* 171 */         String select_item = (String)map.get("select_item");
/* 172 */         String select_view = (String)map.get("select_view");
/* 173 */         String default_value = (String)map.get("default_value");
/* 174 */         sb.append("<fieldInfo>");
/* 175 */         sb.append("<select_item><![CDATA[");
/* 176 */         sb.append(select_item);
/* 177 */         sb.append("]]></select_item>");
/* 178 */         sb.append("<select_view><![CDATA[");
/* 179 */         sb.append(select_view);
/* 180 */         sb.append("]]></select_view>");
/* 181 */         sb.append("<default_value><![CDATA[");
/* 182 */         sb.append(default_value);
/* 183 */         sb.append("]]></default_value>");
/* 184 */         sb.append("</fieldInfo>");
/*     */       }
/*     */ 
/* 188 */       return sb.toString();
/*     */     }
/*     */     catch (Exception e) {
/* 191 */       e.printStackTrace();
/* 192 */     }return "";
/*     */   }
/*     */ 
/*     */   public static Fields getBeanByFieldXml(Fields field, String fieldXml, String fieldType)
/*     */   {
/*     */     try
/*     */     {
/* 207 */       if (fieldType.equals("0"))
/*     */       {
/* 210 */         return getBeanByText(field, fieldXml);
/* 211 */       }if (fieldType.equals("1"))
/* 212 */         return getBeanByAreaNoHtml(field, fieldXml);
/* 213 */       if (fieldType.equals("2"))
/* 214 */         return getBeanByAreaHtml(field, fieldXml);
/* 215 */       if (fieldType.equals("3"))
/* 216 */         return getBeanBySelect(field, fieldXml);
/* 217 */       if (fieldType.equals("4"))
/* 218 */         return getBeanByNumber(field, fieldXml);
/* 219 */       if (fieldType.equals("5"))
/* 220 */         return getBeanByText(field, fieldXml);
/* 221 */       if (fieldType.equals("6"))
/* 222 */         return getBeanByFile(field, fieldXml);
/* 223 */       if (fieldType.equals("8"))
/* 224 */         return getBeanByText(field, fieldXml);
/* 225 */       if (fieldType.equals("9")) {
/* 226 */         return getBeanBySelect(field, fieldXml);
/*     */       }
/* 228 */       return null;
/*     */     }
/*     */     catch (Exception e) {
/* 231 */       e.printStackTrace();
/* 232 */     }return null;
/*     */   }
/*     */ 
/*     */   public static Fields getBeanByText(Fields field, String fieldXml)
/*     */   {
/*     */     try
/*     */     {
/* 244 */       Node node = XmlManager.createNode(fieldXml);
/* 245 */       String field_maxnum = XmlManager.queryNodeValue(node, "//field_maxnum");
/* 246 */       String field_maxlength = XmlManager.queryNodeValue(node, "//field_maxlength");
/* 247 */       String validator = XmlManager.queryNodeValue(node, "//validator");
/* 248 */       String default_value = XmlManager.queryNodeValue(node, "//default_value");
/* 249 */       field.setField_maxnum(field_maxnum);
/* 250 */       field.setField_maxlength(field_maxlength);
/* 251 */       field.setValidator(validator);
/* 252 */       field.setDefault_value(default_value);
/* 253 */       return field;
/*     */     }
/*     */     catch (Exception e) {
/* 256 */       e.printStackTrace();
/* 257 */     }return null;
/*     */   }
/*     */ 
/*     */   public static Fields getBeanByAreaNoHtml(Fields field, String fieldXml)
/*     */   {
/*     */     try
/*     */     {
/* 269 */       Node node = XmlManager.createNode(fieldXml);
/* 270 */       String width = XmlManager.queryNodeValue(node, "//width");
/* 271 */       String height = XmlManager.queryNodeValue(node, "//height");
/* 272 */       String field_maxnum = XmlManager.queryNodeValue(node, "//field_maxnum");
/* 273 */       String default_value = XmlManager.queryNodeValue(node, "//default_value");
/* 274 */       field.setWidth(width);
/* 275 */       field.setHeight(height);
/* 276 */       field.setField_maxnum(field_maxnum);
/* 277 */       field.setDefault_value(default_value);
/* 278 */       return field;
/*     */     } catch (Exception e) {
/* 280 */       e.printStackTrace();
/* 281 */     }return null;
/*     */   }
/*     */ 
/*     */   public static Fields getBeanByAreaHtml(Fields field, String fieldXml)
/*     */   {
/*     */     try
/*     */     {
/* 294 */       Node node = XmlManager.createNode(fieldXml);
/* 295 */       String width = XmlManager.queryNodeValue(node, "//width");
/* 296 */       String height = XmlManager.queryNodeValue(node, "//height");
/* 297 */       String field_maxnum = XmlManager.queryNodeValue(node, "//field_maxnum");
/* 298 */       String default_value = XmlManager.queryNodeValue(node, "//default_value");
/* 299 */       field.setWidth(width);
/* 300 */       field.setHeight(height);
/* 301 */       field.setField_maxnum(field_maxnum);
/*     */ 
/* 303 */       field.setDefault_value(default_value);
/* 304 */       return field;
/*     */     } catch (Exception e) {
/* 306 */       e.printStackTrace();
/* 307 */     }return null;
/*     */   }
/*     */ 
/*     */   public static Fields getBeanBySelect(Fields field, String fieldXml)
/*     */   {
/*     */     try
/*     */     {
/* 319 */       Node node = XmlManager.createNode(fieldXml);
/* 320 */       String select_item = XmlManager.queryNodeValue(node, "//select_item");
/* 321 */       String select_view = XmlManager.queryNodeValue(node, "//select_view");
/* 322 */       String default_value = XmlManager.queryNodeValue(node, "//default_value");
/* 323 */       String data_type = XmlManager.queryNodeValue(node, "//data_type");
/* 324 */       String data_type_id = XmlManager.queryNodeValue(node, "//data_type_id");
/* 325 */       field.setSelect_item(select_item);
/* 326 */       field.setSelect_view(select_view);
/* 327 */       field.setDefault_value(default_value);
/* 328 */       field.setData_type(data_type);
/* 329 */       field.setData_type_id(data_type_id);
/* 330 */       return field;
/*     */     } catch (Exception e) {
/* 332 */       e.printStackTrace();
/* 333 */     }return null;
/*     */   }
/*     */ 
/*     */   public static Fields getBeanByNumber(Fields field, String fieldXml)
/*     */   {
/*     */     try
/*     */     {
/* 345 */       Node node = XmlManager.createNode(fieldXml);
/* 346 */       String max_num = XmlManager.queryNodeValue(node, "//max_num");
/* 347 */       String min_num = XmlManager.queryNodeValue(node, "//min_num");
/* 348 */       String default_value = XmlManager.queryNodeValue(node, "//default_value");
/* 349 */       field.setMax_num(max_num);
/* 350 */       field.setMin_num(min_num);
/* 351 */       field.setDefault_value(default_value);
/* 352 */       return field;
/*     */     } catch (Exception e) {
/* 354 */       e.printStackTrace();
/* 355 */     }return null;
/*     */   }
/*     */ 
/*     */   public static Fields getBeanByTime(Fields field, String fieldXml)
/*     */   {
/*     */     try
/*     */     {
/* 367 */       Node node = XmlManager.createNode(fieldXml);
/* 368 */       String default_value = XmlManager.queryNodeValue(node, "//default_value");
/* 369 */       field.setDefault_value(default_value);
/* 370 */       return field;
/*     */     } catch (Exception e) {
/* 372 */       e.printStackTrace();
/* 373 */     }return null;
/*     */   }
/*     */ 
/*     */   public static Fields getBeanByFile(Fields field, String fieldXml)
/*     */   {
/*     */     try
/*     */     {
/* 385 */       Node node = XmlManager.createNode(fieldXml);
/* 386 */       String default_value = XmlManager.queryNodeValue(node, "//default_value");
/* 387 */       field.setDefault_value(default_value);
/* 388 */       return field;
/*     */     }
/*     */     catch (Exception e) {
/* 391 */       e.printStackTrace();
/* 392 */     }return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.FieldsUtil
 * JD-Core Version:    0.6.2
 */
/*    */ package com.cicro.util;
/*    */ 
/*    */ import java.beans.BeanInfo;
/*    */ import java.beans.IntrospectionException;
/*    */ import java.beans.Introspector;
/*    */ import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
import java.net.URLDecoder;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class BeanToMapUtil
/*    */ {
/*    */   public static Object convertMap(Class type, Map map)
/*    */     throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException
/*    */   {
/* 31 */     BeanInfo beanInfo = Introspector.getBeanInfo(type);
/* 32 */     Object obj = type.newInstance();
/*    */ 
/* 35 */     PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
/* 36 */     for (int i = 0; i < propertyDescriptors.length; i++) {
/* 37 */       PropertyDescriptor descriptor = propertyDescriptors[i];
/* 38 */       String propertyName = descriptor.getName();
/*    */ 
/* 40 */       if (map.containsKey(propertyName))
/*    */       {
/* 42 */         Object value = map.get(propertyName);
/*    */ 
/* 44 */         Object[] args = new Object[1];
/* 45 */         if (String.valueOf(descriptor.getPropertyType()).equals("int")) {
/* 46 */           if ((value != null) && (!"".endsWith(value.toString().trim())))
/*    */           {
/* 50 */             value = Integer.valueOf(String.valueOf(value));
/*    */           }
/*    */         } else { args[0] = value;
/*    */ 
/* 57 */           descriptor.getWriteMethod().invoke(obj, args); }
/*    */       }
/*    */     }
/* 60 */     return obj;
/*    */   }
/*    */ 
/*    */   public static Map convertBean(Object bean)
/*    */     throws IntrospectionException, IllegalAccessException, InvocationTargetException
/*    */   {
/* 73 */     Class type = bean.getClass();
/* 74 */     Map returnMap = new HashMap();
/* 75 */     BeanInfo beanInfo = Introspector.getBeanInfo(type);
/*    */ 
/* 77 */     PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
/* 78 */     for (int i = 0; i < propertyDescriptors.length; i++) {
/* 79 */       PropertyDescriptor descriptor = propertyDescriptors[i];
/* 80 */       String propertyName = descriptor.getName();
/* 81 */       if (!propertyName.equals("class")) {
/* 82 */         Method readMethod = descriptor.getReadMethod();
/* 83 */         Object result = readMethod.invoke(bean, new Object[0]);
/* 84 */         if (result != null)
/* 85 */           returnMap.put(propertyName, result);
/*    */         else {
/* 87 */           returnMap.put(propertyName, "");
/*    */         }
/*    */       }
/*    */     }
/* 91 */     return returnMap;
/*    */   }
public static void main(String[] args) {
	 String content = "http://www.sxjiaxian.gov.cn/search/searchResultGJ.jsp%20";
     String contentold = "http://www.sxjiaxian.gov.cn/search/searchResultGJ.jsp%20";
     try {
         content = URLDecoder.decode(contentold.replaceAll("%20", " ").replaceAll("&lt;", "<"), "utf-8").toLowerCase();
         content = content + URLDecoder.decode(contentold, "utf-8");
     } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
     }

     String s = "aa=123";
     System.out.println(s.substring(s.indexOf("=") + 1));
}
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.BeanToMapUtil
 * JD-Core Version:    0.6.2
 */
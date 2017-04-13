/*    */ package com.cicro.wcm.services.model.services;
/*    */ 
/*    */ import java.beans.BeanInfo;
/*    */ import java.beans.IntrospectionException;
/*    */ import java.beans.Introspector;
/*    */ import java.beans.PropertyDescriptor;
/*    */ import java.io.PrintStream;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
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
/* 45 */         System.out.println(descriptor.getPropertyType());
/* 46 */         if (String.valueOf(descriptor.getPropertyType()).equals("int")) {
/* 47 */           value = Integer.valueOf(String.valueOf(value));
/*    */         }
/* 49 */         args[0] = value;
/*    */ 
/* 54 */         descriptor.getWriteMethod().invoke(obj, args);
/*    */       }
/*    */     }
/* 57 */     return obj;
/*    */   }
/*    */ 
/*    */   public static Map convertBean(Object bean)
/*    */     throws IntrospectionException, IllegalAccessException, InvocationTargetException
/*    */   {
/* 70 */     Class type = bean.getClass();
/* 71 */     Map returnMap = new HashMap();
/* 72 */     BeanInfo beanInfo = Introspector.getBeanInfo(type);
/*    */ 
/* 74 */     PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
/* 75 */     for (int i = 0; i < propertyDescriptors.length; i++) {
/* 76 */       PropertyDescriptor descriptor = propertyDescriptors[i];
/* 77 */       String propertyName = descriptor.getName();
/* 78 */       if (!propertyName.equals("class")) {
/* 79 */         Method readMethod = descriptor.getReadMethod();
/* 80 */         Object result = readMethod.invoke(bean, new Object[0]);
/* 81 */         if (result != null)
/* 82 */           returnMap.put(propertyName, result);
/*    */         else {
/* 84 */           returnMap.put(propertyName, "");
/*    */         }
/*    */       }
/*    */     }
/* 88 */     return returnMap;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.BeanToMapUtil
 * JD-Core Version:    0.6.2
 */
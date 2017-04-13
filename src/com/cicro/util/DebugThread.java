/*    */ package com.cicro.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DebugThread
/*    */ {
/*    */   public static String debugThreadHandl(String status)
/*    */   {
/*  7 */     String message = "";
/*  8 */     if ((status == null) || (status.equals("")))
/*  9 */       status = "RUNNABLE";
/* 10 */     int tc = Thread.activeCount();
/* 11 */     Thread[] ts = new Thread[tc];
/* 12 */     Thread.enumerate(ts);
/* 13 */     List list = Arrays.asList(ts);
/* 14 */     System.out.println("name       | state   | className      |   stackTrace ");
/* 15 */     message = "<table width=\"1000px\" border=\"1\"><tr><th>name</th><th>state</th><th>className</th><th>stackTrace</th></tr>";
/*    */ 
/* 17 */     for (int i = 0; i < list.size(); i++) {
/* 18 */       Thread t = (Thread)list.get(i);
/* 19 */       System.out.println(t.getName() + "       | " + t.getState() + "   | " + t.getClass().getName() + "      |    " + t.getStackTrace());
/* 20 */       message = message + "<tr><td>" + t.getName() + "</td><td>" + t.getState() + "</td><td>" + t.getClass().getName() + "</td><td>" + t.getStackTrace() + "</td></tr>";
/* 21 */       StackTraceElement[] trace = t.getStackTrace();
/* 22 */       if (t.getState().toString().equals(status)) {
/* 23 */         System.err.println("Error may be occurred in method: ");
/* 24 */         message = message + "<tr><td colspan=\"4\">Error may be occurred in method: </p>";
/* 25 */         for (int j = 0; j < trace.length; j++) {
/* 26 */           StackTraceElement ste = trace[j];
/* 27 */           System.err.println("  at " + ste.getFileName() + "." + ste.getMethodName() + "():" + ste.getLineNumber());
/* 28 */           message = message + ste.getFileName() + " -- " + ste.getMethodName() + " -- " + ste.getLineNumber() + "</p>";
/*    */         }
/* 30 */         message = message + "</td></tr>";
/*    */       }
/*    */     }
/* 33 */     return message + "</table>";
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.DebugThread
 * JD-Core Version:    0.6.2
 */
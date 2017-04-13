/*    */ package com.cicro.wcm.jsonlistener_old;
/*    */ 
/*    */ import com.cicro.util.io.FileOperation;
/*    */ import com.cicro.util.jconfig.JconfigUtil;
/*    */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*    */ import com.metaparadigm.jsonrpc.JSONRPCBridge;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import javax.servlet.ServletRequestEvent;
/*    */ import javax.servlet.ServletRequestListener;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import javax.servlet.http.HttpSessionEvent;
/*    */ import javax.servlet.http.HttpSessionListener;
/*    */ 
/*    */ public class JSON_RPC_Listener
/*    */   implements HttpSessionListener, ServletRequestListener
/*    */ {
/* 34 */   private static Set<JSON_RPC_Listener.RPC_Action> RPC_ACTIONS = new HashSet();
/*    */   private HttpServletRequest request;
/* 36 */   private static String JSON_RPC_BRIDGE_KEY = "JSONRPCBridge";
/*    */ 
/*    */   static {
/*    */     try { String classList = FileOperation.readFileToString(JconfigUtilContainer.bashConfig().getProperty(
/* 40 */         "path", "", "jsonrpcFile"));
/*    */ 
/* 42 */       classList = classList.replaceAll("\r\n", "|").replaceAll("\r", "|")
/* 43 */         .replaceAll("\n", "|");
/* 44 */       String[] tempA = classList.split("\\|");
/* 45 */       for (int i = 0; i < tempA.length; i++) {
/* 46 */         if ((tempA[i] != null) && (!"".equals(tempA[i]))) {
/* 47 */           RPC_ACTIONS.add(new JSON_RPC_Listener.RPC_Action(tempA[i].substring(0, 
/* 48 */             tempA[i].indexOf(",")).trim(), tempA[i].substring(
/* 49 */             tempA[i].indexOf(",") + 1).trim()));
/*    */         }
/*    */       }
/* 52 */       RPC_ACTIONS.add(new JSON_RPC_Listener.RPC_Action("JSONUtil", 
/* 53 */         "com.cicro.wcm.jsonlistener.JSONUtil"));
/*    */     } catch (Exception e) {
/* 55 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void requestInitialized(ServletRequestEvent sre) {
/* 60 */     this.request = ((HttpServletRequest)sre.getServletRequest());
/*    */   }
/*    */ 
/*    */   public void requestDestroyed(ServletRequestEvent event)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void sessionCreated(HttpSessionEvent event)
/*    */   {
/* 70 */     if (this.request.getRequestURI().endsWith("/JSON-RPC"))
/*    */     {
/* 72 */       if (this.request.getHeader("referer") == null)
/*    */       {
/* 74 */         return;
/*    */       }
/*    */     }
/* 77 */     HttpSession session = event.getSession();
/* 78 */     JSONRPCBridge bridge = new JSONRPCBridge();
/* 79 */     session.setAttribute(JSON_RPC_BRIDGE_KEY, bridge);
/* 80 */     bridge.registerObject("session", session);
/* 81 */     for (JSON_RPC_Listener.RPC_Action action : RPC_ACTIONS)
/*    */       try {
/* 83 */         String name = action.getName();
/* 84 */         String className = action.getClassName();
/* 85 */         Object obj = Class.forName(className).newInstance();
/* 86 */         bridge.registerObject(name, obj);
/*    */       }
/*    */       catch (Exception e) {
/* 89 */         e.printStackTrace();
/*    */       }
/*    */   }
/*    */ 
/*    */   public void sessionDestroyed(HttpSessionEvent event)
/*    */   {
/* 96 */     HttpSession session = event.getSession();
/* 97 */     session.removeAttribute(JSON_RPC_BRIDGE_KEY);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.jsonlistener.JSON_RPC_Listener
 * JD-Core Version:    0.6.2
 */
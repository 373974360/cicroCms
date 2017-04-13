/*    */ package com.cicro.wcm.services.session;
/*    */ 
/*    */ import com.cicro.wcm.services.control.domain.SiteDomainManager;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.ServletRequestEvent;
/*    */ import javax.servlet.ServletRequestListener;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import javax.servlet.http.HttpSessionEvent;
/*    */ import javax.servlet.http.HttpSessionListener;
/*    */ 
/*    */ public class OnlineCounterListener
/*    */   implements HttpSessionListener, ServletRequestListener
/*    */ {
/*    */   HttpServletRequest request;
/*    */ 
/*    */   public void sessionCreated(HttpSessionEvent hse)
/*    */   {
/* 24 */     ServletContext application = hse.getSession().getServletContext();
/* 25 */     String site_id = SiteDomainManager.getSiteIDByDomain(this.request.getLocalName());
/*    */ 
/* 27 */     OnlineCounter.raise(site_id);
/*    */   }
/*    */   public void sessionDestroyed(HttpSessionEvent hse) {
/* 30 */     String site_id = SiteDomainManager.getSiteIDByDomain(this.request.getLocalName());
/* 31 */     OnlineCounter.reduce(site_id);
/*    */   }
/*    */ 
/*    */   public void requestDestroyed(ServletRequestEvent servletrequestevent)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void requestInitialized(ServletRequestEvent servletrequestevent)
/*    */   {
/* 41 */     this.request = ((HttpServletRequest)servletrequestevent.getServletRequest());
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.session.OnlineCounterListener
 * JD-Core Version:    0.6.2
 */
/*    */ package com.cicro.wcm.webServices.sendInfo;
/*    */ 
/*    */ import com.cicro.license.tools.CryptoTools;
/*    */ import com.cicro.wcm.services.sendInfo.SendInfoUtil;
/*    */ import java.io.PrintStream;
/*    */ import org.codehaus.xfire.XFireFactory;
/*    */ import org.codehaus.xfire.client.XFireProxyFactory;
/*    */ import org.codehaus.xfire.service.Service;
/*    */ import org.codehaus.xfire.service.binding.ObjectServiceFactory;
/*    */ 
/*    */ public class webServiceTest
/*    */ {
/* 48 */   private static final CryptoTools tools = new CryptoTools();
/*    */ 
/*    */   public static void test(String filename, String imgstr)
/*    */   {
/* 20 */     String xml = "<cicrowcm><username><![CDATA[system]]></username><password><![CDATA[=#=88OWKd587QY=]]></password><t_site_id><![CDATA[CMSWCM16]]></t_site_id><s_site_domain><![CDATA[www.cicro.com]]></s_site_domain><s_site_name><![CDATA[政务门户演示网站]]></s_site_name><s_user_name><![CDATA[系统管理员]]></s_user_name><s_send_dtime><![CDATA[2013-02-21 14:28:10]]></s_send_dtime><infoList><info><r_cat_id>10001</r_cat_id><r_cat_name>测试目录三</r_cat_name><send_record_id>87</send_record_id><r_content><info_content><![CDATA[<p><br />&nbsp;&nbsp;&nbsp; 在伟大的中国共产党成立90周年之际，我州各地的广大党员干部和普通百姓，都在以感恩的心情，学习和了解我们国家的执政党&mdash;&mdash;中国共产党的奋斗史、创业史和改革开放史。这是一件非常有意义的事。<br />&nbsp;&nbsp;&nbsp; 一位学者说，&ldquo;不懂历史的人没有根，淡忘历史的民族没有魂&rdquo;。我们应该记住这句名言。我们每个人都有了解党史、读懂党史、运用党史的责任。只有用党的&ldquo;三史&rdquo;来洗礼自己的思想，才能为&ldquo;做懂根的人，做有魂的民族&rdquo;打下思想基础。<br />&nbsp;&nbsp;&nbsp; &ldquo;以铜为镜可以正衣冠，以人为镜可以知得失，以史为镜可以知古今&rdquo;。学过党史的人知道，我们的党在已经走过的90年光辉历程中，真正做到了从无到有、由弱变强的转变，它带领全国各族人民战胜重重艰难险阻，成功地领导了两次革命，干了三件大事，实现了两次飞跃&hellip;&hellip;中国共产党的诞生和发展，扭转了近现代中国历史的走向，改变了中国人民的命运，对整个世界的格局和面貌也产生了广泛而深远的影响。<br />&nbsp;&nbsp;&nbsp; 了解了党史、学习了党史，可以得出这样的结沦：共产党没有自身的特殊利益，共产党的职能就是为人民服务。过去，没有共产党就没有新中国；今天，没有共产党就没有和谐发展的中国；未来，我们应该更加坚定一个信念：没有共产党就不会有未来屹立于世界强国之列的中国和中华民族。</p> ]]></info_content><info_keywords><![CDATA[111111111111111]]></info_keywords><info_description><![CDATA[1111111111111]]></info_description><title><![CDATA[第三方报送信息测试ydc]]></title><model_id><![CDATA[11]]></model_id><author><![CDATA[余丽]]></author><source><![CDATA[昌吉日报]]></source></r_content></info></infoList></cicrowcm>";
/*    */ 
/* 22 */     Service srvcModel = new ObjectServiceFactory().create(ISendInfo.class);
/* 23 */     XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
/* 24 */     String servicesURL = "http://www.16.com/services/SendInfo";
/*    */ 
/* 26 */     ISendInfo service = null;
/*    */     try {
/* 28 */       service = (ISendInfo)factory.create(srvcModel, servicesURL);
/*    */     } catch (Exception e) {
/* 30 */       e.printStackTrace();
/*    */     }
/* 32 */     if (service != null)
/*    */     {
/* 34 */       boolean isCorrect = service.sendInfoFormThirdParty(xml, "", "");
/*    */ 
/* 37 */       if (!isCorrect) {
/* 38 */         System.out.println("webService失败！！！");
/* 39 */         return;
/*    */       }
/* 41 */       System.out.println("成功！！！");
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String encode(String data)
/*    */   {
/* 57 */     return tools.encode(data);
/*    */   }
/*    */ 
/*    */   public static String decode(String data)
/*    */   {
/* 66 */     return tools.decode(data);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 71 */     String expandedName = "rar";
/* 72 */     String str = SendInfoUtil.encodeBase64File("d://ydc/local." + expandedName);
/*    */ 
/* 75 */     test(expandedName, str);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.webServices.sendInfo.webServiceTest
 * JD-Core Version:    0.6.2
 */
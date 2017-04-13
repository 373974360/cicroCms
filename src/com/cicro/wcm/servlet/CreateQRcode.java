/*     */ package com.cicro.wcm.servlet;
/*     */ 
		import com.swetake.util.Qrcode;

/*     */ import java.awt.Color;
		  import java.awt.Graphics2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.servlet.ServletException;
		  import javax.servlet.ServletOutputStream;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ 
/*     */ public class CreateQRcode extends HttpServlet
/*     */ {
			private static final long serialVersionUID = 9160187105146343801L;

/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  26 */     response.setHeader("Pragma", "No-cache");
/*  27 */     response.setHeader("Cache-Control", "no-cache");
/*  28 */     response.setDateHeader("Expires", 0L);
/*     */     try
/*     */     {
				String content = "http://" + request.getServerName() + request.getParameter("url");
				// 设置响应的类型格式为图片格式  
				response.setContentType("image/jpeg");  
				//禁止图像缓存。  
				response.setHeader("Pragma", "no-cache");  
				response.setHeader("Cache-Control", "no-cache");  
				response.setDateHeader("Expires", 0);  
				  
					BufferedImage qRCodeCommon = qRCodeCommon(content,"png",7);
					ServletOutputStream sos = response.getOutputStream();
					ImageIO.write(qRCodeCommon,"png",sos);
					sos.close();
/*     */     } catch (Exception e) {
/*  82 */       e.printStackTrace(System.out);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
/*     */   {
/*  88 */     doGet(request, response);
/*     */   }
			
			/** 
			 * 生成二维码(QRCode)图片的公共方法 
			 * @param content 存储内容 
			 * @param imgType 图片类型 
			 * @param size 二维码尺寸 
			 * @return 
			 */  
			private BufferedImage qRCodeCommon(String content, String imgType, int size) {  
			    BufferedImage bufImg = null;  
			    try {  
			        Qrcode qrcodeHandler = new Qrcode();  
			        // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小  
			        qrcodeHandler.setQrcodeErrorCorrect('M');  
			        qrcodeHandler.setQrcodeEncodeMode('B');  
			        // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大  
			        qrcodeHandler.setQrcodeVersion(size);  
			        // 获得内容的字节数组，设置编码格式  
			        byte[] contentBytes = content.getBytes("utf-8");  
			        // 图片尺寸  
			        int imgSize = 67 + 12 * (size - 1);  
			        bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);  
			        Graphics2D gs = bufImg.createGraphics();  
			        // 设置背景颜色  
			        gs.setBackground(Color.WHITE);  
			        gs.clearRect(0, 0, imgSize, imgSize);  
			
			        // 设定图像颜色> BLACK  
			        gs.setColor(Color.BLACK);  
			        // 设置偏移量，不设置可能导致解析出错  
			        int pixoff = 2;  
			        // 输出内容> 二维码  
			        if (contentBytes.length > 0 && contentBytes.length < 800) {  
			            boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
			            for (int i = 0; i < codeOut.length; i++) {  
			                for (int j = 0; j < codeOut.length; j++) {  
			                    if (codeOut[j][i]) {  
			                        gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
			                    }  
			                }  
			            }  
			        } else {  
			            throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");  
			        }  
			        gs.dispose();  
			        bufImg.flush();  
			    } catch (Exception e) {  
			        e.printStackTrace();  
			    }  
			    return bufImg;  
			}  
			
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.servlet.CreateImage
 * JD-Core Version:    0.6.2
 */
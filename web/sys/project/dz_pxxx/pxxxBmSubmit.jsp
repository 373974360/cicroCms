<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cicro.util.*,com.cicro.project.dz_pxxx.*"%>
<%
	String pxid = Encode.iso_8859_1ToUtf8(FormatUtil.formatNullString(request.getParameter("pxid")));
	String pxmc = Encode.iso_8859_1ToUtf8(FormatUtil.formatNullString(request.getParameter("pxmc")));
	String xm = Encode.iso_8859_1ToUtf8(FormatUtil.formatNullString(request.getParameter("xm")));
	String xb = Encode.iso_8859_1ToUtf8(FormatUtil.formatNullString(request.getParameter("xb")));
	String sfzh = Encode.iso_8859_1ToUtf8(FormatUtil.formatNullString(request.getParameter("sfzh")));
	String gzdw = Encode.iso_8859_1ToUtf8(FormatUtil.formatNullString(request.getParameter("gzdw")));
	String zw = Encode.iso_8859_1ToUtf8(FormatUtil.formatNullString(request.getParameter("zw")));
	String lxfs = Encode.iso_8859_1ToUtf8(FormatUtil.formatNullString(request.getParameter("lxfs")));
	String qq = Encode.iso_8859_1ToUtf8(FormatUtil.formatNullString(request.getParameter("qq")));
	String photo = Encode.iso_8859_1ToUtf8(FormatUtil.formatNullString(request.getParameter("photo")));
	String sfzs = Encode.iso_8859_1ToUtf8(FormatUtil.formatNullString(request.getParameter("sfzs")));
	String codeSession = (String)request.getSession().getAttribute("valiCode");
	String auth_code = request.getParameter("auth_code");
	if(!auth_code.equals(codeSession))
	{
		out.println("<script>");
		out.println("top.alert('验证码不正确')");
		out.println("top.changeCreateImage()");
		out.println("</script>");
		return;
	}
	String type = request.getParameter("type");
	List<PxxxBmBean> pbList = null;
	if(type != null && "1".equals(type))
	{
		String pxid2 = Encode.iso_8859_1ToUtf8(FormatUtil.formatNullString(request.getParameter("pxid2")));
		HashMap<String, String> map = new HashMap<String, String>(); 
		map.put("pxid",pxid2);
		map.put("xm",xm);
		map.put("sfzh",sfzh);
		map.put("start_num", "0");	
		map.put("page_size", "1");
		map.put("orderby", "id desc");
		pbList = PxxxBmManager.getPxxxBmList(map);
		if(pbList != null && pbList.size() > 0)
		{
			out.println("<script>");
			out.println("top.window.location.href='/info/iList.jsp?tm_id=34&id=" + pbList.get(0).getId() + "'");	
			out.println("</script>");
		}
		else
		{
			out.println("<script>");
			out.println("top.alert('没有查找到报名信息！')");
			out.println("</script>");
		}
	}
	else
	{
		PxxxBean pb = PxxxManager.getPxxxBean(pxid);
		String bmid = "";
		if(pb != null)
		{
			String date = pb.getPxsj();
			bmid += date.substring(0,10).replaceAll("-","");
			pbList = PxxxBmManager.getAllPxxxBmByPxID(pxid);
			if(pbList != null && pbList.size() > 0)
			{
				if(pbList.size() < Integer.parseInt(pb.getRsxz()))
				{
					if(pbList.size() > 0 && pbList.size() < 10)
					{
						bmid += "00" +( pbList.size() + 1);
					}
					else if(pbList.size() >= 10 && pbList.size() < 100)
					{
						bmid += "0" +( pbList.size() + 1);
					}
					else
					{
						bmid += pbList.size() + 1;
					}
				}
				else
				{
					out.println("<script>");
					out.println("top.alert('报名人数超出限制，请关注下次培训！')");				
					out.println("</script>");
					return;
				}
			}
			else
			{
				bmid += "001";
			}
		}
		PxxxBmBean pbb = new PxxxBmBean();
		pbb.setPxid(Integer.parseInt(pxid));
		pbb.setPxmc(pxmc);
		pbb.setBmid(bmid);
		pbb.setXm(xm);
		pbb.setXb(xb);
		pbb.setSfzh(sfzh);
		pbb.setGzdw(gzdw);
		pbb.setZw(zw);
		pbb.setLxfs(lxfs);
		pbb.setQq(qq);
		pbb.setPhoto(photo);
		pbb.setBmsj(DateUtil.getCurrentDateTime());
		pbb.setSfzs(sfzs);
		pbb.setStatus("1");
		
		if(PxxxBmManager.insertPxxxBm(pbb,null))
		{
			out.println("<script>");
			out.println("top.alert('报名成功！')");		
			out.println("top.window.location.href='/info/iList.jsp?tm_id=34&id=" + pbb.getId() + "'");	
			out.println("</script>");
		}else
		{
			out.println("<script>");
			out.println("top.alert('报名失败，请重新提交')");				
			out.println("</script>");
		}
	}
%>

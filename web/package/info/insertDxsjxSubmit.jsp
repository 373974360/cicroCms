<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="com.cicro.project.dz_dxsjx.DxsjxInfoBean" pageEncoding="utf-8"%>
<%@page import="com.cicro.project.dz_dxsjx.DxsjxInfoManager,com.cicro.util.FormatUtil"%>
<%@ page import="com.cicro.project.dz_dxsjx.DxsjxCategoryRPC" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.cicro.project.dz_dxsjx.DxsjxCategoryBean" %>
<%@ page import="java.util.List" %>
<%
	String category_id  = FormatUtil.formatNullString(request.getParameter("category_id"));
	String xxmc  = FormatUtil.formatNullString(request.getParameter("xxmc"));
	String sxzy  = FormatUtil.formatNullString(request.getParameter("sxzy"));
	String sznj  = FormatUtil.formatNullString(request.getParameter("sznj"));
	String xm  = FormatUtil.formatNullString(request.getParameter("xm"));
	String xb  = FormatUtil.formatNullString(request.getParameter("xb"));
	String mz  = FormatUtil.formatNullString(request.getParameter("mz"));
	String jg  = FormatUtil.formatNullString(request.getParameter("jg"));
	String zzmm  = FormatUtil.formatNullString(request.getParameter("zzmm"));
	String jkzk  = FormatUtil.formatNullString(request.getParameter("jkzk"));
	String nl  = FormatUtil.formatNullString(request.getParameter("nl"));
	String qq  = FormatUtil.formatNullString(request.getParameter("qq"));
	String brtc  = FormatUtil.formatNullString(request.getParameter("brtc"));
	String yzbm  = FormatUtil.formatNullString(request.getParameter("yzbm"));
	String sfzhm  = FormatUtil.formatNullString(request.getParameter("sfzhm"));
	String lxdh  = FormatUtil.formatNullString(request.getParameter("lxdh"));
	String xxtxdz  = FormatUtil.formatNullString(request.getParameter("xxtxdz"));
	String jtlxrjdh  = FormatUtil.formatNullString(request.getParameter("jtlxrjdh"));
	String jtxxzz  = FormatUtil.formatNullString(request.getParameter("jtxxzz"));
	String hjqk  = FormatUtil.formatNullString(request.getParameter("hjqk"));
	String grzjsq  = FormatUtil.formatNullString(request.getParameter("grzjsq"));
	String zp  = FormatUtil.formatNullString(request.getParameter("zp"));
	String jtcy1_cw  = FormatUtil.formatNullString(request.getParameter("jtcy1_cw"));
	String jtcy1_xm  = FormatUtil.formatNullString(request.getParameter("jtcy1_xm"));
	String jtcy1_gzdw  = FormatUtil.formatNullString(request.getParameter("jtcy1_gzdw"));
	String jtcy1_lxdh  = FormatUtil.formatNullString(request.getParameter("jtcy1_lxdh"));
	String jtcy2_cw  = FormatUtil.formatNullString(request.getParameter("jtcy2_cw"));
	String jtcy2_xm  = FormatUtil.formatNullString(request.getParameter("jtcy2_xm"));
	String jtcy2_gzdw  = FormatUtil.formatNullString(request.getParameter("jtcy2_gzdw"));
	String jtcy2_lxdh  = FormatUtil.formatNullString(request.getParameter("jtcy2_lxdh"));
	String tjb  = FormatUtil.formatNullString(request.getParameter("tjb"));

	String codeSession = (String)request.getSession().getAttribute("valiCode");

	String auth_code = request.getParameter("auth_code");
	System.out.println("******************************************************"+codeSession + "------------------------------------------------" +auth_code);
	if(!auth_code.equals(codeSession))
	{
		out.println("<script>");
		out.println("top.alert('验证码不正确')");
		out.println("top.changeCreateImage()");
		out.println("</script>");
		return;
	}
	
	DxsjxInfoBean dxsjxBean = new DxsjxInfoBean();
	if(category_id == null || "".equals(category_id) || "null".equals(category_id)){
		Map<String,String> map = new HashMap<String, String>();
		map.put("page_size","1");
		map.put("start_num","0");
		map.put("orderby","id desc");
		List<DxsjxCategoryBean> dxsjxCategoryList = DxsjxCategoryRPC.getDxsjxCategoryList(map);
		System.out.println(dxsjxCategoryList + "***********");
		if(dxsjxCategoryList != null && dxsjxCategoryList.size() > 0){
			dxsjxBean.setCategory_id(dxsjxCategoryList.get(0).getId());
		}else{
			out.println("<script>");
			out.println("top.alert('分类信息不正确')");
			out.println("top.changeCreateImage()");
			out.println("</script>");
			return;
		}
	}else{
		dxsjxBean.setCategory_id(Integer.parseInt(category_id));
	}
	dxsjxBean.setXxmc(xxmc);
	dxsjxBean.setSxzy(sxzy);
	dxsjxBean.setSznj(sznj);
	dxsjxBean.setXm(xm);
	dxsjxBean.setXb(xb);
	dxsjxBean.setMz(mz);
	dxsjxBean.setJg(jg);
	dxsjxBean.setZzmm(zzmm);
	dxsjxBean.setJkzk(jkzk);
	dxsjxBean.setNl(nl);
	dxsjxBean.setQq(qq);
	dxsjxBean.setBrtc(brtc);
	dxsjxBean.setYzbm(yzbm);
	dxsjxBean.setSfzhm(sfzhm);
	dxsjxBean.setLxdh(lxdh);
	dxsjxBean.setXxtxdz(xxtxdz);
	dxsjxBean.setJtlxrjdh(jtlxrjdh);
	dxsjxBean.setJtxxzz(jtxxzz);
	dxsjxBean.setHjqk(hjqk);
	dxsjxBean.setGrzjsq(grzjsq);
	dxsjxBean.setZp(zp);
	dxsjxBean.setTjb(tjb);
	dxsjxBean.setJtcy1_cw(jtcy1_cw);
	dxsjxBean.setJtcy1_xm(jtcy1_xm);
	dxsjxBean.setJtcy1_gzdw(jtcy1_gzdw);
	dxsjxBean.setJtcy1_lxdh(jtcy1_lxdh);
	dxsjxBean.setJtcy2_cw(jtcy2_cw);
	dxsjxBean.setJtcy2_xm(jtcy2_xm);
	dxsjxBean.setJtcy2_gzdw(jtcy2_gzdw);
	dxsjxBean.setJtcy2_lxdh(jtcy2_lxdh);
	dxsjxBean.setStatus(0);

	if(DxsjxInfoManager.insertDxsjx(dxsjxBean))
	{
		out.println("<script>");
		out.println("top.alert('报名成功，感谢您的参与')");		
		out.println("top.location.reload()");		
		out.println("</script>");
	}else
	{
		out.println("<script>");
		out.println("top.alert('报名失败，请重新提交')");
		out.println("</script>");
	}
%>

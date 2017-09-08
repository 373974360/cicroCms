<%@ page contentType="text/html; charset=utf-8" %>
<%@ page language="java" import="com.alibaba.fastjson.JSONObject" pageEncoding="utf-8" %>
<%@page import="com.cicro.project.dz_pxxx.PxxxBean,com.cicro.project.dz_pxxx.PxxxBmBean" %>
<%@ page import="com.cicro.project.dz_pxxx.PxxxBmManager" %>
<%@ page import="com.cicro.project.dz_pxxx.PxxxManager" %>
<%@ page import="com.cicro.util.DateUtil" %>
<%@ page import="com.cicro.util.FormatUtil" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%
    String pxid = FormatUtil.formatNullString(request.getParameter("pxid"));
    String pxmc = FormatUtil.formatNullString(request.getParameter("pxmc"));
    String xm = FormatUtil.formatNullString(request.getParameter("xm"));
    String xb = FormatUtil.formatNullString(request.getParameter("xb"));
    String sfzh = FormatUtil.formatNullString(request.getParameter("sfzh"));
    String gzdw = FormatUtil.formatNullString(request.getParameter("gzdw"));
    String zw = FormatUtil.formatNullString(request.getParameter("zw"));
    String lxfs = FormatUtil.formatNullString(request.getParameter("lxfs"));
    String qq = FormatUtil.formatNullString(request.getParameter("qq"));
    String photo = FormatUtil.formatNullString(request.getParameter("photo"));
    String sfzs = FormatUtil.formatNullString(request.getParameter("sfzs"));
    String codeSession = (String) request.getSession().getAttribute("valiCode");
    String auth_code = request.getParameter("auth_code");
    int error_code = 0;
    PxxxBmBean pbb = null;
    if (!auth_code.equals(codeSession)) {
        error_code = -1;
    } else {
        List<PxxxBmBean> pbList = null;
        List<PxxxBmBean> pbList2 = null;
        PxxxBean pb = PxxxManager.getPxxxBean(pxid);
        String bmid = "";
        if (pb != null) {
            String date = pb.getPxsj();
            bmid += date.substring(0, 10).replaceAll("-", "");
            HashMap<String, String> map2 = new HashMap<String, String>();
            map2.put("pxid", pxid);
            map2.put("status", "1");
            pbList = PxxxBmManager.getAllPxxxBmByPxID(map2);
            HashMap<String, String> map3 = new HashMap<String, String>();
            map3.put("pxid", pxid);
            pbList2 = PxxxBmManager.getAllPxxxBmByPxID(map3);
            if (Integer.parseInt(pb.getRsxz()) == 0) {
                error_code = -2;
            } else {
                if (pbList != null) {
                    if (pbList.size() < Integer.parseInt(pb.getRsxz())) {
                        if (pbList2 != null && pbList2.size() > 0) {
                            if (pbList2.size() > 0 && pbList2.size() < 9) {
                                bmid += "00" + (pbList2.size() + 1);
                            } else if (pbList2.size() >= 9 && pbList2.size() < 99) {
                                bmid += "0" + (pbList2.size() + 1);
                            } else {
                                bmid += pbList2.size() + 1;
                            }
                        } else {
                            bmid += "001";
                        }
                    } else {
                        error_code = -2;
                    }
                } else {
                    if (Integer.parseInt(pb.getRsxz()) > 0) {
                        bmid += "001";
                    } else {
                        error_code = -2;
                    }
                }
            }
            if(error_code == 0){
                pbb = new PxxxBmBean();
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
                pbb = PxxxBmManager.insertPxxxBm(pbb, null);
            }
        }else{
            error_code = -3;
        }
    }
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("error_code", error_code);
    jsonObject.put("data", pbb);
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(jsonObject.toJSONString());

%>

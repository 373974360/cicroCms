package com.cicro.project.dz_memberAction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class MemberActionServlet extends HttpServlet
{
	private static final long serialVersionUID = 3131099767169123925L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String method = request.getParameter("method");
		String memberId = request.getParameter("memberId");
		if(method != null && "postInfo".equals(method)){
			String infoId = request.getParameter("infoId");
			String type = "2";
			MemberInfoBean mib = new MemberInfoBean();
			mib.setMemberId(memberId);
			mib.setInfoId(infoId);
			mib.setType(type);
			JSONObject jsonObject = new JSONObject();
			if(MemberInfoManager.insertMemberInfo(mib)){
				jsonObject.put("status", "1");
				jsonObject.put("msg", "执行成功");
			}else{
				jsonObject.put("status", "0");
				jsonObject.put("msg", "执行失败");
			}
			response.setCharacterEncoding("UTF-8");
	        response.getWriter().print(jsonObject.toString());
	        response.getWriter().flush();
	        response.getWriter().close();
		}else{
			String offset = request.getParameter("offset");
			String limit = request.getParameter("limit");
			Map<String,String> m = new HashMap<String,String>();
			m.put("orderby","clickTime desc");
			if(memberId != null && !"".equals(memberId)){
				m.put("memberId", memberId);
			}
			if(offset != null && !"".equals(offset)){
				m.put("start_num", offset);
			}else{
				m.put("start_num","0");
			}
			if(limit != null && !"".equals(limit)){
				m.put("page_size", limit);
			}else{
				m.put("page_size","50");
			}
			List<MemberActionBean> list = MemberActionManager.getMemberActionList(m);
			JSONObject result = new JSONObject();
			result.put("status","1");
			if(list != null && list.size() > 0){
				JSONArray jsonArray = new JSONArray();
				for(MemberActionBean mab : list){
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("memberId",mab.getMemberId());
					jsonObject.put("infoId", mab.getInfoId());
					jsonObject.put("infoUrl",mab.getInfoUrl());
					jsonObject.put("clickTime",mab.getClickTime());
					jsonObject.put("ipAddr",mab.getIpAddr());
					jsonObject.put("byzd",mab.getByzd());
					jsonArray.put(jsonObject);
				}
				result.put("list",jsonArray);
			}
			response.setCharacterEncoding("UTF-8");
	        response.getWriter().print(result.toString());
	        response.getWriter().flush();
	        response.getWriter().close();
		}
	}
	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "1");
		jsonObject.put("msg", "执行成功");
		System.out.println(jsonObject.toString());
	}
}
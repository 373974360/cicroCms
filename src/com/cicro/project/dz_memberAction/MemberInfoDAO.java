package com.cicro.project.dz_memberAction;

import com.cicro.wcm.db.DBManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberInfoDAO {
	public static String getMemberInfoCount(Map<String, String> m) {
		return DBManager.getString("getMemberInfoCount", m);
	}

	public static List<MemberInfoBean> getMemberInfoList(Map<String, String> m) {
		return DBManager.queryFList("getMemberInfoList", m);
	}

	public static MemberInfoBean getMemberInfoBean(String id) {
		Map m = new HashMap();
		m.put("id", id);
		return (MemberInfoBean) DBManager.queryFObj("getMemberInfoBean", m);
	}

	public static boolean insertMemberInfo(MemberInfoBean MemberInfo) {
		return DBManager.insert("insertMemberInfo", MemberInfo);
	}
}
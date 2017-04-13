package com.cicro.project.dz_memberAction;

import com.cicro.wcm.db.DBManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberActionDAO {
	public static String getMemberActionCount(Map<String, String> m) {
		return DBManager.getString("getMemberActionCount", m);
	}

	public static List<MemberActionBean> getMemberActionList(Map<String, String> m) {
		return DBManager.queryFList("getMemberActionList", m);
	}

	public static MemberActionBean getMemberActionBean(String id) {
		Map m = new HashMap();
		m.put("id", id);
		return (MemberActionBean) DBManager.queryFObj("getMemberActionBean", m);
	}

	public static boolean insertMemberAction(MemberActionBean MemberAction) {
		return DBManager.insert("insertMemberAction", MemberAction);
	}
}
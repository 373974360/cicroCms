package com.cicro.project.dz_memberAction;

import java.util.List;
import java.util.Map;

public class MemberActionRPC {
	public static String getMemberActionCount(Map<String, String> m) {
		return MemberActionManager.getMemberActionCount(m);
	}

	public static List<MemberActionBean> getMemberActionList(Map<String, String> m) {
		return MemberActionManager.getMemberActionList(m);
	}

	public static MemberActionBean getMemberActionBean(String gq_id) {
		return MemberActionManager.getMemberActionBean(gq_id);
	}

	public static boolean insertMemberAction(MemberActionBean hb) {
		return MemberActionManager.insertMemberAction(hb);
	}
}
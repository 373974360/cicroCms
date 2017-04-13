package com.cicro.project.dz_memberAction;

import com.cicro.util.DateUtil;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MemberActionManager {
	public static String getMemberActionCount(Map<String, String> m) {
		return MemberActionDAO.getMemberActionCount(m);
	}

	public static List<MemberActionBean> getMemberActionList(Map<String, String> m) {
		return MemberActionDAO.getMemberActionList(m);
	}

	public static MemberActionBean getMemberActionBean(String id) {
		return MemberActionDAO.getMemberActionBean(id);
	}

	public static boolean insertMemberAction(MemberActionBean hb) {
		hb.setId(UUID.randomUUID().toString());
		hb.setClickTime(DateUtil.getCurrentDateTime());
		return MemberActionDAO.insertMemberAction(hb);
	}
}
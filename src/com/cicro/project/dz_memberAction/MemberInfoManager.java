package com.cicro.project.dz_memberAction;

import com.cicro.util.DateUtil;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MemberInfoManager {
	public static boolean insertMemberInfo(MemberInfoBean hb) {
		hb.setId(UUID.randomUUID().toString());
		hb.setAddTime(DateUtil.getCurrentDateTime());
		return MemberInfoDAO.insertMemberInfo(hb);
	}
	
	public static String getMemberInfoCount(Map<String, String> m) {
		return MemberInfoDAO.getMemberInfoCount(m);
	}

	public static List<MemberInfoBean> getMemberInfoList(Map<String, String> m) {
		return MemberInfoDAO.getMemberInfoList(m);
	}

	public static MemberInfoBean getMemberInfoBean(String id) {
		return MemberInfoDAO.getMemberInfoBean(id);
	}
}
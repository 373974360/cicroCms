//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.services.cms.category;

import com.cicro.util.FormatUtil;
import com.cicro.wcm.bean.cms.category.CateClassBean;
import com.cicro.wcm.bean.cms.category.CategoryBean;
import com.cicro.wcm.bean.cms.category.CategoryModel;
import com.cicro.wcm.bean.cms.category.SMCategoryBean;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.catchs.ISyncCatch;
import com.cicro.wcm.catchs.SyncCatchHandl;
import com.cicro.wcm.dao.PublicTableDAO;
import com.cicro.wcm.dao.cms.category.CategoryDAO;
import com.cicro.wcm.dao.cms.category.CategoryGetReguDAO;
import com.cicro.wcm.dao.cms.category.CategoryModelDAO;
import com.cicro.wcm.dao.system.design.DesignDAO;
import com.cicro.wcm.db.DBManager;
import com.cicro.wcm.rmi.file.FileRmiFactory;
import com.cicro.wcm.services.cms.category.CateClassManager;
import com.cicro.wcm.services.cms.category.CategoryModelManager;
import com.cicro.wcm.services.cms.category.CategoryReleManager;
import com.cicro.wcm.services.cms.category.CategorySharedManager;
import com.cicro.wcm.services.cms.category.CategoryTreeUtil;
import com.cicro.wcm.services.cms.category.CategoryUtil;
import com.cicro.wcm.services.cms.category.SyncManager;
import com.cicro.wcm.services.cms.category.CategoryManager.CategoryComparator;
import com.cicro.wcm.services.cms.info.InfoBaseManager;
import com.cicro.wcm.services.cms.workflow.WorkFlowManager;
import com.cicro.wcm.services.control.site.SiteAppRele;
import com.cicro.wcm.services.control.site.SiteManager;
import com.cicro.wcm.services.org.role.RoleUserManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CategoryManager implements ISyncCatch {
	public static Map<String, TreeMap<Integer, CategoryBean>> category_m = new HashMap<String, TreeMap<Integer, CategoryBean>>();
	public static Map<Integer, CategoryBean> all = new HashMap<Integer, CategoryBean>();

	protected static int ROOT_ID = 0;

	static {
		reloadCatchHandl(null);
	}

	public CategoryManager() {
	}

	public void reloadCatch() {
		reloadCatchHandl(null);
	}
	
	public void reloadCatch(String site_id) {
		reloadCatchHandl(site_id);
	}

	public static TreeMap<Integer, CategoryBean> getSiteCategoryMap(String site_id) {
		if (!getSiteCategoryMap(site_id).containsKey(site_id)) {
			category_m.put(site_id, new TreeMap<Integer, CategoryBean>());
		}
		;
		return getSiteCategoryMap(site_id);
	}

	public static void reloadCatchHandl(String site_id) {

		try {
			List e = null;
			if (site_id == null) {
				category_m.clear();
				e = CategoryDAO.getAllCategoryList();
			} else {
				getSiteCategoryMap(site_id).clear();
				e = CategoryDAO.getCategoryListBySiteID(site_id);
			}

			if (e != null && e.size() > 0) {
				for (int i = 0; i < e.size(); ++i) {
					CategoryBean cgb = (CategoryBean) e.get(i);
					getSiteCategoryMap(site_id).put(Integer.valueOf((cgb).getId()), (CategoryBean) e.get(i));
					all.put(cgb.getId(), cgb);
				}
			}
		} catch (Exception var2) {
			var2.printStackTrace();
		}

		CateClassManager.clearSMCateMap();
		CategoryTreeUtil.reloadMap();
	}

	public static void reloadCategory() {
		SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.cms.category.CategoryManager");
	}

	public static int getNewCategoryID() {
		return PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_CATEGORY_TABLE_NAME);
	}

	public static CategoryBean getCategoryBean(int id) {
		if (all.containsKey(Integer.valueOf(id))) {
			return (CategoryBean) all.get(Integer.valueOf(id));
		} else {
			CategoryBean cgb = CategoryDAO.getCategoryBean(String.valueOf(id));
			if (cgb != null) {
				getSiteCategoryMap(cgb.getSite_id()).put(Integer.valueOf(id), cgb);
				all.put(id, cgb);
				return cgb;
			} else {
				return null;
			}
		}
	}

	public static String getMutilCategoryNames(String cat_ids, String site_id) {
		String names = "";
		if (cat_ids != null && !"".equals(cat_ids)) {
			String[] tempA = cat_ids.split(",");

			for (int i = 0; i < tempA.length; ++i) {
				CategoryBean cb = getCategoryBeanCatID(Integer.parseInt(tempA[i]), site_id);
				if (cb != null) {
					names = names + "," + cb.getCat_cname();
				}
			}

			if (names != null && !"".equals(names)) {
				names = names.substring(1);
			}
		}

		return names;
	}

	public static CategoryBean getCategoryBeanCatID(int cat_id, String site_id) {
		if (cat_id == 0) {
			CategoryBean set1 = new CategoryBean();
			set1.setCat_id(cat_id);
			set1.setSite_id(site_id);
			set1.setCat_position("$0$");
			return set1;
		} else {
			Set set = getSiteCategoryMap(site_id).keySet();
			Iterator localIterator = set.iterator();

			CategoryBean cgb;
			do {
				if (!localIterator.hasNext()) {
					return null;
				}

				int i = ((Integer) localIterator.next()).intValue();
				cgb = (CategoryBean) getSiteCategoryMap(site_id).get(Integer.valueOf(i));
			} while (cgb.getCat_id() != cat_id || !site_id.equals(cgb.getSite_id()));

			return cgb;
		}
	}

	public static int getWFIDByCatID(int cat_id, String site_id) {
		CategoryBean cgb = getCategoryBeanCatID(cat_id, site_id);
		return cgb != null ? cgb.getWf_id() : 0;
	}

	public static List<CategoryBean> getCategoryListBySiteID(String site_id, int cat_type) {
		ArrayList list = new ArrayList();
		Set set = getSiteCategoryMap(site_id).keySet();
		Iterator localIterator = set.iterator();

		while (localIterator.hasNext()) {
			int i = ((Integer) localIterator.next()).intValue();
			CategoryBean cgb = (CategoryBean) getSiteCategoryMap(site_id).get(Integer.valueOf(i));
			if (site_id.equals(cgb.getSite_id()) && cgb.getParent_id() == ROOT_ID && cgb.getCat_type() == cat_type
					&& cgb.getIs_archive() == 0) {
				list.add(cgb);
			}
		}

		if (list != null && list.size() > 0) {
			Collections.sort(list, new CategoryComparator());
		}

		return list;
	}

	public static String getCategoryCountBySiteAndType(Map<String, String> m) {
		return CategoryDAO.getCategoryCountBySiteAndType(m);
	}

	public static List<CategoryBean> getCategoryListBySiteAndType(Map<String, String> m) {
		return CategoryDAO.getCategoryListBySiteAndType(m);
	}

	public static List<CategoryBean> getZTCategoryListBySiteAndType(int zt_cat_id, String site_id) {
		ArrayList l = new ArrayList();
		Set s = getSiteCategoryMap(site_id).keySet();
		Iterator localIterator = s.iterator();

		while (localIterator.hasNext()) {
			int i = ((Integer) localIterator.next()).intValue();
			CategoryBean cb = (CategoryBean) getSiteCategoryMap(site_id).get(Integer.valueOf(i));
			if (site_id.equals(cb.getSite_id()) && cb.getParent_id() == 0 && cb.getZt_cat_id() == zt_cat_id
					&& cb.getIs_archive() == 0) {
				l.add(cb);
			}
		}

		return l;
	}

	public static boolean haveCategoryManagementAuthority(int cat_id, String site_id, int user_id) {
		return RoleUserManager.isSiteManager(String.valueOf(user_id), "cms", site_id)
				|| RoleUserManager.isSiteManager(String.valueOf(user_id), "zwgk", site_id)
				|| RoleUserManager.isSiteManager(String.valueOf(user_id), "ggfw", site_id)
				|| CategoryReleManager.isCategoryManagerByUser(user_id, site_id, cat_id);
	}

	public static void setCategoryByPosition(Set<CategoryBean> set, String cat_position, String site_id) {
		String[] tempA = cat_position.split("\\$");

		for (int i = 2; i < tempA.length - 1; ++i) {
			if (tempA[i] != null && !"".equals(tempA[i])) {
				set.add(getCategoryBeanCatID(Integer.parseInt(tempA[i]), site_id));
			}
		}

	}

	public static CategoryBean getCategoryBeanByClassID(int class_id) {
		Set set = all.keySet();
		Iterator localIterator = set.iterator();

		CategoryBean cgb;
		do {
			if (!localIterator.hasNext()) {
				return null;
			}

			int i = ((Integer) localIterator.next()).intValue();
			cgb = (CategoryBean) all.get(Integer.valueOf(i));
		} while (cgb.getClass_id() != class_id || cgb.getParent_id() != ROOT_ID || !"".equals(cgb.getSite_id()));

		return cgb;
	}

	public static SMCategoryBean categoryToSmileBean(CategoryBean cgb) {
		SMCategoryBean smb = new SMCategoryBean();
		smb.setCat_cname(cgb.getCat_cname());
		smb.setCat_id(cgb.getCat_id());
		smb.setSite_id(cgb.getSite_id());
		smb.setCat_position(cgb.getCat_position());
		smb.setParent_id(cgb.getParent_id());
		return smb;
	}

	public static List<CategoryBean> getChildCategoryList(int cat_id, String site_id) {
		Set set = getSiteCategoryMap(site_id).keySet();
		ArrayList list = new ArrayList();
		Iterator localIterator = set.iterator();

		while (localIterator.hasNext()) {
			int i = ((Integer) localIterator.next()).intValue();
			CategoryBean cgb = (CategoryBean) getSiteCategoryMap(site_id).get(Integer.valueOf(i));
			if (cgb.getParent_id() == cat_id && site_id.equals(cgb.getSite_id())) {
				list.add(cgb);
			}
		}

		if (list != null && list.size() > 0) {
			Collections.sort(list, new CategoryComparator());
		}

		return list;
	}

	public static List<CategoryBean> getChildCategoryListForBrowser(int cat_id, String site_id) {
		Set set = getSiteCategoryMap(site_id).keySet();
		ArrayList list = new ArrayList();
		Iterator localIterator = set.iterator();

		while (localIterator.hasNext()) {
			int i = ((Integer) localIterator.next()).intValue();
			CategoryBean cgb = (CategoryBean) getSiteCategoryMap(site_id).get(Integer.valueOf(i));
			if (cgb.getParent_id() == cat_id && site_id.equals(cgb.getSite_id()) && cgb.getIs_show() == 1) {
				cgb.setIs_sub(
						isHasChildNode(((CategoryBean) getSiteCategoryMap(site_id).get(Integer.valueOf(i))).getCat_id(),
								((CategoryBean) getSiteCategoryMap(site_id).get(Integer.valueOf(i))).getSite_id()));
				list.add(cgb);
			}
		}

		if (list != null && list.size() > 0) {
			Collections.sort(list, new CategoryComparator());
		}

		return list;
	}

	public static List<CategoryBean> getChildCategoryListForBrowser2(int cat_id, String site_id) {
		Set set = getSiteCategoryMap(site_id).keySet();
		ArrayList list = new ArrayList();
		Iterator localIterator = set.iterator();

		while (localIterator.hasNext()) {
			int i = ((Integer) localIterator.next()).intValue();
			CategoryBean cgb = (CategoryBean) getSiteCategoryMap(site_id).get(Integer.valueOf(i));
			if (cgb.getParent_id() == cat_id && site_id.equals(cgb.getSite_id()) && cgb.getIs_show() == 1) {
				cgb.setIs_sub(
						isHasChildNode(((CategoryBean) getSiteCategoryMap(site_id).get(Integer.valueOf(i))).getCat_id(),
								((CategoryBean) getSiteCategoryMap(site_id).get(Integer.valueOf(i))).getSite_id()));
				list.add(cgb);
			}
		}

		if (list != null && list.size() > 0) {
			Collections.sort(list, new CategoryComparator());
		}

		return list;
	}

	public static boolean isHasChildNode(int cat_id, String site_id) {
		Set set = getSiteCategoryMap(site_id).keySet();
		Iterator localIterator = set.iterator();

		CategoryBean cgb;
		do {
			if (!localIterator.hasNext()) {
				return false;
			}

			int i = ((Integer) localIterator.next()).intValue();
			cgb = (CategoryBean) getSiteCategoryMap(site_id).get(Integer.valueOf(i));
		} while (cgb.getParent_id() != cat_id || !site_id.equals(cgb.getSite_id()));

		return true;
	}

	public static SMCategoryBean getAllChildForSMCategoryBean(CategoryBean cgb) {
		if (cgb == null) {
			return null;
		} else {
			ArrayList sml = new ArrayList();
			List cl = getChildCategoryList(cgb.getCat_id(), cgb.getSite_id());
			if (cl != null && cl.size() > 0) {
				Iterator var4 = cl.iterator();

				while (var4.hasNext()) {
					CategoryBean smb = (CategoryBean) var4.next();
					if (smb.getIs_archive() == 0) {
						sml.add(getAllChildForSMCategoryBean(smb));
					}
				}
			}

			SMCategoryBean smb1 = categoryToSmileBean(cgb);
			smb1.setSm_list(sml);
			return smb1;
		}
	}

	public static List<CategoryBean> getAllChildCategoryList(int cat_id, String site_id) {
		CategoryBean cgb = getCategoryBeanCatID(cat_id, site_id);
		if (cgb != null) {
			String cat_position = cgb.getCat_position();
			Set set = getSiteCategoryMap(site_id).keySet();
			ArrayList list = new ArrayList();
			Iterator localIterator = set.iterator();

			while (localIterator.hasNext()) {
				int i = ((Integer) localIterator.next()).intValue();
				cgb = (CategoryBean) getSiteCategoryMap(site_id).get(Integer.valueOf(i));
				if (cgb.getCat_position().startsWith(cat_position) && site_id.equals(cgb.getSite_id())) {
					list.add((CategoryBean) getSiteCategoryMap(site_id).get(Integer.valueOf(i)));
				}
			}

			return list;
		} else {
			return null;
		}
	}

	public static String getAllChildCategoryIDS(int cat_id, String site_id) {
		CategoryBean cgb = getCategoryBeanCatID(cat_id, site_id);
		if (cgb != null) {
			String cat_position = cgb.getCat_position();
			Set set = getSiteCategoryMap(site_id).keySet();
			String cat_ids = "";
			Iterator localIterator = set.iterator();

			while (localIterator.hasNext()) {
				int i = ((Integer) localIterator.next()).intValue();
				cgb = (CategoryBean) getSiteCategoryMap(site_id).get(Integer.valueOf(i));
				if (cgb.getCat_position().startsWith(cat_position) && !cat_position.equals(cgb.getCat_position())
						&& site_id.equals(cgb.getSite_id())) {
					cat_ids = cat_ids + "," + cgb.getCat_id();
				}
			}

			if (cat_ids.length() > 0) {
				cat_ids = cat_ids.substring(1);
			}

			return cat_ids;
		} else {
			return null;
		}
	}

	public static String getAllChildCategoryIDS(String cat_ids, String site_id) {
		String ids = "";
		String[] tempA = cat_ids.split(",");

		for (int i = 0; i < tempA.length; ++i) {
			String c_ids = getAllChildCategoryIDS(Integer.parseInt(tempA[i]), site_id);
			if (c_ids != null && !"".equals(c_ids)) {
				ids = ids + "," + c_ids;
			}
		}

		if (ids.length() > 0) {
			ids = ids.substring(1);
		}

		return ids;
	}

	public static String getCategoryContentSavePath(int cat_id, String app_id, String site_id) {
		String save_path = SiteManager.getSitePath(site_id)
				+ CategoryUtil.getFoldePathByCategoryID(cat_id, app_id, site_id);
		return save_path;
	}

	public static boolean categoryIsExist(int parent_id, String cat_ename, String site_id) {
		List l = getChildCategoryList(parent_id, site_id);
		if (l != null && l.size() > 0) {
			for (int i = 0; i < l.size(); ++i) {
				if (((CategoryBean) l.get(i)).getCat_ename().equals(cat_ename)) {
					return true;
				}
			}

			return false;
		} else {
			return false;
		}
	}

	public static boolean insertCategoryByClass(CateClassBean ccb, SettingLogsBean stl) {
		CategoryBean cgb = new CategoryBean();
		cgb.setClass_id(ccb.getClass_id());
		cgb.setCat_cname(ccb.getClass_cname());
		cgb.setCat_ename(ccb.getClass_ename());
		cgb.setId(getNewCategoryID());
		cgb.setCat_id(cgb.getId());
		cgb.setApp_id("system");
		cgb.setIs_generate_index(0);
		return insertCategory(cgb, true, stl);
	}

	public static boolean insertCategory(CategoryBean cgb, boolean is_share, SettingLogsBean stl) {
		insertCategoryHandl(cgb, is_share);
		if (CategoryDAO.insertCategory(cgb, stl)) {
			reloadCategory();
			return true;
		} else {
			return false;
		}
	}

	public static boolean batchUpdateCategory(Map<String, String> cat_m, List<CategoryModel> l, String cat_id,
			String site_id, SettingLogsBean stl) {
		try {
			if (cat_m != null && cat_m.size() > 0) {
				cat_m.put("cat_ids", cat_id);
				cat_m.put("site_id", site_id);
				CategoryDAO.batchUpdateCategory(cat_m, stl);
				reloadCategory();
			}

			if (l != null && l.size() > 0) {
				HashMap e = new HashMap();
				e.put("cat_id", cat_id);
				e.put("site_id", site_id);
				Iterator var7 = l.iterator();

				while (var7.hasNext()) {
					CategoryModel cm = (CategoryModel) var7.next();
					e.put("model_id", String.valueOf(cm.getModel_id()));
					DBManager.delete("delete_category_model", e);
					String[] tempA = cat_id.split(",");

					for (int i = 0; i < tempA.length; ++i) {
						cm.setCat_id(Integer.parseInt(tempA[i]));
						cm.setCat_model_id(
								PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_CATEGORY_MODEL_TABLE_NAME));
						DBManager.insert("insert_category_model", cm);
					}
				}

				CategoryModelManager.reloadCategoryModel();
			}

			return true;
		} catch (Exception var10) {
			var10.printStackTrace();
			return false;
		}
	}

	public static boolean updateCategoryTemplate(Map<String, String> m) {
		if ("template_content".equals(m.get("template_type"))) {
			ArrayList l = new ArrayList();
			CategoryModel pic = new CategoryModel();
			CategoryModel article = new CategoryModel();
			CategoryModel video = new CategoryModel();
			String app_id = "cms";
			String site_id = (String) m.get("site_id");
			String cat_id = (String) m.get("cat_id");
			int template_id = Integer.parseInt((String) m.get("template_id"));
			pic.setApp_id(app_id);
			pic.setSite_id(site_id);
			pic.setCat_id(Integer.parseInt(cat_id));
			pic.setTemplate_content(template_id);
			pic.setModel_id(10);
			article.setApp_id(app_id);
			article.setSite_id(site_id);
			article.setCat_id(Integer.parseInt(cat_id));
			article.setTemplate_content(template_id);
			article.setModel_id(11);
			video.setApp_id(app_id);
			video.setSite_id(site_id);
			video.setCat_id(Integer.parseInt(cat_id));
			video.setTemplate_content(template_id);
			video.setModel_id(13);
			l.add(pic);
			l.add(article);
			l.add(video);
			return CategoryModelManager.updateCategoryModel(l, cat_id, site_id);
		} else if (CategoryDAO.updateCategoryTemplate(m)) {
			reloadCategory();
			return true;
		} else {
			return false;
		}
	}

	public static boolean clearCategoryTemplate(String template_ids, String site_id) {
		if (CategoryDAO.clearCategoryTemplate(template_ids, site_id)) {
			reloadCategory();
			return true;
		} else {
			return false;
		}
	}

	public static boolean insertCategoryNoCatch(CategoryBean cgb, boolean is_share, SettingLogsBean stl) {
		insertCategoryHandl(cgb, is_share);
		return CategoryDAO.insertCategory(cgb, stl);
	}

	public static void insertCategoryHandl(CategoryBean cgb, boolean is_share) {
		if (cgb.getParent_id() == ROOT_ID) {
			cgb.setCat_position("$0$" + cgb.getCat_id() + "$");
			cgb.setCat_level(1);
		} else {
			CategoryBean parentB = getCategoryBeanCatID(cgb.getParent_id(), cgb.getSite_id());
			cgb.setCat_position(parentB.getCat_position() + cgb.getCat_id() + "$");
			cgb.setCat_level(parentB.getCat_level() + 1);
		}

		if (is_share) {
			cgb.setCat_source_id(cgb.getCat_id());
		}

	}

	public static boolean copyBasisCategory(int parent_id, int selected_cat_ids, String site_id, SettingLogsBean stl) {
		List child_list = getChildCategoryList(selected_cat_ids, site_id);
		if (child_list != null && child_list.size() > 0) {
			try {
				for (int e = 0; e < child_list.size(); ++e) {
					copyCategoryHandl(parent_id, (CategoryBean) child_list.get(e), "system", "", true, stl);
				}
			} catch (Exception var6) {
				var6.printStackTrace();
				return false;
			}
		}

		reloadCategory();
		CategoryModelManager.reloadCategoryModel();
		return true;
	}

	public static synchronized boolean copyShareCategory(int parent_id, String selected_cat_ids, String app_id,
			String site_id, SettingLogsBean stl) {
		String[] tempA = selected_cat_ids.split(",");

		for (int i = 0; i < tempA.length; ++i) {
			CategoryBean cgb = getCategoryBeanCatID(Integer.parseInt(tempA[i]), "");
			if (cgb != null) {
				copyCategoryHandl(parent_id, cgb, app_id, site_id, false, stl);
			}
		}

		reloadCategory();
		CategoryModelManager.reloadCategoryModel();
		return true;
	}

	public static synchronized void copyCategoryHandl(int parent_id, CategoryBean cgb, String app_id, String site_id,
			boolean is_share, SettingLogsBean stl) {
		int source_cat_id = cgb.getCat_id();
		int new_cat_id = getNewCategoryID();
		cgb.setId(new_cat_id);
		cgb.setParent_id(parent_id);
		cgb.setCat_id(new_cat_id);
		cgb.setCat_class_id(source_cat_id);
		cgb.setSite_id(site_id);
		cgb.setApp_id(app_id);
		if (parent_id != 0) {
			CategoryBean model_l = getCategoryBeanCatID(parent_id, site_id);
			cgb.setClass_id(model_l.getCat_class_id());
		}

		if (insertCategoryNoCatch(cgb, is_share, stl)) {
			List var12 = CategoryModelManager.getCategoryReleModelList(String.valueOf(source_cat_id), "");
			if (var12 != null && var12.size() > 0) {
				Iterator i = var12.iterator();

				while (i.hasNext()) {
					CategoryModel child_list = (CategoryModel) i.next();
					child_list.setSite_id(site_id);
					child_list.setApp_id(app_id);
					child_list.setCat_id(new_cat_id);
				}

				CategoryModelDAO.insertCategoryModel(var12);
			}

			List var13 = getChildCategoryList(source_cat_id, "");
			if (var13 != null && var13.size() > 0) {
				for (int var11 = 0; var11 < var13.size(); ++var11) {
					copyCategoryHandl(new_cat_id, (CategoryBean) var13.get(var11), app_id, site_id, is_share, stl);
				}
			}
		}

	}

	public static boolean updateCategory(CategoryBean cgb, SettingLogsBean stl) {
		if (CategoryDAO.updateCategory(cgb, stl)) {
			reloadCategory();
			return true;
		} else {
			return false;
		}
	}

	public static boolean updateGKZNCateTemplate(String gkzy_list, String gkzn_list, String gknb_list) {
		try {
			CategoryDAO.updateCateTemplateListByID(gkzn_list, "10");
			CategoryDAO.updateCateTemplateListByID(gknb_list, "11");
			CategoryDAO.updateCateTemplateListByID(gkzy_list, "12");
			reloadCategory();
			return true;
		} catch (Exception var4) {
			var4.printStackTrace();
			return false;
		}
	}

	public static boolean updateCategoryArchiveStatus(String ids, String is_archive, SettingLogsBean stl) {
		if (CategoryDAO.updateCategoryArchiveStatus(ids, is_archive, stl)) {
			reloadCategory();
			return true;
		} else {
			return false;
		}
	}

	public static boolean updateCategoryByClass(String cat_ename, String cat_cname, int class_id) {
		CategoryBean cgb = getCategoryBeanByClassID(class_id);
		if (cgb != null && CategoryDAO.updateCategoryByClass(cat_ename, cat_cname, String.valueOf(cgb.getCat_id()))) {
			reloadCategory();
			return true;
		} else {
			return true;
		}
	}

	public static boolean sortCategory(String ids, SettingLogsBean stl) {
		if (CategoryDAO.sortCategory(ids, stl)) {
			reloadCategory();
			return true;
		} else {
			return false;
		}
	}

	public static boolean deleteCategory(String cat_ids, String site_id, String app_id, SettingLogsBean stl) {
		String[] foldeArr = CategoryUtil.getFoldeArrByCatIDS(cat_ids, site_id, app_id);
		String all_child_ids = getAllChildCategoryIDS(cat_ids, site_id);
		if (!"".equals(all_child_ids)) {
			cat_ids = cat_ids + "," + all_child_ids;
		}

		try {
			CategorySharedManager.deleteCategorySharedByCatID(cat_ids, site_id);
			SyncManager.deleteSyncForCatID(site_id, cat_ids);
			CategoryModelManager.deleteCategoryModel(cat_ids, site_id);
			CategoryReleManager.deleteCategoryReleUserByCatID(cat_ids, site_id);
			InfoBaseManager.clearInfoSearchByCatID(site_id, cat_ids);
			InfoBaseManager.deleteInfoBySite(site_id, cat_ids);
			if (!"zwgk".equals(app_id) && !"ggfw".equals(app_id)) {
				deleteCategoryFolde(foldeArr, site_id, app_id);
			}

			CategoryGetReguDAO.deleteCategoryRegu(cat_ids, site_id);
			DesignDAO.deleteDesignCategory(cat_ids, "", site_id);
			if (CategoryDAO.deleteCategory(cat_ids, site_id, stl)) {
				reloadCategory();
				return true;
			} else {
				return false;
			}
		} catch (Exception var7) {
			if (CategoryDAO.deleteCategory(cat_ids, site_id, stl)) {
				reloadCategory();
				return true;
			} else {
				return false;
			}
		}
	}

	public static void deleteCategoryFolde(String[] foldeArr, String site_id, String app_id) {
		String savePath = FormatUtil.formatPath(SiteManager.getSitePath(site_id));
		if (!"cms".equals(app_id)) {
			savePath = FormatUtil.formatPath(SiteManager.getSitePath(SiteAppRele.getSiteIDByAppID(app_id)));
		}

		if (foldeArr != null && foldeArr.length > 0) {
			for (int i = 0; i < foldeArr.length; ++i) {
				try {
					if (foldeArr[i] != null && !"".equals(foldeArr[i]) && !(savePath + foldeArr[i]).endsWith("ROOT/")
							&& !"/".equals(foldeArr[i])) {
						System.out.println("deleteCategoryFolde-----" + savePath + foldeArr[i]);
						FileRmiFactory.delDir(site_id, savePath + foldeArr[i]);
					}
				} catch (Exception var6) {
					var6.printStackTrace();
				}
			}
		}

	}

	public static int getCategoryCountByClassID(int class_id) {
		Set set = all.keySet();
		int count = 0;
		Iterator localIterator = set.iterator();

		while (localIterator.hasNext()) {
			int i = ((Integer) localIterator.next()).intValue();
			CategoryBean cgb = (CategoryBean) all.get(Integer.valueOf(i));
			if (cgb.getClass_id() == class_id && "".equals(cgb.getSite_id())) {
				++count;
			}
		}

		return count;
	}

	public static boolean deleteCategoryByClassID(String class_ids) {
		if (CategoryDAO.deleteCategoryByClassID(class_ids)) {
			reloadCategory();
			return true;
		} else {
			return false;
		}
	}

	public static boolean releCategoryClass(String class_id, String cat_id, SettingLogsBean stl) {
		if (CategoryDAO.releCategoryClass(class_id, cat_id, stl)) {
			reloadCategory();
			return true;
		} else {
			return false;
		}
	}

	public static boolean moveCategory(String parent_id, String cat_ids, String site_id, SettingLogsBean stl) {
		CategoryBean parent_b = getCategoryBeanCatID(Integer.parseInt(parent_id), site_id);
		if (parent_b == null) {
			return false;
		} else {
			String parent_tree_position = parent_b.getCat_position();
			int cat_level = parent_b.getCat_level();
			if (cat_ids != null && !"".equals(cat_ids)) {
				try {
					String[] e = cat_ids.split(",");

					for (int i = 0; i < e.length; ++i) {
						moveCategoryHandl(e[i], parent_id, parent_tree_position, cat_level, site_id, stl);
					}

					reloadCategory();
					return true;
				} catch (Exception var9) {
					var9.printStackTrace();
					return false;
				}
			} else {
				return true;
			}
		}
	}

	public static void moveCategoryHandl(String cat_id, String parent_id, String tree_position, int cat_level,
			String site_id, SettingLogsBean stl) {
		String position = tree_position + cat_id + "$";
		++cat_level;
		HashMap new_m = new HashMap();
		new_m.put("cat_id", cat_id);
		new_m.put("parent_id", parent_id);
		new_m.put("cat_position", position);
		new_m.put("cat_level", Integer.valueOf(cat_level));
		if (site_id != null && !"".equals(site_id)) {
			new_m.put("site_id", site_id);
		}

		if (CategoryDAO.moveCategory(new_m, stl)) {
			List c_list = getChildCategoryList(Integer.parseInt(cat_id), site_id);
			if (c_list != null && c_list.size() > 0) {
				for (int i = 0; i < c_list.size(); ++i) {
					moveCategoryHandl(String.valueOf(((CategoryBean) c_list.get(i)).getCat_id()), cat_id, position,
							cat_level, site_id, stl);
				}
			}
		}

	}

	public static String getSearchSQLByCategoryIDS(String user_id, String category_ids, String app_id, String site_id) {
		String conn = "";
		String conn_temp = "";
		String[] tempA = category_ids.split(",");
		if (tempA != null && !"".equals(tempA)) {
			for (int i = 0; i < tempA.length; ++i) {
				CategoryBean cb = getCategoryBeanCatID(Integer.parseInt(tempA[i]), site_id);
				if (cb != null) {
					if (cb.getWf_id() != 0) {
						int step_id = WorkFlowManager.getMaxStepIDByUserID(cb.getWf_id(), user_id, app_id, site_id);
						conn = conn + "or (ci.cat_id = " + tempA[i] + " and ci.step_id < " + step_id + ") ";
					} else {
						conn_temp = conn_temp + "," + tempA[i];
					}
				}
			}

			if (conn != null && !"".equals(conn)) {
				conn = conn.substring(2);
				conn = "(" + conn + ")";
			}

			if (conn_temp != "" && !"".equals(conn_temp)) {
				conn_temp = conn_temp.substring(1);
				conn_temp = " ci.cat_id in (" + conn_temp + ")";
				if (conn != null && !"".equals(conn)) {
					conn = "(" + conn + " or " + conn_temp + ")";
				} else {
					conn = conn_temp;
				}
			}
		}

		return " and " + conn;
	}

	public static boolean insertGKDefaultCategory(String node_id) {
		CategoryBean cgb = new CategoryBean();
		cgb.setSite_id(node_id);

		try {
			cgb.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_CATEGORY_TABLE_NAME));
			cgb.setCat_id(10);
			cgb.setCat_ename("gkzn");
			cgb.setCat_cname("公开指南");
			CategoryDAO.insertGKDefaultCategory(cgb);
			cgb.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_CATEGORY_TABLE_NAME));
			cgb.setCat_id(11);
			cgb.setCat_ename("gknb");
			cgb.setCat_cname("公开年报");
			CategoryDAO.insertGKDefaultCategory(cgb);
			cgb.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_CATEGORY_TABLE_NAME));
			cgb.setCat_id(12);
			cgb.setCat_ename("gkzy");
			cgb.setCat_cname("公开指引");
			CategoryDAO.insertGKDefaultCategory(cgb);
			reloadCategory();
			return true;
		} catch (Exception var3) {
			var3.printStackTrace();
			return false;
		}
	}

	public static boolean updateBaseCategoryTemplate(String templage_id) {
		if (CategoryDAO.updateBaseCategoryTemplate(templage_id)) {
			reloadCategory();
			return true;
		} else {
			return false;
		}
	}

	public static String getBaseCategoryTemplateListID() {
		return CategoryDAO.getBaseCategoryTemplateListID();
	}

	public static List<CategoryBean> getCategoryListBySiteIDPid(String site_id, int pid) {
		ArrayList list = new ArrayList();
		Set set = getSiteCategoryMap(site_id).keySet();
		Iterator var5 = set.iterator();

		while (var5.hasNext()) {
			int i = ((Integer) var5.next()).intValue();
			CategoryBean cgb = (CategoryBean) getSiteCategoryMap(site_id).get(Integer.valueOf(i));
			if (site_id.equals(cgb.getSite_id()) && cgb.getParent_id() == pid) {
				list.add(cgb);
			}
		}

		if (list != null && list.size() > 0) {
			Collections.sort(list, new CategoryComparator());
		}

		return list;
	}

	public static void main(String[] args) {
		System.out.println(getCategoryBeanCatID(1883, "HIWCMdemo").getCat_type());
	}

	public static class CategoryComparator implements Comparator<Object> {
		public CategoryComparator() {
		}

		public int compare(Object o1, Object o2) {
			CategoryBean cgb1 = (CategoryBean) o1;
			CategoryBean cgb2 = (CategoryBean) o2;
			return cgb1.getCat_sort() > cgb2.getCat_sort() ? 1 : (cgb1.getCat_sort() == cgb2.getCat_sort() ? 0 : -1);
		}
	}
}

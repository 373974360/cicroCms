//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.services.cms.count;

import com.cicro.wcm.bean.cms.category.CategoryBean;
import com.cicro.wcm.bean.cms.category.ZTCategoryBean;
import com.cicro.wcm.bean.cms.count.InfoAccessBean;
import com.cicro.wcm.bean.cms.info.HitsCountBean;
import com.cicro.wcm.bean.cms.info.InfoBean;
import com.cicro.wcm.bean.control.SiteBean;
import com.cicro.wcm.dao.cms.count.AccessCountDao;
import com.cicro.wcm.services.cms.category.CategoryManager;
import com.cicro.wcm.services.cms.category.ZTCategoryManager;
import com.cicro.wcm.services.cms.info.InfoBaseManager;
import com.cicro.wcm.services.control.site.SiteManager;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccessCountManager {
    public AccessCountManager() {
    }

    public static List<InfoAccessBean> getAccessCountsBySite(Map m) {
        List accbeanList = AccessCountDao.getAccessCountsBySite(m);
        if(accbeanList == null) {
            return null;
        } else {
            for(int i = 0; i < accbeanList.size(); ++i) {
                InfoAccessBean accCountBean = (InfoAccessBean)accbeanList.get(i);
                SiteBean sb = SiteManager.getSiteBeanBySiteID(accCountBean.getSite_id());
                accCountBean.setSite_name(sb.getSite_name());
                accCountBean.setCount(accCountBean.getIcount());
            }

            return accbeanList;
        }
    }

    public static List<InfoAccessBean> getSiteCountListByCate(Map mp) {
        ArrayList listResult = new ArrayList();

        try {
            List e = getSiteCountListByCate(mp);
            String site_id = (String)mp.get("site_id");
            SiteBean sb = SiteManager.getSiteBeanBySiteID(site_id);
            CategoryBean categoryBeanRoot = new CategoryBean();
            categoryBeanRoot.setCat_cname(sb.getSite_name());
            categoryBeanRoot.setCat_id(0);
            categoryBeanRoot.setSite_id(site_id);
            InfoAccessBean bean = getSiteChildListCate(categoryBeanRoot, e);
            listResult.add(bean);
            return listResult;
        } catch (Exception var7) {
            var7.printStackTrace();
            return listResult;
        }
    }

    private static InfoAccessBean getSiteChildListCate(CategoryBean categoryBean, List<InfoAccessBean> list) {
        InfoAccessBean InfoAccessBean = new InfoAccessBean();
        InfoAccessBean.setCat_id(categoryBean.getCat_id());
        List cateBeans = CategoryManager.getChildCategoryList(categoryBean.getCat_id(), categoryBean.getSite_id());
        if(cateBeans.size() != 0) {
            ArrayList accesscount = new ArrayList();
            int bean = 0;
            Iterator var7 = list.iterator();

            while(var7.hasNext()) {
                InfoAccessBean beanR = (InfoAccessBean)var7.next();
                if(InfoAccessBean.getCat_id() == beanR.getCat_id()) {
                    bean += beanR.getIcount();
                }
            }

            var7 = cateBeans.iterator();

            while(var7.hasNext()) {
                CategoryBean beanR1 = (CategoryBean)var7.next();
                if(beanR1.getCat_type() == 0) {
                    InfoAccessBean refBean = getSiteChildListCate(beanR1, list);
                    bean += refBean.getIcount();
                    accesscount.add(refBean);
                }
            }

            InfoAccessBean.setIcount(bean);
        } else {
            int accesscount1 = 0;
            Iterator beanR2 = list.iterator();

            while(beanR2.hasNext()) {
                InfoAccessBean bean1 = (InfoAccessBean)beanR2.next();
                if(InfoAccessBean.getCat_id() == bean1.getCat_id()) {
                    accesscount1 += bean1.getIcount();
                }
            }

            InfoAccessBean.setIcount(accesscount1);
        }

        return InfoAccessBean;
    }

    public static List<InfoAccessBean> getSiteCateAccessList(Map<String, String> mp) {
        int cat_id = Integer.parseInt((String)mp.get("cat_id"));
        String tmp_id = (String)mp.get("p_id");
        boolean p_id = false;
        String site_id = (String)mp.get("site_id");
        int var21;
        if(tmp_id != null) {
            var21 = Integer.parseInt(tmp_id);
        } else {
            var21 = 0;
        }

        List temp_cat_list = null;
        ArrayList ret = new ArrayList();
        List directSubNode = null;
        int bean;
        int j;
        int cid;
        int count;
        int n1;
        int var32;
        int var33;
        String var34;
        String[] var37;
        List var38;
        if(var21 == -1 && cat_id == -1) {
            InfoAccessBean var23 = null;
            List var28 = ZTCategoryManager.getZTCategoryList(site_id);
            int var29 = 0;

            for(var32 = 0; var32 < var28.size(); ++var32) {
                bean = ((ZTCategoryBean)var28.get(var32)).getZt_cat_id();
                temp_cat_list = CategoryManager.getZTCategoryListBySiteAndType(bean, site_id);

                for(var33 = 0; var33 < temp_cat_list.size(); ++var33) {
                    var34 = CategoryManager.getAllChildCategoryIDS(((CategoryBean)temp_cat_list.get(var33)).getCat_id(), site_id);
                    var37 = (String[])null;
                    if(var34 == "") {
                        var23 = new InfoAccessBean();
                        var23.setCat_id(((ZTCategoryBean)var28.get(var32)).getZt_cat_id());
                        var23.setCat_name(((ZTCategoryBean)var28.get(var32)).getZt_cat_name());
                        var23.setIcount(0);
                    } else {
                        var37 = var34.split(",");
                        mp.put("cat_id", var34);
                        var38 = AccessCountDao.getAccessInfoListsByCat_SiteId(mp);
                        if(var38.size() <= 0) {
                            var23 = new InfoAccessBean();
                            var23.setCat_id(((ZTCategoryBean)var28.get(var32)).getZt_cat_id());
                            var23.setCat_name(((ZTCategoryBean)var28.get(var32)).getZt_cat_name());
                            var23.setIcount(0);
                        } else {
                            for(j = 0; j < var38.size(); ++j) {
                                var23 = (InfoAccessBean)var38.get(j);
                                if(var23 != null) {
                                    cid = var23.getCat_id();
                                    count = var23.getIcount();

                                    for(n1 = 0; n1 < var37.length; ++n1) {
                                        if(Integer.parseInt(var37[n1]) == cid) {
                                            var29 += count;
                                        }
                                    }
                                }
                            }

                            var23.setCat_id(((ZTCategoryBean)var28.get(var32)).getZt_cat_id());
                            var23.setCat_name(((ZTCategoryBean)var28.get(var32)).getZt_cat_name());
                            var23.setIcount(var29);
                        }
                    }
                }

                ret.add(var23);
            }
        } else {
            List temp;
            int var25;
            if(var21 == -1 && cat_id != -1) {
                var25 = 0;
                temp_cat_list = CategoryManager.getZTCategoryListBySiteAndType(cat_id, site_id);
                InfoAccessBean var27 = null;

                for(var32 = 0; var32 < temp_cat_list.size(); ++var32) {
                    boolean var35 = CategoryManager.isHasChildNode(((CategoryBean)temp_cat_list.get(var32)).getCat_id(), site_id);
                    if(!var35) {
                        InfoAccessBean var36 = getAccessListBySiteCateId(mp);
                        if(var36 != null) {
                            var36.setCat_id(((CategoryBean)temp_cat_list.get(var32)).getCat_id());
                            var36.setCat_name(((CategoryBean)temp_cat_list.get(var32)).getCat_cname());
                            ret.add(var36);
                        }
                    } else {
                        directSubNode = CategoryManager.getChildCategoryList(((CategoryBean)temp_cat_list.get(var32)).getCat_id(), site_id);

                        for(var33 = 0; var33 < directSubNode.size(); ++var33) {
                            var34 = CategoryManager.getAllChildCategoryIDS(((CategoryBean)directSubNode.get(var33)).getCat_id(), site_id);
                            if(var34 != null && !"".equals(var34)) {
                                var34 = var34 + "," + ((CategoryBean)directSubNode.get(var33)).getCat_id();
                            } else {
                                var34 = String.valueOf(((CategoryBean)directSubNode.get(var33)).getCat_id());
                            }

                            mp.put("cat_id", var34);
                            temp = AccessCountDao.getAccessInfoListsByCat_SiteId(mp);
                            if(temp.size() <= 0) {
                                var27 = new InfoAccessBean();
                                var27.setCat_id(((CategoryBean)temp_cat_list.get(var32)).getCat_id());
                                var27.setCat_name(((CategoryBean)temp_cat_list.get(var32)).getCat_cname());
                                var27.setIcount(0);
                            } else {
                                String[] var39 = var34.split(",");

                                for(j = 0; j < temp.size(); ++j) {
                                    var27 = (InfoAccessBean)temp.get(j);
                                    cid = var27.getCat_id();
                                    count = var27.getIcount();

                                    for(n1 = 0; n1 < var39.length; ++n1) {
                                        if(Integer.parseInt(var39[n1]) == cid) {
                                            var25 += count;
                                        }
                                    }
                                }

                                var27.setCat_id(((CategoryBean)temp_cat_list.get(var32)).getCat_id());
                                var27.setCat_name(((CategoryBean)temp_cat_list.get(var32)).getCat_cname());
                                var27.setIcount(var25);
                            }
                        }

                        ret.add(var27);
                    }
                }
            } else if(cat_id == 0) {
                directSubNode = CategoryManager.getCategoryListBySiteID(site_id, 0);

                for(int isMinorNode = 0; isMinorNode < directSubNode.size(); ++isMinorNode) {
                    CategoryBean n = (CategoryBean)directSubNode.get(isMinorNode);
                    if(n != null || n.equals("null")) {
                        boolean cat = CategoryManager.isHasChildNode(n.getCat_id(), site_id);
                        InfoAccessBean hasOrNo;
                        if(!cat) {
                            mp.remove("cat_id");
                            mp.put("cat_id", String.valueOf(n.getCat_id()));
                            hasOrNo = getAccessListBySiteCateId(mp);
                            if(hasOrNo != null) {
                                hasOrNo.setCat_id(n.getCat_id());
                                hasOrNo.setCat_name(CategoryManager.getCategoryBean(n.getCat_id()).getCat_cname());
                            } else {
                                hasOrNo = new InfoAccessBean();
                                hasOrNo.setCat_id(n.getCat_id());
                                hasOrNo.setCat_name(n.getCat_cname());
                                hasOrNo.setIcount(0);
                            }

                            ret.add(hasOrNo);
                        } else {
                            hasOrNo = null;
                            bean = 0;
                            String icount2 = getAllLeafCateChildIDS(n, site_id).trim();
                            if(icount2 != null && icount2 != "") {
                                mp.remove("cat_id");
                                mp.put("cat_id", icount2);
                                String[] cat_ids = icount2.split(",");
                                temp = AccessCountDao.getAccessInfoListsByCat_SiteId(mp);
                                if(temp.size() <= 0) {
                                    hasOrNo = new InfoAccessBean();
                                    hasOrNo.setCat_id(n.getCat_id());
                                    hasOrNo.setCat_name(n.getCat_cname());
                                    hasOrNo.setIcount(0);
                                    ret.add(hasOrNo);
                                } else {
                                    for(int InfoList = 0; InfoList < temp.size(); ++InfoList) {
                                        hasOrNo = (InfoAccessBean)temp.get(InfoList);
                                        j = hasOrNo.getCat_id();
                                        cid = hasOrNo.getIcount();

                                        for(count = 0; count < cat_ids.length; ++count) {
                                            if(Integer.parseInt(cat_ids[count]) == j) {
                                                bean += cid;
                                            }
                                        }
                                    }

                                    hasOrNo.setCat_id(n.getCat_id());
                                    hasOrNo.setCat_name(n.getCat_cname());
                                    hasOrNo.setIcount(bean);
                                    ret.add(hasOrNo);
                                }
                            }
                        }
                    }
                }
            } else {
                boolean var22 = CategoryManager.isHasChildNode(cat_id, site_id);
                if(!var22) {
                    InfoAccessBean var24 = getAccessListBySiteCateId(mp);
                    if(var24 != null) {
                        var24.setCat_id(cat_id);
                        var24.setCat_name(CategoryManager.getCategoryBean(cat_id).getCat_cname());
                        ret.add(var24);
                    }
                } else {
                    directSubNode = CategoryManager.getChildCategoryList(cat_id, site_id);

                    for(var25 = 0; var25 < directSubNode.size(); ++var25) {
                        CategoryBean var26 = (CategoryBean)directSubNode.get(var25);
                        if(var26 != null || var26.equals("null")) {
                            boolean var31 = CategoryManager.isHasChildNode(var26.getCat_id(), site_id);
                            InfoAccessBean var30;
                            if(!var31) {
                                mp.remove("cat_id");
                                mp.put("cat_id", String.valueOf(var26.getCat_id()));
                                var30 = getAccessListBySiteCateId(mp);
                                if(var30 != null) {
                                    var30.setCat_id(var26.getCat_id());
                                    var30.setCat_name(CategoryManager.getCategoryBean(var26.getCat_id()).getCat_cname());
                                } else {
                                    var30 = new InfoAccessBean();
                                    var30.setCat_id(var26.getCat_id());
                                    var30.setCat_name(var26.getCat_cname());
                                    var30.setIcount(0);
                                }

                                ret.add(var30);
                            } else {
                                var30 = null;
                                var33 = 0;
                                var34 = getAllLeafCateChildIDS(var26, site_id).trim();
                                if(var34 != null && var34 != "") {
                                    mp.remove("cat_id");
                                    mp.put("cat_id", var34);
                                    var37 = var34.split(",");
                                    var38 = AccessCountDao.getAccessInfoListsByCat_SiteId(mp);
                                    if(var38.size() <= 0) {
                                        var30 = new InfoAccessBean();
                                        var30.setCat_id(var26.getCat_id());
                                        var30.setCat_name(var26.getCat_cname());
                                        var30.setIcount(0);
                                        ret.add(var30);
                                    } else {
                                        for(j = 0; j < var38.size(); ++j) {
                                            var30 = (InfoAccessBean)var38.get(j);
                                            cid = var30.getCat_id();
                                            count = var30.getIcount();

                                            for(n1 = 0; n1 < var37.length; ++n1) {
                                                if(Integer.parseInt(var37[n1]) == cid) {
                                                    var33 += count;
                                                }
                                            }
                                        }

                                        var30.setCat_id(var26.getCat_id());
                                        var30.setCat_name(var26.getCat_cname());
                                        var30.setIcount(var33);
                                        ret.add(var30);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return ret;
    }

    public static InfoAccessBean getAccessListBySiteCateId(Map<String, String> mp) {
        int icount = 0;
        String count = "";
        String site_id = (String)mp.get("site_id");
        String cat_id = (String)mp.get("cat_id");
        mp.remove("cat_id");
        String[] temp = (String[])null;
        InfoAccessBean bean = null;
        List InfoList = AccessCountDao.getAccessInfoListsByCat_SiteId(mp);
        if(InfoList != null) {
            for(int i = 0; i < InfoList.size(); ++i) {
                bean = (InfoAccessBean)InfoList.get(i);
                if(cat_id != null) {
                    CategoryBean cb2 = CategoryManager.getCategoryBean(bean.getCat_id());
                    temp = cat_id.split(",");

                    for(int j = 0; j < temp.length; ++j) {
                        CategoryBean cb1 = CategoryManager.getCategoryBean(Integer.parseInt(temp[j]));
                        if(Integer.parseInt(temp[j]) == cb2.getParent_id()) {
                            icount += bean.getIcount();
                        } else if(Integer.parseInt(temp[j]) == bean.getCat_id()) {
                            icount = bean.getIcount();
                        }
                    }

                    bean.setCat_name(cb2.getCat_cname());
                    bean.setIcount(icount);
                }
            }
        }

        return bean;
    }

    public static List<InfoAccessBean> getAccessInfoListsByCats(Map<String, String> mp) {
        int cat_id = Integer.parseInt((String)mp.get("cat_id"));
        String site_id = (String)mp.get("site_id");
        String ids = "";
        boolean isMinorNode = CategoryManager.isHasChildNode(cat_id, site_id);
        if(!isMinorNode) {
            return AccessCountDao.getAccessInfoLists(mp);
        } else {
            ids = CategoryManager.getAllChildCategoryIDS(cat_id, site_id);
            mp.put("cat_id", ids);
            return AccessCountDao.getAccessInfoLists(mp);
        }
    }

    public static InfoAccessBean getBeanByID(String info_id) {
        return AccessCountDao.getBeanByID(info_id);
    }

    public static String getAllLeafCateChildIDS(CategoryBean cgb, String site_id) {
        if(cgb != null) {
            boolean isSub = cgb.isIs_sub();
            String cat_position = cgb.getCat_position();
            Set set = CategoryManager.category_m.keySet();
            String cat_ids = "";
            Iterator var7 = set.iterator();

            while(var7.hasNext()) {
                int i = ((Integer)var7.next()).intValue();
                cgb = (CategoryBean)CategoryManager.category_m.get(Integer.valueOf(i));
                if(cgb.getCat_position().startsWith(cat_position) && !cat_position.equals(cgb.getCat_position()) && site_id.equals(cgb.getSite_id()) && !isSub) {
                    cat_ids = cat_ids + "," + cgb.getCat_id();
                }
            }

            if(cat_ids.length() > 0) {
                cat_ids = cat_ids.substring(1);
            }

            return cat_ids;
        } else {
            return null;
        }
    }

    public static String getCateChildByZt_cateid(CategoryBean cgb, String site_id) {
        if(cgb != null) {
            boolean isSub = cgb.isIs_sub();
            String cat_position = cgb.getCat_position();
            Set set = CategoryManager.category_m.keySet();
            String cat_ids = "";
            Iterator var7 = set.iterator();

            while(var7.hasNext()) {
                int i = ((Integer)var7.next()).intValue();
                cgb = (CategoryBean)CategoryManager.category_m.get(Integer.valueOf(i));
                if(cgb.getCat_position().startsWith(cat_position) && !cat_position.equals(cgb.getCat_position()) && site_id.equals(cgb.getSite_id()) && !isSub) {
                    cat_ids = cat_ids + "," + cgb.getCat_id();
                }
            }

            if(cat_ids.length() > 0) {
                cat_ids = cat_ids.substring(1);
            }

            return cat_ids;
        } else {
            return null;
        }
    }

    public static boolean deleteAccessCountInfos(Map m) {
        return AccessCountDao.deleteAccessCountInfo(m);
    }

    public static List<InfoAccessBean> getCatOrderListByCat_id(Map mp) {
        if(mp.get("row_count") == "" || mp.get("row_count") == null) {
            mp.put("row_count", Integer.valueOf(10));
        }

        List list = AccessCountDao.getCatOrderListByCat_id(mp);
        if(list != null) {
            for(int i = 0; i < list.size(); ++i) {
                InfoAccessBean accessbean = (InfoAccessBean)list.get(i);
                int cid = accessbean.getCat_id();
                int count = accessbean.getIcount();
                CategoryBean cb1 = CategoryManager.getCategoryBean(cid);
                ((InfoAccessBean)list.get(i)).setCat_name(cb1.getCat_cname());
                ((InfoAccessBean)list.get(i)).setCount(count);
            }
        }

        return list;
    }

    public static List<InfoAccessBean> getInfoOrderListByInfo_id(Map mp) {
        ArrayList l = new ArrayList();
        if(mp.get("row_count") == "" || mp.get("row_count") == null) {
            mp.put("row_count", Integer.valueOf(10));
        }

        List list = AccessCountDao.getInfoOrderListByInfo_id(mp);
        if(list != null) {
            for(int i = 0; i < list.size(); ++i) {
                InfoAccessBean accessbean = (InfoAccessBean)list.get(i);
                int info_id = accessbean.getInfo_id();
                int count = accessbean.getIcount();
                InfoBean b = InfoBaseManager.getInfoById(String.valueOf(info_id));
                if(b != null) {
                    ((InfoAccessBean)list.get(i)).setInfo_title(b.getTitle());
                    ((InfoAccessBean)list.get(i)).setCount(count);
                    l.add((InfoAccessBean)list.get(i));
                }
            }
        }

        return l;
    }

    public static Map<String, String> getDayAccessCountList(String site_id, String constant) {
        Object rs_mp = new HashMap();

        try {
            rs_mp = AccessCountDao.getDayAccessCountList(site_id, constant);
        } catch (ParseException var4) {
            var4.printStackTrace();
        }

        return (Map)rs_mp;
    }

    public static void main(String[] args) {
        HashMap mp = new HashMap();
        mp.put("cat_id", "0");
        mp.put("site_id", "HIWCMdemo");
        System.out.println(getAccessInfoListsByCats(mp));
    }
    
  //统计每个栏目的信息访问量
    public static List<HitsCountBean>  getHitsByCat(Map mp){
        List<HitsCountBean> list =  AccessCountDao.getHitsByCat(mp);
        if(list != null && list.size() > 0){
            for(HitsCountBean hcb : list){
                hcb.setCat_cname(CategoryManager.getCategoryBean(hcb.getCat_id()).getCat_cname());
            }
        }
        return list;
    }

    //统计每条信息的访问量
    public static List<HitsCountBean>  getHitsByInfo(Map mp){
        List<HitsCountBean> list =  AccessCountDao.getHitsByInfo(mp);
        if(list != null && list.size() > 0){
            for(HitsCountBean hcb : list){
                hcb.setCat_cname(CategoryManager.getCategoryBean(hcb.getCat_id()).getCat_cname());
            }
        }
        return list;
    }
}

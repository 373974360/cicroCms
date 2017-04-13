package com.cicro.project.dz_driver;


import com.cicro.util.FormatUtil;
import com.cicro.wcm.bean.template.TurnPageBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DriverViolationData {
    private static int cur_page = 1;
    private static int page_size = 15;

    public static void getDriverViolationSearchCon(String params, Map<String, String> con_map) {
        String orderby = "id desc";
        String[] tempA = params.split(";");
        for (int i = 0; i < tempA.length; i++) {
            if (tempA[i].toLowerCase().startsWith("kw=")) {
                String kw = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

                if ((!"".equals(kw)) && (!kw.startsWith("$kw")) && (FormatUtil.isValiditySQL(kw))) {
                    con_map.put("keyword", kw);
                }
            }
            if (tempA[i].toLowerCase().startsWith("name=")) {
                String name = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

                if ((!"".equals(name)) && (!name.startsWith("$name")) && (FormatUtil.isValiditySQL(name))) {
                    con_map.put("name", name);
                }
            }
            if (tempA[i].toLowerCase().startsWith("totle=")) {
                String totle = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

                if ((!"".equals(totle)) && (!totle.startsWith("$totle")) && (FormatUtil.isValiditySQL(totle))) {
                    con_map.put("totle", totle);
                }
            }
            if (tempA[i].toLowerCase().startsWith("totleend=")) {
                String totleend = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

                if ((!"".equals(totleend)) && (!totleend.startsWith("$totleend")) && (FormatUtil.isValiditySQL(totleend))) {
                    con_map.put("totleend", totleend);
                }
            }
            if (tempA[i].toLowerCase().startsWith("status=")) {
                String status = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

                if ((!"".equals(status)) && (!status.startsWith("$status")) && (FormatUtil.isValiditySQL(status))) {
                    con_map.put("status", status);
                }
            }
            if (tempA[i].toLowerCase().startsWith("orderby=")) {
                String o_by = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if ((!"".equals(o_by)) && (!o_by.startsWith("$orderby")) && (FormatUtil.isValiditySQL(o_by))) {
                    orderby = o_by;
                }
            }
            if (tempA[i].toLowerCase().startsWith("size=")) {
                String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
                    page_size = Integer.parseInt(ps);
            }
            if (tempA[i].toLowerCase().startsWith("cur_page=")) {
                String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
                    cur_page = Integer.parseInt(cp);
            }
        }
        con_map.put("page_size", page_size + "");
        con_map.put("current_page", cur_page + "");
        con_map.put("orderby", orderby);
    }

    public static TurnPageBean getDriverViolationCount(String params) {
        Map con_map = new HashMap();
        getDriverViolationSearchCon(params, con_map);

        TurnPageBean tpb = new TurnPageBean();
        tpb.setCount(Integer.parseInt(DriverViolationManager.getDriverViolationCount(con_map)));
        tpb.setCur_page(cur_page);
        tpb.setPage_size(page_size);
        tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);

        if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
            tpb.setPage_count(tpb.getPage_count() - 1);
        }
        if (cur_page > 1) {
            tpb.setPrev_num(cur_page - 1);
        }
        tpb.setNext_num(tpb.getPage_count());
        if (cur_page < tpb.getPage_count()) {
            tpb.setNext_num(cur_page + 1);
        }

        if (tpb.getPage_count() > 10) {
            if (cur_page > 5) {
                if (cur_page > tpb.getPage_count() - 4)
                    tpb.setCurr_start_num(tpb.getPage_count() - 6);
                else
                    tpb.setCurr_start_num(cur_page - 2);
            }
        }
        return tpb;
    }

    public static List<DriverViolationBean> getDriverViolationList(String params) {
        Map con_map = new HashMap();
        getDriverViolationSearchCon(params, con_map);
        int start_page = Integer.parseInt((String) con_map.get("current_page"));
        int page_size = Integer.parseInt((String) con_map.get("page_size"));
        con_map.put("start_num", Integer.valueOf((start_page - 1) * page_size));
        con_map.put("page_size", Integer.valueOf(page_size));
        return DriverViolationManager.getDriverViolationList(con_map);
    }

    public static List<DriverViolationBean> getAllDriverViolationList(String params) {
        return DriverViolationManager.getAllDriverViolationList();
    }

    public static List<DriverViolationBean> getDriverViolationHotList(String params) {
        Map con_map = new HashMap();
        getDriverViolationSearchCon(params, con_map);
        con_map.put("current_page", "1");
        int start_page = Integer.parseInt((String) con_map.get("current_page"));
        int page_size = Integer.parseInt((String) con_map.get("page_size"));
        con_map.put("start_num", Integer.valueOf((start_page - 1) * page_size));
        con_map.put("page_size", Integer.valueOf(page_size));
        return DriverViolationManager.getDriverViolationList(con_map);
    }

    public static DriverViolationBean getDriverViolationObject(String id) {
        return DriverViolationManager.getDriverViolationBean(id);
    }
    
    
    public static void getDriverCompanySearchCon(String params, Map<String, String> con_map) {
        String orderby = "id desc";
        String[] tempA = params.split(";");
        for (int i = 0; i < tempA.length; i++) {
            if (tempA[i].toLowerCase().startsWith("kw=")) {
                String kw = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

                if ((!"".equals(kw)) && (!kw.startsWith("$kw")) && (FormatUtil.isValiditySQL(kw))) {
                    con_map.put("keyword", kw);
                }
            }
            if (tempA[i].toLowerCase().startsWith("simpleName=")) {
                String simpleName = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

                if ((!"".equals(simpleName)) && (!simpleName.startsWith("$simpleName")) && (FormatUtil.isValiditySQL(simpleName))) {
                    con_map.put("simpleName", simpleName);
                }
            }
            if (tempA[i].toLowerCase().startsWith("status=")) {
                String status = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

                if ((!"".equals(status)) && (!status.startsWith("$status")) && (FormatUtil.isValiditySQL(status))) {
                    con_map.put("status", status);
                }
            }
            if (tempA[i].toLowerCase().startsWith("orderby=")) {
                String o_by = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if ((!"".equals(o_by)) && (!o_by.startsWith("$orderby")) && (FormatUtil.isValiditySQL(o_by))) {
                    orderby = o_by;
                }
            }
            if (tempA[i].toLowerCase().startsWith("size=")) {
                String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
                    page_size = Integer.parseInt(ps);
            }
            if (tempA[i].toLowerCase().startsWith("cur_page=")) {
                String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
                    cur_page = Integer.parseInt(cp);
            }
        }
        con_map.put("page_size", page_size + "");
        con_map.put("current_page", cur_page + "");
        con_map.put("orderby", orderby);
    }

    public static TurnPageBean getDriverCompanyCount(String params) {
        Map con_map = new HashMap();
        getDriverViolationSearchCon(params, con_map);

        TurnPageBean tpb = new TurnPageBean();
        tpb.setCount(Integer.parseInt(DriverCompanyManager.getDriverCompanyCount(con_map)));
        tpb.setCur_page(cur_page);
        tpb.setPage_size(page_size);
        tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);

        if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
            tpb.setPage_count(tpb.getPage_count() - 1);
        }
        if (cur_page > 1) {
            tpb.setPrev_num(cur_page - 1);
        }
        tpb.setNext_num(tpb.getPage_count());
        if (cur_page < tpb.getPage_count()) {
            tpb.setNext_num(cur_page + 1);
        }

        if (tpb.getPage_count() > 10) {
            if (cur_page > 5) {
                if (cur_page > tpb.getPage_count() - 4)
                    tpb.setCurr_start_num(tpb.getPage_count() - 6);
                else
                    tpb.setCurr_start_num(cur_page - 2);
            }
        }
        return tpb;
    }

    public static List<DriverCompanyBean> getDriverCompanyList(String params) {
        Map con_map = new HashMap();
        getDriverViolationSearchCon(params, con_map);
        int start_page = Integer.parseInt((String) con_map.get("current_page"));
        int page_size = Integer.parseInt((String) con_map.get("page_size"));
        con_map.put("start_num", Integer.valueOf((start_page - 1) * page_size));
        con_map.put("page_size", Integer.valueOf(page_size));
        return DriverCompanyManager.getDriverCompanyList(con_map);
    }

    public static DriverCompanyBean getDriverCompanyObject(String id) {
        return DriverCompanyManager.getDriverCompanyBean(id);
    }
    
    public static List<DriverViolationBean> getTotleDriverViolationList(String params) {
        Map con_map = new HashMap();
        getDriverViolationSearchCon(params, con_map);
        int start_page = Integer.parseInt((String) con_map.get("current_page"));
        int page_size = Integer.parseInt((String) con_map.get("page_size"));
        con_map.put("start_num", Integer.valueOf((start_page - 1) * page_size));
        con_map.put("page_size", Integer.valueOf(page_size));
        return DriverViolationManager.getTotleDriverViolationList(con_map);
    }
    
    public static TurnPageBean getTotleDriverViolationCount(String params) {
        Map con_map = new HashMap();
        getDriverViolationSearchCon(params, con_map);

        TurnPageBean tpb = new TurnPageBean();
        tpb.setCount(Integer.parseInt(DriverViolationManager.getTotleDriverViolationCount(con_map)));
        tpb.setCur_page(cur_page);
        tpb.setPage_size(page_size);
        tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);

        if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
            tpb.setPage_count(tpb.getPage_count() - 1);
        }
        if (cur_page > 1) {
            tpb.setPrev_num(cur_page - 1);
        }
        tpb.setNext_num(tpb.getPage_count());
        if (cur_page < tpb.getPage_count()) {
            tpb.setNext_num(cur_page + 1);
        }

        if (tpb.getPage_count() > 10) {
            if (cur_page > 5) {
                if (cur_page > tpb.getPage_count() - 4)
                    tpb.setCurr_start_num(tpb.getPage_count() - 6);
                else
                    tpb.setCurr_start_num(cur_page - 2);
            }
        }
        return tpb;
    }
}
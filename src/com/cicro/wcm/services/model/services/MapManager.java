//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.services.model.services;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import oracle.sql.CLOB;

public class MapManager {
    public MapManager() {
    }

    public static List<Map> listMapKeyValueToLow(List<Map> list) {
        ArrayList result = new ArrayList();
        Iterator var3 = list.iterator();

        while(var3.hasNext()) {
            Map map = (Map)var3.next();
            Map map2 = mapKeyValueToLow(map);
            result.add(map2);
        }

        return result;
    }

    public static Map mapKeyValueToLow(Map m) {
        if(m != null && m.size() > 0) {
            HashMap new_m = new HashMap();
            Iterator iter = m.entrySet().iterator();

            while(iter.hasNext()) {
                Entry entry = (Entry)iter.next();
                String key = (String)entry.getKey();
                Object val = entry.getValue();
                new_m.put(key.toLowerCase(), val);
            }

            return new_m;
        } else {
            return m;
        }
    }

    public static Map mapKeyToLowValueToString(Map m) {
        HashMap new_m = new HashMap();

        try {
            if(m != null && m.size() > 0) {
                String key;
                String value;
                for(Iterator e = m.entrySet().iterator(); e.hasNext(); new_m.put(key.toLowerCase(), value)) {
                    Entry entry = (Entry)e.next();
                    key = (String)entry.getKey();
                    Object object = entry.getValue();
                    value = "";
                    if(object instanceof BigDecimal) {
                        value = object.toString();
                    } else if(object instanceof CLOB) {
                        CLOB clob = (CLOB)object;
                        BufferedReader br = new BufferedReader(clob.getCharacterStream());

                        for(String str = ""; (str = br.readLine()) != null; value = value.concat(str)) {
                            ;
                        }
                    } else {
                        value = String.valueOf(object);
                    }
                }

                return new_m;
            } else {
                return m;
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            return new_m;
        }
    }

    public static Object getValue(Map map, String key) {
        try {
            Object e = map.get(key);
            if(e == null) {
                e = map.get(key.toLowerCase());
                if(e == null) {
                    e = map.get(key.toUpperCase());
                } else {
                    e = null;
                }
            }

            return e;
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("BB", "222");
        map.put("AA", "111");
        map.put("aa", "111");
        ArrayList list = new ArrayList();
        list.add(map);
        Iterator var4 = list.iterator();

        Map map1;
        while(var4.hasNext()) {
            map1 = (Map)var4.next();
            System.out.println(map1);
        }

        List list1 = listMapKeyValueToLow(list);
        var4 = list1.iterator();

        while(var4.hasNext()) {
            map1 = (Map)var4.next();
            System.out.println(map1);
        }

    }
}

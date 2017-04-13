//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.catchs;

import com.cicro.util.jconfig.JconfigUtilContainer;
import com.cicro.wcm.rmi.ISyncCatchRmi;
import com.cicro.wcm.rmi.conf.RmiServerConfBean;
import com.cicro.wcm.rmi.conf.RmiServerConfManager;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SyncCatchHandl {
    private static String ip = JconfigUtilContainer.bashConfig().getProperty("ip", "", "rmi_config");
    private static String port = JconfigUtilContainer.bashConfig().getProperty("port", "", "rmi_config");

    public SyncCatchHandl() {
    }

    public static void reladCatchForRMI(String class_name) {
        List rsc = RmiServerConfManager.getAllRmiServerConf();
        if(rsc != null && rsc.size() > 0) {
            Iterator var3 = rsc.iterator();

            while(var3.hasNext()) {
                RmiServerConfBean rscb = (RmiServerConfBean)var3.next();
                String rscb_ip = rscb.getIp();
                String rscb_port = rscb.getPort();

                try {
                    InitialContext e = new InitialContext();
                    String path = "rmi://" + rscb_ip + ":" + rscb_port + "/catchRmi";
                    ISyncCatchRmi iscr = (ISyncCatchRmi)e.lookup(path);
                    iscr.reloadCateh(class_name);
                    System.out.println(path + "------------" + class_name);
                } catch (NamingException var9) {
                    System.out.println("rmi " + ip + " error");
                    var9.printStackTrace();
                } catch (RemoteException var10) {
                    System.out.println("rmi " + ip + " error");
                    var10.printStackTrace();
                }
            }
        }

    }
}

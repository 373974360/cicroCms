//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.startup;

import com.cicro.util.DebugLog;
import com.cicro.util.FormatUtil;
import com.cicro.util.jconfig.JconfigFactory;
import com.cicro.util.jconfig.JconfigUtil;
import com.cicro.util.jconfig.JconfigUtilContainer;
import com.cicro.wcm.catchs.ISyncCatchImpl;
import com.cicro.wcm.dao.org.rmi.OrgRmiImpl;
import com.cicro.wcm.rmi.file.FileRmiImpl;
import com.cicro.wcm.server.LicenseCheck;
import com.cicro.wcm.services.control.rmi.SiteRmiImpl;
import com.cicro.wcm.services.model.services.CustomFormRMIImpl;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerListener implements ServletContextListener {
    String rmiIp = null;
    String rmiPort = null;
    public static boolean isLicenseExist = true;
    public static boolean isLicenseRight = false;

    public ServerListener() throws IOException {
        this.rmiIp = JconfigUtilContainer.bashConfig().getProperty("ip", "", "rmi_config");
        this.rmiPort = JconfigUtilContainer.bashConfig().getProperty("port", "", "rmi_config");
    }

    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.setProperty("java.rmi.server.hostname", this.rmiIp);
            LocateRegistry.createRegistry(Integer.parseInt(this.rmiPort));
            InitialContext e = new InitialContext();
            this.registerRMI(e);
            JconfigUtil bc = JconfigFactory.getJconfigUtilInstance("startListener");
            String[] class_arr = bc.getPropertyNamesByCategory("startloadclass");
            if(class_arr != null && class_arr.length > 0) {
                DebugLog.info("tomcat startup load classes begin");
                int i = class_arr.length;

                while(true) {
                    if(i <= 0) {
                        DebugLog.info("tomcat startup load classes end");
                        break;
                    }

                    try {
                        System.out.println("class_arr---" + i + "   " + class_arr[i - 1] + "   " + bc.getProperty(class_arr[i - 1], "", "startloadclass"));
                        Class.forName(bc.getProperty(class_arr[i - 1], "", "startloadclass"));
                        DebugLog.info(bc.getProperty(class_arr[i - 1], "", "startloadclass"));
                    } catch (Exception var7) {
                        var7.printStackTrace();
                    }

                    --i;
                }
            }

            this.checkLicense();
        } catch (Exception var8) {
            var8.printStackTrace(System.out);
        }

    }

    public void registerRMI(Context namingContext) throws RemoteException {
        SiteRmiImpl siteRmi = new SiteRmiImpl();
        this.registerRMIServer(namingContext, "siteRmi", siteRmi, "站点");
        OrgRmiImpl orgRmi = new OrgRmiImpl();
        this.registerRMIServer(namingContext, "orgRmi", orgRmi, "部门");
        ISyncCatchImpl catchRmi = new ISyncCatchImpl();
        this.registerRMIServer(namingContext, "catchRmi", catchRmi, "缓存");
        FileRmiImpl fileRmi = new FileRmiImpl();
        this.registerRMIServer(namingContext, "fileRmi", fileRmi, "文件");
        CustomFormRMIImpl customRmi = new CustomFormRMIImpl();
        this.registerRMIServer(namingContext, "customRmi", customRmi, "资源库调用");
    }

    public void registerRMIServer(Context namingContext, String rmiName, Object o, String desc) {
        try {
            namingContext.rebind("rmi://" + this.rmiIp + ":" + this.rmiPort + "/" + rmiName, o);
            System.out.println("注册" + desc + "rmi服务成功");
        } catch (Exception var6) {
            System.out.println("注册" + desc + "rmi服务失败");
            var6.printStackTrace();
        }

    }

    private void checkLicense() {
        System.out.println("checkLicense----------begin-----");
        /*
        if(LicenseCheck.isLicenseExist()) {
            if(!LicenseCheck.check()) {
                System.out.println("KEY IS NOT RIGHT");

                try {
                    isLicenseExist = false;
                    isLicenseRight = false;
                } catch (Exception var2) {
                    System.out.println(var2);
                }
            } else {
                isLicenseExist = true;
                isLicenseRight = true;
            }
        } else {
            isLicenseExist = false;
            isLicenseRight = false;
        }
        */
        
        if(!LicenseCheck.check()) {
            System.out.println("KEY IS NOT RIGHT");
            try {
                isLicenseExist = false;
                isLicenseRight = false;
            } catch (Exception var2) {
                System.out.println(var2);
            }
        } else {
            isLicenseExist = true;
            isLicenseRight = true;
        }
        System.out.println("checkLicense----------end-----");
    }

    private void shutdownTomcat() throws IOException {
        String tomcatPath = JconfigUtilContainer.bashConfig().getProperty("path", "", "application_server_path");
        String[] windowsCommand = new String[]{FormatUtil.formatPath(tomcatPath + "/bin/shutdown.bat"), ""};
        String[] linuxCommand = new String[]{JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path") + "/bin/stopas.sh", ""};
        String os = System.getProperty("os.name");
        String[] command;
        if(os.startsWith("Windows")) {
            command = windowsCommand;
        } else {
            if(!os.startsWith("Linux")) {
                throw new IOException("Unknown operating system: " + os);
            }

            command = linuxCommand;
        }

        Process process = Runtime.getRuntime().exec(command);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}

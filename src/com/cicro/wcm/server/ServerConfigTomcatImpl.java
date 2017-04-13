//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.server;

import com.cicro.util.FormatUtil;
import com.cicro.util.io.FileOperation;
import com.cicro.util.jconfig.JconfigUtilContainer;
import com.cicro.util.xml.XmlManager;
import com.cicro.wcm.server.IServerConfig;
import com.cicro.wcm.server.ServerManager;
import java.util.Date;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ServerConfigTomcatImpl implements IServerConfig {
    private static final String asConfigFile = FormatUtil.formatPath(ServerManager.getServerRoot() + "/conf/myHosts.xml");
    private static String DEFAULT_HOST_TEMPLATE = "<Host className=\"org.apache.catalina.core.StandardHost\" autoDeploy=\"true\" configClass=\"org.apache.catalina.startup.ContextConfig\" contextClass=\"org.apache.catalina.core.StandardContext\" debug=\"0\" deployXML=\"true\" errorReportValveClass=\"org.apache.catalina.valves.ErrorReportValve\" liveDeploy=\"true\" name=\"$domain$\" unpackWARs=\"true\" appBase=\"$site-path$\"></Host>";
    private String backupFilePath = "";

    public ServerConfigTomcatImpl() {
    }

    public String getType() {
        return "tomcat";
    }

    public String addSite(Map infos) {
        String domain = (String)infos.get("site_domain");
        String sitePath = FormatUtil.formatPath((String)infos.get("site_path"));
        sitePath = sitePath.substring(0, sitePath.length() - 4);
        if(sitePath.endsWith("/")) {
            sitePath = sitePath.substring(0, sitePath.length() - 1);
        }

        try {
            String ex = FormatUtil.formatPath(JconfigUtilContainer.bashConfig().getProperty("path", "", "manager_path"));
            String host_content = DEFAULT_HOST_TEMPLATE;
            if(ServerManager.isWindows()) {
                if(sitePath.endsWith("\\")) {
                    sitePath = sitePath.substring(0, sitePath.length() - 1);
                }

                System.out.println("addSite------------" + sitePath);
                sitePath = sitePath.replaceAll("\\\\", "\\\\\\\\");
                ex = ex.replaceAll("\\\\", "\\\\\\\\");
            }

            host_content = host_content.replaceAll("\\$domain\\$", domain);
            host_content = host_content.replaceAll("\\$site-path\\$", sitePath);
            host_content = host_content.replaceAll("\\$manager_path\\$", ex);
            this.backupConfFile();
            FileOperation.writeStringToFile(asConfigFile, host_content, true);
            return "NO_ERROR";
        } catch (Exception var10) {
            this.restoreConfFile();
            var10.printStackTrace(System.out);
            return "ERROR_WHEN_WRITE_SERVER_CONFIG";
        }
    }

    public String updateSite(Map infos) {
        String old_site_domain = "name=\"" + (String)infos.get("old_site_domain") + "\"";
        String new_site_domain = "name=\"" + (String)infos.get("new_site_domain") + "\"";
        String alices_old_site_domain = "<Alias>" + (String)infos.get("old_site_domain") + "</Alias>";
        String alices_new_site_domain = "<Alias>" + (String)infos.get("new_site_domain") + "</Alias>";

        try {
            String ex = FileOperation.readFileToString(asConfigFile);
            ex = ex.replaceAll(old_site_domain, new_site_domain).replaceAll(alices_old_site_domain, alices_new_site_domain);
            this.backupConfFile();
            FileOperation.writeStringToFile(asConfigFile, ex, false);
            return "NO_ERROR";
        } catch (Exception var7) {
            this.restoreConfFile();
            var7.printStackTrace(System.out);
            return "ERROR_WHEN_WRITE_SERVER_CONFIG";
        }
    }

    public String delSite(Map infos) {
        String sitePath = FormatUtil.formatPath((String)infos.get("site_path"));
        if(ServerManager.isWindows()) {
            sitePath = sitePath.replaceAll("\\\\", "\\\\\\\\");
        }

        try {
            Document ex = XmlManager.createDOMByFile(asConfigFile);
            String xpath = "Host[./@appBase=\'" + sitePath.substring(0, sitePath.length() - 5) + "\']";
            Node hostNode = XmlManager.queryNode(ex, xpath);
            if(hostNode != null) {
                XmlManager.removeNode(hostNode);
                this.backupConfFile();
                FileOperation.writeStringToFile(asConfigFile, XmlManager.node2String(ex), false);
            }

            return "NO_ERROR";
        } catch (Exception var6) {
            this.restoreConfFile();
            var6.printStackTrace(System.out);
            return "ERROR_WHEN_WRITE_SERVER_CONFIG";
        }
    }

    public String reset(Map infos) {
        return "NO_ERROR";
    }

    public String addAlias(Map infos) {
        String site_id = (String)infos.get("site_id");
        String sitePath = FormatUtil.formatPath((String)infos.get("site_path"));
        String domain = (String)infos.get("site_domain");
        ServerManager.isWindows();
        if(site_id != null && site_id.trim().length() != 0) {
            if(domain != null && (domain = domain.trim().toLowerCase()).length() != 0) {
                try {
                    Document e = XmlManager.createDOMByFile(asConfigFile);
                    String xpath = "Host[./@appBase=\'" + sitePath.substring(0, sitePath.length() - 5) + "\']";
                    Node hostNode = XmlManager.queryNode(e, xpath);
                    if(hostNode != null) {
                        Node aliasNode = XmlManager.createNode("<Alias>" + domain + "</Alias>");
                        XmlManager.insertNode(hostNode, aliasNode);
                        this.backupConfFile();
                        FileOperation.writeStringToFile(asConfigFile, XmlManager.node2String(e), false);
                        return "NO_ERROR";
                    } else {
                        return "SITE_HOST_TAG_NOT_FOUND";
                    }
                } catch (Exception var9) {
                    var9.printStackTrace();
                    this.restoreConfFile();
                    return "ERROR_WHEN_ADD_ALIAS";
                }
            } else {
                return "DOMAIN_ID_CAN_NOT_BE_NULL";
            }
        } else {
            return "SITE_ID_CAN_NOT_BE_NULL";
        }
    }

    public String delAlias(Map infos) {
        String site_id = (String)infos.get("site_id");
        String domain = (String)infos.get("site_domain");
        if(site_id != null && site_id.trim().length() != 0) {
            if(domain != null && (domain = domain.trim().toLowerCase()).length() != 0) {
                try {
                    Document e = XmlManager.createDOMByFile(asConfigFile);
                    String xpath = "//Alias[text()=\'" + domain + "\']";
                    Node aliasNode = XmlManager.queryNode(e, xpath);
                    if(aliasNode != null) {
                        XmlManager.removeNode(aliasNode);
                        this.backupConfFile();
                        FileOperation.writeStringToFile(asConfigFile, XmlManager.node2String(e), false);
                    }

                    return "NO_ERROR";
                } catch (Exception var7) {
                    this.restoreConfFile();
                    var7.printStackTrace(System.out);
                    return "ERROR_WHEN_DELETE_ALIAS";
                }
            } else {
                return "DOMAIN_ID_CAN_NOT_BE_NULL";
            }
        } else {
            return "SITE_ID_CAN_NOT_BE_NULL";
        }
    }

    private void backupConfFile() {
        try {
            this.backupFilePath = asConfigFile + (new Date()).getTime();
            FileOperation.copyFile(asConfigFile, this.backupFilePath, true);
        } catch (Exception var2) {
            var2.printStackTrace(System.out);
        }

    }

    private void restoreConfFile() {
        try {
            FileOperation.copyFile(this.backupFilePath, asConfigFile, true);
        } catch (Exception var2) {
            var2.printStackTrace(System.out);
        }

    }

    public static void main(String[] args) {
    }
}

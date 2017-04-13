//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.timer;

import com.cicro.util.quartz.CicroTaskScheduler;
import com.cicro.util.quartz.FormatRssCronExpression;
import org.quartz.Job;

public class StartUpTask {
    private static final String groupName = "timerTask";

    static {
        startupHandl();
    }

    public StartUpTask() {
    }

    public static String getGroupName() {
        return "timerTask";
    }

    public static void startupHandl() {
        try {
            Job e = (Job)Class.forName("com.cicro.wcm.timer.TimerTaskJob").newInstance();
            CicroTaskScheduler.addCornTask("wcm_timer", "timerTask", e, FormatRssCronExpression.formatCronExp("1m"));
            Job cl_d = (Job)Class.forName("com.cicro.wcm.timer.TimerTaskJobForDay").newInstance();
            CicroTaskScheduler.addCornTask("wcm_timer_day", "timerTask_day", cl_d, "0 0 0 * * ?");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}

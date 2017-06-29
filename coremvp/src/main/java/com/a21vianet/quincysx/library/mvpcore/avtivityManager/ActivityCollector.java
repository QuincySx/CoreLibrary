package com.a21vianet.quincysx.library.mvpcore.avtivityManager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wang.rongqiang on 2017/1/4.
 * Activity队列 管理Activity
 */

public class ActivityCollector {
    private static List<Activity> sActivityList = Collections.synchronizedList(new
            ArrayList<Activity>());

    public static void addActivity(Activity activity) {
        sActivityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        sActivityList.remove(activity);
    }

    /**
     * 获取队列最顶层Activity
     *
     * @return 栈顶Activity
     */
    public static Activity getTopActivity() {
        if (sActivityList.isEmpty()) {
            return null;
        } else {
            return sActivityList.get(sActivityList.size() - 1);
        }
    }

    /**
     * 关闭所有Activity
     */
    public static void finishAll() {
        synchronized (sActivityList) {
            for (int i = sActivityList.size() - 1; i >= 0; i--) {
                sActivityList.get(i).finish();
                sActivityList.remove(i);
            }
        }
    }
}

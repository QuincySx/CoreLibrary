package com.a21vianet.quincysx.corelibrary.avtivityManager;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by wang.rongqiang on 2017/1/4.
 * Activity队列 管理Activity
 */

public final class ActivityCollector {

    private static Stack<Activity> sActivityStack = new Stack();

    public static void addActivity(Activity activity) {
        sActivityStack.add(activity);
    }

    public static void removeActivity(Activity activity) {
        sActivityStack.remove(activity);
    }

    /**
     * 获取队列最顶层Activity
     *
     * @return 栈顶Activity
     */
    public static Activity getCurrentActivity() {
        if (sActivityStack.isEmpty()) {
            return null;
        } else {
            return sActivityStack.lastElement();
        }
    }

    /**
     * 关闭所有Activity
     */
    public static void finishAllActivity() {
        synchronized (sActivityStack) {
            for (int i = sActivityStack.size() - 1; i >= 0; i--) {
                if (sActivityStack.get(i) != null) {
                    sActivityStack.get(i).finish();
                }
            }
            sActivityStack.clear();
        }
    }
}

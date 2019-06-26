package com.ilifesmart.model;

import android.util.Log;

public class PersonBeanJava {
    public static final String TAG = "PersonBeanJava";

    public static void testDemo() {
        PersonBean2 bean = new PersonBean2("Jack", 30, true);
        Log.d(TAG, "testDemo: age " + bean.getAge());
        Log.d(TAG, "testDemo: name " + bean.getName());
        Log.d(TAG, "testDemo: isMale " + bean.isMale());

        bean.setMale(false);
    }
}

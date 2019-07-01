package com.ilifesmart.java;

import android.util.Log;
import com.ilifesmart.strings.StringFunction;

public class Test {
    public static final String TAG = "TEST_JAVA";

    public static void main() {
        int result = StringFunction.maxInt(1, 2);
        Log.d(TAG, "main: result " + result);

        char lastChar = StringFunction.lastChar("Kotlin");
        Log.d(TAG, "main: last " + lastChar);

        Log.d(TAG, "main: ATOMIC_STEPCOUNT " + StringFunction.getATOMIC_INTEGER());
    }
}

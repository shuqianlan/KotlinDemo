package com.ilifesmart.java;

import android.util.Log;
import com.ilifesmart.kotlin.State;
import com.ilifesmart.kotlin.View2;
import org.jetbrains.annotations.NotNull;

public class Button implements View2 {
    public static final String TAG = "JavaButton";

    @NotNull
    @Override
    public State getCurrentState() {
        Log.d(TAG, "getCurrentState: ");
        return new ButtonState();
    }

    @Override
    public void restoreState(@NotNull State state) {

    }

    public class ButtonState implements State {

    }
}

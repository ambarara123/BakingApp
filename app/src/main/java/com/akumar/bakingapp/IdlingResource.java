package com.akumar.bakingapp;

import android.support.annotation.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

public class IdlingResource implements android.support.test.espresso.IdlingResource{
    @Nullable
    private volatile ResourceCallback callback;

    private AtomicBoolean atomicBoolean = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return atomicBoolean.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }

    public void setIdleState(boolean isIdleNow) {
        atomicBoolean.set(isIdleNow);
        if (isIdleNow && callback != null) {
            callback.onTransitionToIdle();
        }
    }
}

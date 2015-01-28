package com.corp.rialzista.clickerapp;

/**
 * Created by Константин on 28.01.2015.
 */
public class Counter {

    private String mTitle;
    private int mCounterValue;

    public Counter(String title, int startValue) {
        this.mTitle = title;
        this.mCounterValue = startValue;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setCounterValue(int value) {
        this.mCounterValue = value;
    }

    public int getCounterValue() {
        return this.mCounterValue;
    }

}

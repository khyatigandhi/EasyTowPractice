package com.knoxpo.khyati.easytowpractice.models;

public class SideItem {

    private long mId;
    private String mTitle;

    public SideItem(long id, String title){
        mId  = id;
        mTitle = title;
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }
}

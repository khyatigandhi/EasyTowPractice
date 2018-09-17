package com.knoxpo.khyati.easytowpractice.models;

import java.util.ArrayList;

public class SideItemLab {

    public static final long
            SIDE_ITEM_FOOD_ID = 0,
            SIDE_ITEM_SPA_ID = 1;

    private static SideItemLab sInstance;
    public static SideItemLab getInstance() {
        if(sInstance== null){
            sInstance = new SideItemLab();
        }
        return sInstance;
    }

    private ArrayList<SideItem> mSideItems;

    private SideItemLab(){
        mSideItems = new ArrayList<>();
        mSideItems.add(new SideItem(SIDE_ITEM_FOOD_ID, "Food"));
        mSideItems.add(new SideItem(SIDE_ITEM_SPA_ID, "Spa"));
    }

    public ArrayList<SideItem> getSideItems(){
        return mSideItems;
    }

    public int getSize(){
        return mSideItems.size();
    }
}

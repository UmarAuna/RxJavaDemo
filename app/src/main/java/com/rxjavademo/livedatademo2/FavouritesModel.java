package com.rxjavademo.livedatademo2;

public class FavouritesModel {
    public long mId, mDate;
    public String mUrl;

    public FavouritesModel(long mId, String mUrl, long mDate) {
        this.mId = mId;
        this.mDate = mDate;
        this.mUrl = mUrl;
    }

    public FavouritesModel(FavouritesModel favouritesModel){
        mId = favouritesModel.mId;
        mUrl = favouritesModel.mUrl;
        mDate = favouritesModel.mDate;
    }


}

package com.rxjavademo.Dagger2Demo3MVP.di.module;


import com.rxjavademo.Dagger2Demo3MVP.adapter.RecyclerViewAdapter;
import com.rxjavademo.Dagger2Demo3MVP.di.scopes.ActivityScope;
import com.rxjavademo.Dagger2Demo3MVP.ui.Dagger2Demo3MVP;

import dagger.Module;
import dagger.Provides;

@Module (includes = {Dagger2Demo3MVPContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getCoinList(RecyclerViewAdapter.ClickListener clickListener){
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(Dagger2Demo3MVP dagger2Demo3MVP){
        return dagger2Demo3MVP;
    }
}

package com.merlin.training_mvvm.ui.activity.main.fragment.video_list.paging;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.merlin.training_mvvm.domain.models.Incident;

public class ItemDataSourceFactory extends DataSource.Factory<Integer, Incident> {

    private MutableLiveData<PageKeyedDataSource<Integer, Incident>> itemLiveDataSource = new MutableLiveData<>();
    private MutableLiveData<Pair<Integer,String>> networkStateLiveData = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Incident> create() {
        ItemDataSource itemDataSource = new ItemDataSource(networkStateLiveData);
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Incident>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }

    public MutableLiveData<Pair<Integer,String>> getNetworkStateLiveData() {
        return networkStateLiveData;
    }
}
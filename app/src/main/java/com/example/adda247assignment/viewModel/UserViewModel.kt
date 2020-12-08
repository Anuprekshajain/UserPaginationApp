package com.example.adda247assignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.adda247assignment.model.Data
import com.example.adda247assignment.model.UserDataSource
import com.example.adda247assignment.model.UserDataSourceFactory

class UserViewModel : ViewModel() {

    var userPagedList: LiveData<PagedList<Data>>
    private var liveDataSource: LiveData<UserDataSource>

    init {
        val itemDataSourceFactory = UserDataSourceFactory()
        liveDataSource = itemDataSourceFactory.userLiveDataSource

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(UserDataSource.PAGE_SIZE)
            .build()

        userPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }
}
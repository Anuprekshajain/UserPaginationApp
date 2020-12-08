package com.example.adda247assignment.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class UserDataSourceFactory : DataSource.Factory<Int, Data>() {

    val userLiveDataSource = MutableLiveData<UserDataSource>()

    override fun create(): DataSource<Int, Data> {
        val userDataSource = UserDataSource()
        userLiveDataSource.postValue(userDataSource)
        return userDataSource
    }
}
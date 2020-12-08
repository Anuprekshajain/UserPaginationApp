package com.example.adda247assignment.model

import androidx.paging.PageKeyedDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataSource : PageKeyedDataSource<Int, Data>() {

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Data>) {
        val service = ApiServiceBuilder.buildService(RestApiInterface::class.java)
        val call = service.fetchUser(params.key)

        call?.enqueue(object : Callback<UserResponse?> {

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.data
                    val key = if (params.key > 1) params.key - 1 else 0
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {

            }
        })
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Data>
    ) {

        val service = ApiServiceBuilder.buildService(RestApiInterface::class.java)
        val call = service.fetchUser(FIRST_PAGE)
        call.enqueue(object : Callback<UserResponse?> {

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.data

                    responseItems?.let {
                        callback.onResult(responseItems, null, FIRST_PAGE + 1)
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
            }
        })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Data>) {
        val service = ApiServiceBuilder.buildService(RestApiInterface::class.java)
        val call = service.fetchUser(params.key)
        call.enqueue(object : Callback<UserResponse?> {

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.data
                    val key = params.key + 1
                    responseItems?.let {
                        callback.onResult(responseItems, key)

                    }
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
            }
        })

    }

    companion object {
        const val PAGE_SIZE = 20
        const val FIRST_PAGE = 1
    }

}
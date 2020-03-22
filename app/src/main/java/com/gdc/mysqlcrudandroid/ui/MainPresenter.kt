package com.gdc.mysqlcrudandroid.ui

import android.util.Log
import com.gdc.mysqlcrudandroid.data.ApiService
import com.gdc.mysqlcrudandroid.model.PostResponse
import com.gdc.mysqlcrudandroid.model.User
import com.gdc.mysqlcrudandroid.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val view: MainContract.View): MainContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun insertUser(name: String, age: String, address: String) {
        ApiService.theCrudApi.insertUser(name,age, address)
                .enqueue(object : Callback<PostResponse> {
                    override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                        if (response.isSuccessful) {
                            Log.d("_logCrud", "Response: " + response.body())
                            view.onInsertResult(response.body()!!)
                        } else {
                            Log.d("_logCrud", "Error: " + response.message())
                        }
                    }
                    override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                        Log.d("_logCrud", "Fail: " + t.message)
                    }
                })
    }

    override fun showUser() {
        view.onLoading(true)
        ApiService.theCrudApi.getUser()
                .enqueue(object : Callback<UserResponse> {
                    override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                        view.onLoading(false)
                        if (response.isSuccessful) {
                            Log.d("_logCrud", "Response: " + response.body())
                            view.onShowResult(response.body()!!)
                        } else {
                            Log.d("_logCrud", "Error: " + response.message())
                        }
                    }
                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        view.onLoading(false)
                        Log.d("_logCrud", "Fail: " + t.message)
                    }
                })
    }

    override fun updateUser(id: String, name: String, age: String, address: String) {
        ApiService.theCrudApi.updateuser(id, name,age, address)
                .enqueue(object : Callback<PostResponse> {
                    override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                        if (response.isSuccessful) {
                            Log.d("_logCrud", "Response: " + response.body())
                            view.onInsertResult(response.body()!!)
                        } else {
                            Log.d("_logCrud", "Error: " + response.message())
                        }
                    }
                    override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                        Log.d("_logCrud", "Fail: " + t.message)
                    }
                })
    }

    override fun deleteUser(id: String) {
        ApiService.theCrudApi.deleteUser(id)
                .enqueue(object : Callback<PostResponse> {
                    override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                        if (response.isSuccessful) {
                            Log.d("_logCrud", "Response: " + response.body())
                            view.onDeleteResult(response.body()!!)
                        } else {
                            Log.d("_logCrud", "Error: " + response.message())
                        }
                    }
                    override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                        Log.d("_logCrud", "Fail: " + t.message)
                    }
                })
    }

}
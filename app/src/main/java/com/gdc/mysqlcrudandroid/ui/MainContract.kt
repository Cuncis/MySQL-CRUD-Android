package com.gdc.mysqlcrudandroid.ui

import com.gdc.mysqlcrudandroid.model.PostResponse
import com.gdc.mysqlcrudandroid.model.User
import com.gdc.mysqlcrudandroid.model.UserResponse

interface MainContract {

    interface Presenter {
        fun insertUser(name: String, age: String, address: String)
        fun showUser()
        fun updateUser(id: String, name: String, age: String, address: String)
        fun deleteUser(id: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onShowResult(response: UserResponse)
        fun onInsertResult(response: PostResponse)
        fun onUpdateResult(response: PostResponse)
        fun onDeleteResult(response: PostResponse)
        fun onLoading(loading: Boolean)
        fun showMessage(message: String)
    }

}
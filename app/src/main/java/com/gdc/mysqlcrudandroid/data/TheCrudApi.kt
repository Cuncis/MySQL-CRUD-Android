package com.gdc.mysqlcrudandroid.data

import com.gdc.mysqlcrudandroid.model.PostResponse
import com.gdc.mysqlcrudandroid.model.User
import com.gdc.mysqlcrudandroid.model.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface TheCrudApi {

    @GET("read.php")
    fun getUser(): Call<UserResponse>

    @POST("insert.php")
    @FormUrlEncoded
    fun insertUser(
            @Field("nama") name: String,
            @Field("usia") age: String,
            @Field("domisili") address: String
    ): Call<PostResponse>

    @POST("update.php")
    @FormUrlEncoded
    fun updateuser(
            @Field("id") id: String,
            @Field("nama") name: String,
            @Field("usia") age: String,
            @Field("domisili") address: String
    ): Call<PostResponse>

    @POST("delete.php")
    @FormUrlEncoded
    fun deleteUser(
            @Field("id") id: String
    ): Call<PostResponse>


}
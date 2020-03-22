package com.gdc.mysqlcrudandroid.model


import com.google.gson.annotations.SerializedName


data class UserResponse(

	@SerializedName("result")
	val result: List<User>? = null,

	@SerializedName("kode")
	val kode: Int? = null
)
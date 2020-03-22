package com.gdc.mysqlcrudandroid.model


import com.google.gson.annotations.SerializedName

data class PostResponse(

	@SerializedName("pesan")
	val pesan: String? = null,

	@SerializedName("kode")
	val kode: Int? = null
)
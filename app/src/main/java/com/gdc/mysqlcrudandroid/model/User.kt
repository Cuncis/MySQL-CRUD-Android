package com.gdc.mysqlcrudandroid.model


import com.google.gson.annotations.SerializedName


data class User(

	@SerializedName("usia")
	var usia: String? = null,

	@SerializedName("nama")
	var nama: String? = null,

	@SerializedName("id")
    var id: String? = null,

	@SerializedName("domisili")
	var domisili: String? = null
)
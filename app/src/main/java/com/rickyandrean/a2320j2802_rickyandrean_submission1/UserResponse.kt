package com.rickyandrean.a2320j2802_rickyandrean_submission1

import com.google.gson.annotations.SerializedName

data class UserResponseSearch (
 	@field:SerializedName("items")
 	val items: ArrayList<UserResponseItem>
)

data class UserResponseItem(
	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,
)

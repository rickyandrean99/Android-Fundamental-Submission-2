package com.rickyandrean.a2320j2802_rickyandrean_submission1

import com.google.gson.annotations.SerializedName

data class UserResponseItem(
    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,
)

data class UserResponseSearch(
    @field:SerializedName("items")
    val items: ArrayList<UserResponseItem>
)

data class UserDetail(
    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("location")
    val location: String,

    @field:SerializedName("company")
    val company: String,

    @field:SerializedName("followers")
    val followers: Int,

    @field:SerializedName("following")
    val following: Int,

    @field:SerializedName("public_repos")
    val repositories: Int,
)

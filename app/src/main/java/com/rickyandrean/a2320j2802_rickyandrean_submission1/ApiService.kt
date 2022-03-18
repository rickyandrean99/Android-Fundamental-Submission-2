package com.rickyandrean.a2320j2802_rickyandrean_submission1

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("users")
    fun getUserList(): Call<ArrayList<UserResponseItem>>

    @GET("search/users")
    fun searchUser(@Query("q") username: String): Call<UserResponseSearch>

    @GET("users/{username}")
    fun getUserDetail(@Path("username") username: String): Call<UserDetail>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<ArrayList<UserResponseItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<UserResponseItem>>
}
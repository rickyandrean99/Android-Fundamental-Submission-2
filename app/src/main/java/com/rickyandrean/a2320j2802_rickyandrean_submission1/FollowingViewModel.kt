package com.rickyandrean.a2320j2802_rickyandrean_submission1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel: ViewModel() {
    private val _following = MutableLiveData<ArrayList<UserResponseItem>>()

    val following: LiveData<ArrayList<UserResponseItem>> = _following
    var callFirstTime: Boolean = true

    fun loadFollowing(username: String) {
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object: Callback<ArrayList<UserResponseItem>> {
            override fun onResponse(call: Call<ArrayList<UserResponseItem>>, response: Response<ArrayList<UserResponseItem>>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _following.value = responseBody!!
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<UserResponseItem>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "FollowingFragment"
    }
}
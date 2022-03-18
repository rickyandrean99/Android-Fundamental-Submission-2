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
    private val _loading = MutableLiveData<Boolean>()

    val following: LiveData<ArrayList<UserResponseItem>> = _following
    val loading: LiveData<Boolean> = _loading
    var callFirstTime: Boolean = true

    fun loadFollowing(username: String) {
        _loading.value = true

        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object: Callback<ArrayList<UserResponseItem>> {
            override fun onResponse(call: Call<ArrayList<UserResponseItem>>, response: Response<ArrayList<UserResponseItem>>) {
                _loading.value = false

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
                _loading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "FollowingFragment"
    }
}
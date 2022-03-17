package com.rickyandrean.a2320j2802_rickyandrean_submission1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    private val _user = MutableLiveData<UserDetail?>()
    private val _loading = MutableLiveData<Boolean>()

    val user: LiveData<UserDetail?> = _user
    val loading: LiveData<Boolean> = _loading

    fun loadDetailUser(username: String){
        _loading.value = true

        val client = ApiConfig.getApiService().getUserDetail(username)
        client.enqueue(object: Callback<UserDetail> {
            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    _user.value = responseBody
                    _loading.value = false
                }
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "DetailViewModel"
    }
}
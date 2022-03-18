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
    var callFirstTime: Boolean = true
    lateinit var username: String

    fun loadDetailUser(username: String){
        _loading.value = true

        val client = ApiConfig.getApiService().getUserDetail(username)
        client.enqueue(object: Callback<UserDetail> {
            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                _loading.value = false

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _user.value = responseBody
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                _loading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "DetailViewModel"
    }
}
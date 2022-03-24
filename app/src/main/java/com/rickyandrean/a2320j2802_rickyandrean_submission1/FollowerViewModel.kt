package com.rickyandrean.a2320j2802_rickyandrean_submission1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel : ViewModel() {
    private val _followers = MutableLiveData<ArrayList<UserResponseItem>>()
    private val _loading = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<Event<String>>()

    val followers: LiveData<ArrayList<UserResponseItem>> = _followers
    val loading: LiveData<Boolean> = _loading
    val error: LiveData<Event<String>> = _error
    var callFirstTime: Boolean = true

    fun loadFollowers(username: String) {
        _loading.value = true

        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<ArrayList<UserResponseItem>> {
            override fun onResponse(
                call: Call<ArrayList<UserResponseItem>>,
                response: Response<ArrayList<UserResponseItem>>
            ) {
                _loading.value = false

                if (response.isSuccessful) {
                    _followers.value = response.body()
                } else {
                    _error.value = Event("Error: Failed to get data")
                }
            }

            override fun onFailure(call: Call<ArrayList<UserResponseItem>>, t: Throwable) {
                _loading.value = false
                _error.value = Event("Error: Failed to get data")
            }
        })
    }
}
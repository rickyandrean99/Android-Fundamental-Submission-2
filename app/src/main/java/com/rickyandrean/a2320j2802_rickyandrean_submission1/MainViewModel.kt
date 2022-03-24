package com.rickyandrean.a2320j2802_rickyandrean_submission1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _users = MutableLiveData<ArrayList<UserResponseItem>>()
    private val _loading = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<Event<String>>()

    val users: LiveData<ArrayList<UserResponseItem>> = _users
    val loading: LiveData<Boolean> = _loading
    val error: LiveData<Event<String>> = _error

    init {
        this.loadUsers()
    }

    fun loadUsers() {
        _loading.value = true

        val client = ApiConfig.getApiService().getUserList()
        client.enqueue(object : Callback<ArrayList<UserResponseItem>> {
            override fun onResponse(
                call: Call<ArrayList<UserResponseItem>>,
                response: Response<ArrayList<UserResponseItem>>
            ) {
                _loading.value = false

                if (response.isSuccessful) {
                    _users.value = response.body()
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

    fun searchUsers(name: String) {
        _loading.value = true

        val client = ApiConfig.getApiService().searchUser(name)
        client.enqueue(object : Callback<UserResponseSearch> {
            override fun onResponse(
                call: Call<UserResponseSearch>,
                response: Response<UserResponseSearch>
            ) {
                _loading.value = false

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _users.value = responseBody.items
                    }
                } else {
                    _error.value = Event("Error: Failed to get data")
                }
            }

            override fun onFailure(call: Call<UserResponseSearch>, t: Throwable) {
                _loading.value = false
                _error.value = Event("Error: Failed to get data")
            }
        })
    }
}
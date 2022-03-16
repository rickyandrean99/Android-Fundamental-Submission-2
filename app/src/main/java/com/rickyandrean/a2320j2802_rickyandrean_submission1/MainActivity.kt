package com.rickyandrean.a2320j2802_rickyandrean_submission1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rickyandrean.a2320j2802_rickyandrean_submission1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchData()

        val listUserAdapter = UserAdapter(userList)

        binding.rvUsers.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listUserAdapter
        }
    }

    private fun fetchData() {
        val dataUsername = resources.getStringArray(R.array.username)
        val dataName = resources.getStringArray(R.array.name)
        val dataLocation = resources.getStringArray(R.array.location)
        val dataRepository = resources.getStringArray(R.array.repository)
        val dataCompany = resources.getStringArray(R.array.company)
        val dataFollowers = resources.getStringArray(R.array.followers)
        val dataFollowing = resources.getStringArray(R.array.following)
        val dataAvatar = resources.obtainTypedArray(R.array.avatar)

        for (i in dataUsername.indices) {
            val user = User(
                dataUsername[i],
                dataName[i],
                dataLocation[i],
                dataRepository[i].toInt(),
                dataCompany[i],
                dataFollowers[i].toInt(),
                dataFollowing[i].toInt(),
                dataAvatar.getResourceId(i, -1)
            )
            userList.add(user)
        }
    }
}
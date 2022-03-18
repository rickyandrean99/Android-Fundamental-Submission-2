package com.rickyandrean.a2320j2802_rickyandrean_submission1

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.rickyandrean.a2320j2802_rickyandrean_submission1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private var open: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        mainViewModel.users.observe(this, { users ->
            binding.rvUsers.adapter = UserAdapter(users)
        })

        mainViewModel.loading.observe(this, {
            showLoading(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView

        with(searchView) {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            queryHint = resources.getString(R.string.search)

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    Log.d("result", "tembak submit")
                    mainViewModel.searchUsers(query)
                    clearFocus()

                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    // 'open' variable used so that when the user open search view for the first time,
                    // the app will not load again the mainViewModel.loadUsers().
                    // The mainViewModel.loadUsers() will execute again after user typing something
                    // and then remove the text until empty or clear it.
                    // I used this way because onQueryTextSubmit can't detect empty string
                    Log.d("result", "tembak change")
                    if (newText.isEmpty() && open) {
                        Log.d("result", "tembak load")
                        mainViewModel.loadUsers()
                    } else if (newText.isEmpty()) {
                        Log.d("result", "tembak awal") // Jika rotate dan dijalankan, ini jalan terus diawal
                        open = true
                    }

                    return false
                }
            })
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = super.onOptionsItemSelected(item)

    private fun showLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
                rvUsers.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                rvUsers.visibility = View.VISIBLE
            }
        }
    }
}
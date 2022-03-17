package com.rickyandrean.a2320j2802_rickyandrean_submission1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.rickyandrean.a2320j2802_rickyandrean_submission1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(USERNAME).toString()

        detailViewModel.loadDetailUser(username)
        supportActionBar?.title = username
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailViewModel.user.observe(this, { user ->
            Glide.with(this)
                .load(user?.avatarUrl)
                .circleCrop()
                .into(binding.imgDetailAvatar)

            with(binding) {
                tvDetailName.text = user?.name ?: "(no name)"
                tvDetailLocation.text = user?.location ?: "(no location)"
                tvDetailCompany.text = user?.company ?: "(no company)"
                tvDetailRepository.text = StringBuilder().append(user?.repositories ?: 0).append("\n repositories")
                tvDetailFollowers.text = StringBuilder().append(user?.followers ?: 0).append("\n Followers")
                tvDetailFollowing.text = StringBuilder().append(user?.following ?: 0).append("\n Following")
            }
        })

        detailViewModel.loading.observe(this, {
            showLoading(it)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Make sure user click back button. I got this number by logging
        // the item.itemId.toString because I don't know the id of back button
        if (item.itemId.toString() == "16908332") {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBarDetail.visibility = View.VISIBLE
        } else {
            binding.progressBarDetail.visibility = View.GONE
        }
    }

    companion object {
        const val USERNAME = "username"
    }
}
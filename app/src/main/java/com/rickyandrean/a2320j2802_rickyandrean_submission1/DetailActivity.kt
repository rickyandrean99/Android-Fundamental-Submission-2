package com.rickyandrean.a2320j2802_rickyandrean_submission1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.rickyandrean.a2320j2802_rickyandrean_submission1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(detailViewModel) {
            if (callFirstTime) {
                username = intent.getStringExtra(USERNAME).toString()
                loadDetailUser(detailViewModel.username)
                callFirstTime = false
            }
        }

        supportActionBar?.title = detailViewModel.username
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
                tvDetailRepository.text =
                    StringBuilder().append(user?.repositories ?: 0).append("\n repositories")
                tvDetailFollowers.text =
                    StringBuilder().append(user?.followers ?: 0).append("\n Followers")
                tvDetailFollowing.text =
                    StringBuilder().append(user?.following ?: 0).append("\n Following")
            }

            val viewPager = binding.viewPager
            val tabs = binding.tabs
            viewPager.adapter = SectionPagerAdapter(this)
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TITLES[position])
            }.attach()
        })

        detailViewModel.loading.observe(this, {
            showLoading(it)
        })

        detailViewModel.error.observe(this, {
            it.handler()?.let { error ->
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Make sure user click back button. I got this number by logging
        // the item.itemId.toString because I don't know the id of back button
        if (item.itemId.toString() == BACK_BUTTON) finish()

        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) {
                progressBarDetail.visibility = View.VISIBLE
                tabs.visibility = View.GONE
                viewPager.visibility = View.GONE
            } else {
                progressBarDetail.visibility = View.GONE
                tabs.visibility = View.VISIBLE
                viewPager.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        const val USERNAME = "username"
        const val BACK_BUTTON = "16908332"

        @StringRes
        private val TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }
}
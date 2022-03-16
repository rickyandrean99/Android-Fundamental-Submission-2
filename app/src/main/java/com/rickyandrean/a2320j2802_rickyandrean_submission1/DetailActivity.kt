package com.rickyandrean.a2320j2802_rickyandrean_submission1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.rickyandrean.a2320j2802_rickyandrean_submission1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER)

        binding.btnDetailBack.setOnClickListener(this)

        Glide.with(this)
            .load(user?.avatar)
            .circleCrop()
            .into(binding.imgDetailAvatar)

        with(binding) {
            tvDetailUsername.text = user?.username
            tvDetailName.text = user?.name
            tvDetailLocation.text = user?.location
            tvDetailCompany.text = user?.company
            tvDetailRepository.text = StringBuilder().append(user?.repository).append("\n repositories")
            tvDetailFollowers.text = StringBuilder().append(user?.followers).append("\n Followers")
            tvDetailFollowing.text = StringBuilder().append(user?.following).append("\n Following")
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_detail_back) finish()
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}
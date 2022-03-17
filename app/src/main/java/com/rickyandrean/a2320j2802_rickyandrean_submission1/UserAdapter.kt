package com.rickyandrean.a2320j2802_rickyandrean_submission1

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rickyandrean.a2320j2802_rickyandrean_submission1.databinding.ItemRowUserBinding

class UserAdapter(private val listUser: ArrayList<UserResponseItem>) :
    RecyclerView.Adapter<UserAdapter.ListViewHolder>() {
    class ListViewHolder(var binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.tvItemUsername.text = listUser[position].login

        with(holder.itemView) {
            Glide.with(context)
                .load(listUser[position].avatarUrl)
                .circleCrop()
                .into(holder.binding.imgItemAvatar)

            setOnClickListener {
                val detailUserIntent = Intent(context, DetailActivity::class.java)
                detailUserIntent.putExtra(DetailActivity.USERNAME, listUser[position].login)
                context.startActivity(detailUserIntent)
            }
        }
    }

    override fun getItemCount(): Int = listUser.size
}
package com.rickyandrean.a2320j2802_rickyandrean_submission1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rickyandrean.a2320j2802_rickyandrean_submission1.databinding.FragmentFollowingBinding

class FollowingFragment : Fragment() {
    private val followingViewModel by viewModels<FollowingViewModel>()
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFollowing.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
        }

        with(followingViewModel) {
            if (callFirstTime) {
                loadFollowing(
                    requireActivity().intent.getStringExtra(DetailActivity.USERNAME).toString()
                )
                callFirstTime = false
            }
        }

        followingViewModel.following.observe(requireActivity(), {
            binding.rvFollowing.adapter = UserAdapter(it)
        })

        followingViewModel.loading.observe(requireActivity(), {
            showLoading(it)
        })

        followingViewModel.error.observe(requireActivity(), {
            it.handler()?.let { error ->
                Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBarFollowing.visibility = View.VISIBLE
        } else {
            binding.progressBarFollowing.visibility = View.GONE
        }
    }
}
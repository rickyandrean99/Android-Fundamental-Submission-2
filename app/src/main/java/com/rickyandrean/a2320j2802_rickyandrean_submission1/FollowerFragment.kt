package com.rickyandrean.a2320j2802_rickyandrean_submission1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rickyandrean.a2320j2802_rickyandrean_submission1.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private val followerViewModel by viewModels<FollowerViewModel>()
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFollower.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
        }

        with(followerViewModel) {
            if (callFirstTime) {
                loadFollowers(
                    requireActivity().intent.getStringExtra(DetailActivity.USERNAME).toString()
                )
                callFirstTime = false
            }
        }

        followerViewModel.followers.observe(requireActivity(), {
            binding.rvFollower.adapter = UserAdapter(it)
        })

        followerViewModel.loading.observe(requireActivity(), {
            showLoading(it)
        })

        followerViewModel.error.observe(requireActivity(), {
            it.handler()?.let { error ->
                Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBarFollower.visibility = View.VISIBLE
        } else {
            binding.progressBarFollower.visibility = View.GONE
        }
    }
}
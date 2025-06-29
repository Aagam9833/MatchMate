package com.aagamshah.matchmate.presentation.profilelistactivity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aagamshah.matchmate.common.Resource
import com.aagamshah.matchmate.databinding.ActivityUserListBinding
import com.aagamshah.matchmate.domain.model.ProfileModel
import com.aagamshah.matchmate.presentation.adapter.ProfileAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding
    private val profileListViewModel: ProfileListViewModel by viewModels()
    private val profileListAdapter = ProfileAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUI()
        setObserver()

    }

    private fun setObserver() {
        profileListViewModel.users.observe(this) { result: Resource<List<ProfileModel>> ->
            when (result) {
                is Resource.Error -> {
                    binding.pbLoader.visibility = View.GONE
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Success -> {
                    binding.pbLoader.visibility = View.GONE
                    setData(result.data)
                }
            }
        }
    }

    private fun setData(data: List<ProfileModel>) {
        profileListAdapter.updateData(data)
    }

    private fun setUI() {
        enableEdgeToEdge()
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvProfiles.adapter = profileListAdapter

    }
}
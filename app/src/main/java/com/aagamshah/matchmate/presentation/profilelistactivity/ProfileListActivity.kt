package com.aagamshah.matchmate.presentation.profilelistactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aagamshah.matchmate.common.Resource
import com.aagamshah.matchmate.common.hasInternetConnection
import com.aagamshah.matchmate.databinding.ActivityUserListBinding
import com.aagamshah.matchmate.domain.model.ProfileModel
import com.aagamshah.matchmate.presentation.adapter.ProfileAdapter
import com.aagamshah.matchmate.presentation.mainactivity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding
    private val profileListViewModel: ProfileListViewModel by viewModels()
    private val profileListAdapter = ProfileAdapter(mutableListOf()) { id, isAccepted ->
        profileListViewModel.saveProfileChoice(id, isAccepted)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUI()
        setObserver()
        callApi()

    }

    private fun callApi() {
        if (this.hasInternetConnection()) {
            profileListViewModel.fetchProfiles()
        } else {
            profileListViewModel.fetchOfflineProfiles()
        }
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
                    profileListViewModel.storeProfilesOffline(result.data)
                    setData(result.data)
                }
            }
        }

        profileListViewModel.isPreferenceCleared.observe(this) {
            if (it) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
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

        binding.mbChangePref.setOnClickListener {
            profileListViewModel.clearPreference()
        }

    }
}
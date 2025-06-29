package com.aagamshah.matchmate.presentation.mainactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aagamshah.matchmate.R
import com.aagamshah.matchmate.data.remote.Gender
import com.aagamshah.matchmate.databinding.ActivityMainBinding
import com.aagamshah.matchmate.presentation.profilelistactivity.ProfileListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUI()
        setObserver()

    }

    private fun setObserver() {
        mainViewModel.isPreferenceSelected.observe(this) { result ->
            if (result) {
                startActivity(Intent(this, ProfileListActivity::class.java))
                finish()
            }
        }
    }

    private fun setUI() {
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.mbSave.setOnClickListener {
            val gender = when (binding.rgGender.checkedRadioButtonId) {
                R.id.rb_male -> Gender.male
                R.id.rb_female -> Gender.female
                else -> null
            }
            val ageText = binding.etAge.text.toString().trim()

            mainViewModel.saveUserPreference(gender, ageText) { success, message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                if (success) {
                    startActivity(Intent(this, ProfileListActivity::class.java))
                    finish()
                }
            }
        }

    }
}
package com.aagamshah.matchmate.presentation.mainactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.matchmate.data.remote.Gender
import com.aagamshah.matchmate.domain.model.UserPreferenceModel
import com.aagamshah.matchmate.domain.repository.UserPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPreferenceRepository: UserPreferenceRepository
) : ViewModel() {

    private val _isPreferenceSelected = MutableLiveData<Boolean>()
    val isPreferenceSelected: LiveData<Boolean> = _isPreferenceSelected

    init {
        fetchPreference()
    }

    private fun fetchPreference() {
        viewModelScope.launch {
            val result = userPreferenceRepository.getPreference()

            if (result != null) {
                _isPreferenceSelected.postValue(true)
            } else {
                _isPreferenceSelected.postValue(false)
            }

        }
    }


    fun saveUserPreference(gender: Gender?, ageText: String, onResult: (Boolean, String) -> Unit) {
        val age = ageText.toIntOrNull()
        when {
            gender == null -> onResult(false, "Please select a gender")
            age == null || age < 18 -> onResult(false, "Enter valid age above 18")
            else -> {
                viewModelScope.launch {
                    userPreferenceRepository.savePreference(UserPreferenceModel(gender, age))
                    onResult(true, "Saved successfully")
                }
            }
        }
    }
}
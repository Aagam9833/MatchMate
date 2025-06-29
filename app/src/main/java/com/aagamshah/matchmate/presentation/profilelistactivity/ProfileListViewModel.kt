package com.aagamshah.matchmate.presentation.profilelistactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.matchmate.common.Resource
import com.aagamshah.matchmate.domain.model.ProfileModel
import com.aagamshah.matchmate.domain.model.UserPreferenceModel
import com.aagamshah.matchmate.domain.repository.ProfileRepository
import com.aagamshah.matchmate.domain.repository.UserPreferenceRepository
import com.aagamshah.matchmate.domain.usecase.CalculateMatchScoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileListViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val userPreferenceRepository: UserPreferenceRepository,
    private val calculateMatchScoreUseCase: CalculateMatchScoreUseCase
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<ProfileModel>>>()
    val users: LiveData<Resource<List<ProfileModel>>> = _users

    private val _isPreferenceCleared = MutableLiveData<Boolean>()
    val isPreferenceCleared: LiveData<Boolean> = _isPreferenceCleared

    fun fetchProfiles() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = profileRepository.fetchProfiles()
            if (result is Resource.Success) {
                val prefs = userPreferenceRepository.getPreference()
                val updated = prefs?.let { calculateMatchScore(result.data, it) } ?: result.data
                _users.postValue(Resource.Success(updated))
            } else {
                _users.postValue(result)
            }
        }
    }

    fun storeProfilesOffline(profiles: List<ProfileModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.saveProfiles(profiles)
        }
    }

    fun saveProfileChoice(id: String, isAccepted: Boolean?) {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.updateProfileChoice(id, isAccepted)
        }
    }

    fun fetchOfflineProfiles() {
        viewModelScope.launch(Dispatchers.IO) {
            val localProfiles = profileRepository.fetchOfflineProfiles()
            val prefs = userPreferenceRepository.getPreference()
            val updated = prefs?.let { calculateMatchScore(localProfiles, it) } ?: localProfiles
            _users.postValue(Resource.Success(updated))
        }
    }

    fun clearPreference() {
        viewModelScope.launch {
            userPreferenceRepository.deletePreference()
            _isPreferenceCleared.postValue(true)
        }
    }

    private fun calculateMatchScore(
        profiles: List<ProfileModel>,
        prefs: UserPreferenceModel
    ): List<ProfileModel> {
        return profiles.map {
            it.copy(matchScore = calculateMatchScoreUseCase(it, prefs))
        }
    }

}
package com.aagamshah.matchmate.presentation.profilelistactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.matchmate.common.Resource
import com.aagamshah.matchmate.domain.model.ProfileModel
import com.aagamshah.matchmate.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileListViewModel @Inject constructor(
    private val userRepository: ProfileRepository
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<ProfileModel>>>()
    val users: LiveData<Resource<List<ProfileModel>>> = _users

    init {
        fetchUser()
    }

    private fun fetchUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = userRepository.fetchUsers()
            _users.postValue(result)
        }
    }

}
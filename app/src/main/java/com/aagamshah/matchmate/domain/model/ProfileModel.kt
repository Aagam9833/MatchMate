package com.aagamshah.matchmate.domain.model

import com.aagamshah.matchmate.data.remote.Dob
import com.aagamshah.matchmate.data.remote.Name
import com.aagamshah.matchmate.data.remote.Picture

data class ProfileModel(
    val gender: String,
    val name: Name,
    val dob: Dob,
    val registered: Dob,
    val picture: Picture,
    val nationality: String,
    val city: String,
    val id: String,
    var isAccepted: Boolean? = null,
    val matchScore: Int = 0
)
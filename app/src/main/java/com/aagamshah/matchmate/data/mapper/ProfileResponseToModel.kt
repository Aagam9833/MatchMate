package com.aagamshah.matchmate.data.mapper

import com.aagamshah.matchmate.data.remote.Result
import com.aagamshah.matchmate.domain.model.ProfileModel

fun Result.toProfileModel(): ProfileModel {
    return ProfileModel(
        gender = gender.name,
        name = name,
        dob = dob,
        registered = registered,
        picture = picture,
        nationality = nat,
        location = location
    )
}

fun List<Result>.toProfileList(): List<ProfileModel> = map { it.toProfileModel() }
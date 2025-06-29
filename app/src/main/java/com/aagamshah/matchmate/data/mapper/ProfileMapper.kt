package com.aagamshah.matchmate.data.mapper

import com.aagamshah.matchmate.data.local.entity.ProfileEntity
import com.aagamshah.matchmate.data.remote.Dob
import com.aagamshah.matchmate.data.remote.Name
import com.aagamshah.matchmate.data.remote.Picture
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
        city = location.city,
        id = login.uuid
    )
}

fun List<Result>.toProfileList(): List<ProfileModel> = map { it.toProfileModel() }

fun ProfileModel.toEntity(): ProfileEntity {
    return ProfileEntity(
        id = id,
        gender = gender,
        title = name.title,
        firstName = name.first,
        lastName = name.last,
        dob = dob.date,
        age = dob.age,
        registered = registered.date,
        pictureUrl = picture.large,
        nationality = nationality,
        city = city,
        isAccepted = isAccepted
    )
}

fun ProfileEntity.toDomain(): ProfileModel {
    return ProfileModel(
        id = id,
        gender = gender,
        name = Name(title, firstName, lastName),
        dob = Dob(dob, age),
        registered = Dob(registered, 0), // Assuming 0 if not stored
        picture = Picture(pictureUrl, pictureUrl, pictureUrl),
        nationality = nationality,
        city = city,
        isAccepted = isAccepted
    )
}

fun List<ProfileModel>.toEntityList(): List<ProfileEntity> {
    return map { it.toEntity() }
}

fun List<ProfileEntity>.toDomainList(): List<ProfileModel> {
    return map { it.toDomain() }
}
package com.aagamshah.matchmate.data.mapper

import com.aagamshah.matchmate.data.local.entity.UserPreferenceEntity
import com.aagamshah.matchmate.domain.model.UserPreferenceModel

fun UserPreferenceEntity.toDomain(): UserPreferenceModel =
    UserPreferenceModel(genderPreference, age, country)

fun UserPreferenceModel.toEntity(): UserPreferenceEntity =
    UserPreferenceEntity(0, genderPreference, age, country)
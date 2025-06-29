package com.aagamshah.matchmate.domain.usecase

import com.aagamshah.matchmate.domain.model.ProfileModel
import com.aagamshah.matchmate.domain.model.UserPreferenceModel
import javax.inject.Inject

class CalculateMatchScoreUseCase @Inject constructor() {

    operator fun invoke(profile: ProfileModel, prefs: UserPreferenceModel): Int {
        var score = 0

        if (!profile.gender.equals(prefs.genderPreference.name, ignoreCase = true)) {
            score += 25
        }

        if (profile.nationality.equals(prefs.country.name, ignoreCase = true)) {
            score += 25
        }

        val ageDiff = kotlin.math.abs(profile.dob.age - prefs.age)
        score += when {
            ageDiff <= 3 -> 50
            ageDiff <= 10 -> 25
            else -> 0
        }

        return score
    }
}

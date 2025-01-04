package com.untouchable.everytime.university.controller.data

import com.untouchable.everytime.university.service.data.UniversityData

data class UniversitySummary(
    val id: Long,
    val name: String
) {
    companion object {
        fun from(universityData: UniversityData): UniversitySummary {
            return UniversitySummary(
                id = universityData.id,
                name = universityData.name
            )
        }
    }
}

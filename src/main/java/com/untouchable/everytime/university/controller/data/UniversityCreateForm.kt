package com.untouchable.everytime.university.controller.data

import com.untouchable.everytime.university.domain.UniversityLink

data class UniversityCreateForm(
    val name: String,
    val address: String,
    val zipCode: String,
    val tell: String? = null,
    val fax: String? = null,
    val pages: Set<UniversityLink> = setOf()
)

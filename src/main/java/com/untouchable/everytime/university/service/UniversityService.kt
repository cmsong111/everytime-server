package com.untouchable.everytime.university.service

import com.untouchable.everytime.university.controller.data.UniversityCreateForm
import com.untouchable.everytime.university.domain.University
import com.untouchable.everytime.university.repository.UniversityJpaRepository
import com.untouchable.everytime.university.service.data.UniversityData
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UniversityService(
    private val universityJpaRepository: UniversityJpaRepository
) {

    @Transactional(readOnly = true)
    fun findUniversity(
        name: String?,
        pageable: Pageable,
    ): Page<UniversityData> {
        return universityJpaRepository.findByNameNullable(name, pageable).map { university ->
            UniversityData.from(university)
        }
    }

    @Transactional(readOnly = true)
    fun findUniversityById(
        id: Long
    ): UniversityData {
        return UniversityData.from(
            universityJpaRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("University not found")
        )
    }

    @Transactional
    fun createUniversity(
        universityCreateForm: UniversityCreateForm
    ): UniversityData {
        return UniversityData.from(
            universityJpaRepository.save(
                University.create(
                    name = universityCreateForm.name,
                    address = universityCreateForm.address,
                    zipCode = universityCreateForm.zipCode,
                    tell = universityCreateForm.tell,
                    fax = universityCreateForm.fax,
                    pages = universityCreateForm.pages,
                )
            )
        )
    }

    @Transactional
    fun deleteUniversity(
        id: Long
    ) {
        universityJpaRepository.deleteById(id)
    }
}

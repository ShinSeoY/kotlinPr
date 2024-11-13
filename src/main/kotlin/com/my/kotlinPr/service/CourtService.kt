package com.my.kotlinPr.service

import com.my.kotlinPr.entity.Court
import com.my.kotlinPr.repository.CourtRepository
import org.springframework.stereotype.Service

@Service
class CourtService(
        private val courtRepository: CourtRepository
) {
    fun find():List<Court>{
        return courtRepository.findAll()
    }
}
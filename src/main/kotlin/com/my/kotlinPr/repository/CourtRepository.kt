package com.my.kotlinPr.repository

import com.my.kotlinPr.entity.Court
import org.springframework.data.jpa.repository.JpaRepository

interface CourtRepository: JpaRepository<Court, Long> {
//    fun findAll(name:String):List<Court>
}
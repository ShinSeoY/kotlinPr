package com.my.kotlinPr.dto

import org.springframework.data.domain.PageRequest

data class PageRequestDto (
        val index: Int,
        val size: Int
){
    fun toPageable() = PageRequest.of(this.index, this.size)
}
package com.my.kotlinPr.controller

import com.my.kotlinPr.entity.Court
import com.my.kotlinPr.service.CourtService
import com.my.kotlinPr.utils.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/court")
class CourtController(
        @Autowired var courtService: CourtService
){
    private val log = logger<CourtController>()

    @GetMapping("")
    fun findAll(): List<Court>{
        var courts: List<Court> = courtService.find()
        log.info("{}", courts[0].id)
        log.info(courts[0].name)
        log.info("{}", courts[0].createdAt)
        log.info("{}", courts[0].updatedAt)
        return courts
    }

}
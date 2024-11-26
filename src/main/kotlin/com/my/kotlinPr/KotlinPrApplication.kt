package com.my.kotlinPr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class KotlinPrApplication

fun main(args: Array<String>) {
	runApplication<KotlinPrApplication>(*args)
}

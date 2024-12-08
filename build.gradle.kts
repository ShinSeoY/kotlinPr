plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	kotlin("plugin.jpa") version "1.9.25"
	kotlin("kapt") version "1.9.21" // Kotlin annotation processing 플러그인
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.my"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.vladmihalcea:hibernate-types-60:2.20.0")
	implementation ("io.hypersistence:hypersistence-utils-hibernate-60:3.2.0")

	runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// querydsl
	implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	kapt ("jakarta.persistence:jakarta.persistence-api")
	kapt ("jakarta.annotation:jakarta.annotation-api")
	kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
//	kapt("org.springframework.boot:spring-boot-configuration-processor")

	// coroutine
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.2")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

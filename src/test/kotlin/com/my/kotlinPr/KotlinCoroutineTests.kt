package com.my.kotlinPr

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.Duration.ofSeconds
import kotlin.system.measureTimeMillis

@SpringBootTest
class KotlinCoroutineTests {


    suspend fun doSomethingUsefulOne(): Int {
        delay(1000)
        return 13
    }

    suspend fun doSomethingUsefulTwo(): Int {
        delay(1000)
        return 29
    }

    @Test
    fun `test suspend`() = runBlocking {
        val time = measureTimeMillis {
            val one = doSomethingUsefulOne()
            val two = doSomethingUsefulTwo()
            println("$one , $two")
        }
        println("$time")
    }

    @Test
    fun `test async suspend`() = runBlocking {
        val time = measureTimeMillis {
            val one = async { doSomethingUsefulOne() }
            val two = async {doSomethingUsefulTwo() }
            println("${one.await()} , ${two.await()}")
        }
        println("$time")
    }

    @Test
    fun `test delay with console log`() = runBlocking {
        println("Hello,")
        delay(5000L)
        println("World!")
    }

    @Test
    fun testWithCoroutineScope() = runBlocking {
        val scope = CoroutineScope(Dispatchers.Default)

        scope.launch {
            delay(5000)
            println("World!")
        }

        println("Hello,")
        delay(6000) // 코루틴이 완료될 때까지 대기
        println("----")
    }

    @Test
    fun main() {
        GlobalScope.launch {
            delay(5000L)
            println("World!")
        }
        println("Hello,")
    }


}

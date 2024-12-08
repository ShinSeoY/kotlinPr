package com.my.kotlinPr

fun main() {
    println("---------------------------------------- if 문")
    var i = 5
    var result: String = when {
        i > 10 -> {
            "10보다 큼"
        }

        i > 5 -> {
            "5보다 큼"
        }

        else ->
            "5555"
    }
    println(result)

    var result2 = if (i > 5) true else false

    println("---------------------------------------- for 문")
    var list = listOf(0, 1, 2, 3, 4)
    var sum = 0
    var sum2 = 0

    for (i in 0..list.size - 1) {
        sum += list[i]
    }

    for (i in 3 downTo 0 step 2) {
        sum2 += list[i]
    }
    println(sum)
    println(sum2)

    println("---------------------------------------- list, array")
    var array = arrayOf(0, 1, 2, 3)
    var arrayList = arrayListOf<Int>()
    var list1 = listOf(0, 1, 2)
    val list3 = mutableListOf<String>() // list의 참조값은 변하지 않으니 val로 해도 댐
    list3.add("1")
    list3.add("2")

    println("---------------------------------------- nullable")
    var name: String? = "not null"

//    var nonNullName: String = name // nonNullName은 null이면 안되고 name은 null이 가능하므로 타입오류
    var nonNullName: String = name ?: "i am null" // name == Null -> i am null을 반환
    var nonNullName2: String = name!! // name은 무조건 Null이 아니다를 나타냄

    name = "www "
    name?.uppercase() // name != null -> uppercase()
    name?.let { it -> println(it + "hello") } // name != null -> print 문 실행

    println("---------------------------------------- 함수")
    println(sum(b = 10, a = 20))

    println("---------------------------------------- data class")
    var sandy = Person("sandy", 20)
    var sandy2 = Person("sandy", 20)
//    sandy.name = "john"     //불가능
    sandy.age = 10
    println(sandy == sandy2)

    var john = PersonData("john", 20)
    var john2 = PersonData("john", 20)
    println(john == john2)

    println("---------------------------------------- open class")
    var tiresNum = Benz().getNumberOfTires()
    println(tiresNum)

    println("---------------------------------------- 주 생성자, 부 생성자")
    var train = Train(10);
    println(train.tireNumber)
    println(train.kind)

    var train2 = Train(10, 80, "ktx");
    println(train2.tireNumber)
    println(train2.kind)

    println("---------------------------------------- getter setter")
    var train3 = Train(10, 80, "ktx");
//    train3.color = "black"
    println(train3.color)

    println("---------------------------------------- generic")
    var box = Box(10)
    var box2 = Box("hello")

    println(box.value)
    println(box2.value)

    println("---------------------------------------- 고차함수")
//    myFun(10, {print("함수중")}) 아래처럼 밖으로 뺼수 있음 - 후행 람다(마지막 람다식만 밖으로 뺼수 있음)
    myFun(10) { println("함수중") }
    println(myLamda(0.1) { a: Double -> a > 1 })
    val newnew: ArrayList<Person> = arrayListOf<Person>()
    persons.map {
        if (it.age > 10) newnew.add(it)
    }
    println(newnew)

    println("---------------------------------------- Scope functions")
//    null 체크가 필요하면 → let
//    객체 초기화/설정이 필요하면 → apply
//    여러 속성/메서드를 사용해야 하면 → with
//    계산된 값을 반환해야 하면 → run
//    객체 자신을 반환, 부가 작업이 필요하면 → also
    solution(intArrayOf(1, 2, 3, 4, 6, 7, 8, 0))

    println("---------------------------------------- lable(레이블, 라벨) 리턴")
//    해당 표현식에 이름을 붙여준다, break와 continue 시 label을 사용하여 가장 가까운 루프 말고 원하는 루프의 흐름을 제어가능
    // break와 continue에서는 안쪽 for문을 빠져나옴
    // return문 일경우 가장 가까운 함수 혹은 익명함수에서만 반환되기때문에 바깥쪽 for문을 빠져나옴
    for (i in 1..10) {
        println("i는 ${i}")
        myloop@ for (j in 1..5) {
            println("j는 ${j}")
            if (j == 3) break@myloop
        }
        println("---끝")
    }

    // 3일때는 리턴 아래코드는 실행하지 않고, 1 2 4 5, a b d e일때만 아래 코드를 실행함
    // for문의 continue와 동일한 결과
    fun mylabel() {
        arrayListOf(1, 2, 3, 4, 5).forEach lit@{
            if (it == 3) return@lit
            println("---끝2..${it}")
        }

        arrayListOf("a", "b", "c", "d", "e").forEach{
            if (it == "c") return@forEach
            println("---끝3..${it}")
        }
    }
    mylabel()

    // 가장 가까운 함수를 종료함
    fun printMyLabel() {
        arrayListOf(1, 2, 3, 4, 5).forEach myPrint@ {
            fun printmy(){
                if (it == 3) return
                println("value $it ")
            }
            printmy()
        }
    }
    printMyLabel()

}

fun sum(a: Int, b: Int, c: Int = 0) = a + b + c

data class Person(val name: String, var age: Int)

class NewPerson

// equals, hashCode, toString 재정의 해줌
data class PersonData(val name: String, var age: Int)

open class Car {

    // 이 메서드는 하위 클래스에서 override 불가능
    fun getNumberOfTires(): Int = 4

    // 하위 클래스에서 override 가능
    open fun hasSunRoof(): Boolean = false
}

// open 클래스는 상속이 가능
class Benz() : Car() {

    // getNumberOfTires 메서드는 override 불가능
    // hasSunRoof 메서드는 open변경자가 붙어서 override 가능
    override fun hasSunRoof(): Boolean = true
}

open class Vehicle(var tireNumber: Int, var kind: String)

class Train(var driverNumber: Int, tireNumber: Int, kind: String) : Vehicle(tireNumber, kind) {

    var color: String? = "red"
        private set
        get() = "color : $field"

    constructor(driverNumber: Int) : this(driverNumber, 8, "jtx")
}

class Box<T>(var value: T)

fun myFun(a: Int, myFunction: () -> Unit) { // unit == void
    println("$a 함수 시작")
    myFunction()
}

fun myLamda(a: Double, lam: (a: Double) -> Boolean): String {
    return if (lam(a)) "big~~" else "small~~"
}

val persons = arrayListOf<Person>(Person("일길동", 10), Person("이길동", 20), Person("삼길동", 30))


fun solution(ingredient: IntArray): Int {
    var sum = 0
    var list = arrayListOf<Int>()
    for (i in ingredient) {
        list.add(i)
        if (list.size >= 4 && i == 1 && i - 1 == 3 && i - 2 == 2 && i - 3 == 1) {
            sum++
            repeat(4) {
                list.removeAt(list.size - 1)
            }
        }
    }
    return sum
}

fun myFun(ingredient: IntArray): IntArray {
    var indexOfOne = ingredient.indexOf(1)
//    var res = ingredient.slice(IntRange(maxOf(0, indexOfOne - 3), indexOfOne +1))
    ingredient.filterIndexed { index, _ ->
        index < maxOf(0, indexOfOne - 3) || index >= indexOfOne
    }
    return ingredient
}
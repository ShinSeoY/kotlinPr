package com.my.kotlinPr

fun main(){

}


// 코루틴은 경량 스레드(light-weight threads)
// OS에서 생성/스케줄링/관리되는 일반 스레드와 달리 Continuation객체를 통해 생성 & 단일 스레드 내에서 여러 개의 실행 컨텍스트를 만들어서,
// 비동기 작업을 작은 덩어리로 분할하고, 필요한 만큼만 실행,  적은 비용으로 문맥 교환이 가능

// 협력함수 : 실행을 중단하고 재개하는 것을 가능하게 하는 프로그램의 구성요소

// Continuation 객체 : 코루틴의 일시 중지 지점, 코루틴이 다시 실행될 때 해당 지점에서부터 실행이 이어지도록 보장

// CoroutineScope(Interface) : 새로운 coroutine을 실행하기 위한 범위를 정의, suspend fun은 이 범위 안에서만 실행이 가능
// - ContextScope : internal class이기 때문에, 우리가 직접 호출하지 못하고 fun CoroutineScope을 통해 생성
// - GlobalScope : application 전체의 생명주기 동안 작동하며 조기에 취소되지 않는 top-level coroutine을 시작하는 데 사용
//  public object GlobalScope : CoroutineScope {
//      override val coroutineContext: CoroutineContext
//          get() = EmptyCoroutineContext
//  }
// - MainScope : Android 에서 UI Component를 관리하기 위해 사용
// - coroutineScope(앞에 소문자 c임) : 바로 바깥에 있는(부모) CoroutineScope를 상속받으며, 부모를 일시 중단시키고 block 내의 작업을 수행

// Coroutine Builder : Coroutine Scope를 만들어주는 기능
// - runBlocking : 작업이 완료될 때까지 현재 스레드를 블로킹함, main 함수와 test 코드에서 사용되도록 권장
// - withContext : 현재 CoroutineScope를 일시 중단하고 결과값을 반환하기 때문에 순서의 보장이 필요할 때 사용
//                 runBlocking 은 현재 스레드를 차단 해버리며, withContext는 일시 중단
// - async, launch
//    - async : 비동기 작업의 결과값을 받을 수 있음
//    - launch : Job 객체를 반환, 결과값 받을 수 없음

// 코루틴 빌더(launch, async 등)을 이용해 suspend 함수를 사용 -> 새로운 코루틴 생성 -> 그때 그 함수를 호출한 호출자는 잠시 실행을 정지(이 또한 다른 코루틴, 다른 스레드에서 실행되고 있음)
// -> 실행을 멈추게 되면 사용하고 있던 스레드는 반납 -> 호출된 새로운 코루틴이 작업을 시작
// 일시정지 될 때는 Continuation 객체를 반환하는데,
// Continuation 객체는 멈췄던 곳에서 다시 코루틴을 실행할 수 있음(다른 Thread에서 실행 될 수도 있음)
// Thread는 저장이 불가능하고, 멈추는 것만 가능
// 하지만 Continuation은 멈추는 것은 물론 이전의 실행 상태를 저장하고 있음
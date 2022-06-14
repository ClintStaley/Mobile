package Coroutines;

import kotlinx.coroutines.*

suspend fun <T> slowVal(msDelay: Long, value: T) : T {
    delay(msDelay);
    println("${Thread.currentThread()} computing $value")
    return value;
}

fun main() {
    val states = arrayOf("Starting", "Doing Task 1", "Doing Task 2", "Ending")
   
    runBlocking() {
        repeat(3) {
            val msDelay = 500L*it
            launch {
                println("${Thread.currentThread()} has started")
                for (i in states) {
                    println("${Thread.currentThread()} - $i")
                    delay(msDelay)
                }
            }
        }
    }

    runBlocking() {
        val deferred: Deferred<Int> = async(Dispatchers.IO) {slowVal(1000, 42)}

        println(deferred.isCompleted)
        println(deferred.await())
        println(deferred.isCompleted)

        println(awaitAll(
          async {slowVal(1000, 10)}, 
          async {slowVal(500, 20)},
          async(Dispatchers.Default) {slowVal(0, 30)})
         .reduce {a, b -> a+b});
    }

    runBlocking() {
        val longJob = async {slowVal(100000, 42)}
        
        try { 
           delay(1000);
           if (!longJob.isCompleted)  // Pretty much guaranteed true
              longJob.cancel();
           println(longJob.await());
        }
        catch (err: CancellationException) {
            println(err)
        }
    }
}

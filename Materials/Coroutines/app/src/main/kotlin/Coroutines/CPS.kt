package Coroutines;

import kotlinx.coroutines.*;

suspend fun myDelay(mS: Long) {
    delay(mS);
    println("Delaying for $mS mS");
}

suspend fun <T> slowValue(msDelay: Long, value: T) : T {
    myDelay(msDelay);
    println("${Thread.currentThread()} computing $value")
    return value;
}

fun main() {
    runBlocking() {
       val sum = slowValue(4000, 42) + slowValue(4000, 43);

       println("Sum is $sum")
    }
}

// Exercise to convert to CPSByHand
//
// 0. Convert suspends and runBlocking to comments.
// 1. Add continuation parameter to all suspends (as final param)
// 2. Pass continuations, using {} notation.
// 3. Convert all suspends to Unit return, and "return" via the continuation
// 4. Take care unwinding the slowValue adding expression.  Two calls/continuations.
// 5. Run the result to ensure correctness.
// 6. NOW, simulate the operation of "delay" by creating a MutableList of
//    continuations and enqueuing the continuation, then returning without
//    result.  Run to see what happens.
// 7. Then, add one queue.removeAt(0)() to release one suspension.  Run/test.
// 8. Add a second, for complete run.
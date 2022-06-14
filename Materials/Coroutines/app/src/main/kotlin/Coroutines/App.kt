package Coroutines;

import kotlinx.coroutines.*

suspend fun suspendingAction(name: String, msDelay: Long) {
    println("Starting $name on ${Thread.currentThread()}");
    delay(msDelay);
    println("Done with $name on ${Thread.currentThread()}");
}

suspend fun blockingAction(name: String, msDelay: Long) {
    println("Starting $name on ${Thread.currentThread()}");
    Thread.sleep(msDelay);
    println("Done with $name on ${Thread.currentThread()}");
}

suspend fun loopingAction(name: String, counts: Long) {
    var count = counts;

    println("Starting $name on ${Thread.currentThread()}");
    while (count-- > 0)
       ;
    println("Done with $name on ${Thread.currentThread()}");
}
suspend fun suspendingValue(name: String, msDelay: Long, value: Int) : Int {
    suspendingAction(name, msDelay);
    return value;
}

suspend fun main() {
    var scope1 = CoroutineScope(Dispatchers.Default);
    val scope2 = CoroutineScope(Dispatchers.IO);

    println("Main thread is ${Thread.currentThread()}");
    // suspendingAction("Single launch suspender from main", 1000)

/*
    // Sequential suspending actions in one coroutine.  8s coroutine duration
    scope1.launch() {
        repeat(8) {
            suspendingAction("Single launch suspender $it", 1000)
        }
    }
    println("Sequential suspending launch done");

    // Parallel/serial suspending actions in 8 coroutines.  3s duration.
    repeat(8) {
        scope1.launch() {
           suspendingAction("Suspender Loop $it A", 1000);
           suspendingAction("Suspender Loop $it B", 1000);
           suspendingAction("Suspender Loop $it C", 1000);
        }
    }
    println("Parallel and series suspending launches done");

    // Parallel Deferred<Int> computations in different scope.  1s duration
    scope2.launch() {
        val results = mutableListOf<Deferred<Int>>();
        var total:Int  = 0;

        repeat(20) {
            results.add(async(){suspendingValue("Async for $it", 1000, it)});
        }
        println("Async calls done");
        results.forEach {
            println("Gonna do an await");
            val res = it.await();
            println("Adding $res");
            total += res;
        }
        println("Async total is $total")
    }
    println("Parallel async suspending launches done");
*/
    // Nominally parallel *blocking* actions.  Duration 10s with 10-19 threads
    repeat(24) {
        scope1.launch() {
            blockingAction("Blocking $it", 5000);
            loopingAction("Looping $it", 100000000000);
            // suspendingAction("Suspending $it", 5000);
        }
    }
    println("First blocking launches done");

    Thread.sleep(500)
    println("Buh-bye")
    scope1.cancel()
    scope1 = CoroutineScope(Dispatchers.Default);
    
    // More parallel *blocking* actions.  
    repeat(10) {
        scope1.launch() {
            blockingAction("Second Blocking $it", 5000);
        }
    }
    println("Second blocking launches done");

    //Thread.sleep(2000);
    //scope2.cancel();     // Cancel all async coroutines

    Thread.sleep(9000);
    scope1.cancel();     // Cancel part or all of launches, depending on delay
    Thread.sleep(3000);
}

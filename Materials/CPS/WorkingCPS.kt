val queue = mutableListOf<()->Unit>();

fun myDelay(mS: Long, continuation: () -> Unit) {
    println("Delaying for $mS mS");
    queue.add({continuation()});
}

fun <T> slowValue(msDelay: Long, value: T,
 continuation: (T) -> Unit) {
    myDelay(msDelay) {
        println("${Thread.currentThread()} computing $value");
        continuation(value)
    }
}

fun main() {
    var sum = 0;
    
    slowValue<Int>(100, 42) {
        sum += it;
        slowValue(100, 43) {
            sum += it;
            println("Sum is $sum")
        }
    }
    println("Arbitrary long running actions....");
    queue.removeAt(0)();
    println("More arbitrary long running actions....");
    queue.removeAt(0)();
}

val queue = mutableListOf<()->Unit>();

fun delay(mS: Long, continuation: () -> Unit) {
    println("Delaying for $mS mS");
    queue.add({continuation()}); //continuation();
}

fun <T> slowVal(msDelay: Long, value: T, continuation: (T) -> Unit) {
    delay(msDelay) {
         println("${Thread.currentThread()} computing $value");
         continuation(value)
    }
}

fun main() {
    var sum = 0;
    
    slowVal(100, 42) { //it: Int -> 
        sum += it;
        slowVal(100, 43) { //it: Int -> 
            sum += it;
            println("Sum is $sum")
        }
    } 

    println("Do stuff while first suspend is completing in delay function");
    queue.removeAt(0)();
    println("Do stuff while second suspend is completing in delay function");
    queue.removeAt(0)();
}

package Coroutines;

/*
var square = {x: Int -> x*x}

fun main() {
   println(square(square(42)));
}

*/
var square = {x: Int, cont: (Int) -> Unit -> cont(x*x)}

fun main() {
   square(42, {res: Int -> square(res, {res2: Int -> println(res2)})})
}
/*
var doPrint = {v: Int -> println("Value is $v")}

fun main() {
     val a = 42
     val  b = 43
     val c = 44
     doPrint(max(a, max(b, c)))
     println("Done")
}
*/

var max: (Int, Int, (Int)->Unit) -> Unit = {a, b, cnt ->
   cnt(if (a > b) a else b)
}

var doPrint = {v: Int, cnt: () -> Unit -> println("Value is $v"); cnt()}

fun main() {
     val a = 42
     val  b = 43
     val c = 44
     max(b, c, {it -> max(a, it, {it2 -> doPrint(it2, {println("Done")})})})
}
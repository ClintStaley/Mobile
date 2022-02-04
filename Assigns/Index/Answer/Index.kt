import kotlin.collections.*
import kotlin.text.Regex
import java.io.File

fun main(args: Array<String>) {
   File(args[0]).readLines()
    .flatMapIndexed({idx, line -> line.split(Regex("\\W+"))
    .filter({it.length > 0}).toSet().map({it to idx})})
    .groupBy({it.first}, {it.second + 1}).toSortedMap()
    .forEach({word, lines -> println("${word}: ${lines.joinToString(", ")}")})
}

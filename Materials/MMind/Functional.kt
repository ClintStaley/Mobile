import kotlin.collections.ArrayList
import kotlin.math.sqrt
import kotlin.math.min
import java.util.Random

// Tack distance onto Pair
fun Pair<Int, Int>.distance(pt2: Pair<Int, Int>) : Double {
   return sqrt((first - pt2.first)*(first - pt2.first).toDouble()
    + (second - pt2.second)*(second - pt2.second)) 
}

fun main() {
   // Make random Pairs
   val rnd = Random(1)
   val pts = Array<Pair<Int, Int>>(4) {rnd.nextInt(10) to rnd.nextInt(10)}

   // Generate list of self-
   val pairs = pts.flatMap({p1 -> pts.mapNotNull({p2 -> if (p1 != p2) p1 to p2 else null})})
   pairs.forEach({println("${it.first} <-> ${it.second}")});

   println("Closest distance: ${pairs.minOf({it.first.distance(it.second)})}")
   println("Average distance: ${pairs.sumOf({it.first.distance(it.second)})/pairs.size}");

   // Big bad reduce to collect sum, min, count all at once.
   data class Stats(val sum: Double, val min: Double, val count: Int)

   val allStats = pairs.map(
    {Stats(it.first.distance(it.second), it.first.distance(it.second), 1)})
    .reduce({s1, s2 -> Stats(s1.sum + s2.sum, min(s1.min, s2.min), s1.count + s2.count)})

   println(allStats)
}
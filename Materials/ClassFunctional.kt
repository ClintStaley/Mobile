import kotlin.random.Random
import kotlin.math.sqrt
import kotlin.math.min

fun Pair<Int, Int>.distance(pt2: Pair<Int, Int>) : Double {
   return sqrt((first - pt2.first)*(first - pt2.first).toDouble()
    + (second - pt2.second)*(second - pt2.second)) 
}

fun main() {
   val rnd = Random(1)
   val pts = Array(4) {rnd.nextInt(100) to rnd.nextInt(100)}

   val ptPairs = pts.flatMap {pt1 -> 
    pts.mapNotNull {pt2 -> if (pt1 == pt2) null else pt1 to pt2}}

   ptPairs.forEach {println(it)}
   println(ptPairs.minOf {it.first.distance(it.second)})

   // Big bad reduce to collect sum, min, count all at once.
   data class Stats(val sum: Double, val min: Double, val count: Int)

}
   // Array of four randomly set points.  use "to"

   // val pairs = List of all pairs of points, except self-pairs (two different map functions will be needed)
   

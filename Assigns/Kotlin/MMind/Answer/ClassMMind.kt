class Params() {
    var maxChar: Char? = null
    var length: Int? = null
    var seed: Int? = null

    fun read() {
        while (true) {
            print("Enter max character, number of characters, and seed");
            val line = readLine()?.trim()?.split(' ')
    }
}


fun main() {
   var prms = Params()

   prms.read()
   println(prms)
}
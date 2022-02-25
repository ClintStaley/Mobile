annotation class Note(val word: String)

@Note("Hiya")
fun main() {
   println((::main.annotations[0] as Note).word)
}
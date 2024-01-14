package com.cstaley.mobile.mmind

import java.util.Random

class Params {
   var maxChar: Char? = null
   var length: Int? = null
   var seed: Long? = null
   val randRange 
      get() = (maxChar ?: 'A') - 'A' + 1

   fun read() {
      var args: List<String>

      while (true) {
         print("Enter max letter, number of letters and seed: ")
         args = readLine()?.trim()?.split(' ') ?: listOf<String>()
         // args.forEachIndexed({idx, value -> println("${idx}. ${value}")})
         
         if (args.size == 3) {
            try {
               maxChar = args[0].uppercase()[0];
               length = args[1].toInt();
               seed = args[2].toLong();
               if (maxChar !in 'A'..'F')
                   println("Max char must be between A and F")
               else if (length !in 1..10)
                   println("Number of chars must be between 1 and 10")
               else if (seed!! < 0)
                   println("Enter a nonnegative integer for seed")
               else
                   return
            }
            catch (err: NumberFormatException) {
               println("Bad integer format")
            }
         }
         else
             println("Must have three entries")
      }
   }
}

data class Results(var exact: Int, var inexact: Int, var solved: Boolean)

class Pattern(val params: Params) {
    var content: Array<Char>
    var rnd: Random = Random(params.seed ?: 1)
    val length = params.length ?: 4;

    init {
        content =  Array<Char>(length){'A' + rnd.nextInt(params.randRange)}
    }

    fun read() {
        var letters: List<String>?

        do {
            var errors = 0;
            letters = readLine()?.trim()?.uppercase()?.split(' ')
            if (letters != null) {
                letters.forEach({letter ->
                    if (letter.length != 1 || letter < "A"
                     || letter > "${params.maxChar}") {
                         println("${letter} is not a valid guess");
                         errors++
                    }
                })
                if (letters.size != length) {
                    println("Guess is too ${if (letters.size < length) "short" else "long"}")
                    errors++;
                }
            }
        } while (letters == null || errors > 0)

        content = Array<Char>(length) {idx: Int -> letters[idx][0]}
    }

    fun match(other: Pattern): Results {
        var rtn = Results(0, 0, false)
        val usUsed = Array<Boolean>(length) {false}
        val otherUsed = Array<Boolean>(other.length) {false}

        content.forEachIndexed({idx, value -> 
            if (other.content[idx] == value) {
                rtn.exact++
                usUsed[idx] = true
                otherUsed[idx] = true
            }})

        content.forEachIndexed({idx1, value1 -> 
            other.content.forEachIndexed({idx2, value2 ->
                if (!usUsed[idx1] && !otherUsed[idx2] && value1 == value2) {
                    usUsed[idx1] = true 
                    otherUsed[idx2] = true
                    rtn.inexact++
                }
            })
        })

        rtn.solved = length == rtn.exact
        return rtn
    }

    override fun toString(): String {
        val rtn = StringBuilder();

        content.forEach({c -> rtn.append(c)})

        return rtn.toString()
    }
}

fun main() {
   val params = Params()
   params.read()

   val model = Pattern(params)
   println("Model: ${model.toString()}")
   val guess = Pattern(params)

   do {
       print("Enter a guess: ");
       guess.read()
       val results = model.match(guess)
       println("${results.exact} exact and ${results.inexact} inexact");
   } while (!results.solved)
}
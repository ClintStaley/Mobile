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

data class Results(val exact: Int, val inexact: Int)

class Pattern(val params: Params) {
    var content: Array<Char>
    var rnd: Random = Random(params.seed ?: 1)

    init {
        content =  Array<Char>(params.length ?: 4)
         {'A' + rnd.nextInt(params.randRange)}
        println(content)  
    }

    fun read() {
        

    }

    fun match(): Results {
        return Results(1, 1)
    }

    override fun toString(): String {
        val rtn = StringBuilder();

        content.forEach({c -> rtn.append(c)})

        return rtn.toString()
    }
}

fun main() {
   val params = Params()
   val model: Pattern

   params.read()
   model = Pattern(params)
   println(model.toString())
}
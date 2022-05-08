package main;

import kotlin.js.console;
import kotlin.js.Promise;

external fun setTimeout(handler: dynamic, timeout: Int = definedExternally,
 vararg arguments: Any?) : Int

var makeStringPromise = {value: String, delay: Int -> 
   Promise<String>({res, _ -> setTimeout({res(value)}, delay)});
};

/*
fun main() {
   console.log(await coHelloWorld())
}

async coHelloWorld() : String {
   var rtn = await Hello();
   var rtn = rtn + await Punctuation();
   return rtn + await World();
}

async Hello() : String {
   // return string via, say, web hit or other async action.
}
... etc for Punctuation and World.
*/

fun Hello() : Promise<String> {
   return makeStringPromise("Hello", 2000);
}

fun Punctuation() : Promise<String> {
   return makeStringPromise(", ", 2000);
}

fun World() : Promise<String> {
   return makeStringPromise("world!", 2000);  
}

fun main() {
   coHelloWorld().then({console.log(it)});
   console.log("End of main")
}

fun coHelloWorld() : Promise<Any> { // Need Any to satisfy Kotlin
   var rtn = "";

   return Hello()
  .then({rtn += it; Punctuation()})
  .then({rtn += it; World()})
  .then({rtn + it});
}
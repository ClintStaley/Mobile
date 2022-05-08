package main;

import kotlin.js.console;
import kotlin.js.Promise;

external fun setTimeout(handler: dynamic, timeout: Int = definedExternally,
 vararg arguments: Any?) : Int

var makeGoodPromise = {value: String, delay: Int -> 
   Promise<String>({res, _ -> setTimeout({res(value)}, delay)});
};

var makeBadPromise = {value: String, delay: Int -> 
   Promise<String>({_, rej -> setTimeout({rej(Throwable(value))}, delay)});
};

fun main() {
   var simplePrm = makeGoodPromise("Simple Result", 4000);

   simplePrm.then({res -> console.log(res)});

   var complexPrm = makeGoodPromise("Complex Result", 3000);
   
   complexPrm = complexPrm
   .then({res -> 
      console.log("Doing first then with result $res");
      makeGoodPromise("New promise from $res", 3000);
   })
   .then(
      {res -> 
         console.log("Second promise sez: $res");
         "Second promise from $res";
      },
      {_ -> 
         console.log("Or maybe not");
         "Wow, an error!";
      }
   );
   var failedPrm = makeBadPromise("Bad promise, bad!", 8000);

   failedPrm
   .then({_ -> console.log("Happy outcome!")})
   .then({_ -> console.log("And another happy outcome!")})
   .then(null, {err -> console.log("Failed because " + err)});
//   .catch({err -> console.log("Failed because $err")});

   complexPrm.then({res -> console.log("Where were we? Oh yeah: ${res}")});
/*
*/
   console.log("All done!");
}
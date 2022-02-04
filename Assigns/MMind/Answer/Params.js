var Params = function() {
   this.read = function() {
      var paramLine;
      
      while (true) {
         print("Enter max character, number of characters, and seed");
         paramLine = readline().trim().split(' ');
         if (paramLine.length != 3)
            print("Must have three entries");
         else {
            this.maxChar = paramLine[0].toUpperCase().charAt(0),
            this.length = parseInt(paramLine[1]),
            this.seed = parseInt(paramLine[2])
          
            if (this.maxChar < "A" || this.maxChar > "F")
               print("Max char must be between A and F");
            else if (!this.length || this.length > 10)
               print("Number of chars must be between 1 and 10");
            else if (!this.seed || this.seed < 0)
               print("Enter a nonnegative integer for seed");
            else {
               this.randRange
                = this.maxChar.charCodeAt(0) - 'A'.charCodeAt(0) + 1;
               return;
            }
         }
      }
   }
};
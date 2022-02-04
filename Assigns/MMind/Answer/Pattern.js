var Pattern = function(params) {
   this.params = params;
   this.content = [];
   
   for (var i = 0; i < params.length; i++)
      this.content[i] = new String('');
   
   this.toString = function() {
      var rtn = this.content[0];
      
      for (var i = 1; i < this.content.length; i++)
        rtn = rtn + " " + this.content[i];
      return rtn;
   };
   
   this.read = function() {   
      var line, letters, letter, errors;

      do {
         letters = [];
         errors = 0;
         if (line = readline()) {
            letters = line.trim().toUpperCase().split(' ');
            for (var i = 0; i < letters.length; i++) {
               letter = letters[i];
               if (letter.length != 1 || letter < 'A' || letter > this.params.maxChar) {
                  print(letters[i], " is not a valid guess");
                  errors++;
               }
            }
            if (letters.length < this.params.length) {
               print("Guess is too short");
               errors++;
            }
            else if (letters.length > this.params.length) {
               print("Guess is too long");
               errors++;
            }
         }
      } while (!line || errors);
      
      for (var i = 0; i < letters.length; i++)
         this.content[i] = letters[i].trim();
   };
   
   this.randomize = function() {
      for (var i = 0; i < this.params.length; i++) {
         this.content[i] = String.fromCharCode('A'.charCodeAt(0)
          + Math.random() * this.params.randRange);
      }
   }
   
   this.match = function(other) {
      var result = {
         exact: 0,
         inexact: 0,
      };
      var thisUsed = [], otherUsed = [];
      
      for (var i = 0; i < this.content.length; i++)
         if (this.content[i] === other.content[i]) {
            thisUsed[i] = otherUsed[i] = true;
         result.exact++;
      }
   
      for (var i = 0; i < this.content.length; i++)
         for (var j = 0; j < other.content.length; j++)
            if (!thisUsed[i] && !otherUsed[j]
               && this.content[i] === other.content[j]) {
               thisUsed[i] = otherUsed[j] = true;
               result.inexact++;
            }
            
      result.solved = this.params.length === result.exact;
      return result;
   }
};
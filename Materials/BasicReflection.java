package com.cstaley.csci220.Reflection;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class BasicReflection {
   public static void main(String[] args) {
      String clsName;
      Class type;
      Method mtd;
      int result;
      Scanner input = new Scanner(System.in);
      Object obj;

      try {
         // Mess around a little with our Scanner object
         type = input.getClass();
         System.out.printf("It's a %s, and it's derived from %s\n",
          type.getName(), type.getSuperclass().getName());

         mtd = type.getMethod("nextInt", null);
         if (0 != (mtd.getModifiers() & Modifier.PUBLIC)) {
            System.out.printf("It's got a public %s method, returning %s.\n",
             mtd.getName(), mtd.getReturnType().getName());
            System.out.printf("Let's call it. Please enter an int: ");

            // Shouldn't we call hasNextInt first?
            if ((Boolean) type.getMethod("hasNextInt", null).invoke(input)) {
               result = (Integer) mtd.invoke(input);
               System.out.printf("Yep, we got a %d\n", result);
            }
         }

         // Now let's build a StringBuilder from scratch
         type = StringBuilder.class; // Get Class object directly
         obj = type.getConstructor(null).invoke(); // Create a StringBuilder
         mtd = type.getMethod("append", String.class); // Find method w/ params.
         mtd.invoke(obj, "Saple");

         // Insert the missing "m"
         // Fill in in class....
         
         System.out.printf("\nStringBuilder content is %s\n", obj.toString());
      } catch (Exception err) {
         System.out.println(err.getMessage());
      }
   }
}

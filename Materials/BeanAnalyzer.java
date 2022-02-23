package com.cstaley.csci220.Reflection;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.lang.reflect.Method;

public class BeanAnalyzer {

   private static Map<String, Class> findProperties(Class cls) {
      String name;
      Class rtn;
      Map<String, Class> results = new TreeMap<String, Class>();

      for (Method method : cls.getMethods()) {
         if (method.getName().startsWith("get")
          && method.getParameterTypes().length == 0
          && (rtn = method.getReturnType()) != void.class) {

            name = method.getName().substring(3);
            try {
               method = cls.getMethod("set" + name, // Fill in in class); 
               if (method.getReturnType() == void.class)
                  results.put(name, rtn);
               else
                  throw new NoSuchMethodException();
            } catch (NoSuchMethodException err) {
               results.put(name + "(r/o)", rtn);
            }
         }
      }
      return results;
   }

   public static void main(String[] args) {
      String clsName;
      Map<String, Class> props;
      Map.Entry<String, Class> prop;
      Iterator<Map.Entry<String, Class>> itr;
      Class type;
      Scanner input = new Scanner(System.in);

      do {
         System.out.print("Enter class name: ");
         clsName = input.next();

         if (!clsName.equals("Quit")) {
            try {
               props = findProperties(Class.forName(clsName));

               for (itr = props.entrySet().iterator(); itr.hasNext();) {
                  prop = itr.next();
                  type = prop.getValue();
                  if (!type.isArray())
                     System.out.println("Name: " + prop.getKey()
                      + "   Type: " + type.getName());
                  else
                     System.out.println("Name: " + prop.getKey()
                      + "   Type: " + type.getComponentType().getName() + "[]");
               }
            } catch (ClassNotFoundException err) {
               System.out.println("Can't find class " + clsName);
            }
         }
         System.out.println();
      } while (!clsName.equals("Quit"));
   }
}

/*
 * Sample Run Enter class name: Prog18 Name: Class(r/o) Type: java.lang.Class
 * 
 * Enter class name: String Can't find class String
 * 
 * Enter class name: java.lang.String Name: Bytes(r/o) Type: byte[] Name:
 * Class(r/o) Type: java.lang.Class
 * 
 * Enter class name: javax.swing.JComponent Name: AccessibleContext(r/o) Type:
 * javax.accessibility.AccessibleContext Name: ActionMap Type:
 * javax.swing.ActionMap Name: AlignmentX Type: float Name: AlignmentY Type:
 * float Name: AncestorListeners(r/o) Type: javax.swing.event.AncestorListener[]
 * Name: Autoscrolls Type: boolean Name: Background Type: java.awt.Color Name:
 * Border Type: javax.swing.border.Border Name: Bounds Type: java.awt.Rectangle
 * Name: Class(r/o) Type: java.lang.Class Name: ColorModel(r/o) Type:
 * java.awt.image.ColorModel Name: ComponentCount(r/o) Type: int Name:
 * ComponentListeners(r/o) Type: java.awt.event.ComponentListener[] Name:
 * ComponentOrientation Type: java.awt.ComponentOrientation Name:
 * Components(r/o) Type: java.awt.Component[] Name: ContainerListeners(r/o)
 * Type: java.awt.event.ContainerListener[] Name: Cursor Type: java.awt.Cursor
 * Name: DebugGraphicsOptions Type: int Name: DefaultLocale Type:
 * java.util.Locale Name: DropTarget Type: java.awt.dnd.DropTarget Name:
 * FocusCycleRootAncestor(r/o) Type: java.awt.Container Name:
 * FocusListeners(r/o) Type: java.awt.event.FocusListener[] Name:
 * FocusTraversalKeysEnabled Type: boolean Name: FocusTraversalPolicy Type:
 * java.awt.FocusTraversalPolicy Name: Font Type: java.awt.Font Name: Foreground
 * Type: java.awt.Color
 * 
 * ... 25 lines or so deleted ...
 * 
 * Name: Size Type: java.awt.Dimension Name: ToolTipText Type: java.lang.String
 * Name: Toolkit(r/o) Type: java.awt.Toolkit Name: TopLevelAncestor(r/o) Type:
 * java.awt.Container Name: TransferHandler Type: javax.swing.TransferHandler
 * Name: TreeLock(r/o) Type: java.lang.Object Name: UIClassID(r/o) Type:
 * java.lang.String Name: VerifyInputWhenFocusTarget Type: boolean Name:
 * VetoableChangeListeners(r/o) Type: java.beans.VetoableChangeListener[] Name:
 * VisibleRect(r/o) Type: java.awt.Rectangle Name: Width(r/o) Type: int Name:
 * X(r/o) Type: int Name: Y(r/o) Type: int
 * 
 * Enter class name: Quit
 */
/*!***************************************************
 *  Letter
 * ========
 * @author : Randolfo A Goncalves
 * @file   : Letter.java 
 * @date   : 13-04-24
 *****************************************************/

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;

class Btn extends JButton{
// Fields:
   static Color  bg  = new Color(255,255,224),
                 fg  = Color.BLACK;
   
   boolean clicked = false;
   int value = 0 ;
   // Build: 

   Btn(String text,boolean click){ 
      super(text);
      dft(this);
      clicked=click;
   }

// Methods:

   //! Button default
   static void dft(JButton btn){
      btn.setBackground(bg);
      btn.setForeground(fg);
      btn.setFocusPainted(false);
      btn.setBorderPainted(false);
   }

   static void select(Btn btn){
      if(btn.clicked){ 
         btn.setBackground(Color.RED);
         btn.setForeground(Color.WHITE);
         btn.clicked=false;
      }else{
         dft(btn);
         btn.clicked=true;
            }
      }
}

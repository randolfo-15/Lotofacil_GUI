/*!***************************************************
 *  Main
 * ======
 * @author : Randolfo A Goncalves
 * @file   : Main.java 
 * @date   : 04-04-24
 *****************************************************/

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSeparator;

class Main extends JFrame{
// Fields
// ======
   JPanel    pnl  = new Graph("rec/images/background.png"); //< Panel
   JMenuBar  mn   = new JMenuBar();                         //< Menu
   final int size = 100;                                    //< N° de buttons
   JButton[] btn  = new JButton[size];                      //< Buttons
   JButton   play = new JButton("",new ImageIcon("rec/images/play.png")); 

   // Color:
   Color df = new Color(255,255,224);

// Build
// =====
   Main(){
      super("Zebra de Ouro 2024");
      init_mabr();
      init_panel();
      init_buttons();
      init_frame();
   }   
   
   //! Startup Frame
   private void init_frame(){
      setSize(660,460);
      setResizable(false);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setJMenuBar(mn);
      setContentPane(pnl);
      setIconImage(new ImageIcon("rec/images/trevo.png").getImage());
      setVisible(true);
   }
  
   //! Startup Menu Bar
   private void init_mabr(){
      setAutoRequestFocus(false);
      play.setFont(Fonts.create("rec/fonts/font.ttf",20)); 
      play.setBorderPainted(false);
      play.setContentAreaFilled(false);
      play.setFocusPainted(false);
      mn.setBorder(BorderFactory.createLineBorder(df));
      mn.add(new JSeparator(0));
      mn.add(play);
   }

   //! Startup Panel
   private void init_panel(){ pnl.setBorder(BorderFactory.createLineBorder(df)); }

   // Startup Buttons
   private void init_buttons(){
      for(int i=0;i<size;i++) {
         btn[i]=(i<10)?(new JButton("0"+i)):(new JButton(""+i)); //< Create button 
         btn[i].setBackground(df);
         btn[i].setFont(Fonts.create("rec/fonts/font.ttf",20));
         pnl.add(btn[i]);
      }
   }

   public static void main(String[] args) { new Main(); }
}


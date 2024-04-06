/*!***************************************************
 *  Main
 * ======
 * @author : Randolfo A Goncalves
 * @file   : Main.java 
 * @date   : 04-04-24
 *****************************************************/

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

class Main extends JFrame{
// Fields
// ======
   JPanel    pnl  = new Graph("rec/images/background.png"); //< Panel
   JMenuBar  mn   = new JMenuBar();                         //< Menu

   final int size = 100,                                    //< N° de buttons
             marking = 6;                                   //< N° de marcações
   
   int hits = 0;                                            //< Acertos

   JButton[] btn  = new JButton[size];                      //< Buttons
   JButton   act  = new JButton("",new ImageIcon("rec/images/play.png")); 
   JLabel    label= new JLabel(" Escolha 6 numeros e aperte play ");
   boolean   flag = true;                                   //< Flag de controle do botão play

   // Color:
   Color bg = new Color(255,255,224);
   Color fg = Color.BLACK;

   // Icones:
   ImageIcon icn_play = new ImageIcon("rec/images/play.png");
   ImageIcon icn_replay = new ImageIcon("rec/images/replay.png");
   
   // Ticket:
   ArrayList<Integer> choose  = new ArrayList<Integer>();
   ArrayList<Integer> awarded = new ArrayList<Integer>();
   Random rand = new Random();

// Build
// =====
   Main(){
      super("Zebra de Ouro 2024");
      init_mabr();
      init_panel();
      init_label();
      init_buttons();
      init_frame();
   }   

//------------------------------- Startup -------------------------------
   //! Startup Frame
   private void init_frame(){
      setSize(680,480);
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
      act.setBorderPainted(false);
      act.setContentAreaFilled(false);
      
      act.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if(choose.size()<marking)return;
            if(flag) play(); 
            else     replay();
            flag=!flag;
         }
      });

      mn.setBorder(BorderFactory.createLineBorder(bg));
      mn.add(new JSeparator(0));
      mn.add(act);
   }

   //! Startup Label
   private void init_label(){
      JPanel panel = new JPanel();
      panel.setBackground(bg);
      panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      label.setFont(Fonts.create("rec/fonts/font.ttf",31));
      panel.add(label);
      pnl.add(panel); 
   }

   //! Startup Panel
   private void init_panel(){ pnl.setBorder(BorderFactory.createLineBorder(bg)); }

   // Startup Buttons
   private void init_buttons(){
      // Setting button
      for(int i=0;i<size;i++) {
         btn[i]=(i<10)?(new JButton("0"+i)):(new JButton(""+i)); //< Create button 
         btn[i].setBackground(bg);
         btn[i].setForeground(fg);
         btn[i].setFont(Fonts.create("rec/fonts/font.ttf",20));
         pnl.add(btn[i]);
      }

      // Atribuir evento
      for(var button: btn) button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if(choose.size() < marking) select(button);
         }
      });
   }

//------------------------------- Actions -------------------------------
   //! Play action
   private void play(){
      act.setIcon(icn_replay); 
      for(int i=0;i<marking;i++) awarded.add(rand.nextInt(size));
      
      for(var chs:choose)
         for(var awd:awarded) 
            if(chs==awd){
               hits++;
               btn[chs].setBackground(Color.YELLOW);
               btn[chs].setForeground(fg);
            }else{
               btn[awd].setBackground(Color.GREEN);
            }
         
   }

   //! Button default
   private void dft(JButton btn){
      btn.setBackground(bg);
      btn.setForeground(fg);
   }

   //! Replay action
   private void replay(){
      act.setIcon(icn_play);
      for(var i:awarded) dft(btn[i]); 
      for(var i:choose)  dft(btn[i]);      
      choose.clear();
      awarded.clear();
      hits=0;

   }

   //! Select
   private void select(JButton btn){
      btn.setBackground(Color.RED);
      btn.setForeground(Color.WHITE);
      choose.add(Integer.parseInt(btn.getText()));
   }
   
//------------------------------- Main -------------------------------
   public static void main(String[] args) { new Main(); }
}


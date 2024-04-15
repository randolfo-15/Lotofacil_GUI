import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import java.awt.Graphics;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.CardLayout;
import java.awt.Container;


class Mega extends JPanel{
// Fields
// ======
   static JLabel label  = new JLabel(" Escolha "+Main.marking+" numeros e aperte play ");

   static final int size = 100;                                    
   
   static int hits = 0;                                           

   static Btn[] btn  = new Btn[size];                            

   // Color:
   static Color bg = new Color(255,255,224);
   static Color fg = Color.BLACK;

   
   // Ticket:
   static ArrayList<Integer> choose  = new ArrayList<Integer>();
   static ArrayList<Integer> awarded = new ArrayList<Integer>();
   static Random rand = new Random();


//------------------------------- Startup -------------------------------

   Mega(){
      init_panel();
      init_label();
      init_buttons();
   }


   //! Startup Panel
   private void init_panel(){ setLayout(new BorderLayout()); }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon(Main.background).getImage(),0,0, this.getWidth(), this.getHeight(), this);
        
    }


   // Startup Buttons
   private void init_buttons(){
      // Setting button
      JPanel panel = Main.panel(new FlowLayout());
      //panel.setBackground(Main.dft);
      for(int i=0;i<size;i++) {
         btn[i]=(i<10)?(new Btn("0"+i,true)):(new Btn(""+i,true)); //< Create button 
         btn[i].setBackground(bg);
         btn[i].setForeground(fg);
         btn[i].setFont(Fonts.create("rec/fonts/font.ttf",20));
         panel.add(btn[i]); 
      }

      // Atribuir evento
      for(var button: btn) button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if((choose.size() < Main.marking||
               check_choose(Integer.parseInt(button.getText())))
               &&Main.flag
            ) 
               select(button);
         }
      });
      add(panel,BorderLayout.CENTER);
   }

//------------------------------- Actions -------------------------------


   //! Select
   static void select(Btn btn){
      if(btn.clicked){ 
         btn.setBackground(Color.RED);
         btn.setForeground(Color.WHITE);
         choose.add(Integer.parseInt(btn.getText()));
         btn.clicked=false;
      }else{
         Btn.dft(btn);
         btn.clicked=true;
         for(int i=0;i<choose.size();i++) 
            if(choose.get(i)==Integer.parseInt(btn.getText())){
               choose.remove(i);
               break;
            }
      }
   }
   
   // Check chooses
   static boolean check_choose(int n){
      for(var chs:choose) if(n==chs) return true;
      return false;
   }

   //! Startup Label
   private void init_label(){
      JPanel panel = new JPanel();
      panel.setBackground(Btn.bg);
      panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      label.setFont(Fonts.create("rec/fonts/font.ttf",31));
      panel.add(label);
      add(panel,BorderLayout.SOUTH); 
   }

//------------------------------- Main -------------------------------
   //! create splash
   static void create_splash(){ 
      JFrame fm = new JFrame();
      JLabel lb = new JLabel(new ImageIcon("rec/images/zebra0.png"));
      JProgressBar prog =new JProgressBar(0,100);
      JPanel p1 = new JPanel(new BorderLayout());
      fm.setSize(500,470);
      fm.setLocationRelativeTo(null);
      fm.setUndecorated(true);
      
      Color tras = new Color(0,0,0,1);
      
      fm.setBackground(tras); 
      p1.setBackground(tras); 
      prog.setForeground(new Color(255,165,0));

      p1.add(lb,BorderLayout.CENTER);
      p1.add(prog,BorderLayout.SOUTH);
      fm.add(p1);

      fm.setVisible(true);
      try{ 
         for(int i=0;i<100;i++){
            Thread.sleep(30);
            prog.setValue(i);
         }
      }
      catch(Exception e){ e.printStackTrace(); }
      fm.setVisible(false);
   }         

}

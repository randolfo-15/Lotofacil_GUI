/*!***************************************************
 *  Main
 * ======
 * @author : Randolfo A Goncalves
 * @file   : Main.java 
 * @date   : 04-04-24
 *****************************************************/

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.CardLayout;
import java.awt.Container;

class Main extends JFrame{
//--------------------------------------------------------------------------   
// Field
//--------------------------------------------------------------------------

   //  Panels
   // --------
   private CardLayout card   = new CardLayout();                     //! Manager panels.
   private Container  buffer = null;                                 //! Container de paneis.
   private Map<String,JPanel> panel = new HashMap<String,JPanel>();  //! Units Panel.

   //  Buttons
   // ---------
   private Map<String,JButton> buttons = new HashMap<String,JButton>();      //! Units Buttons.
   public static  JButton action = new JButton();
   static boolean flag = true ;
   
   //  Menu Bar
   // ----------
   private JMenuBar mbar = new JMenuBar();

   //  Color 
   // -------
   static Color dft = new Color(0,0,0,1); 
   static final String background = "rec/images/background.png";
   
   //  Slinder
   // ---------
   static int marking = 6;                                         
   static JSlider slider = new JSlider(1,46,marking);

   //  Label
   // -------
   static JLabel status = new JLabel("");

   //  Radio button
   // --------------
   static JRadioButton[] prir   = new JRadioButton[]{
      new JRadioButton("Par"),
      new JRadioButton("Impar")  
   };
   static ButtonGroup group = new ButtonGroup();

   //  Icones
   // --------
   ImageIcon icn_play = new ImageIcon("rec/images/play.png");
   ImageIcon icn_replay = new ImageIcon("rec/images/replay.png");

//--------------------------------------------------------------------------   
// Build
//--------------------------------------------------------------------------

   Main(){
      super("Zebra de ouro 2024");
      init(buffer);
      init(mbar); 
      init(this);
   }

   //! Startup frame
   private void init(JFrame my){
      my.setSize(680,490);
      my.setResizable(false);
      my.setLocationRelativeTo(null);
      my.setDefaultCloseOperation(EXIT_ON_CLOSE);
      my.setJMenuBar(mbar);
      my.setIconImage(new ImageIcon("rec/images/zebra0.png").getImage());
      card.show(buffer, "MENU");
      my.setVisible(true);
   }
//--------------------------------------------------------------------------   
// Buffer
//--------------------------------------------------------------------------   

   //! Startup buffer
   private void init(Container panels){
      buffer = getContentPane();
      buffer.setLayout(card);

      panel.put("MENU",init(panel.get("MENU"))); 
      panel.put("MEGA",new Mega()); 
      panel.put("LETR",new Letter()); 
      panel.put("PRIR",new ParImpar()); 
     
      panel.forEach((key,pnl) -> buffer.add(key,pnl));
   }
//--------------------------------------------------------------------------   
// Menu Bar
//--------------------------------------------------------------------------
   //! Play action
   private void play(){
      action.setIcon(icn_replay); 
      slider.setVisible(false);
      status.setText(" Resultado: ");
      int value=0;
      boolean exist = false;
      for(int i=0;i<Main.marking;i++){
         do{
            exist =false;
            value = Mega.rand.nextInt(0,Mega.size);
            for(var awd:Mega.awarded) if(awd==value){ exist=true;break;}
         }while(exist);
         Mega.awarded.add(value);
         Mega.btn[value].setBackground(Color.GREEN);
      } 

      for(var awd:Mega.awarded)
         for(var chs:Mega.choose)
            if(awd==chs) {
               Mega.hits++;
               Mega.btn[awd].setBackground(Color.YELLOW);
               Mega.btn[awd].setForeground(Btn.fg);
               break;
            }
      Mega.label.setText("Ganhou R$ "+(Mega.hits*500.00));
   }



   //! Replay action
   private void replay(){
      action.setIcon(icn_play);
      slider.setVisible(true);
      status.setText(" Apostas: ");
      for(var i:Mega.awarded) Btn.dft(Mega.btn[i]); 
      for(var i:Mega.choose) {
         Btn.dft(Mega.btn[i]);      
         Mega.btn[i].clicked=true;
      }
      Mega.choose.clear();
      Mega.awarded.clear();
      Mega.hits=0;
      Mega.label.setText(" Escolha "+Main.marking+" numeros e aperte play ");
   }

   private void radio_button(){
      for(var radio: prir){
         radio.setFont(Fonts.create("rec/fonts/font.ttf",18));;
         radio.setFocusPainted(false);
         radio.setBorderPainted(false);
         group.add(radio);
      } 
      
   }

   private void slinder(){
      slider.setMajorTickSpacing(5);
      slider.setMinorTickSpacing(1);
      slider.setPaintTicks(true);
      slider.setPaintLabels(true);
      slider.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent arg0) {
            int value = slider.getValue();
            if(value<marking) 
            { 
               for(int i=Mega.choose.size()-1;i>=value;i--) Mega.select(Mega.btn[Mega.choose.get(i)]);
               marking=value;
            }else marking = value;

            Mega.label.setText(" Escolha "+marking+" numeros e aperte play "); 
         }
      });
   }

   private void actions(){
      action.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if(Mega.choose.size()<marking){
               JOptionPane.showMessageDialog(
                     null,
                     " Numero insulficiente de casas marcadas.",
                     "",
                     0,
                     new ImageIcon("rec/images/zebra1.png")
               );
               return;
            }
            if(flag) play(); 
            else     replay();
            flag=!flag;
         }
      });
   }

   private void init(JMenuBar mbar){
      recource("MENU");
      active_btn(buttons.get("MENU"),"rec/images/menu.png"); 
      active_btn(action,"rec/images/play.png");
      status.setFont(Fonts.create("rec/fonts/font.ttf",25));
      radio_button();
      slinder();
      actions();

      mbar.add(status);
      for(var n: prir) mbar.add(n);
      mbar.add(slider);
      mbar.add(new JSeparator(0));
      mbar.add(action);
      mbar.add(buttons.get("MENU"));
   }

   private void active_btn(JButton btn, String image){
      btn.setIcon(new ImageIcon(image));
      btn.setBackground(dft);
      btn.setFocusPainted(false);
      btn.setBorderPainted(false);
      btn.setOpaque(false);
   }
   
//--------------------------------------------------------------------------   
// Buttons
//--------------------------------------------------------------------------

   //! Startup buttons
   private void init(Map<String,JButton> btn){
      buttons.put("MENU",new JButton("")); 
      buttons.put("MEGA",new JButton(" Lotofacil ")); 
      buttons.put("LETR",new JButton(" Jogo das Letras ")); 
      buttons.put("PRIR",new JButton(" Impar ou Par ")); 
     
      buttons.forEach((key,value) -> init(key,value));
   }   

   //! Setting Btns
   private void init(String key , JButton btn){
      btn.setFont(Fonts.create("rec/fonts/font.ttf", 40));
      btn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            recource(key);
            card.show(buffer, key);
         }
      });
   }

   //! Features
   private void recource(String key){
      switch (key){
         case "MEGA":
         {
            status.setText(" Apostas: ");
            visible(true,key) ;     
         }
         break;
         case "LETR":
         { 
            status.setText(" Escolha uma letra: "); 
            visible(false,key);
         } 
         break;
         case "MENU":
         {
            status.setText(""); 
            visible(false,key);
         } 
         break;
         case "PRIR":
         {
            status.setText(" Escolha uma categoria: ");
            visible(false,key);
         }
         break;
      }
   }

   //! visible recource
   private void visible(boolean value,String key){
      slider.setVisible(value);
      action.setVisible(value);
      value = (key.equals("PRIR"))?!value:false;
      for(var n: prir) n.setVisible(value);
   } 
//--------------------------------------------------------------------------   
// Menu
//--------------------------------------------------------------------------
   private JPanel init(JPanel menu){
      menu = panel(new FlowLayout());
      
      JPanel layout = new JPanel(new GridLayout(4,1,15,30)); 
      layout.setBackground(dft);
      
      init(buttons);                                                  
      layout.add(new JLabel(""));
      layout.add(buttons.get("MEGA"));
      layout.add(buttons.get("LETR"));
      layout.add(buttons.get("PRIR"));
      
      menu.add(layout);
      return menu;                                 
   }   

   static JPanel panel(LayoutManager mng){
      Graph pnl = new Graph("rec/images/background.png");
      return pnl;
   }
//--------------------------------------------------------------------------   
// main
//--------------------------------------------------------------------------

   public static void main(String[] args) { 
      Mega.create_splash();
      new Main(); 
   }
}


/*!***************************************************
 *  Letter
 * ========
 * @author : Randolfo A Goncalves
 * @file   : Letter.java 
 * @date   : 04-04-24
 *****************************************************/
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;

class Letter extends Graph{
// Field: 
   private Btn[] bts = new Btn[26];
   private JPanel    pnl = new JPanel(new GridLayout(4,4,6,6)),
                     main = new JPanel(new BorderLayout());
   private List<String> chs = new ArrayList<String>();  
// Build:
   Letter(){ 
      super("rec/images/background.png");
      init();
   } 
   
   void init(){
      pnl.setBackground(new Color(0,0,0,1));
      main.setBackground(new Color(0,0,0,1));

      // Buttons
      int ch = 65;
      for(int i=0;i<26;i++){ 
         init(i,(char)ch++); // Inicia buttons 
         pnl.add(bts[i]);    // Plug button 
      }
      
      // Plugs:
      main.add(pnl,BorderLayout.CENTER);
      add(main);

   }

   void init(int i,char c){
      bts[i] = new Btn(c+"",true);
      bts[i].setBackground(Btn.bg);
      bts[i].setForeground(Btn.fg);
      bts[i].setFont(Fonts.create("rec/fonts/digtal.ttf", 50));
      bts[i].addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){ 
            Btn.select(bts[i]);
            String test = ((char)(new Random().nextInt(65,91)))+"";
            boolean result = (bts[i].getText().equals(test))?true:false;
            JOptionPane.showMessageDialog(null,((result)?"Acertou ":"Errou ")+" a letra correta é "+test); 
            Btn.select(bts[i]);
         }
      });
   }
   
}

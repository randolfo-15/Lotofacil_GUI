/*!***************************************************
 *  Par Impar
 * ===========
 * @author : Randolfo A Goncalves
 * @file   : Letter.java 
 * @date   : 04-04-24
 *****************************************************/
import javax.swing.JPanel;
import javax.swing.JSeparator;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

class ParImpar extends JPanel{
//-----------------------------------------------------------------------
// Field
//-----------------------------------------------------------------------
    private Btn[] bts = new Btn[10]; 
    
    private JPanel[] pnl =new JPanel[]{
        new JPanel(new GridLayout(5,1,5,3)),
        new JPanel(),
        new JPanel(new GridLayout(5,1,5,3))
    }; 

    int choose = 0,
        awd = 0;

//-----------------------------------------------------------------------
// Build
//-----------------------------------------------------------------------
    ParImpar(){
        start();
    }

//-----------------------------------------------------------------------
// Startups
//-----------------------------------------------------------------------
    private void start(){
        awd = Mega.rand.nextInt(1,6);
        for(int i=0;i<5;i++)  pnl[0].add(init_pc(i));
        for(int i=5;i<10;i++) pnl[2].add(init_user(i));
        pnl[1].add(new JLabel(new ImageIcon("rec/images/zebra0.png")));
        for(var panel:pnl) {
            panel.setBackground(Main.dft);
            add(panel); 
        }
    }
    
    private void setFont(Btn btn){
        btn.setFont(Fonts.create("rec/fonts/digtal.ttf", 45)); 
    }

    private Btn init_pc(int i){
        bts[i] = new Btn("X",false); 
        setFont(bts[i]);
        return bts[i];
    }

    private Btn init_user(int i){
       bts[i] = new Btn((i-4)+"",true); 
       setFont(bts[i]);
       
       bts[i].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Btn.select(bts[i]);        
               Btn.select(bts[i-5]);        
               choose = i-4; 
               bts[i-5].setText(awd+"");
               JOptionPane.showMessageDialog(
                       null,
                       (result(choose+awd))
               ); 
                awd = Mega.rand.nextInt(1,6);
                bts[i-5].setText("X");
                bts[i-5].clicked=false;
                Btn.select(bts[i]);        
           }
       }); 
    
       return bts[i];
    }

    private String result(int value){
        if(Main.group.isSelected(Main.prir[0].getModel())) 
            return ((value%2==0)?" Ganhou deu Par - ":"Perdeu deu Impar - ")+value;
        else if(Main.group.isSelected(Main.prir[1].getModel())) 
            return ((value%2==1)?" Ganhou deu Impar - ":"Perdeu deu Par - ")+value;
        else return "Selecione uma categoria";
    }


//-----------------------------------------------------------------------
// Setting
//-----------------------------------------------------------------------
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon(Main.background).getImage(),0,0, this.getWidth(), this.getHeight(), this);
        
    } 
}

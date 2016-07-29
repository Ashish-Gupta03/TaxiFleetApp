/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxifleet;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author ashish
 */
public class TaxiObject {
  static JFrame jFrame=new JFrame("Taxi Fleet System");
private static int x,y;
    private static draw object=new draw();

    public static void main(String args[])
{    
    jFrame.setVisible(true);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setSize(1400, 900);
  //      jFrame.pack();        
    jFrame.getContentPane().add(object);
JLabel jLabel1=new JLabel();
jLabel1.setText("Taxi Fleet System");
jLabel1.setFont(new Font("TimesRoman", Font.BOLD, 20));
jLabel1.setLocation(jFrame.getHeight(), jFrame.getWidth());
jFrame.getContentPane().add(jLabel1);
    
    object.addMouseListener(new CarShapes());
}
    static class CarShapes extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)  
        {
            x=e.getX();
            y=e.getY();            
            object.drawing(x,y,jFrame);
        }
    }
}

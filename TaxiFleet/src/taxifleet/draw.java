/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxifleet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 *
 * @author ashish
 */
public class draw extends JPanel {
    private static int x,y;
    private BufferedImage availableImg,bookedImg;    
    TaxiFleet t=new TaxiFleet();
            TaxiService ts=new TaxiService();

    MouseListener ml = new MouseAdapter() {};
    ArrayList<Taxi> newT=new ArrayList<Taxi>();
    JPanel jp=new JPanel();
    public draw()
    {
        setBackground(Color.white);
        setPreferredSize(new Dimension(1000, 1000));
        setBorder(BorderFactory.createEtchedBorder(Color.orange, Color.red));
        try{
         availableImg=ImageIO.read(getClass().getResourceAsStream("Beetle_yellow.png"));        
        }
        catch(IOException e)
        {}
        //t.start(10);        
    }
    
    
    
    Point start;

    Point end;        
    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        start = new Point((int)e.getX(),(int)e.getY());
//.getPoint();
    }

    public void mouseDragged(MouseEvent e) {
        end = new Point((int)e.getX(),(int)e.getY());
        repaint();
    }

    public void mouseReleased(MouseEvent e) {
        int from = (int) (start.getX() / 130);
        int to = (int) (end.getX() / 130);
        repaint();
    }

    
    public void mouseEntered(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }
    
    public void drawing(int x,int y,JFrame jf)
    {
        int flag1=0;
        this.x=x;
        this.y=y;     
        
         String test1= JOptionPane.showInputDialog("Please input user id: ");
                  
   int status=0;
   
   for(User u:ts.usrs)
   {
       System.out.println("user: "+u.getUserId()+"bal is "+ u.getBal() +"cab is "+ u.getCabId() +"ride is "+u.getRides() +"isbusy: ");       
        if(u.getUserId()==Integer.parseInt(test1) && u.getCabId()!=-1)
        {status=ts.release(Integer.parseInt(test1), new Location(x,y));
        flag1=1;break;}
   }
   if(flag1==0)
       status=ts.request(Integer.parseInt(test1), new Location(x,y));
    for(User u:ts.usrs)
   {
       System.out.println("user: "+u.getUserId()+"bal is "+ u.getBal() +"cab is "+ u.getCabId() +"ride is "+u.getRides() +"isbusy: ");       
   }
   System.out.println("Status is: "+status);   
//         JLabel jl=new JLabel("Check Visible");
//         jp.add(jl);
//         jl.setText("Status is: "+status);
//         jl.setVisible(true);
//        System.out.println("x is: "+x+" y is: "+y);    
//         jl.setFont(new Font("TimesRoman", Font.BOLD, 16));
//         //jf.add(jl);
//         jl.setVisible(true);        
//             Timer timer = new Timer(1000,new ActionListener(){
//        @Override   
//    public void actionPerformed(ActionEvent arg0) {        
//    removeAll();
//        validate();       
//       //  t.start(1);
//        newT=t.getTaxis(); 
//
//        repaint();
//        
//        setLayout(null);
//        }
//    });
//    timer.start();             
    repaint();
    }    
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        g.setColor(Color.BLACK);
        g.drawImage(availableImg,50 ,10 , this);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("Taxi Fleete System",600, 30);   
        g.drawLine(0, 40,getWidth(),40);
        
        newT=t.getTaxis();
         for(Taxi tx:newT){
                int id=tx.getId();
                int x=tx.getLoc().getX();
                int y=tx.getLoc().getY();
                int rider=tx.getRider_id();
                if(rider!=-1)tx.setStatus(1);
                boolean status=tx.isBusy();
                System.out.println("taxiID: "+id+" rider id: "+rider+" status: "+status);
                if(status)
                    g.setColor(Color.RED);
                else g.setColor(Color.YELLOW);
                g.fillRect(x, y-45, 38,38);
                g.setColor(Color.BLUE);
                g.setFont(new Font("TimesRoman", Font.BOLD, 9));
                g.drawString("Rider:"+rider,x, y-10);
                g.setFont(new Font("TimesRoman", Font.BOLD, 12));
                //g.drawString("Y:"+y,x, y+20);
                g.drawString(""+id,x+15, y-25);
            }
  
//object.add(lb);
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 2, 2);  
    }
}
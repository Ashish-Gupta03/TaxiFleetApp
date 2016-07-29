/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxifleet;
import java.util.*;
import static taxifleet.TaxiService.usrs;

/**
 *
 * @author rkjain2001
 */
public class TaxiFleet {
    private int num_taxis;
    static ArrayList<Taxi> taxi;
    ArrayList<Long> start_time;

    public TaxiFleet() {
        num_taxis=0;
        taxi=new ArrayList<>();
        start_time=new ArrayList<>();
    }

    public ArrayList<Taxi> getTaxis() {  
        Random r = new Random();
        for(int i=0;i<num_taxis;i++){
              int Low = 0;
int High = 1000;
int x = r.nextInt(High-Low) + Low;
int y = r.nextInt(High-Low) + Low;
            taxi.get(i).setLoc(new Location(x,y));                        
            }                   
        return taxi;
    }

    public int getNum_taxis() {
        return num_taxis;
    }

    public void setNum_taxis(int num_taxis) {
        this.num_taxis = num_taxis;
    }
    private int distance(Location l1,Location l2){
        int X=l1.getX()-l2.getX();
        int Y=l1.getY()-l2.getY();
        int xsq=X*X;
        int ysq=Y*Y;
        return (int)Math.sqrt(xsq+ysq);
    }
    
    public void start(int num){
         Random r = new Random();
        for(int i=0;i<num;i++){
              int Low = 0;
int High = 1000;
int x = r.nextInt(High-Low) + Low;
int y = r.nextInt(High-Low) + Low;
            taxi.add(new Taxi(i,0,new Location(x,y)));
            start_time.add(-1l);
        }
        num_taxis=num;
    }
    public int request(int userId,Location loc){
        int u=0,i=0,flag=-1;
        int nearest=100000;
        int nearest_t=-1;
        for(i=0;i<num_taxis;i++){
            int x;
            x = distance(taxi.get(i).getLoc(),loc);
            if (x<nearest && !(taxi.get(i).isBusy())){
                nearest=x;
                nearest_t=i;
            }
        }
        taxi.get(nearest_t).setStatus(1);
        taxi.get(nearest_t).setRider_id(userId);
        start_time.set(nearest_t, System.currentTimeMillis());
        System.out.println("userid: "+userId);
        for(i=0;i<usrs.size();i++){
            if(usrs.get(i).getUserId()==userId)
             flag=i;                
        }
        try{
         u=flag;
        }
        catch(Exception e){e.printStackTrace();}
        System.out.println("index s: "+u);
        if(u!=-1)
            TaxiService.usrs.get(u).setCabId(nearest_t);
        else {
            //TaxiService.usrs.add(new User(userId));
            u=flag;
            TaxiService.usrs.get(u).setCabId(nearest_t);
        }
        return nearest_t;
    }
    
    public int release(int userId, Location loc){
        int j,flag=-1;
        for(j=0;j<usrs.size();j++){
            if(usrs.get(j).getUserId()==userId)
             flag=j;                
        }
        int cabId=TaxiService.usrs.get(flag).getCabId();
        long start=start_time.get(cabId);
        long end=System.currentTimeMillis();
        taxi.get(cabId).setLoc(loc);
        taxi.get(cabId).setRider_id(-1);
        taxi.get(cabId).setStatus(0);
        return (int)((end-start)/(1000));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxifleet;
import java.util.*;
import static taxifleet.TaxiFleet.taxi;
/**
 *
 * @author rkjain2001
 */
public class TaxiService {

    /**
     * @param args the command line arguments
     */
    static ArrayList<User> usrs;
    TaxiFleet tf;
    
    public TaxiService(){
        usrs=new ArrayList<>();
        usrs.add(new User(138));
        tf=new TaxiFleet();
        tf.start(10);
    }
        
    public int request(int userId,Location loc){
        int i,flag=-1,bal=0;
        if(userId>160)
            return -3;//-3 for invalid id
        
        for(i=0;i<usrs.size();i++){
            if(usrs.get(i).getUserId()==userId)
             flag=i;                
        }
        
        if(flag==-1){             
            usrs.add(new User(userId));            
            flag=usrs.size()-1;
            //taxi.get(flag).setRider_id(userId);
        }
        try{
        bal=usrs.get(flag).getBal();
        }
        catch(Exception e){e.printStackTrace();}
        
        if(bal<=5)
            return -2;//-2 for user cannot book due to insufficient balance
        else{
            int taxiId=tf.request(userId, loc);
            if(taxiId==-1){
                usrs.get(flag).setBal(bal-10);                
                return -1;//no more taxis left
            }
            else{
                usrs.get(flag).setCabId(taxiId);
            }
        }
        return 0;
    }
    
    public int release(int userId,Location loc){
        int flag=-1,j;
        int time=tf.release(userId, loc);
        int cost=6*time;
        for(j=0;j<usrs.size();j++){
            if(usrs.get(j).getUserId()==userId)
             flag=j;                
        }
        int i=flag;
        if(i==-1)
            return -1;//invalid user
        usrs.get(i).setCabId(-1);
        taxi.get(i).setStatus(0);
        usrs.get(i).setBal(usrs.get(i).getBal()-cost);
        usrs.get(i).setRides(usrs.get(i).getRides()+1);
        return 0;
    }
    
    public void freqUser(){
        for(int i=0;i<usrs.size();i++){
            if(usrs.get(i).getRides()==5){
                usrs.get(i).setBal(usrs.get(i).getBal()+50);
                usrs.get(i).setRides(0);
            }
        }
    }
    
    public static void main(String[] args) {
        TaxiService T=new TaxiService();
        //T.start(10);
        T.request(138, new Location(3,4));
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxifleet;

/**
 *
 * @author rkjain2001
 */
public class Taxi {
    private int id;
    private int status;//busy=1 else 0 is free
    private int rider_id;//-1 if taxi is free
    private Location loc;

    public Taxi(int id, int status, Location loc) {
        this.id = id;
        this.status = status;
        this.rider_id =-1;
        this.loc = loc;
    }

    public Taxi(int id) {
        this.id = id;
        this.status = 0;
        this.rider_id =-1;
        this.loc = new Location(0,0);
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBusy() {
        if(status==1)
            return true;
        else
            return false;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRider_id() {
        return rider_id;
    }

    public void setRider_id(int rider_id) {
        this.rider_id = rider_id;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    
    
//    public static void main(String[] args) {
//        taxiservice.TaxiService t= new taxiservice.TaxiService();
//        t.start(10);
//        int i=10;
//        i = t.request(6, new taxiservice.Location(5,6));
//        System.out.println(i);
//    }
}

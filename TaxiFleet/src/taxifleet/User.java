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
public class User {
    private int UserId;
    private int Bal;
    private int rides;
    private int CabId;
    
    public User(int usrid){
        this.UserId=usrid;
        Bal=100;
        rides=0;
        CabId=-1;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public int getBal() {
        return Bal;
    }

    public void setBal(int Bal) {
        this.Bal = Bal;
    }

    public int getRides() {
        return rides;
    }

    public void setRides(int rides) {
        this.rides = rides;
    }

    public int getCabId() {
        return CabId;
    }

    public void setCabId(int CabId) {
        this.CabId = CabId;
    }
    
    
}

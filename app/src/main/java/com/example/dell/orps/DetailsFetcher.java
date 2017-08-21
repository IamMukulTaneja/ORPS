package com.example.dell.orps;

/**
 * Created by Mukul on 20-08-2017.
 */

public class DetailsFetcher {

    private String slot_fpno,Book_time,Commit_status,checkin,checkout,key;
    public DetailsFetcher(){}
    public DetailsFetcher(String key,String slot_fpno,String Book_time,String Commit_status,String checkin,String checkout ){
       this.key=key;
        this.slot_fpno=slot_fpno;
        this.Book_time=Book_time;
        this.Commit_status=Commit_status;
        this.checkin=checkin;
        this.checkout=checkout;
    }
    public String getSlot()
    {
        return slot_fpno;
    }
    public void setSlot(String slot_fpno)
    {
        this.slot_fpno=slot_fpno;
    }
    public String getBook_time()
    {
        return Book_time;
    }

    public void setBook_time(String Book_time)
    {
        this.Book_time=Book_time;
    }
    public String getCommit_Status()
    {
        return Commit_status;
    }
    public void setCommit_status(String Commit_Status)
    {
        this.Commit_status=Commit_Status;
    }
    public String getCheckin()
    {
        return checkin;
    }
    public void setCheckin(String checkin){
        this.checkin=checkin;
    }
    public String getCheckout()
    {
        return checkout;
    }
    public void setCheckout(String checkout)
    {
        this.checkout=checkout;
    }
    public void setKey(String key)
    {
        this.key=key;
    }
    public String getKey()
    {
        return key;
    }
}

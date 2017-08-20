package com.example.dell.orps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Mukul on 08-08-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ChecklistHolder> {
    int numberlist;
    public String mRegKey,mTime,mStatus,mCheckin,mCheckout;
    public MyAdapter(String key,String time,String status,String checkin,String checkout){
        mRegKey=key;
        mTime=time;
        mStatus=status;
        mCheckin=checkin;
        mCheckout=checkout;

    }public MyAdapter(int mNumberList){
      numberlist=mNumberList;
    }
    @Override
    public ChecklistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.list_layout,parent,false);
        ChecklistHolder checklistHolder=new ChecklistHolder(view);
        return checklistHolder;
    }
    @Override
    public void onBindViewHolder(ChecklistHolder holder, int position) {
        holder.reg_key.setText("res"+mRegKey);
    }

    @Override
    public int getItemCount() {
        return numberlist;
    }
    class ChecklistHolder extends RecyclerView.ViewHolder{
    TextView reg_key,time,car,status,checkin,checkout,cancel;
    public ChecklistHolder(View itemView) {
        super(itemView);
        reg_key=(TextView)itemView.findViewById(R.id.registrationkey);
        time=(TextView)itemView.findViewById(R.id.timetextview);
        car=(TextView)itemView.findViewById(R.id.carnumber);
        status=(TextView)itemView.findViewById(R.id.statustextview);
        checkin=(TextView)itemView.findViewById(R.id.checkin);
        checkout=(TextView)itemView.findViewById(R.id.checkout);
        cancel=(TextView)itemView.findViewById(R.id.canceltextview);
    }
}





}
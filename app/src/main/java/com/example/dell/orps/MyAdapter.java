package com.example.dell.orps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mukul on 08-08-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ChecklistHolder> {
List<DetailsFetcher> details;
    public MyAdapter(){}
   public MyAdapter(List<DetailsFetcher> list){
      details=list;
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
        DetailsFetcher detailsFetcher=details.get(position);
        holder.reg_key.setText(detailsFetcher.getKey());
        holder.status.setText(detailsFetcher.getCommit_Status());
        holder.time.setText(detailsFetcher.getBook_time());
        holder.car.setText("DL11SH7403");
        holder.checkin.setText("Checkin");
        holder.checkout.setText("Checkout");
        holder.cancel.setText("Cancel");
    }

    @Override
    public int getItemCount() {
        return details.size();
    }
    public class ChecklistHolder extends RecyclerView.ViewHolder{
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
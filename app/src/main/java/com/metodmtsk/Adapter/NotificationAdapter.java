package com.metodmtsk.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.metodmtsk.Pojo.Notification;
import com.metodmtsk.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder>{

    ArrayList<Notification> mProductList;
    LayoutInflater inflater;

    public NotificationAdapter(Context context, ArrayList<Notification> products) {
        inflater = LayoutInflater.from(context);
        this.mProductList = products;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_notification_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Notification selectedProduct = mProductList.get(position);
        holder.setData(selectedProduct, position);

    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productName, productDescription,productDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productDescription = (TextView) itemView.findViewById(R.id.productDescription);
            productDate = (TextView) itemView.findViewById(R.id.productDate);

        }

        public void setData(Notification selectedProduct, int position) {

            this.productName.setText(selectedProduct.getTitle());
            this.productDescription.setText(selectedProduct.getMessage());



            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy" );
            Date date;
            try {
                date = originalFormat.parse(selectedProduct.getSendDate());


                this.productDate.setText(targetFormat.format(date));

            } catch (ParseException ex) {
                // Handle Exception.
            }




        }


        @Override
        public void onClick(View v) {


        }


    }
}

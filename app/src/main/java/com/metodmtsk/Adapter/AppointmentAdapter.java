package com.metodmtsk.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.metodmtsk.Pojo.AppointmentClaim;
import com.metodmtsk.Pojo.AppointmentDetail;
import com.metodmtsk.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.MyViewHolder>{

    ArrayList<AppointmentClaim> mProductList;
    LayoutInflater inflater;

    public AppointmentAdapter(Context context, ArrayList<AppointmentClaim> products) {
        inflater = LayoutInflater.from(context);
        this.mProductList = products;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_appointment_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AppointmentClaim selectedProduct = mProductList.get(position);
        holder.setData(selectedProduct, position);

    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productName, productDescription,productLicence,productCount;

        public MyViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productDescription = (TextView) itemView.findViewById(R.id.productDescription);
            productLicence = (TextView) itemView.findViewById(R.id.licence);

        }

        public void setData(AppointmentClaim selectedProduct, int position) {

            this.productDescription.setText(selectedProduct.getCourse_section());
            this.productLicence.setText(selectedProduct.getLicence());


            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date date;
            try {
                date = originalFormat.parse(selectedProduct.getCourse_time());


                this.productName.setText(targetFormat.format(date));

            } catch (ParseException ex) {


            }
        }

        @Override
        public void onClick(View v) {


        }


    }
}

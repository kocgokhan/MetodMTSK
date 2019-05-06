package com.metodmtsk.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.metodmtsk.Activitiy.VideoDetailActivity;
import com.metodmtsk.Pojo.Video;
import com.metodmtsk.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder>{

    ArrayList<Video> mVideoList;
    LayoutInflater inflater;
    Intent intent;
    String link;
    String title;
    String detail;
    String category;
    public VideoAdapter(Context context, ArrayList<Video> video) {
        inflater = LayoutInflater.from(context);
        this.mVideoList = video;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_video_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Video selectedProduct = mVideoList.get(position);
        holder.setData(selectedProduct, position);

    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productName, productDescription,productCategory;
        ImageView productLinkImage,cardVideo;

        public MyViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productDescription = (TextView) itemView.findViewById(R.id.productDescription);
            productCategory = (TextView) itemView.findViewById(R.id.productCategory);
            productLinkImage = (ImageView) itemView.findViewById(R.id.linkImage);
            cardVideo = (ImageView) itemView.findViewById(R.id.cardVideo);
            cardVideo.setOnClickListener(this);
        }

        public void setData(Video selectedProduct, int position) {

            String imgResource = "https://img.youtube.com/vi/" +  selectedProduct.getLink() +  "/0.jpg";

            this.productName.setText(selectedProduct.getTitle());
            this.productCategory.setText(selectedProduct.getCategory());

            link=selectedProduct.getLink();
            title=selectedProduct.getTitle();
            category=selectedProduct.getCategory();
            detail=selectedProduct.getDetail();


            Picasso.get().load(imgResource).into(this.productLinkImage);
        }


        @Override
        public void onClick(View v) {

            cardVideo(v.getContext());
        }

        private void cardVideo(Context context) {

            Intent intent = new Intent(context, VideoDetailActivity.class);
            intent.putExtra("link", link);
            intent.putExtra("title", title);
            intent.putExtra("detail", detail);
            intent.putExtra("category", category);
            context.startActivity(intent);

        }


    }


}

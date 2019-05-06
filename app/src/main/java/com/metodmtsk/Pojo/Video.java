package com.metodmtsk.Pojo;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by GK
 * on 16.02.2019.
 */

public class Video {

    private static final String TAG = "UserPojo ";

    private int video_id;
    private String title, link, category,detail;
    // private ArrayList<UserOtherImage> userOtherImageArrayList = new ArrayList<>();

    public Video(JSONObject response, boolean isLogin) {
        try {
            this.video_id = response.getInt("video_id");
            this.title = response.getString("title");
            this.link = response.getString("link");
            this.detail = response.getString("detail");
            this.category = response.getString("category");
            if (isLogin) {
                this.video_id = response.getInt("video_id");
                this.title = response.getString("title");
                this.link = response.getString("link");
                this.detail = response.getString("detail");
                this.category = response.getString("category");
                //this.login = response.getBoolean("login");
            }
        } catch (JSONException e) {
            Log.wtf(TAG, "json parse catche dustu : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Video() {

    }

    public int getVideoID() {
        return video_id;
    }

    public String getTitle() {
        return title;
    }
    public String getDetail() {
        return detail;
    }

    public String getLink() {
        return link;
    }

    public String getCategory() {
        return category;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public static ArrayList<Video> getData(ArrayList<Video> ary) {
        ArrayList<Video> productList = new ArrayList<Video>();

        for (int i = 0; i < ary.size(); i++) {
            Video temp = new Video();

            temp.setTitle(ary.get(i).title);
           temp.setLink(ary.get(i).link);
            temp.setDetail(ary.get(i).detail);
            temp.setCategory(ary.get(i).category);

            productList.add(temp);

        }


        return productList;


    }

}

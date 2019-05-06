package com.metodmtsk.Pojo;

import android.util.Log;

import com.metodmtsk.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by GK
 * on 16.02.2019.
 */

public class Notification {

    private static final String TAG = "UserPojo ";

    private int userID;
    private String title, message, sendDate;
    private boolean notificationData;
    // private ArrayList<UserOtherImage> userOtherImageArrayList = new ArrayList<>();

    public Notification(JSONObject response, boolean isLogin) {
        try {
            this.notificationData = response.getBoolean("notificationData");
            this.title = response.getString("title");
            this.message = response.getString("message");
            this.sendDate = response.getString("sendDate");
            this.userID = response.getInt("userID");
            if (isLogin) {
                this.notificationData = response.getBoolean("notificationData");
                this.title = response.getString("title");
                this.message = response.getString("message");
                this.sendDate = response.getString("sendDate");
                this.userID = response.getInt("userID");
            }

        } catch (JSONException e) {
            Log.wtf(TAG, "json parse catche dustu : " + e.getMessage());
            e.printStackTrace();
        }
    }


    public Notification() {
        
    }

    public Notification(boolean b, String title, String message, String sendDate, JSONObject jsonObject) {
    }

    public boolean isNotificationData() {
        return notificationData;
    }

    public int getUserID() {
        return userID;
    }

    public String getTitle() {
        return title;
    }
    public String getMessage() {
        return message;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public void setNotificationData(boolean notificationData) {
        this.notificationData = notificationData;
    }

    public static ArrayList<Notification> getData(ArrayList<Notification> ary) {
        ArrayList<Notification> productList = new ArrayList<Notification>();

        for (int i = 0; i < ary.size(); i++) {
            Notification temp = new Notification();
           // temp.setTitle(ary.get(i).title);
            temp.setTitle(ary.get(i).title);
           //temp.setMessage(ary.get(i).message);
            temp.setMessage(ary.get(i).message);
            temp.setSendDate(ary.get(i).sendDate);

            productList.add(temp);

        }


        return productList;


    }
}


  /*  public ArrayList<UserOtherImage> getUserOtherImageArrayList() {
        return userOtherImageArrayList;
    }*/



package com.metodmtsk.Pojo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by GK
 * on 16.02.2019.
 */

public class Links {

    private static final String TAG = "UserPojo ";

    private String linkID,status;
    private String title, link;
    private boolean linkDataEqual;
    // private ArrayList<UserOtherImage> userOtherImageArrayList = new ArrayList<>();

    public Links(JSONObject response, boolean isLogin) {
        try {
            this.title = response.getString("title");
            this.link = response.getString("link");
            this.linkID = response.getString("linkID");
            this.status = response.getString("status");
            this.linkDataEqual = response.getBoolean("linkDataEqual");
            if (isLogin) {
                this.title = response.getString("title");
                this.link = response.getString("link");
                this.linkID = response.getString("linkID");
                this.status = response.getString("status");
                this.linkDataEqual = response.getBoolean("linkDataEqual");
            }

        } catch (JSONException e) {
            Log.wtf(TAG, "json parse catche dustu : " + e.getMessage());
            e.printStackTrace();
        }
    }



    public static String getTAG() {
        return TAG;
    }

    public String getLinkID() {
        return linkID;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public boolean isLinkDataEqual() {
        return linkDataEqual;
    }

    public void setLinkID(String linkID) {
        this.linkID = linkID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public void setLinkDataEqual(boolean linkDataEqual) {
        this.linkDataEqual = linkDataEqual;
    }


}




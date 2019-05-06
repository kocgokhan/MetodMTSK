package com.metodmtsk.Pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by GK
 * on 16.02.2019.
 */
public class LinkList extends ArrayList<LinkList> {

    private static final String TAG = "LinkList ";

    private String linkID,status;
    private String title, link;

    public LinkList(JSONObject response) {
        try {
            this.title = response.getString("title");
            this.link = response.getString("link");
            this.linkID = response.getString("linkID");
            this.status = response.getString("status");
        } catch (JSONException e) {
            Log.wtf(TAG, "json parse catche dustu : " + e.getMessage());
            e.printStackTrace();
        }
    }




    public LinkList() {

    }

    public LinkList(JSONObject jsonObject, boolean b) {
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



}

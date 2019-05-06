package com.metodmtsk.Pojo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class AppointmentSectionList {


    private static final String TAG = "AppointmentDetail ";

    private String course_section;

    public AppointmentSectionList(JSONObject response, boolean isLogin) {
        try {
            this.course_section = response.getString("course_section");
            if (isLogin) {
                this.course_section = response.getString("course_section");
                //this.login = response.getBoolean("login");
            }
        } catch (JSONException e) {
            Log.wtf(TAG, "json parse catche dustu : " + e.getMessage());
            e.printStackTrace();
        }
    }


    public String getCourse_section() {
        return course_section;
    }

    public static String getTAG() {
        return TAG;
    }


    public void setCourse_section(String course_section) {
        this.course_section = course_section;
    }



}

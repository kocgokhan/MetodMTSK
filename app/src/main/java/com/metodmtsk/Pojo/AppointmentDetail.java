package com.metodmtsk.Pojo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AppointmentDetail {


    private static final String TAG = "AppointmentDetail ";

    private String course_time;
    private String course_section;

    public AppointmentDetail(JSONObject response) {
        try {
            this.course_time = response.getString("course_time");
            this.course_section = response.getString("userImageID");
        } catch (JSONException e) {
            Log.wtf(TAG, "json parse catche dustu : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public AppointmentDetail() {

    }

    public String getCourse_time() {
        return course_time;
    }

    public String getCourse_section() {
        return course_section;
    }


    public static String getTAG() {
        return TAG;
    }

    public void setCourse_time(String course_time) {
        this.course_time = course_time;
    }

    public void setCourse_section(String course_section) {
        this.course_section = course_section;
    }




}

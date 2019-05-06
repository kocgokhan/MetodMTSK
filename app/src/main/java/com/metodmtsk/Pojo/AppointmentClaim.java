package com.metodmtsk.Pojo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AppointmentClaim {


    private static final String TAG = "AppointmentDetail ";

    private String course_time;
    private String course_section;
    private String appointment_count;
    private String licence;
    private boolean ClaimEqual;

    public AppointmentClaim(JSONObject response) {
        try {
            this.course_time = response.getString("course_time");
            this.course_section = response.getString("course_section");
            this.appointment_count = response.getString("appointment_count");
            this.licence = response.getString("licence");
            this.ClaimEqual = response.getBoolean("ClaimEqual");
        } catch (JSONException e) {
            Log.wtf(TAG, "json parse catche dustu : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public AppointmentClaim(JSONObject jsonObject, boolean b) {

    }

    public AppointmentClaim() {

    }

    public String getCourse_time() {
        return course_time;
    }

    public String getCourse_section() {
        return course_section;
    }

    public String getAppointment_count() {
        return appointment_count;
    }

    public String getLicence() {
        return licence;
    }

    public boolean getClaimEqual() {
        return ClaimEqual;
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

    public void setAppointment_count(String appintment_count) {
        this.appointment_count = appintment_count;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public void setClaimEqual(boolean claimEqual) {
        ClaimEqual = claimEqual;
    }



    public static ArrayList<AppointmentClaim> getDriverAppintmentList(ArrayList<AppointmentClaim> ary) {

        ArrayList<AppointmentClaim> productList = new ArrayList<AppointmentClaim>();

        for (int i = 0; i < ary.size(); i++) {
            AppointmentClaim temp = new AppointmentClaim();
            // temp.setTitle(ary.get(i).title);
            temp.setCourse_time(ary.get(i).course_time);
            //temp.setMessage(ary.get(i).message);
            temp.setCourse_section(ary.get(i).course_section);
            temp.setLicence(ary.get(i).licence);

            productList.add(temp);

        }

        return productList;
    }
}

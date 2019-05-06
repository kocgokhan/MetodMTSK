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

public class Appointment {

    private static final String TAG = "UserPojo ";

    private ArrayList<AppointmentDetail> driverAppintmentList = new ArrayList<>();
    private String appointment_count;
    // private ArrayList<UserOtherImage> userOtherImageArrayList = new ArrayList<>();

    public Appointment(JSONObject response, boolean isLogin) {
        try {
            this.appointment_count = response.getString("appointment_count");
            if (isLogin) {
                this.appointment_count = response.getString("appointment_count");
                //this.login = response.getBoolean("login");
            }
            else {

                JSONArray jsonArray = response.getJSONArray("DriverAppointments");
                for (int i = 0; i < jsonArray.length(); i++) {
                    AppointmentDetail userOtherImage = new AppointmentDetail(jsonArray.getJSONObject(i));
                    driverAppintmentList.add(userOtherImage);
                }
            }
        } catch (JSONException e) {
            Log.wtf(TAG, "json parse catche dustu : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Appointment() {

    }




    public String getAppointment_count() {
        return appointment_count;
    }

    public void setAppointment_count(String appointment_count) {
        this.appointment_count = appointment_count;
    }




}





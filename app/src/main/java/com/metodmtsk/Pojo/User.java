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

public class User {

    private static final String TAG = "UserPojo ";

    private int driver_id;
    private String name, surname, email, password,phone,address;
    private String age;
    private String file_name;
    private int status;
    private int appointment_count;
    private String regDate, visitDate;
    private String oneSignalID;
    private String country;
    private String state;
    private String licence_type;
    private ArrayList<LinkList> linkList = new ArrayList<>();

    public User(JSONObject response, boolean isLogin) {
        try {
            this.driver_id = response.getInt("driver_id");
            this.appointment_count = response.getInt("appointment_count");
            this.name = response.getString("name");
            this.surname = response.getString("surname");
            this.age = response.getString("age");
            this.file_name = response.getString("file_name");
            this.oneSignalID = response.getString("oneSignalID");

            if (isLogin) {
                this.driver_id = response.getInt("driver_id");
                this.appointment_count = response.getInt("appointment_count");
                this.name = response.getString("name");
                this.surname = response.getString("surname");
                this.age = response.getString("age");
                this.file_name = response.getString("file_name");
                this.oneSignalID = response.getString("oneSignalID");
                this.oneSignalID = response.getString("oneSignalID");
                //this.login = response.getBoolean("login");
                JSONArray jsonArray = response.getJSONArray("linkList");
                for (int i = 0; i < jsonArray.length(); i++) {
                    LinkList linkLists = new LinkList(jsonArray.getJSONObject(i));
                    linkList.add(linkLists);
                }
            }
        } catch (JSONException e) {
            Log.wtf(TAG, "json parse catche dustu : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public User() {


    }

    public int getDriverID() {
        return driver_id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public String getAge() {
        return age;
    }

    public String getImage() {
        return file_name;
    }

    public int getStatus() {
        return status;
    }

    public int getAppointment_count() {
        return appointment_count;
    }


    public String getRegDate() {
        return regDate;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public String getOneSignalID() {
        return oneSignalID;
    }

    public String getCountry() {
        return country;
    }

    public void setAppointment_count(int appointment_count) {
        this.appointment_count = appointment_count;
    }

    public ArrayList<LinkList> getLinkList() {



        return linkList;
    }


}

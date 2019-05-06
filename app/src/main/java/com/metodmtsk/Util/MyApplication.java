package com.metodmtsk.Util;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.metodmtsk.Activitiy.MainActivity;
import com.metodmtsk.Activitiy.NotificationActivity;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

/**
 * Created by GK
 * on 16.02.2019.
 */

public class MyApplication extends Application {

    private static MyApplication _instance;
    private RequestQueue _requestQueue;
    private SharedPreferences _preferences;

    public static MyApplication get() {
        return _instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseAnalytics.getInstance(this);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new OneSignal.NotificationOpenedHandler() {
                    @Override
                    public void notificationOpened(OSNotificationOpenResult result) {
                        OSNotificationAction.ActionType actionType = result.action.type;
                        JSONObject data = result.notification.payload.additionalData;


                        Log.wtf("MyApplication", "data : " + data);

                        if (data != null) {


                            Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                        }

                    }
                }).init();

        _instance = this;
        _preferences = PreferenceManager.getDefaultSharedPreferences(this);
        _requestQueue = Volley.newRequestQueue(this);
    }

    public RequestQueue getRequestQueue() {
        return _requestQueue;
    }

    public SharedPreferences getPreferences() {
        return _preferences;
    }

    public SharedPreferences.Editor getPreferencesEditor() {
        return _preferences.edit();
    }

}

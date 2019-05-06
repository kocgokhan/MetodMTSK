package com.metodmtsk.Activitiy;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.metodmtsk.Adapter.NotificationAdapter;
import com.metodmtsk.Adapter.VideoAdapter;
import com.metodmtsk.Pojo.Notification;
import com.metodmtsk.Pojo.User;
import com.metodmtsk.Pojo.Video;
import com.metodmtsk.R;
import com.metodmtsk.Request.AqJSONObjectRequest;
import com.metodmtsk.Util.MyApplication;
import com.jaedongchicken.ytplayer.JLog;
import com.jaedongchicken.ytplayer.YoutubePlayerView;
import com.jaedongchicken.ytplayer.model.PlaybackQuality;
import com.jaedongchicken.ytplayer.model.YTParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.metodmtsk.R.color.background;
import static com.metodmtsk.Util.StaticFields.BASE_URL;
import static com.metodmtsk.Util.StaticFields.HASH;

public class VideoActivity extends AppCompatActivity {

    private static final String TAG = "VideoAct ";

    private TextView currentSec;
    private YoutubePlayerView youtubePlayerView;


    TextView titles ;
    String links ;
    TextView details ;
    YTParams params;

    RecyclerView recyclerView;
    ArrayList<String> mylist;


    String baslik;

    private ArrayList<Video> videoArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


        Bundle bundle = getIntent().getExtras();
        baslik = bundle.getString("title");

        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#273D52\" >" + baslik + "</font>")));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_arrow);

        recyclerView = (RecyclerView) findViewById(R.id.recylerview);


        String loginResponse = MyApplication.get().getPreferences().getString("loginResponse", null);
        if (loginResponse != null) {
            try {
                JSONObject jsonObject = new JSONObject(loginResponse);
                Video user = new Video(jsonObject, true);
                requestJson(baslik);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
    private void requestJson(String category) {
        JSONObject params = new JSONObject();
        try {
            params.put("category", category);
            params.put("hash", HASH);
            params.put("aqGokhan", 1);

            Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.wtf(TAG, "onResponse : " + response);
                    try {
                        JSONArray jsonArray = response.getJSONArray("aqArrayi");
                        JSONObject jsonObject;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);
                            Video video = new Video(jsonObject, false);
                            videoArrayList.add(video);

                        }
                        drawVideo(videoArrayList);

                        MyApplication.get().getRequestQueue().getCache().clear();
                    } catch (JSONException e) {
                        Log.wtf(TAG, "response catch e.getMessage() : " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "onErrorResponse : " + error);
                }
            };

            AqJSONObjectRequest aqJSONObjectRequest = new AqJSONObjectRequest(TAG, BASE_URL + "videoList", params, listener, errorListener);
            MyApplication.get().getRequestQueue().add(aqJSONObjectRequest);
        } catch (JSONException e) {
            Log.wtf(TAG, "request params catch e.getMessage() : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void drawVideo(ArrayList<Video> list) {


        VideoAdapter videoAdapter = new VideoAdapter(this, Video.getData(list));
        recyclerView.setAdapter(videoAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        Log.wtf("notification", String.valueOf(list));
    }

}

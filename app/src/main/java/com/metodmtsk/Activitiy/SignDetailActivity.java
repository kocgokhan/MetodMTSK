package com.metodmtsk.Activitiy;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.webkit.WebView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.metodmtsk.Pojo.LinkList;
import com.metodmtsk.Pojo.Links;
import com.metodmtsk.Pojo.Notification;
import com.metodmtsk.Pojo.User;
import com.metodmtsk.R;
import com.metodmtsk.Request.AqJSONObjectRequest;
import com.metodmtsk.Util.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

import static com.metodmtsk.Util.StaticFields.BASE_URL;
import static com.metodmtsk.Util.StaticFields.HASH;

public class SignDetailActivity extends AppCompatActivity {

    WebView webview;
    private String linkIDS;
    private String linkID;
    private String url;

    String link;
    String title;
    String status;
    int test;

    private ArrayList<LinkList> linkList = new ArrayList<>();

    User userFırst;

    private static final String TAG = "SignDetailAct ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_detail);


        Bundle bundle = getIntent().getExtras();
        linkIDS = bundle.getString("title");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_arrow);


        webview = (WebView)findViewById(R.id.view);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);


        String loginResponse = MyApplication.get().getPreferences().getString("loginResponse", null);
        if (loginResponse != null) {
            try {


                JSONObject jsonObject = new JSONObject(loginResponse);

                userFırst = new User(jsonObject,true);
                linkList = userFırst.getLinkList();

                for (int i =0; i<linkList.size();i++){


                    linkID = linkList.get(i).getLinkID();
                    title = linkList.get(i).getTitle();
                    link = linkList.get(i).getLink();


                    Log.wtf(TAG, String.valueOf(linkID));
                    if (title.equals(linkIDS) ){

                        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#273D52\" >" + title + "</font>")));
                        webview.loadUrl("https://"+link);

                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        MyApplication.get().getRequestQueue().getCache().clear();




        //getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#273D52\" >" + title + "</font>")));

            //webview.loadUrl(link);








    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

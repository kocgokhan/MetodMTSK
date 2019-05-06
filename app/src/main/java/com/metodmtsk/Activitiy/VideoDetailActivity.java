package com.metodmtsk.Activitiy;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.metodmtsk.R;
import com.jaedongchicken.ytplayer.JLog;
import com.jaedongchicken.ytplayer.YoutubePlayerView;
import com.jaedongchicken.ytplayer.model.PlaybackQuality;
import com.jaedongchicken.ytplayer.model.YTParams;

public class VideoDetailActivity extends AppCompatActivity {

    private TextView currentSec;
    private TextView detail;
    private TextView title;
    private YoutubePlayerView youtubePlayerView;
    private String link;
    private String titles;
    private String details;
    private String categorys;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);



        Intent intent = this.getIntent();


        Bundle bundle = getIntent().getExtras();
        link = bundle.getString("link");
        titles = bundle.getString("title");
        details = bundle.getString("detail");
        categorys = bundle.getString("category");

        detail = (TextView) findViewById(R.id.detail);
        title = (TextView) findViewById(R.id.title);


        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#273D52\" >" + categorys + "</font>")));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_arrow);

        youtubePlayerView = (YoutubePlayerView) findViewById(R.id.youtubePlayerView);


        currentSec = (TextView) findViewById(R.id.currentSec);
        currentSec.setText(String.valueOf(0));

        YTParams params = new YTParams();
        // params.setControls(0);
        // params.setAutoplay(1);
        params.setVolume(100);
        params.setPlaybackQuality(PlaybackQuality.small);

        title.setText(titles);
        detail.setText(details);



        youtubePlayerView.initializeWithUrl("https://www.youtube.com/watch?v="+link, params, new YoutubePlayerView.YouTubeListener() {

            @Override
            public void onReady() {
                // when player is ready.
                JLog.i("onReady()");
            }

            @Override
            public void onStateChange(YoutubePlayerView.STATE state) {
                /**
                 * YoutubePlayerView.STATE
                 *
                 * UNSTARTED, ENDED, PLAYING, PAUSED, BUFFERING, CUED, NONE
                 *
                 */

                JLog.i("onStateChange(" + state + ")");
            }

            @Override
            public void onPlaybackQualityChange(String arg) {
                String message = "onPlaybackQualityChange(" + arg + ")";
                Toast.makeText(VideoDetailActivity.this, message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPlaybackRateChange(String arg) {
                String message = "onPlaybackRateChange(" + arg + ")";
                Toast.makeText(VideoDetailActivity.this, message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String arg) {
                String message = "onError(" + arg + ")";
                Toast.makeText(VideoDetailActivity.this, message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onApiChange(String arg) {
                String message = "onApiChange(" + arg + ")";
                Toast.makeText(VideoDetailActivity.this, message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCurrentSecond(double second) {
                currentSec.setText(String.valueOf(second));
            }

            @Override
            public void onDuration(double duration) {
                String message = "onDuration(" + duration + ")";
                JLog.i(message);
            }

            @Override
            public void logs(String log) {
                // javascript debug log. you don't need to use it.
                JLog.d(log);
            }
        });

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // this is optional but you need.
        youtubePlayerView.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // pause video when on the background mode.
        youtubePlayerView.pause();
    }
    public static void start(Context context, String gameId) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        context.startActivity(intent);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

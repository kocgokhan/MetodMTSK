package com.metodmtsk.Activitiy;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.metodmtsk.R;

public class AppointmentResultActivity extends AppCompatActivity implements View.OnClickListener {

    private String result;
    private ImageView check;
    private ImageView error;
    private ImageView back;

    private TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_result);

        boolean isCheck =false;

        Bundle bundle = getIntent().getExtras();
        result = bundle.getString("result");

        isCheck = Boolean.parseBoolean(result);

        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#273D52\" >" + getString(R.string.appointmentTitle) + "</font>")));


        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));



        results=(TextView)findViewById(R.id.result);

        check=(ImageView)findViewById(R.id.check);

        error=(ImageView)findViewById(R.id.error);


        if (isCheck ==true){

            error.setVisibility(View.INVISIBLE);
            results.setText("Randevu İşleminiz Başarıyla Gerçekleşmiştir");

        }else if (isCheck ==true){

            check.setVisibility(View.INVISIBLE);
            results.setText("Randevu İşlemi Sırasında Hata Oluşmuştur");

        }

        back=(ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppointmentResultActivity.this, AppointmentActivity.class);
                finish();

            }
        });


    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}

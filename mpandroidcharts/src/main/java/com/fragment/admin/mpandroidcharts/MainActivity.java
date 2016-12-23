package com.fragment.admin.mpandroidcharts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_pieChart;
    private Button btn_columnarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }

    private void initListeners() {
        btn_pieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentTo(pieChartActivity.class);

            }
        });

        btn_columnarChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentTo(columnarChartActivity.class);
            }
        });
    }

    private void IntentTo(Class cls) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, cls);
        startActivity(intent);
    }

    private void initViews() {
        btn_pieChart = (Button) findViewById(R.id.btn_pieChart);
        btn_columnarChart= (Button) findViewById(R.id.btn_columnarChart);
    }

}

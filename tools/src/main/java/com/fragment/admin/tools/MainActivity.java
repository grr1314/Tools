package com.fragment.admin.tools;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fragment.admin.tools.ibook.Note_Activity;

public class MainActivity extends AppCompatActivity {

    private Button btn_Light;
    private Button btn_Calculator;
    private Button btn_NoteBook;
    private Button btn_Call;

    private EditText ed_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        btn_Light = (Button) findViewById(R.id.btn_Light);
        btn_Light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LightActivity.class);
                startActivity(intent);
            }
        });

        btn_Calculator = (Button) findViewById(R.id.btn_Calculator);
        btn_Calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
                startActivity(intent);
            }
        });

        btn_NoteBook = (Button) findViewById(R.id.btn_NoteBook);
        btn_NoteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Note_Activity.class);
                startActivity(intent);
            }
        });
        btn_Call = (Button) findViewById(R.id.btn_Call);
        btn_Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用系统的拨号服务实现电话拨打功能
                String phone_number = ed_number.getText().toString();

                phone_number = phone_number.trim();//删除字符串首部和尾部的空格

                if (phone_number != null && !phone_number.equals("")) {

                    //调用系统的拨号服务实现电话拨打功能
                    //封装一个拨打电话的intent，并且将电话号码包装成一个Uri对象传入
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone_number));
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);//内部类
                }
            }
        });

    }
}

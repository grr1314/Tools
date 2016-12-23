package com.fragment.admin.typeface;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private Typeface typeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        text= (TextView) findViewById(R.id.text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.typeface01) {
            typeface=Typeface.createFromAsset(getAssets(),"OpenSans-Regular.ttf");
            text.setTypeface(typeface);
            Toast.makeText(getApplicationContext(),"typeface01",1).show();
            text.setText("Hello");
            return true;
        }
        if (id == R.id.typeface02) {
            typeface=Typeface.createFromAsset(getAssets(),"OpenSans-Semibold.ttf");
            text.setTypeface(typeface);
            Toast.makeText(getApplicationContext(),"typeface02",1).show();
            text.setText("Hello");
            return true;
        }
        if (id == R.id.typeface03) {
            typeface=Typeface.createFromAsset(getAssets(),"OpenSans-SemiboldItalic.ttf");
            text.setTypeface(typeface);
            Toast.makeText(getApplicationContext(),"typeface03",1).show();
            text.setText("Hello");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

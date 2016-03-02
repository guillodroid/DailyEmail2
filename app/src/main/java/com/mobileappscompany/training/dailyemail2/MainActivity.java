package com.mobileappscompany.training.dailyemail2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //probando tururu
    FloatingActionButton fab;
    EditText todayText, tomorrowText, confidenceText;
    ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    protected void onResume() {
        todayText = (EditText) findViewById(R.id.todayText);
        tomorrowText = (EditText) findViewById(R.id.tomorrowText);
        confidenceText = (EditText) findViewById(R.id.confidenceText);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.share_option);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share_option){
            shareAction(item);
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMail(View view) {
        StringBuilder body = new StringBuilder();
        body.append("Today \n");
        body.append(todayText.getText().toString() + "\n\n\n");
        body.append("Tomorrow \n");
        body.append(tomorrowText.getText().toString() + "\n\n\n");
        body.append("Confidence \n");
        body.append(confidenceText.getText().toString());
        //Intent emailIntent = new Intent();
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:gmo.rodriguez.m@gmail.com"));
        //emailIntent.setType("text/plain");
        //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Daily Report");
        //emailIntent.putExtra(Intent.EXTRA_TEXT, body.toString());
        Bundle emailBundle = new Bundle();
        //emailBundle.putString(Intent.ACTION_SENDTO, "mailto:gmo.rodriguez.m@gmail.com");
        emailBundle.putString(Intent.EXTRA_SUBJECT, "Daily Report");
        emailBundle.putString(Intent.EXTRA_TEXT, body.toString());
        emailIntent.putExtras(emailBundle);
        startActivityForResult(emailIntent, 1);
    }

    public void shareAction(MenuItem item) {

        Intent iS = new Intent(Intent.ACTION_SEND);
        iS.setType("text/plain");
        iS.putExtra(Intent.EXTRA_TEXT, "http://www.google.com");
        startActivity(iS);

    }
}

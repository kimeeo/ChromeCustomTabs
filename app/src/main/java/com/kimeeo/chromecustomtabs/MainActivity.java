package com.kimeeo.chromecustomtabs;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.kimeeo.kAndroid.chromeCustomTabs.CustomWebTabs;
import com.kimeeo.kAndroid.chromeCustomTabs.TabAction;
import com.kimeeo.kAndroid.chromeCustomTabs.TabConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabConfig tc=new TabConfig();
                Intent intent =new Intent(Intent.ACTION_VIEW,Uri.parse("www.google.com"));
                PendingIntent pendingIntent =PendingIntent.getActivity(MainActivity.this,0,intent,0);

                tc.addMenuItems(new TabAction(1,"Hello1",createPendingIntent(ActionBroadcastReceiver.ACTION_MENU_ITEM)));
                tc.addMenuItems(new TabAction(1,"Hello2",createPendingIntent(ActionBroadcastReceiver.ACTION_MENU_ITEM)));
                tc.addMenuItems(new TabAction(1,"Hello3",createPendingIntent(ActionBroadcastReceiver.ACTION_MENU_ITEM)));

                tc.setSecondaryToolbarViews(BottomBarManager.getSecondaryToolbarViews(MainActivity.this),
                        BottomBarManager.getSecondaryToolbarClickableIDs(),
                        BottomBarManager.getSecondaryToolbarIntent(MainActivity.this));

                tc.setActionButton(new TabAction(1,R.drawable.ic_launcher,R.string.app_name,pendingIntent));
                tc.setShowTitle(true);
                tc.setSecondaryToolbarColorRes(R.color.colorAccent);
                tc.setToolbarColorRes(R.color.colorPrimary);
                CustomWebTabs.getInstance().open(MainActivity.this,Uri.parse("http://www.google.com"),tc,null);
            }
        });
    }


    private PendingIntent createPendingIntent(int actionSourceId) {
        Intent actionIntent = new Intent(this.getApplicationContext(), ActionBroadcastReceiver.class);
        actionIntent.putExtra(ActionBroadcastReceiver.KEY_ACTION_SOURCE, actionSourceId);
        return PendingIntent.getBroadcast(getApplicationContext(), actionSourceId, actionIntent, 0);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

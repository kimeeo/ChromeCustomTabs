package com.kimeeo.chromecustomtabs;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.customtabs.CustomTabsIntent;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by BhavinPadhiyar on 5/15/17.
 */

public class BottomBarManager extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        int clickedId = intent.getIntExtra(CustomTabsIntent.EXTRA_REMOTEVIEWS_CLICKED_ID, -1);
        Toast.makeText(context, "Current URL " + intent.getDataString() + "\nClicked id "+ clickedId, Toast.LENGTH_SHORT).show();
    }

    public static RemoteViews getSecondaryToolbarViews(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.remote_view);
        return remoteViews;
    }



    public static int[] getSecondaryToolbarClickableIDs() {
        return new int[]{R.id.b1, R.id.b2};
    }

    public static PendingIntent getSecondaryToolbarIntent(Context context) {
        Intent intent = new Intent(context, BottomBarManager.class);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }


}

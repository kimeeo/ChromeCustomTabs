package com.kimeeo.chromecustomtabs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.customtabs.CustomTabsIntent;
import android.widget.Toast;

/**
 * Created by BhavinPadhiyar on 5/15/17.
 */

public class ActionBroadcastReceiver extends BroadcastReceiver {
    public static final String KEY_ACTION_SOURCE = "org.chromium.customtabsdemos.ACTION_SOURCE";
    public static final int ACTION_ACTION_BUTTON = 1;
    public static final int ACTION_MENU_ITEM = 2;
    public static final int ACTION_TOOLBAR = 3;

    @Override
    public void onReceive(Context context, Intent intent) {
        String url = intent.getDataString();
        if (url != null) {
            String toastText =getToastText(context, intent.getIntExtra(KEY_ACTION_SOURCE, -1), url);
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
        }
    }

    private String getToastText(Context context, int actionId, String url) {
        switch (actionId) {
            case ACTION_ACTION_BUTTON:
                return "ACTION_BUTTON";
            case ACTION_MENU_ITEM:
                return "ACTION_MENU_ITEM";
            case ACTION_TOOLBAR:
                return "ACTION_TOOLBAR";
            default:
                return "default";
        }
    }
}
package com.kimeeo.kAndroid.chromeCustomTabs;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;

import java.util.List;

/**
 * Created by BhavinPadhiyar on 06/05/16.
 */
public class CustomWebTabs
{
    private CustomTabsSession mCustomTabsSession;
    private CustomTabsClient mClient;
    private CustomTabsServiceConnection mConnection;
    private ConnectionCallback mConnectionCallback;
    private static CustomWebTabs mInstance = null;
    public static CustomWebTabs getInstance(){
        if(mInstance == null)
            mInstance = new CustomWebTabs();
        return mInstance;
    }
    private CustomWebTabs() {

    }
    public static void bindService(Context context) {
        getInstance().bindCustomTabService(context);
    }
    public static void unbindService(Context context){
        getInstance().unbindCustomTabService(context);
    }
    public void open(Activity activity,Uri uri,CustomTabsIntent customTabsIntent,CustomTabFallback fallback) {
        String packageName = CustomTabsHelper.getPackageNameToUse(activity);
        if (packageName == null) {
            if (fallback != null)
                fallback.openUri(activity, uri);
        } else {
            customTabsIntent.intent.setPackage(packageName);
            customTabsIntent.launchUrl(activity, uri);
        }
    }
    public void open(Activity activity,Uri uri,TabConfig config,CustomTabFallback fallback) {
        if(config!=null)
        {
            CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
            if(config.getActionButton()!=null) {
                String title;
                if(config.getActionButton().getTitleRes()>0)
                    title = activity.getResources().getString(config.getActionButton().getTitleRes());
                else
                    title =config.getActionButton().getTitle();

                Bitmap icon;
                if(config.getActionButton().getIconRes()>0)
                    icon =getIcon(activity,config.getActionButton().getIconRes(),config.getActionButton().getIconColor());
                else
                    icon = config.getActionButton().getIcon();

                intentBuilder.setActionButton(icon, title, config.getActionButton().getIntent());
            }

            intentBuilder.setShowTitle(config.getShowTitle());

            if(config.getCloseButton()!=null)
                intentBuilder.setCloseButtonIcon(config.getCloseButton());

            if(config.getToolbarColor()>0)
                intentBuilder.setToolbarColor(config.getToolbarColor());
            else if(config.getToolbarColorRes()>0)
                intentBuilder.setToolbarColor(activity.getResources().getColor(config.getToolbarColorRes()));

            if(config.getMenuItems()!=null && config.getMenuItems().size()!=0)
            {
                for (TabConfig.Action action : config.getMenuItems()) {
                    String title;
                    if(action.getTitleRes()>0)
                        title = activity.getResources().getString(action.getTitleRes());
                    else
                        title =action.getTitle();
                    intentBuilder.addMenuItem(title, action.getIntent());
                }
            }

            if(config.getAnimation()!=null) {
                intentBuilder.setStartAnimations(activity,config.getAnimation().getEnter(),config.getAnimation().getExit());
                intentBuilder.setExitAnimations(activity,config.getAnimation().getExit(),config.getAnimation().getEnter());
            }

            if(config.getToolbarItem()!=null && config.getToolbarItem().size()!=0)
            {
                for (TabConfig.Action action : config.getToolbarItem()) {
                    String title;
                    if(action.getTitleRes()>0)
                        title = activity.getResources().getString(action.getTitleRes());
                    else
                        title =action.getTitle();

                    Bitmap icon;
                    if(action.getIconRes()>0)
                        icon =getIcon(activity,action.getIconRes(),action.getIconColor());
                    else
                        icon = action.getIcon();

                    intentBuilder.addToolbarItem(action.getId(),icon,title, action.getIntent());
                }

                if(config.getSecondaryToolbarColor()>0)
                    intentBuilder.setSecondaryToolbarColor(config.getSecondaryToolbarColor());
                else if(config.getSecondaryToolbarColorRes()>0)
                    intentBuilder.setSecondaryToolbarColor(activity.getResources().getColor(config.getSecondaryToolbarColorRes()));
            }
            open(activity,uri,intentBuilder.build(),fallback);
        }
        else if (fallback != null)
            fallback.openUri(activity, uri);
    }

    public Bitmap getIcon(Context context,@DrawableRes int iconId,int iconColor) {

        Drawable drawable=context.getResources().getDrawable(iconId);

        if (iconColor>0)
            drawable.setColorFilter(iconColor, PorterDuff.Mode.SRC_ATOP);

        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public Bitmap getIcon(Context context,@DrawableRes int iconId) {
        return getIcon(context,iconId,-1);
    }


    public void open(Activity activity,Uri uri,CustomTabsIntent customTabsIntent) {
        open(activity,uri,customTabsIntent,null);
    }
    public void open(Activity activity,TabConfig config,Uri uri) {
        open(activity,uri,config,null);
    }
    public void open(Activity activity,Uri uri) {
        open(activity,uri,new TabConfig(),null);
    }
    public void bindCustomTabService(Context context) {
        if (mClient != null)
            return;
        String packageName = CustomTabsHelper.getPackageNameToUse(context);
        if (packageName == null)
            return;
        mConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
                mClient = client;
                mClient.warmup(0L);
                if (mConnectionCallback != null)
                    mConnectionCallback.onCustomTabsConnected();
                getSession();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mClient = null;
                if (mConnectionCallback != null)
                    mConnectionCallback.onCustomTabsDisconnected();
            }
        };
        CustomTabsClient.bindCustomTabsService(context, packageName, mConnection);
    }
    public void unbindCustomTabService(Context context) {
        if (mConnection == null)
            return;
        context.unbindService(mConnection);
        mClient = null;
        mCustomTabsSession = null;
    }
    public CustomTabsSession getSession() {
        if (mClient == null) {
            mCustomTabsSession = null;
        } else if (mCustomTabsSession == null) {
            mCustomTabsSession = mClient.newSession(null);
        }
        return mCustomTabsSession;
    }
    public boolean mayLaunchUrl(Uri uri, Bundle extras, List<Bundle> otherLikelyBundles) {
        if (mClient == null)
            return false;
        CustomTabsSession session = getSession();
        if (session == null)
            return false;
        return session.mayLaunchUrl(uri, extras, otherLikelyBundles);
    }
    public void setConnectionCallback(ConnectionCallback connectionCallback) {
        this.mConnectionCallback = connectionCallback;
    }
    public ConnectionCallback getConnectionCallback() {
        return  mConnectionCallback;
    }
    public interface ConnectionCallback {
        void onCustomTabsConnected();
        void onCustomTabsDisconnected();
    }
    public interface CustomTabFallback {
        void openUri(Activity activity, Uri uri);
    }

}

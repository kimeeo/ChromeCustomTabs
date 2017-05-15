package com.kimeeo.kAndroid.chromeCustomTabs;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by BhavinPadhiyar on 5/15/17.
 */

public class TabAction {
    private int iconRes;

    private int iconColor=0;
    private Bitmap icon;
    private int id;
    private String title;
    private int titleRes;
    private PendingIntent intent;

    public TabAction(int id,String title, PendingIntent intent)
    {
        this.id = id;
        this.title =title;
        this.intent=intent;
    }

    public TabAction(int id,Bitmap icon,String title, PendingIntent intent)
    {
        this.id = id;
        this.title =title;
        this.intent=intent;
        this.icon = icon;
    }

    public TabAction(int id, @StringRes int titleRes, PendingIntent intent)
    {
        this.id = id;
        this.titleRes =titleRes;
        this.intent=intent;
    }

    public TabAction(int id,Bitmap icon,@StringRes int titleRes, PendingIntent intent)
    {
        this.id = id;
        this.titleRes =titleRes;
        this.intent=intent;
        this.icon = icon;
    }

    public TabAction(int id, @DrawableRes int iconRes, @StringRes int titleRes, PendingIntent intent)
    {
        this.id = id;
        this.titleRes =titleRes;
        this.intent=intent;
        this.iconRes = iconRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public int getIconColor() {
        return iconColor;
    }

    public void setIconColor(int iconColor) {
        this.iconColor = iconColor;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PendingIntent getIntent() {
        return intent;
    }

    public void setIntent(PendingIntent intent) {
        this.intent = intent;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public int getTitleRes() {
        return titleRes;
    }

    public void setTitleRes(@StringRes int titleRes) {
        this.titleRes = titleRes;
    }
}

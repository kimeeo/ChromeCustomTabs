package com.kimeeo.kAndroid.chromeCustomTabs;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BhavinPadhiyar on 06/05/16.
 */
public class TabConfig {
    private int toolbarColor;
    private int secondaryToolbarColor;
    private int toolbarColorRes;
    private int secondaryToolbarColorRes;

    public int getToolbarColorRes() {
        return toolbarColorRes;
    }

    public void setToolbarColorRes(@ColorRes  int toolbarColorRes) {
        this.toolbarColorRes = toolbarColorRes;
    }

    public int getSecondaryToolbarColorRes() {
        return secondaryToolbarColorRes;
    }

    public void setSecondaryToolbarColorRes(@ColorRes int secondaryToolbarColorRes) {
        this.secondaryToolbarColorRes = secondaryToolbarColorRes;
    }

    private boolean showTitle;
    private List<TabAction> menuItems;

    private Bitmap closeButtonBitmap;
    private TabAction action;
    private Animation animation;

    private List<TabAction> toolbarItem;

    public void setToolbarColor(int color)
    {
        this.toolbarColor = color;
    }
    public int getToolbarColor()
    {
        return toolbarColor;
    }
    public int getSecondaryToolbarColor() {
        return secondaryToolbarColor;
    }

    public void setSecondaryToolbarColor(int secondaryToolbarColor) {
        this.secondaryToolbarColor = secondaryToolbarColor;
    }

    public void setShowTitle(boolean showTitle)
    {
        this.showTitle= showTitle;
    }
    public boolean getShowTitle()
    {
        return showTitle;
    }
    public List<TabAction> getMenuItems()
    {
        return menuItems;
    }
    public void setMenuItems(List<TabAction> menus)
    {
        this.menuItems = menus;
    }
    public void addMenuItems(TabAction action)
    {
        if(menuItems==null)
            menuItems = new ArrayList<>();
        menuItems.add(action);
    }

    public List<TabAction> getToolbarItem() {
        return toolbarItem;
    }

    public void setToolbarItem(List<TabAction> toolbarItem) {
        this.toolbarItem = toolbarItem;
    }
    public void addToolbarItem(TabAction action) {
        if(toolbarItem==null)
            toolbarItem = new ArrayList<>();
        toolbarItem.add(action);
    }
    public Bitmap getCloseButton() {
        return closeButtonBitmap;
    }

    public void setCloseButton(Bitmap closeButtonBitmap) {
        this.closeButtonBitmap = closeButtonBitmap;
    }
    public void setActionButton(TabAction action)
    {
        this.action= action;
    }
    public TabAction getActionButton()
    {
        return  action;
    }
    public void setAnimation(Animation animation)
    {
        this.animation = animation;
    }
    public Animation getAnimation()
    {
        return animation;
    }

    public void setDefaultAnimation()
    {
        Animation animation = new Animation(R.anim.slide_in_right, R.anim.slide_out_left);
        setAnimation(animation);
    }

    public void setAnimation(@AnimRes int enter, @AnimRes int exit)
    {
        Animation animation = new Animation(enter,exit);
        setAnimation(animation);
    }

    public class Animation
    {
        private int exit;
        private int enter;

        public Animation(@AnimRes int enter, @AnimRes int exit)
        {
            this.enter=enter;
            this.exit=exit;
        }

        public int getEnter() {
            return enter;
        }
        public void setEnter(@AnimRes int enter) {
            this.enter = enter;
        }

        public int getExit() {
            return exit;
        }
        public void setExit(@AnimRes int exit) {
            this.exit = exit;
        }


    }
}

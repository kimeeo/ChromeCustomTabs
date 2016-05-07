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
    private List<Action> menuItems;

    private Bitmap closeButtonBitmap;
    private Action action;
    private Animation animation;

    private List<Action> toolbarItem;

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
    public List<Action> getMenuItems()
    {
        return menuItems;
    }
    public void setMenuItems(List<Action> menus)
    {
        this.menuItems = menus;
    }
    public void addMenuItems(Action action)
    {
        if(menuItems==null)
            menuItems = new ArrayList<>();
        menuItems.add(action);
    }

    public List<Action> getToolbarItem() {
        return toolbarItem;
    }

    public void setToolbarItem(List<Action> toolbarItem) {
        this.toolbarItem = toolbarItem;
    }
    public void addToolbarItem(Action action) {
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
    public void setActionButton(Action action)
    {
        this.action= action;
    }
    public Action getActionButton()
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
    public class Action
    {
        private int iconRes;

        private int iconColor=0;
        private Bitmap icon;
        private int id;
        private String title;
        private int titleRes;
        private PendingIntent intent;

        public Action(int id,String title, PendingIntent intent)
        {
            this.id = id;
            this.title =title;
            this.intent=intent;
        }

        public Action(int id,Bitmap icon,String title, PendingIntent intent)
        {
            this.id = id;
            this.title =title;
            this.intent=intent;
            this.icon = icon;
        }

        public Action(int id,@StringRes int titleRes, PendingIntent intent)
        {
            this.id = id;
            this.titleRes =titleRes;
            this.intent=intent;
        }

        public Action(int id,Bitmap icon,@StringRes int titleRes, PendingIntent intent)
        {
            this.id = id;
            this.titleRes =titleRes;
            this.intent=intent;
            this.icon = icon;
        }

        public Action(int id, @DrawableRes int iconRes, @StringRes int titleRes, PendingIntent intent)
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
}

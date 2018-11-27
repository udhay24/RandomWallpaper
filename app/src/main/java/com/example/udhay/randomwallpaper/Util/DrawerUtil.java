package com.example.udhay.randomwallpaper.Util;

import android.app.Activity;
import android.support.v7.widget.Toolbar;

import com.example.udhay.randomwallpaper.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class DrawerUtil {

    static Drawer drawer;

    public static void getDrawer(Activity activity, Toolbar toolbar) {

        PrimaryDrawerItem featured = new PrimaryDrawerItem().withName("Featured")
                .withIcon(R.drawable.featured_image);

        PrimaryDrawerItem collection = new PrimaryDrawerItem()
                .withName("Collections")
                .withIcon(R.drawable.collection);

        PrimaryDrawerItem saved = new PrimaryDrawerItem()
                .withName("Saved")
                .withIcon(R.drawable.fav_photo);

        PrimaryDrawerItem settings = new PrimaryDrawerItem()
                .withName("Settings")
                .withIcon(R.drawable.settings);

        PrimaryDrawerItem help = new PrimaryDrawerItem()
                .withName("Help & Feedback")
                .withIcon(R.drawable.information);

        PrimaryDrawerItem support = new PrimaryDrawerItem()
                .withName("Support")
                .withIcon(R.drawable.support);


        IProfile freeUser = new ProfileDrawerItem()
                .withName("Anonymous User")
                .withEmail("Free Account")
                .withIcon(R.drawable.avatar);

        IProfile addUser = new ProfileSettingDrawerItem()
                .withName("Add User")
                .withDescription("Click to Sign In")
                .withIcon(R.drawable.add);

        IProfile manageAccounts = new ProfileSettingDrawerItem()
                .withName("Manage Account")
                .withIcon(R.drawable.manage_accounts);


        AccountHeader accountHeader = new AccountHeaderBuilder()
                .addProfiles(freeUser, addUser, manageAccounts)
                .withActivity(activity)
                .withHeaderBackground(R.drawable.account_header)
                .build();

        drawer = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withAccountHeader(accountHeader)
                .build();

        drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

        drawer.addItems(featured, collection, saved, new SectionDrawerItem().withName("Settings"), settings, help, support);


    }
}

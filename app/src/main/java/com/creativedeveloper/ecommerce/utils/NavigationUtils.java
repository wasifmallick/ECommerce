package com.creativedeveloper.ecommerce.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.creativedeveloper.ecommerce.AppConstants;
import com.creativedeveloper.ecommerce.R;

public class NavigationUtils implements AppConstants {
        public static void replaceFragment(Activity activity,
                                       int navId) {
        Bundle bundle = new Bundle();
        Navigation.findNavController(activity, R.id.fragment_nav_host)
                .navigate(navId, bundle, new NavOptions.Builder()
                        .setLaunchSingleTop(true)
                        .build());
    }
}


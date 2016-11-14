package com.example.rental.discovery.GridViewTest;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by caolu on 2016/11/5.
 */

public class WindowSize {
    public static int getWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        return width;
    }

}
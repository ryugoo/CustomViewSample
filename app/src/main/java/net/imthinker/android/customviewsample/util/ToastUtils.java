package net.imthinker.android.customviewsample.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class ToastUtils {
    private static final Handler MAIN_THREAD_HANDLER = new Handler(Looper.getMainLooper());

    public static void showShort(final Context context, final String message) {
        MAIN_THREAD_HANDLER.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showLong(final Context context, final String message) {
        MAIN_THREAD_HANDLER.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}

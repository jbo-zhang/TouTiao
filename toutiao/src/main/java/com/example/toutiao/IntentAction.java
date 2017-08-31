package com.example.toutiao;

import android.content.Context;
import android.content.Intent;

/**
 * Created by zhangjinbo on 17-8-30.
 */

public class IntentAction {

    public static void send(Context context, String shareText) {
        Intent shareIntent = new Intent()
                .setAction(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, shareText);
        context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.share_to)));
    }
}

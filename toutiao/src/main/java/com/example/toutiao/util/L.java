package com.example.toutiao.util;

import android.util.Log;

public class L {
	private static boolean DEBUG = true;
	private static String TAG = "TOUTIAO";

	public static void d(String clazz, String info) {
		if(DEBUG) {
			Log.d(TAG, "[" + clazz + "] " + info);
		}
	}
	
}

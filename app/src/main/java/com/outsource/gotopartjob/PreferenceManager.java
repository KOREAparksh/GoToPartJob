package com.outsource.gotopartjob;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    /**
     * 이 클래스는 제가 만들어둔 preference를 쉽게 사용하기 위한 클래스입니다.
     * 전부 정적 클래스/메소드여서 바로바로 사용할 수 있는 것이 장접입니다.
     * 메인클래스를 확인해보세요
     */

    public final static String PREF_NAME = "outsource";
    private static final String DEFAULT_VALUE_STRING = null;

    public static final String KEY_LON= "lon";
    public static final String KEY_LAT= "lan";

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void setString(Context context, String key, String value)
    {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String  getString(Context context, String key)
    {
        SharedPreferences prefs = getPreferences(context);
        String value = prefs.getString(key, DEFAULT_VALUE_STRING);
        return value;
    }

}

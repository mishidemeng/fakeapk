package com.turing.fakeapk.fake;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Field;

import de.robv.android.xposed.XSharedPreferences;

public class SharedPref {
    private Context shareContext;
    private static SharedPreferences mySharedPref;
    private static XSharedPreferences myXsharedPref;
    public SharedPref(Context appContext) {
        shareContext = appContext;
        mySharedPref = shareContext.getSharedPreferences(Common.PREFS_FILE, 1);
    }

    public void setSharedPref(String key, String value) {
        try {
            mySharedPref.edit().putString(key, value).commit();
        } catch (Exception e) {
            System.out.println("setSharedPref ERROR: " + e.getMessage());
        }
    }

    public static String getValue(String key) {
        String value = "";
        try {
            value = mySharedPref.getString(key, null);
        } catch (Exception e) {
            System.out.println("getSharedPref ERROR: " + e.getMessage());
        }
        return value;
    }

    public static XSharedPreferences getMyXSharedPref() {
        if (myXsharedPref != null) {
            myXsharedPref.reload();
            return myXsharedPref;
        }
        try {
            /*
            Class clazz = Class.forName("com.gd.hz.jzy.SpecialPackageName");
            Field field = clazz.getField("packageName");
            String packageName = field.get(clazz).toString();
            String packageName = (String)XposedHelpers.getStaticObjectField(clazz,"packageName");
            System.out.println("getMyXSharedPref -- packageName:" + packageName);
            */
            myXsharedPref = new XSharedPreferences(Common.PACKAGE_NAME, Common.PREFS_FILE);
            return myXsharedPref;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getXValue(String key) {
        String value = "";
        try {
            value = getMyXSharedPref().getString(key, null);
        } catch (Exception e) {
            System.out.println("getSharedPref ERROR: " + e.getMessage());
        }
        return value;
    }

    public static void setXValue(String key, String value) {
        try {
            getMyXSharedPref().edit().putString(key, value).commit();
        } catch (Exception e) {
            System.out.println("setXValue ERROR: " + e.getMessage());
        }
    }

}

package com.codey.json;

import android.content.SharedPreferences;

public class SharePreferenceUtil {
	//默认构造
	private SharePreferenceUtil(){};
	private static final SharePreferenceUtil PREFERENCE_UTIL=new SharePreferenceUtil();
	/**
	 * 单例模式设置
	 * @return
	 */
	public static SharePreferenceUtil getInstanse()
	{
		return PREFERENCE_UTIL;
	}
	public void putStringData(SharedPreferences preferences,String key,String value)
	{
		SharedPreferences.Editor editor=preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	public String getshareString(SharedPreferences preferences,String key)
	{
		return preferences.getString(key,"value");
	}
	public void putIntData(SharedPreferences preferences,String key,int value)
	{
		SharedPreferences.Editor editor=preferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	public int getIntData(SharedPreferences preferences,String key)
	{
		return preferences.getInt(key, 0);
	}
}

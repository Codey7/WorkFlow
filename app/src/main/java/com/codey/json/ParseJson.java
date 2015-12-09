package com.codey.json;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.JsonReader;
import android.util.Log;


public class ParseJson {
	private ParseJson(){};
	private static final ParseJson PARSE_JSON=new ParseJson();
	private JsonReader reader;
	/**
	 * 单例模式设置
	 * @return
	 */
	public static ParseJson getInstanse()
	{
		return PARSE_JSON;
	}
	private JSONTokener jsonParser;
	private JSONObject jsonObject;
	public JSONObject packJson(int type,String jsonString,String name)
	{
		switch (type) {
		case 1:
			try {
				jsonObject=new JSONObject();
				return jsonObject.put(name, jsonString);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
		return jsonObject;
	}
	public HashMap<String,String> parseBannerJson(String jsonString)
	{
		HashMap<String, String> mHashMap=new HashMap<>();
		reader=new JsonReader(new StringReader(jsonString));
		try
		{
			reader.beginObject();
			while (reader.hasNext())
			{

			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return mHashMap;
	}
	/*public HashMap<String, Object> parseSelJson(String jsonString)
	{
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		reader=new JsonReader(new StringReader(jsonString));
		try {
			reader.beginArray();
			while(reader.hasNext())
			{
				reader.beginObject();
				 while (reader.hasNext()) {  
	                    // 取出当前键值对的key  
	                    String tagName = reader.nextName();
	                    Log.e("next", tagName);
	                    // 取出key所对应的value  
	                    if (tagName.equals("name")) {  
	                    	mHashMap.put("name", reader.nextString());
	                    }
	                    else if(tagName.equals("servetime"))
	                    	mHashMap.put("servetime", reader.nextString());
	                }
				 reader.endObject();
			}
			reader.endArray();
			Log.e("next", mHashMap.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mHashMap;
	}
	*//**
	 * 挂号单信息的json解析
	 * @param jsonString
	 * @return
	 *//*
	private List<String> parseRegJson(String jsonString)
	{
		List<String> mList=new ArrayList<String>();
		reader=new JsonReader(new StringReader(jsonString));
		try {
			reader.beginObject();
			while(reader.hasNext())
			{
				String tagName = reader.nextName();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
}

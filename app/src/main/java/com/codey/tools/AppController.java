package com.codey.tools;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.codey.workflow.R;

public class AppController extends Application
{

	public static final String TAG = AppController.class.getSimpleName();

	private RequestQueue mRequestQueue;

	private static AppController mInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
	}

	public static synchronized AppController getInstance() {
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}


	public <T> void addToRequestQueue(Request<T> req, String tag) {
		// set the default tag if tag is empty
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
	public void displayImg(final View view,String url,RequestQueue mRequestQueue){
		ImageLoader imageLoader = new ImageLoader(mRequestQueue, new BitmapCache());

		//ImageLoader.ImageListener listener = ImageLoader.getImageListener((ImageView) view, R.drawable.head, R.drawable.head);
		imageLoader.get(url, new ImageLoader.ImageListener()
		{
			@Override
			public void onResponse(ImageLoader.ImageContainer request, boolean b)
			{
				if(request.getBitmap()!=null)
				{
					((ImageView) view).setImageDrawable(new CircleImageDrawable(request.getBitmap()));
				}
				else
				{
					((ImageView) view).setImageResource(R.drawable.head);
				}
			}

			@Override
			public void onErrorResponse(VolleyError volleyError)
			{

			}
		});
		//指定图片允许的最大宽度和高度
		//imageLoader.get("http://developer.android.com/images/home/aw_dac.png",listener, 200, 200);
	}
}

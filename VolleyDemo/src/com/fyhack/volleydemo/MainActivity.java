package com.fyhack.volleydemo;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fyhack.volleydemo.po.IP;
import com.google.gson.Gson;

public class MainActivity extends Activity {
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
		requestJson("http://ip.jsontest.com/");
		requestImage(imageView, "http://www.baidu.com/img/bdlogo.gif");
	}
	
	private void init(){
		imageView = (ImageView) findViewById(R.id.image);
	}
	
	private void requestJson(String url){
		RequestQueue rQueue = Volley.newRequestQueue(getApplicationContext());
		rQueue.add(new JsonObjectRequest(Method.GET, url, null,
				new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						System.out.println(response.toString());
						IP ip = new IP(); 
						Gson gson = new Gson();
						ip = gson.fromJson(response.toString(), ip.getClass());
						System.out.println("ip :" + ip.getIp());
					}
				}, null));
		rQueue.start();
	}
	
	private void requestImage(ImageView iv, String imgUrl){
		ImageListener imageListener = ImageLoader.getImageListener(iv, 0, 0);
		ImageLoader i = new ImageLoader(null, null);
		i.get(imgUrl, imageListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

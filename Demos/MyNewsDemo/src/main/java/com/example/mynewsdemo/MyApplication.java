package com.example.mynewsdemo;


import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.content.SyncStatusObserver;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.SaveCallback;
import com.example.adapter.NewsAdapter;
import com.example.database.DataBase;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

@SuppressWarnings("unused")
public class MyApplication extends OrmLiteBaseActivity<DataBase>{
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	AVOSCloud.initialize(this, "pr5za5oexy91m6a2f85vfh7vhui9acja8t7vliafy69w1mx3","5hwoosp5mo6qkuka2cvz29f7s89p8wmv4aarxqb96fn8tet3");
	AVAnalytics.trackAppOpened(getIntent());




//	AVObject aObject=new AVObject("ArrayNew");
	
//	JSONArray jsonArray=new JSONArray();	
//	jsonArray.put("http://up-pic.qiniudn.com/2014-10-10_5437a256596e6.jpg");
//	jsonArray.put("http://up-pic.qiniudn.com/2014-10-10_5437a256596e6.jpg");
//	aObject.put("Images", jsonArray);
//	aObject.put("Content", "玩咖就是");
//	aObject.saveInBackground();

//	myObject.put("Imageurl", "http://up-pic.qiniudn.com/2014-10-10_5437a256596e6.jpg");
//	JSONArray myJsonArray=new JSONArray();
//	myJsonArray.put(myObject);
//	myJsonArray.put(myObject);
//	aObject.put("ImageUrl",myJsonArray);
//	aObject.put("Image", "http://up-pic.qiniudn.com/2014-10-10_5437a256596e6.jpg");
//	aObject.put("Content", "玩咖“i无码");
//	AVQuery<AVObject> query=new AVQuery<AVObject>("News");
//	String string = aObject.getString("Image");
//   
//	aObject.saveInBackground(new SaveCallback() {
//		
//		@Override
//		public void done(AVException arg0) {
//			
//			if (arg0==null) {
//				System.out.println("sssss成功");
//			}else {
//				System.out.println("ssss失败");
//
//			}
//			
//		}
//	});

}
}

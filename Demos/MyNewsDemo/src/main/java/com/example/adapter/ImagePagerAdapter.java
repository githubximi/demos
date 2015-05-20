package com.example.adapter;

import java.util.List;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

import com.example.mynewsdemo.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent.DispatcherState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterExtras;
import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class ImagePagerAdapter extends RecyclingPagerAdapter{
	private List<AVObject> datas;
    @RootContext
    Context context;
	public void setDatas(List<AVObject> datas) {
		this.datas = datas;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup container) {
        PagerImageItem pagerImageItem;
		if (convertView==null) {

            pagerImageItem=PagerImageItem_.build(context);
		}else{
            pagerImageItem= (PagerImageItem) convertView;
        }

        pagerImageItem.bind(datas.get(position).getString("Pager_ImageUrl"));
		return pagerImageItem;
	}

	@Override
	public int getCount() {
		if (datas!=null) {
			return datas.size();
		}
		return 0;
	}

}

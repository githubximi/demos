package com.example.adapter;


import java.util.List;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.json.JSONArray;
import org.json.JSONException;
import com.avos.avoscloud.AVObject;
import com.example.bean.News_Bean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/*
* name:粟慧文
* */
@SuppressWarnings("unused")
@EBean
public class NewsAdapter extends BaseAdapter{
	public static final int LAYOUT_ONE=0;
	public static final int LAYOUT_TWO=2;
	private List<AVObject> datas;
    @RootContext
	Context context;
	public void setData(List<AVObject> avdatas){
		this.datas=avdatas;
	    notifyDataSetChanged();
	}
    	@Override
	public int getItemViewType(int position) {
            int images_id = datas.get(position).getInt("images_id");
               if(datas!=null){
                   if(images_id==1){
                       return LAYOUT_TWO;
                }
                   if(images_id==2){
                       return LAYOUT_ONE;
                   }
               }


		return 0;
	}

    //因为有三种视图，所以返回3
	@Override
	public int getViewTypeCount() {
		
		return 3;
	}
	@Override
	public int getCount() {
		if (datas!=null) {
		
			return datas.size();
		}
		return 0;
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		JSONArray jsonArray = datas.get(position).getJSONArray("images");
            int itemViewType = getItemViewType(position);
            switch (itemViewType) {

                case LAYOUT_ONE:
                    MoreViewItem moreViewItem;
                    if (convertView==null){
                       moreViewItem=MoreViewItem_.build(context);
                    }else{
                        moreViewItem= (MoreViewItem) convertView;
                    }
                    JSONArray jsonArray3 = datas.get(position).getJSONArray("images");
                     int i1;
                    String object1 = null;
                    for ( i1 = 0; i1 < jsonArray3.length(); i1++) {
                        try {
                            object1 = (String) jsonArray3.get(i1);

                            moreViewItem.moreBing(datas.get(position).getString("title"),object1,i1);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    convertView=moreViewItem;
                    break;
                case LAYOUT_TWO:
                    NewsItemView newsItemView;
                    if (convertView==null){
                         newsItemView=NewsItemView_.build(context);
                    }else{
                        newsItemView= (NewsItemView) convertView;
                    }
                 JSONArray jsonArray2 = datas.get(position).getJSONArray("images");
                    String object = null;
                    for (int i = 0; i < jsonArray2.length(); i++) {
                        try {
                            object = (String) jsonArray2.get(i);
                            newsItemView.newsBind(datas.get(position).getString("title"),datas.get(position).getString("content"),object);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                        convertView=newsItemView;
                    break;

            }
		return convertView;
	}


}

package com.example.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.alibaba.fastjson.JSON;
import com.example.bean.News_Bean;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * Created by hd94 on 2015/5/14.
 */
@EBean
public class DB_Adapter extends BaseAdapter{
    public static final int LAYOUT_ONE=0;
    public static final int LAYOUT_TWO=2;
    private List<News_Bean> data;

    @RootContext
    Context context;
    public void setData_DB(List<News_Bean> data_DB) {
        this.data= data_DB;

    }
    @Override
    public int getCount() {

        if (data!=null){

            return data.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {

      int images_id = data.get(position).getImages_id();


        if(data!=null){
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
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        int itemViewType = getItemViewType(position);
        switch (itemViewType) {

            case LAYOUT_ONE:
                MoreViewItem moreViewItem;
                if (convertView==null){
                    moreViewItem=MoreViewItem_.build(context);
                }else{
                    moreViewItem= (MoreViewItem) convertView;
                }


                String title = data.get(position).getTitle().toString();
                String images = data.get(position).getImages();
                try {
                    JSONArray jsonImages=new JSONArray(images);

                    Log.d("222222",""+jsonImages );
                    int i;
                   for ( i=0;i<jsonImages.length();i++){
                       String iv = (String) jsonImages.get(i);
                       moreViewItem.moreBing(title,iv,i);
                   }
                } catch (JSONException e) {
                    e.printStackTrace();
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
                String titles = data.get(position).getTitle().toString();
                String content = data.get(position).getContent().toString();
                String image = data.get(position).getImages().toString();
                try {
                    JSONArray jsonArray=new JSONArray(image);
                    String iv = (String) jsonArray.get(0);
                    newsItemView.newsBind(titles,content,iv);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                convertView=newsItemView;
                break;

        }
        return convertView;
    }




}

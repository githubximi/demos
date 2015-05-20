package com.example.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mynewsdemo.R;
import com.lidroid.xutils.BitmapUtils;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;

/**
 * Created by hd94 on 2015/5/6.
 */
@EViewGroup(R.layout.list_item)
public class NewsItemView extends LinearLayout{

    BitmapUtils bitmapUtils;
    @ViewById(R.id.list_image)
    ImageView imageView;
    @ViewById(R.id.list_tv)
    TextView titleTextView;
    @ViewById(R.id.content_tv)
    TextView contentTextView;
    public NewsItemView(Context context) {
        super(context);
        bitmapUtils=new BitmapUtils(context);
    }

    public void newsBind(String title,String content,String image){
        titleTextView.setText(title);
        contentTextView.setText(content);
        bitmapUtils.display(imageView,image);
    }

}

package com.example.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mynewsdemo.R;
import com.lidroid.xutils.BitmapUtils;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by hd94 on 2015/5/6.
 */
@EViewGroup(R.layout.list_more_item)
public class MoreViewItem extends LinearLayout {
    BitmapUtils bitmapUtils;

    @ViewById(R.id.more_tv)
    TextView textView;
    @ViewById(R.id.more_iv1)
    ImageView imageView1;
    @ViewById(R.id.more_iv2)
    ImageView imageView2;
    @ViewById(R.id.more_iv3)
    ImageView imageView3;

    public MoreViewItem(Context context) {
        super(context);
        bitmapUtils=new BitmapUtils(context);
    }

    public void moreBing(String content,String imageUrl,int positon){
        ImageView[] imageViews=new ImageView[]{imageView1,imageView2,imageView3};
        textView.setText(content);
        bitmapUtils.display(imageViews[positon],imageUrl);
    }
}

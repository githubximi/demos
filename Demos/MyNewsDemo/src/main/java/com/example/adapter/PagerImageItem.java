package com.example.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mynewsdemo.R;
import com.lidroid.xutils.BitmapUtils;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

/**
 * Created by hd94 on 2015/5/5.
 */
@EViewGroup(R.layout.pager_image)

public class PagerImageItem extends LinearLayout{

    BitmapUtils bitmapUtils;


    @ViewById(R.id.pager_iv)
    ImageView imageView;

    public void bind(String content){
        bitmapUtils.display(imageView,content);
    }

    public PagerImageItem(Context context) {
        super(context);
        bitmapUtils=new BitmapUtils(context);
    }
}

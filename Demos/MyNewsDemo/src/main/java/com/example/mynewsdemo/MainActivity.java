package com.example.mynewsdemo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.example.adapter.DB_Adapter;
import com.example.adapter.ImagePagerAdapter;
import com.example.adapter.NewsAdapter;


import com.example.bean.News_Bean;
import com.j256.ormlite.dao.Dao;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
@EActivity(R.layout.activity_main)
public class MainActivity extends MyApplication {
	@SuppressWarnings("unused")
    @ViewById(R.id.mian_lv)
	 ListView lView;
    @Bean
    ImagePagerAdapter adapters;
    @Bean
	NewsAdapter adapter;
    @Bean
    DB_Adapter db_adapter;

    News_Bean news_bean;
    Dao<News_Bean,Integer> newsDao;

    private List<AVObject> avdatas;
    private List<AVObject> pagerList;

    private View view;
    private ViewPager viewPager;
    int heightPixels;
    int widthPixels;
    private LinearLayout new_ll;
    private int prePosition=0;
    private TextView new_tv;
    private String[] titles;
    @AfterViews
    void aFterViews(){

        news_bean=new News_Bean();
        try {
         newsDao=getHelper().getNewsDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initView();
        //从数据库获取
        lView.addHeaderView(view);
        queryDAO();
        //后台获取数据
       datas();

    }

    private void queryDAO() {
        try {
                List<News_Bean> news_beans = newsDao.queryForAll();
                Log.d("1111111",""+news_beans);
                if (news_beans!=null&&news_beans.size()!=0){
                    db_adapter.setData_DB(news_beans);
                    db_adapter.notifyDataSetChanged();
                    lView.setAdapter(db_adapter);
                    //count: 获取item总数
//                    int count = lView.getAdapter().getCount();
//                    Log.d("1111111111","count"+count);

                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Background //开启新线程后台运行，注意不要引用UI控件,而且返回值类型一定是void
    void datas(){

        //获取listView数据
        initdatas();
        //获取ViewPager数据
       getPagerDatas(tag);
        //UI线程
       upUiData();
    }
    @UiThread()
    void upUiData(){
        //屏幕宽高
        initPingMu();
        viewPager.setAdapter(adapters);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (new_ll!=null){
                    new_tv.setText(titles[position]);
                    new_ll.getChildAt(prePosition).setBackgroundResource(R.drawable.dot);
                    new_ll.getChildAt(position).setBackgroundResource(R.drawable.dot_);
                }else{
                }
                prePosition=position;
            }
            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @ItemClick
    void mian_lv(int position){
        Toast.makeText(this,"点击了item"+position,Toast.LENGTH_SHORT).show();
    }




	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
         //  dialog();
            exitBy2Click();

         return true;
        }
        return true;
    }
    private static Boolean isExit=false;
    private void exitBy2Click() {
        Timer timer=null;
        if (isExit==false){
            isExit=true; //准备退出
            Toast.makeText(this,"亲，再按一次退出应用",Toast.LENGTH_SHORT).show();
            timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                   isExit=false;//取消退出
                }
            },2000);//两秒内没有按返回键，则启动定时器取消刚才执行的任务
        }else{

            finish();
            System.exit(0);

        }

    }

    /**
     * 提示是否退出对话框
     */
    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("是否退出?");
        builder.setTitle("操作提示");
        builder.setNegativeButton("否",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.setPositiveButton("是",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        System.exit(0);
                    }
                });
        builder.create().show();
    }
    //屏幕宽高
    private void initPingMu() {
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        heightPixels = metrics.heightPixels;
        widthPixels = metrics.widthPixels;
    }
	private void initdatas() {
		AVQuery<AVObject> avQuery=AVQuery.getQuery("ArrayNew");

		avQuery.findInBackground(new FindCallback<AVObject>() {
			@Override
			public void done(List<AVObject> arg0, AVException arg1) {
				if (arg1==null) {
                    avdatas=new ArrayList<AVObject>();
					avdatas=arg0;
					adapter.setData(avdatas);
                    adapter.notifyDataSetChanged();
                    lView.setAdapter(adapter);
                    //网上获取数据添加到数据库
                    for (int i=0;i<arg0.size();i++){
                        news_bean.setTitle(arg0.get(i).getString("title"));
                        news_bean.setImages_id(arg0.get(i).getInt("images_id"));
                        news_bean.setContent(arg0.get(i).getString("content"));
                       JSONArray images = arg0.get(i).getJSONArray("images");
                       JSON.toJSONString(images);
                       try {
                         news_bean.setImages(images.toString());
                         newsDao.createOrUpdate(news_bean);
                       } catch (SQLException e) {
                           e.printStackTrace();
                       }
                   }
				}
			}
		});
		
	}


	private void initView() {
        initPingMu();
		view = LayoutInflater.from(this).inflate(R.layout.pager1,null);
        viewPager = (ViewPager) view.findViewById(R.id.vp1);
        ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
        layoutParams.height=heightPixels/3;
        viewPager.setLayoutParams(layoutParams);
        new_tv = (TextView) view.findViewById(R.id.new_tv);
        titles=getTitles();
        new_tv.setText(titles[0]);
	}


	private void getPagerDatas(final boolean tag) {
		AVQuery<AVObject> pagerQuery=AVQuery.getQuery("Pager");
		pagerQuery.findInBackground(new FindCallback<AVObject>() {
			@Override
			public void done(List<AVObject> arg0, AVException arg1) {
				if (arg1==null) {
                   viewPager.setBackgroundColor(Color.BLACK);
					pagerList=new ArrayList<AVObject>();
					pagerList=arg0;
					adapters.setDatas(pagerList);
                    View view1 = null;
                    //清空小圆点
                    if (tag==true){
                        new_ll.removeAllViews();
                    }
                        for(int i=0;i<pagerList.size();i++){

                            new_ll = (LinearLayout) view.findViewById(R.id.new_ll);
                            view1=new View(MainActivity.this);
                            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(10,10);
                            params.leftMargin=5;
                            view1.setLayoutParams(params);
                            view1.setBackgroundResource(R.drawable.dot);
                            new_ll.addView(view1);

                    }
                    new_ll.getChildAt(0).setBackgroundResource(R.drawable.dot_);

				}else {
					Toast.makeText(MainActivity.this, "获取数据失败，请检查网络", Toast.LENGTH_SHORT).show();

				}
			}
		});
		
	}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*
         *
         * add()方法的四个参数，依次是：
         *
         * 1、组别，如果不分组的话就写Menu.NONE,
         *
         * 2、Id，这个很重要，Android根据这个Id来确定不同的菜单
         *
         * 3、顺序，那个菜单现在在前面由这个参数的大小决定
         *
         * 4、文本，菜单的显示文本
         */

        menu.add(Menu.NONE, Menu.FIRST + 1, 5, "刷新").setIcon(

                android.R.drawable.ic_menu_delete);

        // setIcon()方法为菜单设置图标，这里使用的是系统自带的图标，同学们留意一下,以

        // android.R开头的资源是系统提供的，我们自己提供的资源是以R开头的

        menu.add(Menu.NONE, Menu.FIRST + 2, 2, "保存").setIcon(

                android.R.drawable.ic_menu_edit);

        menu.add(Menu.NONE, Menu.FIRST + 3, 6, "清除数据").setIcon(

                android.R.drawable.ic_menu_help);

        menu.add(Menu.NONE, Menu.FIRST + 4, 1, "添加").setIcon(

                android.R.drawable.ic_menu_add);

        menu.add(Menu.NONE, Menu.FIRST + 5, 4, "详细").setIcon(

                android.R.drawable.ic_menu_info_details);

        menu.add(Menu.NONE, Menu.FIRST + 6, 3, "退出").setIcon(

                android.R.drawable.ic_menu_send);

        return true;
    }
    boolean tag=false;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case Menu.FIRST + 1:
                initdatas();

                getPagerDatas(true);
                Toast.makeText(this, "刷新菜单被点击了", Toast.LENGTH_LONG).show();

                break;

            case Menu.FIRST + 2:

                Toast.makeText(this, "保存菜单被点击了", Toast.LENGTH_LONG).show();

                break;

            case Menu.FIRST + 3:

                Toast.makeText(this, "清除数据", Toast.LENGTH_LONG).show();
                try {
                    newsDao.deleteBuilder().delete();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case Menu.FIRST + 4:

                Toast.makeText(this, "添加菜单被点击了", Toast.LENGTH_LONG).show();

                break;

            case Menu.FIRST + 5:

                Toast.makeText(this, "详细菜单被点击了", Toast.LENGTH_LONG).show();

                break;

            case Menu.FIRST + 6:

                AVUser.logOut();
				Toast.makeText(MainActivity.this,
						"重新登入", Toast.LENGTH_LONG).show();
				Intent intent=new Intent(MainActivity.this,LoginActivity_.class);
				startActivity(intent);
				finish();


                break;

        }

        return false;

    }

    public String[] getTitles() {
        return new String[]{"今日头条","深圳现状","英雄联盟","少女时代","天竺神圣","明天晚报","大刀早已饥渴","a","","",""};
    }

}

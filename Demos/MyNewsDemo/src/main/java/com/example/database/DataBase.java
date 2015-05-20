package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.bean.News_Bean;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by hd94 on 2015/5/14.
 */
public class DataBase extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_Name="ormlite.db";
    private static final int DATABASE_VERSION=1;
    private Dao<News_Bean,Integer> newsDao=null;

    public DataBase(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, DATABASE_Name, null, DATABASE_VERSION);
    }
    public DataBase(Context context){
        super(context,DATABASE_Name,null,DATABASE_VERSION);
    }
    /**
     * 创建SQLite数据库
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,News_Bean.class);
            newsDao=getNewsDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<News_Bean, Integer> getNewsDao() throws SQLException {
        if (newsDao==null){
            newsDao=getDao(News_Bean.class);
        }
        return newsDao;
    }

    /**
     * 更新SQLite数据库
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            TableUtils.dropTable(connectionSource,News_Bean.class,true);
            onCreate(sqLiteDatabase,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() {
        super.close();
        newsDao=null;
    }
}

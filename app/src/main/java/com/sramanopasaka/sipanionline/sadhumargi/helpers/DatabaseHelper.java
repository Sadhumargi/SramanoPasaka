package com.sramanopasaka.sipanionline.sadhumargi.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private final ConcurrentHashMap<Class, Boolean> dao;

    private static final String DATABASE_NAME = "sadhumargi.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dao = new ConcurrentHashMap<>();
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        Log.i(DatabaseHelper.class.getName(), "onCreate");
        try {
            TableUtils.createTable(getConnectionSource(), OfflineTable.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }


    public <T> Dao<T, Integer> getCustomDao(Class<T> cls) throws SQLException {
        if (dao.get(cls) == null || !dao.get(cls)) {
            dao.putIfAbsent(cls, true);
        }
        return getDao(cls);
    }

    @Override
    public void close() {
        super.close();
    }
}
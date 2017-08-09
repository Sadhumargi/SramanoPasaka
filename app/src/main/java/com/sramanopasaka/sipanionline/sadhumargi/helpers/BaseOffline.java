package com.sramanopasaka.sipanionline.sadhumargi.helpers;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.sramanopasaka.sipanionline.sadhumargi.MyApplication;

import java.sql.SQLException;
import java.util.List;

public class BaseOffline {

    private static Gson gson = new Gson();


    protected static boolean deleteOfflineData(String id) {
        try {
            DeleteBuilder<OfflineTable, Integer> offlineTableIntegerDeleteBuilder = MyApplication.getInstance().getDBHepler().getCustomDao(OfflineTable.class).deleteBuilder();
            offlineTableIntegerDeleteBuilder.where().eq("id", id);
            int delete = offlineTableIntegerDeleteBuilder.delete();
            return delete > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected static <T> T getStringToObject(String obj, Class<T> cls) {
        return gson.fromJson(obj, cls);
    }


    protected static void saveOfflineData(final String id, final Object item) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Dao<OfflineTable, Integer> offlineDao = MyApplication.getInstance().getDBHepler().getCustomDao(OfflineTable.class);
                    OfflineTable table = new OfflineTable();
                    table.id = id;
                    table.data = gson.toJson(item);
                    if (getOfflineData(id) == null) {
                        offlineDao.create(table);
                    } else {
                        offlineDao.update(table);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.run();
    }

    protected static String getOfflineData(String id) {
        String offlineData = null;
        try {
            Dao<OfflineTable, Integer> offlineDao = MyApplication.getInstance().getDBHepler().getCustomDao(OfflineTable.class);
            QueryBuilder<OfflineTable, Integer> offlineTableIntegerQueryBuilder = offlineDao.queryBuilder();
            offlineTableIntegerQueryBuilder.where().eq("id", id);
            List<OfflineTable> query = offlineDao.query(offlineTableIntegerQueryBuilder.prepare());
            if (query != null && query.size() > 0) {
                offlineData = query.get(0).data;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offlineData;
    }

}
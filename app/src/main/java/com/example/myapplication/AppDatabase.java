package com.example.myapplication;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

/**
 * 应用数据库：单例模式，管理所有表和Dao
 */
@Database(entities = {RoleEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    // 单例实例
    private static volatile AppDatabase INSTANCE;

    // 获取RoleDao
    public abstract RoleDao roleDao();

    // 获取数据库单例
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // 创建数据库（名称：role_db）
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "role_db"
                            )
                            .allowMainThreadQueries() // 简化：允许主线程操作（正式版建议用协程）
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
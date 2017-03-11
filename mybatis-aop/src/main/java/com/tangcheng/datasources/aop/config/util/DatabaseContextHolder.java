package com.tangcheng.datasources.aop.config.util;

/**
 * 作用：
 * 1、保存一个线程安全的DatabaseType容器
 */
public class DatabaseContextHolder {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static DatabaseType getDatabaseType() {
        return contextHolder.get() == null ? DatabaseType.TEST2 : contextHolder.get();
    }

    public static void setDatabaseType(DatabaseType dbType) {
        if (dbType == null) throw new NullPointerException("dbType must not be null");
        contextHolder.set(dbType);
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}
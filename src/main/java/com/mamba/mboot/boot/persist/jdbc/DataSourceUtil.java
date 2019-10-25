package com.mamba.mboot.boot.persist.jdbc;

import com.mamba.mboot.boot.common.ThreadContext;
import javax.sql.DataSource;

public class DataSourceUtil {
    public DataSourceUtil() {
    }

    public static void switchDataSource(String key) {
        ThreadContext.put(DialectRoutingDatasource.DATASOURCE_NAME_KEY, key);
    }

    public static DataSource getCurrentDataSource() {
        DataSource dataSource = (DataSource)ThreadContext.get(DialectRoutingDatasource.DATASOURCE_KEY);
        return dataSource;
    }
}
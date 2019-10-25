package com.mamba.mboot.boot.persist.jdbc;

import com.mamba.mboot.boot.common.exception.NEError;
import com.mamba.mboot.boot.common.exception.NEException;
import com.mamba.mboot.boot.persist.jdbc.dialect.DerbyDialect;
import com.mamba.mboot.boot.persist.jdbc.dialect.ImpalaDialect;
import com.mamba.mboot.boot.persist.jdbc.dialect.MySQLDialect;
import com.mamba.mboot.boot.persist.jdbc.dialect.OracleDialect;
import com.mamba.mboot.boot.persist.jdbc.dialect.SqlServerDialect;
import java.util.HashMap;
import java.util.Map;

public class DialectProvider {
    private static final Map<String, Dialect> DIALECTS = new HashMap();

    public DialectProvider() {
    }

    public static Dialect get(String key) {
        if (key != null) {
            key = key.toUpperCase();
        }

        if (!DIALECTS.containsKey(key)) {
            throw new NEException(NEError.SYSTEM_INTERNAL_ERROR, "dialect " + key + " is not supported");
        } else {
            return (Dialect)DIALECTS.get(key);
        }
    }

    static {
        DIALECTS.put("DERBY", new DerbyDialect());
        DIALECTS.put("ORACLE", new OracleDialect());
        DIALECTS.put("MYSQL", new MySQLDialect());
        DIALECTS.put("SQLSERVER", new SqlServerDialect());
        DIALECTS.put("IMPALA", new ImpalaDialect());
    }
}
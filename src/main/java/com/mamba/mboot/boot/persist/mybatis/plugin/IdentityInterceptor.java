package com.mamba.mboot.boot.persist.mybatis.plugin;

import com.mamba.mboot.boot.persist.jdbc.Dialect;
import com.mamba.mboot.boot.persist.jdbc.DialectProvider;
import com.mamba.mboot.boot.persist.jdbc.DialectUtil;
import com.mamba.mboot.boot.persist.mybatis.plugin.AbstractInterceptor.PageSqlSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
)})
public class IdentityInterceptor extends AbstractInterceptor implements Interceptor {
    private String dialect = "MYSQL";

    public IdentityInterceptor() {
    }

    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation
                .getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        String id = mappedStatement.getId();
        if (!StringUtils.endsWith(id, "!selectKey")) {
            return invocation.proceed();
        }
        Configuration configuration = mappedStatement.getConfiguration();
        DataSource dataSource = configuration.getEnvironment().getDataSource();
        Dialect dialect = DialectUtil.getDialect(dataSource);
        if (dialect == null) {
            dialect = DialectProvider.get(this.dialect);
        }
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        if (boundSql != null && boundSql.getSql() != null && StringUtils.isNotEmpty(boundSql.getSql())) {
            return invocation.proceed();
        }
        String identitySql = dialect.getIdentityString();
        MappedStatement newMs = copyFromMappedStatement(mappedStatement,
                new PageSqlSource(getBoundSql(boundSql, identitySql)));
        invocation.getArgs()[0] = newMs;
        return invocation.proceed();
    }


    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    public void setProperties(Properties arg0) {
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }
}

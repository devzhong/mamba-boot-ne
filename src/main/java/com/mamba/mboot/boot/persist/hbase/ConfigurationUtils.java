package com.mamba.mboot.boot.persist.hbase;

import java.util.Enumeration;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.springframework.util.Assert;

public abstract class ConfigurationUtils {
    public ConfigurationUtils() {
    }

    public static void addProperties(Configuration configuration, Properties properties) {
        Assert.notNull(configuration, "A non-null configuration is required");
        if (properties != null) {
            Enumeration props = properties.propertyNames();

            while(props.hasMoreElements()) {
                String key = props.nextElement().toString();
                configuration.set(key, properties.getProperty(key));
            }
        }

    }
}

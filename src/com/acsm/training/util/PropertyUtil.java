package com.acsm.training.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;

/**
 * Property工具类
 * 
 * @author Administrator
 * 
 */
public class PropertyUtil {

    private static Logger log = Logger.getLogger(PropertyUtil.class);

    private static Properties properties;

    static {
        // 初始化License的数据
        properties = new Properties();
        InputStream inputStream = ApplicationListener.class.getClassLoader()
                .getResourceAsStream("license.properties");
        try {
            properties.load(inputStream);
        }
        catch (IOException e) {
            log.error("初始化License文件失败", e);
        }
    }

    public static String getProperty(String propertyKey) {
        if (propertyKey == null || propertyKey.isEmpty()) {
            return "";
        }
        return properties.get(propertyKey).toString();
    }
}
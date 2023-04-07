package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void printLog(Logger logger, String message, Object... objs) {
        if (loggerShow(logger)) {
            logger.debug(message, objs);
        }
    }

    /**
     * 日志显示
     *
     * @return
     */
    public static boolean loggerShow() {
        return logger.isDebugEnabled();
    }

    /**
     * 日志显示
     *
     * @return
     */
    public static boolean loggerShow(Logger logger) {
        return logger.isDebugEnabled();
    }
}

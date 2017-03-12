package com.xavier.utilities;

import org.apache.log4j.Logger;

/**
 * Created by Xavier on 2017/3/7.
 */
public class DI {
    public static final String infoStart(String keyword) {
        return ">>>-------------->>>--------------" + keyword + "-------------->>>--------------";
    }

    public static final String infoEnd(String keyword) {
        return "<<<--------------<<<--------------" + keyword + "--------------<<<--------------";
    }

    public static final String printMessage(Object message) {
        return
                "\n>>>-------------->>>-------------->>>-------------->>>-------------->>>-------------->>>-------------->>>\n" +
                        message +
                        "\n<<<--------------<<<--------------<<<--------------<<<--------------<<<--------------<<<--------------<<<\n";
    }

    public static final void debug(Logger logger, Object message) {
        String formattedMessage =
                "\n>>>------------------------------------------------------------------------------------------------\n" +
                        message +
                        "\n<<<------------------------------------------------------------------------------------------------\n";
        logger.debug(formattedMessage);
    }

    public static final void info(Logger logger, Object message) {
        String formattedMessage =
                "\n>>>------------------------------------------------------------------------------------------------\n" +
                        message +
                        "\n<<<------------------------------------------------------------------------------------------------\n";
        logger.info(formattedMessage);
    }
}

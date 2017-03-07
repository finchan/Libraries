package com.xavier.utilities;

/**
 * Created by Xavier on 2017/3/7.
 */
public class DebugInfo {
    public static final String debugStart( String keyword ) {
        return "-------------->>>--------------" + keyword + "-------------->>>--------------";
    }

    public static final String debugEnd( String keyword ) {
        return "--------------<<<--------------" + keyword + "--------------<<<--------------";
    }

    public static final String debugEmbrace(Object message) {
        return "\n-------------->>>-------------->>>-------------->>>--------------\n" +
                message +
                "\n--------------<<<--------------<<<--------------<<<--------------\n";
    }
}

package com.example.dell.jaapactivity;

import android.provider.BaseColumns;

public class JapContractClass {
    public static final class WaitListEntry implements BaseColumns {
        public static final String TABLE_JAP_DATA = "japData";
        public static final String KEY_ID = "id";
        public static final String KEY_TYPE = "type";
        public static final Long KEY_TIME = 0l;
        public static final String VIDEO_URL = null;
        public static final boolean HAS_VIDEO = false;

    }
}

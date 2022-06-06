package entity.sql;

import java.util.Map;
import java.util.HashMap;

public class DatabaseStructure {

    // Database name
    public static final String DATABASE_NAME = "echat";

    // Table names
    public static final String TABLE_USER = "user";
    public static final String TABLE_SESSION = "session";
    public static final String TABLE_SESSION_MEMBER = "session_member";
    public static final String TABLE_LOG = "log";

    public enum UserColumn{

    }
    public enum SessionColumn{

    }
    public enum SessionMemberColumn{

    }
    public enum LogColumn{

    }

    // Column names
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_NICKNAME = "user_name";
    public static final String COLUMN_PASSWORD = "password";

    public static final String COLUMN_SESSION_ID = "session_id";
    public static final String COLUMN_SESSION_NAME = "session_name";
    public static final String COLUMN_OWNER_ID = "owner_id";

    public static final String COLUMN_LOG_CONTENT = "log_content";
    public static final String COLUMN_LOG_TIME = "log_time";
}

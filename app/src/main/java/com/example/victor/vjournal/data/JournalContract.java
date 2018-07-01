package com.example.victor.vjournal.data;

import android.provider.BaseColumns;

/**
 * Created by FrankStiles on 28-Jun-18.
 */

public final class JournalContract {

    public class JournalEntry implements BaseColumns{

        public static final String TABLE_USER_NAME = "users";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "first_name";

        public static final String TABLE_CONTACT_NAME = "contacts";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE_NO = "phone_no";

        public static final String TABLE_JOURNALS_NAME = "users_journals";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_GENDER = "gender";

        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;

    }
}

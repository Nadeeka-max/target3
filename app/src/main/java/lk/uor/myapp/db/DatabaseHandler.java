package lk.uor.myapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import lk.uor.myapp.bean.UserBean;
import lk.uor.myapp.bean.EventBean;
import lk.uor.myapp.bean.UserBean;

/**
 * Created by vidula-admin on 6/11/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION   = 1;
    // Database Name
    private static final String DATABASE_NAME   = "icbt_mad.db";

    private static final String TABLE_USER      = "User";
    private static final String TABLE_EVENT     = "Event";

    private static final String KEY_ID          = "key_id";
    private static final String NAME            = "name";

    private static final String EMAIL           = "email";
    private static final String CONTACT_NO      = "contact_no";
    private static final String USERNAME        = "username";
    private static final String PASSWORD        = "password";

    private static final String DESCRIPTION     = "description";
    private static final String TYPE            = "type";
    private static final String DATE            = "date";
    private static final String TIME            = "time";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Tables inside OnCreate()
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + EMAIL + " TEXT,"
                + CONTACT_NO + " TEXT,"
                + USERNAME + " TEXT,"
                + PASSWORD + " TEXT" + ")";

        String CREATE_EVENT_TABLE = "CREATE TABLE " + TABLE_EVENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + DESCRIPTION + " TEXT,"
                + TYPE + " TEXT,"
                + DATE + " TEXT,"
                + TIME + " TEXT" + ")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_EVENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);

        // Create tables again
        onCreate(db);
    }

    // Adding new user
    public long addUser(UserBean user) {
        long rowID = -1;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, user.getName()); // Name
        values.put(EMAIL, user.getEmail()); // Email
        values.put(CONTACT_NO, user.getCotactNo()); // Contact
        values.put(USERNAME, user.getUsername()); // Username
        values.put(PASSWORD, user.getPassword()); // Password

        // Inserting Row
        rowID = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
        return rowID;
    }

    // Getting User Count
    public int getUserCount() {
        String countQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        db.close();

        // return count
        return count;
    }

    public UserBean getUser(String username, String password){
        UserBean userBean = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String[] column = new String[]{KEY_ID, NAME, EMAIL, CONTACT_NO, USERNAME, PASSWORD};

        Cursor cursor = db.query(TABLE_USER, column
                , USERNAME + "=? AND " + PASSWORD + "=?",
                new String[]{username, password}, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    userBean = new UserBean(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                            cursor.getString(3), cursor.getString(4),
                            cursor.getString(5));
                } while (cursor.moveToNext());
            }
        }

        cursor.close();
        db.close();

        return userBean;
    }

//    public ArrayList<EventBean> getEventList(){
//        ArrayList<EventBean> eventList = new ArrayList<EventBean>();
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String[] column = new String[]{KEY_ID, NAME, DESCRIPTION, TYPE, DATE, TIME};
//        Cursor cursor = db.query(TABLE_EVENT, column, null, null, null, null, null, null);
//
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                do {
//                    eventList.add(new EventBean(cursor.getString(0), cursor.getString(1),
//                            cursor.getString(2), cursor.getString(3),
//                            cursor.getString(4), cursor.getString(5)));
//                } while (cursor.moveToNext());
//            }
//        }
//
//        cursor.close();
//        db.close();
//        return eventList;
//    }

    // Getting User Count
    public int getUserCount(String username, String password) {
        String countQuery = "SELECT * FROM " + TABLE_USER + " WHERE "
                + USERNAME + "='" + username + "' AND "
                + PASSWORD + "='" + password + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        db.close();

        // return count
        return count;
    }

    // Adding new Event
//    public long addEvent(EventBean event) {
//        long rowID = -1;
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(NAME, event.getName()); // Name
//        values.put(DESCRIPTION, event.getDescription()); // Description
//        values.put(TYPE, event.getType()); // Type
//        values.put(DATE, event.getDate()); // Date
//        values.put(TIME, event.getTime()); // Time
//
//        // Inserting Row
//        rowID = db.insert(TABLE_EVENT, null, values);
//        db.close(); // Closing database connection
//        return rowID;
//    }

    // Updating new Event
//    public long updateEvent(EventBean event) {
//        long rowID = -1;
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(NAME, event.getName()); // Name
//        values.put(DESCRIPTION, event.getDescription()); // Description
//        values.put(TYPE, event.getType()); // Type
//        values.put(DATE, event.getDate()); // Date
//        values.put(TIME, event.getTime()); // Time
//
//        // Inserting Row
//        rowID = db.update(TABLE_EVENT, values, KEY_ID + "=?", new String[]{event.getKeyID()});
//        db.close(); // Closing database connection
//        return rowID;
//    }

//    public int deleteEvent(EventBean eventBean){
//        int row = 0;
//        SQLiteDatabase db = this.getWritableDatabase();
//        row = db.delete(TABLE_EVENT, KEY_ID + "=?", new String[]{eventBean.getKeyID()});
//        db.close();
//        return row;
//    }

}

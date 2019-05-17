//package com.example.emrekacan.exampleproject.Data;
//
//import android.content.ContentProvider;
//import android.content.ContentUris;
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.UriMatcher;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.net.Uri;
//
//public class DatabaseProvider extends ContentProvider {
//
//    SQLiteDatabase db;
//
//    static final String CONTENT_AUTHORITY = "com.example.emrekacan.exampleproject.databaseprovider";
//    static final String PATH_NOTLAR = "notes";
//
//    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
//    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NOTLAR);
//
//    static final UriMatcher matcher;
//
//    static {
//        matcher = new UriMatcher(UriMatcher.NO_MATCH);
//        matcher.addURI(CONTENT_AUTHORITY, PATH_NOTLAR, 1);
//    }
//
//    private final static int DATABASE_VERSION = 1;
//    private final static String DATABASE_NAME = "notesdatabase.db";
//    private final static String NOTLAR_TABLE_NAME = "notes";
//
//    private final static String CREATE_NOTES_TABLE = "CREATE TABLE " + NOTLAR_TABLE_NAME
//            + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
//            + "notIcerik TEXT NOT NULL, "
//            + "notTarih TEXT, "
//            + "tamamlandi INTEGER DEFAULT 0 ); ";
//
//
//    @Override
//    public boolean onCreate() {
//        DataBaseHelper helper = new DataBaseHelper(getContext());
//        db = helper.getWritableDatabase();
//        return false;
//    }
//
//
//    @Override
//    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
//        return null;
//    }
//
//    @Override
//    public String getType(Uri uri) {
//        return null;
//    }
//
//
//    @Override
//    public Uri insert(Uri uri, ContentValues values) {
//        switch (matcher.match(uri)) {
//            case 1:
//                Long columnID = db.insert(DATABASE_NAME, null, values);
//                if (columnID > 0) {
//                    Uri _uri = ContentUris.withAppendedId(CONTENT_URI, columnID);
//                    return _uri;
//                }
//                break;
//        }
//        return null;
//    }
//
//    @Override
//    public int delete(Uri uri, String selection, String[] selectionArgs) {
//        return 0;
//    }
//
//    @Override
//    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
//        return 0;
//    }
//
//    private class DataBaseHelper extends SQLiteOpenHelper {
//
//        public DataBaseHelper(Context context) {
//            super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase db) {
//            db.execSQL(CREATE_NOTES_TABLE);
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            db.execSQL("DROP TABLE IF EXISTS " + NOTLAR_TABLE_NAME);
//            onCreate(db);
//        }
//    }
//}

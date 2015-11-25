package com.example.aadam.librariansassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aadam on 16-Nov-15.
 */
public class DBManager
{

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Library";

    private static final String TABLE_BOOKS = "Books";

    private static final String KEY_ID = "_id";
    private static final String KEY_BOOK_NAME = "name";
    private static final String KEY_AUTHOR_ID = "authorID";
    private static final String KEY_AUTHOR_NAME = "authorName";
    private static final String KEY_COPIES = "copies";

    private static final String CREATE_BOOKS_TABLE = "CREATE TABLE Books (_id INTEGER PRIMARY KEY autoincrement, name TEXT, authorID TEXT, authorName TEXT, copies TEXT);";


    // other attributes
    private final Context context;
    private MyDatabaseHelper DBHelper;
    private SQLiteDatabase db;

    //
    public DBManager(Context ctx)
    {
        this.context 	= ctx;
        DBHelper 		= new MyDatabaseHelper(context);
    }

    private static class MyDatabaseHelper extends SQLiteOpenHelper
    {
        //
        MyDatabaseHelper(Context context)
        {
            // Call constructor of superclass of SQLLiteOpenHelper
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        //
        public void onCreate(SQLiteDatabase db)
        {
            // execute SQL Create table
            db.execSQL(CREATE_BOOKS_TABLE);
        }

        @Override
        //
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
            // execute SQL to update database structure, if relevant .
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);

            onCreate(db);
        }
    }   // end inner class


    // Include methods to connect to the database (e.g. getWriteable/getReadable database).. and to close the database.

    public DBManager open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        DBHelper.close();
    }

    //insert
    //column values sent as parameters from calling activity
    public long insertTask(String bName, String aName, String aID, String copies)
    {

        ContentValues initialValues = new ContentValues();

        // set the column values – in this case, firstname/surname/city
        initialValues.put(KEY_BOOK_NAME, bName);
        initialValues.put(KEY_AUTHOR_NAME, aName);
        initialValues.put(KEY_AUTHOR_ID, aID);
        initialValues.put(KEY_COPIES, copies);

        return db.insert(TABLE_BOOKS, null, initialValues);
    }


    // method for querying database (unused)
    public Cursor getBook(String query)
    {

        Cursor mCursor = db.rawQuery(
               // "SELECT * FROM Tasks WHERE name LIKE " + "'" + query + "';", null);
                "SELECT * FROM Books", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;

    }


    public Cursor deleteBook(String query)
    {

        Cursor mCursor = db.rawQuery(

                "DELETE FROM Books WHERE _id = " + "'" + query + "';", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;

    }

    public Cursor updateBook(String bookID, String newValue)
    {

        Cursor mCursor = db.rawQuery(

                "UPDATE Books " +
                        "SET copies = " + "'" + newValue + "' " +
                        "WHERE _id = " + "'" + bookID + "';", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;

    }

    //Return all the books for the ListBooks activity
    public Cursor getAll()
    {
        Cursor mCursor = db.rawQuery(
                "SELECT * FROM Books ;", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;

    }

}

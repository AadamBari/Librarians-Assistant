package com.example.aadam.librariansassistant;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Aadam on 19-Nov-15.
 */
public class BookCursorAdapter extends CursorAdapter{

    public BookCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.row, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView bookName = (TextView) view.findViewById(R.id.book_name);
        TextView bookAuthor = (TextView) view.findViewById(R.id.author);
        TextView copies = (TextView) view.findViewById(R.id.copies);
        TextView bookID = (TextView) view.findViewById(R.id.book_id);
        // Extract properties from cursor
        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        String author = cursor.getString(cursor.getColumnIndexOrThrow("authorName"));
        String cop = cursor.getString(cursor.getColumnIndexOrThrow("copies"));
        String id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));

        // Populate fields with extracted properties
        bookName.setText(name);
        bookAuthor.setText(author);
        copies.setText(cop);
        bookID.setText(id);
    }


}

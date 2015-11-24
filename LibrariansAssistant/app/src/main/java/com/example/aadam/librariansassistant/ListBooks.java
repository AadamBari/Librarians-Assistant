package com.example.aadam.librariansassistant;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListBooks extends AppCompatActivity {

    DBManager db = new DBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);

        ListView myListView = (ListView ) findViewById(R.id.listViewBooks);

        try {

            db.open();
            Cursor result = db.getAll();
            BookCursorAdapter cursorAdapter = new BookCursorAdapter(this, result);
            myListView.setAdapter(cursorAdapter);
            db.close();

        } catch (Exception ex) {
            Context context = getApplicationContext();
            CharSequence text = "Error opening database";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        //a listener to see whe you click on an item inside that list
        myListView.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {

                    //setting up a listen on list, now list waiting for user to click something
                    //also gives information on which item clicked
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        //get value of item at tapped position
                        Cursor myCursor = (Cursor) parent.getItemAtPosition(position);
                        String bookId = myCursor.getString(0);
                        String bookName = myCursor.getString(1);
                        String bookAuthor = myCursor.getString(3);
                        Toast.makeText(ListBooks.this, bookName +" by "+ bookAuthor, Toast.LENGTH_SHORT).show();

                        //Intent i = new Intent(ListBooks.this, Edition.class);
                        //startActivity(i);

                    }
                }

        );

    }


}

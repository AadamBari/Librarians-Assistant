package com.example.aadam.librariansassistant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddBook extends AppCompatActivity {

    DBManager db = new DBManager(this);
    EditText bookName;
    EditText authorName;
    EditText authorID;
    EditText copies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);


    }

    public void onClickAddBook(View view){

        bookName = (EditText) findViewById(R.id.editTextBookName);
        authorName = (EditText) findViewById(R.id.editTextAuthorName);
        authorID = (EditText) findViewById(R.id.editTextAuthorID);
        copies = (EditText) findViewById(R.id.editTextCopies);

        try {

            //instantiate database
            db.open();
            db.insertTask(bookName.getText().toString(), authorName.getText().toString(), authorID.getText().toString(), copies.getText().toString());
            db.close();

        } catch (Exception ex) {
            Context context = getApplicationContext();
            CharSequence text = "Error opening database";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        Toast toast = Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT);
        toast.show();

        Intent i = new Intent(this, ListBooks.class);
        startActivity(i);

    }


}//end AddBook

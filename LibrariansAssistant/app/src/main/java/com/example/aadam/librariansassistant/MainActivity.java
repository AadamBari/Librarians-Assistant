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


public class MainActivity extends AppCompatActivity {

    DBManager db = new DBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            //instantiate database
            db.open();
            //initial values
            //db.insertTask("The Da Vinci Code", "Dan Brown", "500", "1");
            //db.insertTask("Legend", "David Gemmell", "300", "3");
            //db.insertTask("The Art of War", "Sun Tzu", "800", "2");
            //db.insertTask("A Clockwork Orange", "Anthony Burgess", "405", "5");
            //db.insertTask("The Hunger Games", "Suzanne Collins", "650", "3");
            db.insertTask("A Memory of Light", "Robert Jordan", "150", "0");

            db.close();

        } catch (Exception ex) {
            Context context = getApplicationContext();
            CharSequence text = "Error opening database";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void onClickSearch(View view){

        Intent i = new Intent(this, ListBooks.class);
        startActivity(i);

    }

    //Add book Button
    public void onClickAdd(View view){

        Intent i = new Intent(this, AddBook.class);
        startActivity(i);

    }

}
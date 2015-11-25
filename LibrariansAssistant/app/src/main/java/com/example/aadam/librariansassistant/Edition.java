package com.example.aadam.librariansassistant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Edition extends AppCompatActivity {

    //text views
    TextView theBookText;
    TextView theAuthorText;

    //database strings passed in from ListBooks
    String theBookID;
    String theBookName;
    String theAuthorName;
    String noOfCopies;

    DBManager db = new DBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);

        theBookText = (TextView) findViewById(R.id.currentBook);
        theAuthorText = (TextView) findViewById(R.id.authorText);

        //get book data from previous activity (ListBooks)
        Bundle bookData = getIntent().getExtras();

        if (bookData == null)
        {
            return;
        }

        //get selected book's title and author
        theBookName = bookData.getString("nameMessage");
        theAuthorName = bookData.getString("authorMessage");

        theBookText.setText(theBookName);
        theAuthorText.setText(theAuthorName);

        //get the selected book's id and no of copies
        theBookID = bookData.getString("idMessage");
        noOfCopies = bookData.getString("copiesMessage");

    }

    public void onClickDelete(View view){

        try {

            //instantiate database
            db.open();
            db.deleteBook(theBookID);
            db.close();

            Toast toast = Toast.makeText(this, theBookName + " has been deleted", Toast.LENGTH_SHORT);
            toast.show();

        } catch (Exception ex) {
            Context context = getApplicationContext();
            CharSequence text = "Error opening database";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void onClickReserve(View view){

        try {

            //convert noOfCopies to int, decrement than  reverse
            int temp = Integer.parseInt(noOfCopies);

            if (temp > 0)
            {

                temp--; //decrement
                noOfCopies = Integer.toString(temp);

                //instantiate database
                db.open();
                db.updateBook(theBookID, noOfCopies);
                db.close();

                Toast toast = Toast.makeText(this, theBookName +" has been reserved", Toast.LENGTH_SHORT);
                toast.show();

            }
            else
            {
                Toast toast = Toast.makeText(this, theBookName +" is not available", Toast.LENGTH_SHORT);
                toast.show();

            }


        } catch (Exception ex) {
            Context context = getApplicationContext();
            CharSequence text = "Error opening database";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    //Return to main menu
    public void onClickMainMenu(View view){

        finish();
        //Intent i = new Intent(this, MainActivity.class);
        //startActivity(i);
    }

    //back button option, so can return to ListBooks
    public void onBackPressed()
    {
        finish();
        Intent i = new Intent(this, ListBooks.class);
        startActivity(i);

    }

}

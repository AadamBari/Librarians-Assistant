package com.example.aadam.librariansassistant;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.delete_message);

            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    //instantiate database
                    db.open();
                    db.deleteBook(theBookID);
                    db.close();

                    Toast toast = Toast.makeText(Edition.this, theBookName + " has been deleted", Toast.LENGTH_SHORT);
                    toast.show();

                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

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

            //create dialog box to confirm user wants to reserve
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.reserve_message);

            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button

                    //convert noOfCopies to int
                    int temp = Integer.parseInt(noOfCopies);

                    //if copies available
                    if (temp > 0)
                    {

                        temp--; //decrement
                        noOfCopies = Integer.toString(temp);

                        //instantiate database
                        db.open();
                        db.updateBook(theBookID, noOfCopies);
                        db.close();

                        Toast toast = Toast.makeText(Edition.this, theBookName +" has been reserved", Toast.LENGTH_SHORT);
                        toast.show();

                    }
                    else
                    {
                        Toast toast = Toast.makeText(Edition.this, theBookName +" is not available", Toast.LENGTH_SHORT);
                        toast.show();

                    }


                }
            }); //end positive button
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            }); //end negative button

            //create the dialog box
            AlertDialog dialog = builder.create();
            //show the dialog box
            dialog.show();


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

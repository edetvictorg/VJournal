package com.example.victor.vjournal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victor.vjournal.data.JournalContract.JournalEntry;
import com.example.victor.vjournal.data.JournalDbHelper;


public class AddJournal extends AppCompatActivity {

    private EditText mEventTitle;
    private EditText mEventDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal);

        mEventTitle = (EditText) findViewById(R.id.add_event_title);
        mEventDescription = (EditText) findViewById(R.id.add_event_description);

    }



    public boolean insertJournal(){

        JournalDbHelper journalDbHelper = new JournalDbHelper(this);
        SQLiteDatabase db = journalDbHelper.getWritableDatabase();

        /*=============================================*/
        String nameString = "Franklin";
        String emailString = "Chidubem";            //THIS ARE DUMMY VARIABLES WOULD SOON BE GOTTEN FROM GOOGLE SIGN IN
        String phoneString = "08131911058";
               /* ==================================*/
        String titleString = mEventTitle.getText().toString().trim();
        String eventString = mEventDescription.getText().toString().trim();

        ContentValues value = new ContentValues();
            value.put(JournalEntry.COLUMN_NAME, nameString);
            value.put(JournalEntry.COLUMN_EMAIL, emailString);
            value.put(JournalEntry.COLUMN_PHONE_NO, phoneString);
            value.put(JournalEntry.COLUMN_GENDER, 0);
            value.put(JournalEntry.COLUMN_TITLE, titleString);
            value.put(JournalEntry.COLUMN_DESCRIPTION, eventString);
            value.put(JournalEntry.COLUMN_TIME, "2 Jul 2012");
            value.put(JournalEntry.COLUMN_DATE, "9:00am");

        long rowId = db.insert(JournalEntry.TABLE_JOURNALS_NAME,null,value);

        if(rowId == -1){
            return false;
        }else{
            return true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(insertJournal()){
            Toast.makeText(AddJournal.this,"Successfully Inserted to Database ",Toast.LENGTH_SHORT).show();
        }
        finish();
        return super.onOptionsItemSelected(item);
    }


}

package com.example.victor.vjournal;

import android.content.ContentValues;
import android.content.Intent;
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

public class EditJournal extends AppCompatActivity {


    private EditText mEventTitle;
    private EditText mEventDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_journal);

        mEventTitle = (EditText) findViewById(R.id.edit_title);
        mEventDescription = (EditText) findViewById(R.id.edit_description);

    }


    public boolean updateJournal(){

        JournalDbHelper journalDbHelper = new JournalDbHelper(this);

        SQLiteDatabase db = journalDbHelper.getReadableDatabase();

        String titleString = mEventTitle.getText().toString().trim();
        String eventString = mEventDescription.getText().toString().trim();

        ContentValues value = new ContentValues();
        value.put(JournalEntry.COLUMN_TITLE, titleString);
        value.put(JournalEntry.COLUMN_DESCRIPTION, eventString);
        value.put(JournalEntry.COLUMN_TIME, "2 Jul 2012");
        value.put(JournalEntry.COLUMN_DATE, "9:00am");

        String selection = JournalEntry.COLUMN_NAME +" = ? ";
        String[]  selectionArgs =  {"Franklin"};

        long rowId = db.update(JournalEntry.TABLE_JOURNALS_NAME,
                value,selection,selectionArgs);

        if(rowId == -1){
            return false;
        }else{
            return true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_btn,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.save_btn){
            if(updateJournal()){
                Intent I = new Intent(EditJournal.this,JournalEntries.class);
                startActivity(I);
                Toast.makeText(EditJournal.this,"Successfully Updated to Database ",Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }



}

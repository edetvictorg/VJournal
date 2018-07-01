package com.example.victor.vjournal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.victor.vjournal.data.JournalContract.JournalEntry;
import com.example.victor.vjournal.data.JournalDbHelper;

import java.util.ArrayList;


public class JournalEntries extends AppCompatActivity implements  JournalAdapter.ListItemClickListener{

    private JournalAdapter mJournalAdapter;
    private RecyclerView mJournalList;
    private JournalDbHelper mJournalEntries;

    private ArrayList<CustomJournalData> mJournalDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entries);

        mJournalEntries = new JournalDbHelper(this);
        mJournalList = (RecyclerView) findViewById(R.id.rv_journal_entries);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mJournalList.setLayoutManager(layoutManager);
        mJournalList.setHasFixedSize(true);

        setAdapterAndUpdateUI();

        /*ArrayList<CustomJournalData> dataFromDatabase = dataSourceFromDb();

        mJournalAdapter = new JournalAdapter(dataFromDatabase*//*dataSource was set from dataFromDbFunction)*//*,this);
        mJournalList.setAdapter(mJournalAdapter);*/

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JournalEntries.this, AddJournal.class);
                startActivity(intent);
            }
        });
    }

    public void setAdapterAndUpdateUI(){
        ArrayList<CustomJournalData> dataFromDatabase = dataSourceFromDb();
        mJournalAdapter = new JournalAdapter(dataFromDatabase/*dataSource was set from dataFromDbFunction)*/,
                this,JournalEntries.this);
        mJournalList.setAdapter(mJournalAdapter);
    }

    @Override
    protected void onStart() {
        setAdapterAndUpdateUI();
        super.onStart();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        // FOR NOW NOTHING TO DO
    }



    public ArrayList<CustomJournalData> dataSourceFromDb(){

        mJournalDataSource = new ArrayList<CustomJournalData>();

        SQLiteDatabase db = mJournalEntries.getReadableDatabase();

        String[] projection ={
                JournalEntry._ID,
                JournalEntry.COLUMN_NAME,
                JournalEntry.COLUMN_EMAIL,
                JournalEntry.COLUMN_PHONE_NO,
                JournalEntry.COLUMN_TITLE,
                JournalEntry.COLUMN_DESCRIPTION,
                JournalEntry.COLUMN_TIME,
                JournalEntry.COLUMN_DATE
        };

        String selection = JournalEntry.COLUMN_NAME +" = ? ";
        String[]  selectionArgs =  {"Franklin"};

       Cursor cursor = db.query(
                 JournalEntry.TABLE_JOURNALS_NAME,
                 projection,
                 selection,
                 selectionArgs,
                null,
                null,
                null
        );

       try{
            //GETS TABLE COLUMN INDEX VIA CURSOR
            int titleIndex =  cursor.getColumnIndex(JournalEntry.COLUMN_TITLE);
            int descriptionIndex = cursor.getColumnIndex(JournalEntry.COLUMN_DESCRIPTION);
            int timeIndex = cursor.getColumnIndex(JournalEntry.COLUMN_TIME);
            int dateIndex = cursor.getColumnIndex(JournalEntry.COLUMN_DATE);


            while(cursor.moveToNext()){
                String titleValue = cursor.getString(titleIndex);
                String descriptionValue = cursor.getString(descriptionIndex);
                String timeValue = cursor.getString(timeIndex);
                String dateValue = cursor.getString(dateIndex);

               mJournalDataSource.add(new CustomJournalData(titleValue, descriptionValue , timeValue,dateValue));
            }
        }finally{
            cursor.close();
        }

        return mJournalDataSource;

    }
}

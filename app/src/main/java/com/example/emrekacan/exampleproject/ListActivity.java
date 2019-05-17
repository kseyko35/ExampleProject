package com.example.emrekacan.exampleproject;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.emrekacan.exampleproject.Adapter.RecyclerViewAdapter;
import com.example.emrekacan.exampleproject.Data.DatabaseHelper;
import com.example.emrekacan.exampleproject.Data.Notes;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerViewAdapter mRecyclerViewAdapter;
    LinearLayoutManager manager=new LinearLayoutManager(this);
    ArrayList<Notes> mNotes=new ArrayList<>();
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView=findViewById(R.id.recyclerView);

        mDatabaseHelper=new DatabaseHelper(getApplicationContext());


        mNotes=getNotes();
        mRecyclerViewAdapter=new RecyclerViewAdapter(this,mNotes);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    private ArrayList<Notes> getNotes(){
        Cursor cursor=mDatabaseHelper.getData();
        Notes volatileNote;
        if(cursor!=null && cursor.getCount()>0){
            while(cursor.moveToNext()){

                volatileNote=new Notes();

                volatileNote.setNoteContext(cursor.getString(cursor.getColumnIndex("context")));
                Log.d("taner",cursor.getString(cursor.getColumnIndex("id")));
                volatileNote.setDate(cursor.getString(cursor.getColumnIndex("date")));
                volatileNote.setIsItOk(cursor.getInt(cursor.getColumnIndex("isItOk")));
                mNotes.add(volatileNote);
            }
        }
        return mNotes;
    }
}

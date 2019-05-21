package com.example.emrekacan.exampleproject;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.emrekacan.exampleproject.Adapter.RecyclerViewAdapter;
import com.example.emrekacan.exampleproject.Data.DatabaseHelper;
import com.example.emrekacan.exampleproject.Data.Notes;
import com.example.emrekacan.exampleproject.Fragment.EditFragment;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity  implements  RecyclerViewAdapter.Listener, RecyclerViewAdapter.EditListener {
    RecyclerView mRecyclerView;
    RecyclerViewAdapter mRecyclerViewAdapter;
    LinearLayoutManager manager=new LinearLayoutManager(this);
    ArrayList<Notes> mNotes=new ArrayList<>();
    DatabaseHelper mDatabaseHelper;
    TextView mEmptyList;
    int data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView=findViewById(R.id.recyclerView);
        mEmptyList=findViewById(R.id.emptyList);

        mDatabaseHelper=new DatabaseHelper(getApplicationContext());


        mNotes=getNotes();
        mRecyclerViewAdapter=new RecyclerViewAdapter(this,mNotes ,this,this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        if(mNotes.size()==0 && mRecyclerViewAdapter.getItemCount()==0){
            mRecyclerView.setVisibility(View.GONE);
            mEmptyList.setVisibility(View.VISIBLE);
        }
        else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyList.setVisibility(View.GONE);
        }
    }

    private ArrayList<Notes> getNotes(){
        Cursor cursor=mDatabaseHelper.getData();
        Notes volatileNote;
        if(cursor!=null && cursor.getCount()>0){
            while(cursor.moveToNext()){

                volatileNote=new Notes();
                volatileNote.setId(cursor.getInt(cursor.getColumnIndex("id")));
                volatileNote.setNoteContext(cursor.getString(cursor.getColumnIndex("context")));
                volatileNote.setDate(cursor.getString(cursor.getColumnIndex("date")));
                volatileNote.setIsItOk(cursor.getInt(cursor.getColumnIndex("isItOk")));
                mNotes.add(volatileNote);
            }
        }
        return mNotes;
    }

    @Override
    public void showMessage() {

        if(mNotes.size()==0 && mRecyclerViewAdapter.getItemCount()==0){
            mRecyclerView.setVisibility(View.GONE);
            mEmptyList.setVisibility(View.VISIBLE);
        }
        else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyList.setVisibility(View.GONE);
        }

    }


    @Override
    public void showEditFragment(String data, int position) {
        EditFragment newNote = new EditFragment();
        newNote.show(getSupportFragmentManager(), "DialogNewNote");
        Bundle bundle=new Bundle();
        bundle.putString("data",data);
        bundle.putInt("position",position);
        newNote.setArguments(bundle);

    }
}

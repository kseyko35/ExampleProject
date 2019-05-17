package com.example.emrekacan.exampleproject.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emrekacan.exampleproject.Data.DatabaseHelper;
import com.example.emrekacan.exampleproject.Data.Notes;
import com.example.emrekacan.exampleproject.R;

import java.sql.Struct;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NoteHolder> {

    LayoutInflater mInflater;
    ArrayList<Notes> allNotes;
    DatabaseHelper mDatabaseHelper;

    public RecyclerViewAdapter(Context context, ArrayList<Notes> mNotes){
        mInflater=LayoutInflater.from(context);
        allNotes=mNotes;
        mDatabaseHelper=new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=mInflater.inflate(R.layout.recycler_row, viewGroup,false);

        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteHolder noteHolder, int i) {
        noteHolder.mTextDate.setText(allNotes.get(i).getNoteContext());
        noteHolder.mTextContext.setText(allNotes.get(i).getDate());

        noteHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPosition = noteHolder.getAdapterPosition();
                Log.d("thien.van","on Click onBindViewHolder");
                allNotes.remove(newPosition);
                notifyItemRemoved(newPosition);
                notifyItemRangeChanged(newPosition, allNotes.size());
                int data= allNotes.get(newPosition).getId();
                mDatabaseHelper.deleteName(allNotes.get(newPosition).getId());


//                int data=allNotes.get(newPosition).getId();
//                mDatabaseHelper.deleteNote(allNotes.get(newPosition).getId());
//                mDatabaseHelper.delete_byID(allNotes.get(newPosition).getId());

            }
        });

    }


    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public static class NoteHolder extends RecyclerView.ViewHolder{

        TextView mTextContext;
        TextView mTextDate;


        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            mTextContext=itemView.findViewById(R.id.textViewContext);
            mTextDate=itemView.findViewById(R.id.textViewDate);


        }
    }


}


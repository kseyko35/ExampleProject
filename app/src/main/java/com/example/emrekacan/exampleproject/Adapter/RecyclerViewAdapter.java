package com.example.emrekacan.exampleproject.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emrekacan.exampleproject.Data.DatabaseHelper;
import com.example.emrekacan.exampleproject.Data.Notes;
import com.example.emrekacan.exampleproject.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NoteHolder> {

    LayoutInflater mInflater;
    ArrayList<Notes> allNotes;
    DatabaseHelper mDatabaseHelper;

    Listener mListener;
    EditListener mEditListener;



    public interface Listener {
        void showMessage();
    }
    public interface EditListener{
        void showEditFragment(String data, int position);
    }




    public RecyclerViewAdapter(Context context, ArrayList<Notes> mNotes,Listener listener,EditListener editlistener){
        mEditListener=editlistener;
        mListener = listener;
        this.mInflater=LayoutInflater.from(context);
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
        noteHolder.mTextContext.setText(allNotes.get(i).getNoteContext());
        noteHolder.mTextDate.setText(allNotes.get(i).getDate());

        noteHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPosition = noteHolder.getAdapterPosition();

                mDatabaseHelper.deleteName(allNotes.get(newPosition).getId());
                allNotes.remove(newPosition);
                notifyItemRemoved(newPosition);
                notifyItemRangeChanged(newPosition, allNotes.size());
                if(allNotes.size()== 0) {
                    if (mListener != null){
                        mListener.showMessage();
                    }
                }


            }

        });
        noteHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(),Integer.toString(noteHolder.getAdapterPosition()) ,Toast.LENGTH_SHORT).show();
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                EditFragment editNote = new EditFragment();
//                activity.getSupportFragmentManager().beginTransaction().add(R.id.listActivity,editNote).commit();
                if (mEditListener != null){
                    String editData=allNotes.get(noteHolder.getAdapterPosition()).getNoteContext();
                    int newPosition=allNotes.get(noteHolder.getAdapterPosition()).getId();
                    mEditListener.showEditFragment(editData,newPosition);

                }
                return true;
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
//        FrameLayout mEditFrameLayout;


        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            mTextContext=itemView.findViewById(R.id.textViewContext);
            mTextDate=itemView.findViewById(R.id.textViewDate);
//            mEditFrameLayout=itemView.findViewById(R.id.editFrameLayout)

        }
    }


}


package com.example.emrekacan.exampleproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.emrekacan.exampleproject.Data.DatabaseProvider;

//import com.example.emrekacan.exampleproject.Data.DatabaseHelper;

import com.example.emrekacan.exampleproject.Data.DatabaseHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentDialogNewNote extends DialogFragment {
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.closeButton)
    ImageButton mCloseButton;
    @BindView(R.id.buttonAdd)
    Button mButtonAdd;
    @BindView(R.id.editNote)
    EditText mEditNote;
    @BindView(R.id.DatePicker)
    DatePicker mDatePicker;
    DatabaseHelper mDatabaseHelper;
//    static final Uri CONTENT_URI1= DatabaseProvider.CONTENT_URI;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dialog, container, false);
        ButterKnife.bind(this, view);

        mDatabaseHelper=new DatabaseHelper(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ContentValues values=new ContentValues();
//                values.put("noteContent", mEditNote.getText().toString());
//                values.put("notDate",mEditNote.getText().toString());
//                Uri uri=getActivity().getContentResolver().insert(CONTENT_URI1,values);
//                Toast.makeText(getContext(), " " + uri, Toast.LENGTH_SHORT).show();
                String newEntry= mEditNote.getText().toString();
                int month=mDatePicker.getMonth()+1;
                int day=mDatePicker.getDayOfMonth();
                int year=mDatePicker.getYear();
                String newDate=day + " " + month + " " + year + " ";
                if(mEditNote.length()!=0){
                    AddData(newEntry,newDate);
                    mEditNote.setText("");
                }
                else  Toast.makeText(getContext(),"You must write", Toast.LENGTH_SHORT).show();
            }
        });
        Cursor data = mDatabaseHelper.getData();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            Log.d("seyfi",data.getString(1));
        }

    }
    public void AddData(String newEntry,String newDate){
        boolean insertData= mDatabaseHelper.addData(newEntry,newDate);

        if(insertData){
            Toast.makeText(getContext(),"data success", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(),"data wrong", Toast.LENGTH_SHORT).show();
    }

}

package com.example.emrekacan.exampleproject.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
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

import com.example.emrekacan.exampleproject.Data.DatabaseHelper;
import com.example.emrekacan.exampleproject.Data.Notes;
import com.example.emrekacan.exampleproject.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditFragment extends DialogFragment {

    @BindView(R.id.editBox)
    EditText mEditText;
    @BindView(R.id.deleteButton)
    Button mDeleteButton;
    @BindView(R.id.saveButton)
    Button mSaveButton;
    DatabaseHelper mDatabaseHelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.edit_fragment, container, false);
        ButterKnife.bind(this, view);
        mDatabaseHelper=new DatabaseHelper(getContext());
        if (getArguments() != null) {
            String mParam1 = getArguments().getString("data");

            mEditText.setText(mParam1);
        }

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final int mNewPosition=getArguments().getInt("position");
        final String newContext = getArguments().getString("data");
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                mDatabaseHelper.deleteName(mNewPosition);
                mEditText.setText("");
                Toast.makeText(getContext(),"removed from database",Toast.LENGTH_SHORT).show();

            }
        });
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldContext = mEditText.getText().toString();
                if(!oldContext.equals("")){
                    mDatabaseHelper.updateName(oldContext,mNewPosition,newContext);
                }else{
                    Toast.makeText(getContext(),"You must enter a name",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

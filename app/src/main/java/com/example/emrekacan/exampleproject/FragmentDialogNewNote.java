package com.example.emrekacan.exampleproject;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emrekacan.exampleproject.Data.DatabaseProvider;

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

    static final Uri CONTENT_URI= DatabaseProvider.CONTENT_URI;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dialog, container, false);
        ButterKnife.bind(this, view);


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
                ContentValues values=new ContentValues();
                values.put("noteContent", mEditNote.getText().toString());

                Uri uri=getActivity().getContentResolver().insert(CONTENT_URI,values);
                Toast.makeText(getContext(), " " + uri, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

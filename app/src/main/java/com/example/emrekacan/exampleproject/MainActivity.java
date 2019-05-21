package com.example.emrekacan.exampleproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.emrekacan.exampleproject.Fragment.FragmentDialogNewNote;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity

{
    Toolbar mToolbar;
    ImageView mBackgroundImageView;
    Button mAddNewNote, mListButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);


        mBackgroundImageView = findViewById(R.id.imgBackground);
        mAddNewNote = findViewById(R.id.addNoteButton);
        mListButton = findViewById(R.id.listNotes);

        mListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mNotes.isEmpty()){
//                    Toast.makeText(getApplicationContext(),"Your List is Empty", Toast.LENGTH_SHORT).show();
//                }
//                else{
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
//                }

            }
        });


        Glide.with(this).load(R.drawable.bck).centerCrop().into(mBackgroundImageView);
        mAddNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteDialogShow();
            }
        });

    }

    private void noteDialogShow() {

        FragmentDialogNewNote newNote = new FragmentDialogNewNote();
        newNote.show(getSupportFragmentManager(), "DialogNewNote");
    }


}

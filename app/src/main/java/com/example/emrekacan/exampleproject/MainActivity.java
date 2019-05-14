package com.example.emrekacan.exampleproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;
    ImageView mBackgroundImageView;
    Button mAddNewNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mBackgroundImageView=findViewById(R.id.imgBackground);
        mAddNewNote=findViewById(R.id.addNoteButton);




        Glide.with(this).load(R.drawable.bck).centerCrop().into(mBackgroundImageView);
        mAddNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteDialogShow();
            }
        });

    }

    private void noteDialogShow() {

        FragmentDialogNewNote newNote=new FragmentDialogNewNote();
        newNote.show(getSupportFragmentManager(),"DialogNewNote");
    }
}

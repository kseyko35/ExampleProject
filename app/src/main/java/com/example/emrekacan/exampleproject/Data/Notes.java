package com.example.emrekacan.exampleproject.Data;

public class Notes {

    private int id;
    private String noteContext;
    private String date;
    private int isItOk;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteContext() {
        return noteContext;
    }

    public void setNoteContext(String noteContext) {
        this.noteContext = noteContext;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsItOk() {
        return isItOk;
    }

    public void setIsItOk(int isItOk) {
        this.isItOk = isItOk;
    }
}

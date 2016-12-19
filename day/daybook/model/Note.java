package com.elysey.daybook.model;

public class Note {

    public int idNote;
    public String titleDay;
    public String noteDay;
    public String timeDay;
    public String dateDay;

    public Note(int idNote, String titleDay, String noteDay, String timeDay, String dateDay) {
        this.idNote = idNote;
        this.titleDay = titleDay;
        this.noteDay = noteDay;
        this.timeDay = timeDay;
        this.dateDay = dateDay;

    }

}

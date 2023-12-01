package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

public class AMaintenanceNote {
    private String description;
    private String date;
    private String noteTaker;
    private int noteIndex;

    public AMaintenanceNote(String description, String date, String noteTaker, int noteIndex) {
        this.description = description;
        this.date = date;
        this.noteTaker = noteTaker;
        this.noteIndex = noteIndex;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getNoteTaker() {
        return noteTaker;
    }

    public int getNoteIndex() {
        return noteIndex;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNoteTaker(String noteTaker) {
        this.noteTaker = noteTaker;
    }

    public void setNoteIndex(int noteIndex) {
        this.noteIndex = noteIndex;
    }
}

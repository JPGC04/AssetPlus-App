package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

public class MaintenanceNote {
    private String description;
    private String date;
    private String noteTaker;
    private int ticket;

    public MaintenanceNote(String description, String date, String noteTaker, int ticket) {
        this.description = description;
        this.date = date;
        this.noteTaker = noteTaker;
        this.ticket = ticket;
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

    public int getTicket() {
        return ticket;
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

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }
    
}

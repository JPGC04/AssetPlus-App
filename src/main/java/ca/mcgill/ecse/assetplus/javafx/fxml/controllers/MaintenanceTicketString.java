package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

public class MaintenanceTicketString {
    private String id;
    private String date;
    private String ticketRaiser;
    private String description;
    private String status;
    private String asset;
    private String fixer;
    private String priorityLevel;
    private String timeToResolve;

    public MaintenanceTicketString(String id, String date, String ticketRaiser, String description, String status, String asset) {
        this.id = id;
        this.date = date;
        this.ticketRaiser = ticketRaiser;
        this.description = description;
        this.status = status;
        this.asset = asset;

    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTicketRaiser(String ticketRaiser) {
        this.ticketRaiser = ticketRaiser;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public void setFixer(String fixer) {
        this.fixer = fixer;
    }

    public void setPriorityLevel(String level){
      this.priorityLevel = level;
    }

    public void setTimeToResolve(String time){
      this.timeToResolve = time;
    }

    public String getPriorityLevel(){
      return this.priorityLevel;
    }

    public String getTimeToResolve(){
      return this.timeToResolve;
    }
    // Getters
    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTicketRaiser() {
        return ticketRaiser;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getAsset() {
        return asset;
    }

    public String getFixer() {
        return fixer;
    }
}

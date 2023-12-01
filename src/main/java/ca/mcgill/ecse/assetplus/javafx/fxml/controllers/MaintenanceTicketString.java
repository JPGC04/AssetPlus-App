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
    private boolean requiresApproval;
    private String room;
    private String floor;
    private String purchaseDate;
    private String lifespan;
    private String assetType;

    public MaintenanceTicketString(String id, String date, String ticketRaiser, String description, String status, String asset,String room, String floor, String purchaseDate, String lifespan, String assetType) {
        this.id = id;
        this.date = date;
        this.ticketRaiser = ticketRaiser;
        this.description = description;
        this.status = status;
        this.asset = asset;
        this.room=room;
        this.floor=floor;
        this.purchaseDate=purchaseDate;
        this.lifespan=lifespan;
        this.assetType=assetType;

    }

    // Setters
    public String getAssetType(){
        return assetType;
    };
    public void setAssetType(String assetType){
        this.assetType=assetType;
    };

    public void setRequiresApproval(boolean requiresApproval){
        this.requiresApproval = requiresApproval;
    };

    public boolean getRequiresApproval(){
        return this.requiresApproval;
    }
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
    // Setters for new fields

public void setRoom(String room) {
    this.room = room;
}

public void setFloor(String floor) {
    this.floor = floor;
}

public void setPurchaseDate(String purchaseDate) {
    this.purchaseDate = purchaseDate;
}

public void setLifespan(String lifespan) {
    this.lifespan = lifespan;
}

// Getters for new fields

public String getRoom() {
    return room;
}

public String getFloor() {
    return floor;
}

public String getPurchaseDate() {
    return purchaseDate;
}

public String getLifespan() {
    return lifespan;
}
}

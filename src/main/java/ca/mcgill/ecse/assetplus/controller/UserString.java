package ca.mcgill.ecse.assetplus.controller;

public class UserString {
    private String name;
    private String email;
    private String password;
    private String number;
    private String EmployeeGuest;

    public UserString(String name, String email, String password, String number,String EmployeeGuest) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.number = number;
        this.EmployeeGuest=EmployeeGuest;
    }

    // Setters
    public void setEmployeeGuest(String EmployeeGuest) {
        this.EmployeeGuest=EmployeeGuest;    
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    // Getters
    public String getEmployeeGuest() {
        return EmployeeGuest;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNumber() {
        return number;
    }
}

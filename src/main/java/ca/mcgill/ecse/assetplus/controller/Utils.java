package ca.mcgill.ecse.assetplus.controller;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.HotelStaff;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;

public class Utils {
    private static AssetPlus assetplus= AssetPlusApplication.getAssetPlus();

    private Utils() {}
    public static MaintenanceTicket getMaintenanceTicketbyID(int ticketID){
        for (MaintenanceTicket ticket:assetplus.getMaintenanceTickets()) {
            if (ticketID==ticket.getId()) {
                return ticket;
            }
        }
        return null;
    }
    public static HotelStaff getHotelStaffbyemail(String email){
        if(assetplus.getManager().getEmail()==email){
            return assetplus.getManager();
        }
        for (HotelStaff staff:assetplus.getEmployees()) {
            if (email==staff.getEmail()) {
                return staff;
            }
        }
        return null;
    }
}

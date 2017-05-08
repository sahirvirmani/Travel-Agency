/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelagency;
import java.io.*;
/**
 *
 * @author Sahir
 */
public class BusTicket extends Ticket {
    boolean AC;
    File customerDetails, TicketSplit, Fare;
    String TicketSplitText, FareText;
    int n;
    BusTicket(boolean a, int no_tickets) {
        AC = a;
        n = no_tickets;
        int bus, train;
        TicketSplitText = "";
        FareText = "";
        customerDetails = new File("Details.txt");
        TicketSplit = new File("TicketSplit.txt");
        Fare = new File("Fare.txt");
        try {
            FileReader fr = new FileReader(customerDetails);
            BufferedReader br = new BufferedReader(fr);
            String name = br.readLine();
            int age = Integer.parseInt(br.readLine());
            System.out.println("Name is: " + name + " age is " + age);
            br.close();
            fr.close();
            super.name = name;
            super.age = age;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        
        try {
            FileReader fr = new FileReader(TicketSplit);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                TicketSplitText += line + "\n";
            }
            System.out.println("TST " + TicketSplitText);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        
        try {
            FileReader fr = new FileReader(Fare);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                FareText += line + "\n";
            }
            System.out.println("FT " + FareText);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        
        StringBuffer sb = new StringBuffer(TicketSplitText);
        int i = sb.indexOf("Bus");
        String SBus = sb.substring(sb.indexOf(":",i) + 1,sb.indexOf("\n",i));
        System.out.println("SBus " + SBus);
        
        int j = sb.indexOf("Train");
        System.out.println("index " + sb.indexOf(":",j));
        String STrain = sb.substring(sb.indexOf(":",j) + 1,sb.indexOf("\n",j));
        System.out.println("STrain " + STrain);
        SBus = SBus.trim();
        STrain = STrain.trim();
        bus = Integer.parseInt(SBus);
        train = Integer.parseInt(STrain);
        
        sb = new StringBuffer(FareText);
        String Sfare;
        if(a == true) {
            i = sb.indexOf("Bus AC");
            Sfare = sb.substring(sb.indexOf(":",i) + 1, sb.indexOf("\n",i));
            System.out.println("SFare " + Sfare);
        }
        else {
            i = sb.indexOf("Bus Non AC");
            Sfare = sb.substring(sb.indexOf(":",i) + 1, sb.indexOf("\n",i));
            System.out.println("SFare " + Sfare);
        }
        Sfare = Sfare.trim();
        int fare = Integer.parseInt(Sfare);
        System.out.println("fare " + fare);
        
        

        super.no_busTickets = bus;
        super.no_trainTickets = train;
        super.bookingStatus = true;
        super.fare = fare;
        System.out.println("End of constructor");
    }
    public void printTicket() {
        System.out.println("Name: " + super.name);
        System.out.println("Age: " + super.age);
        System.out.println("Fare per Ticket: " + fare);
        System.out.println("Total Fare: " + (fare * n));
        System.out.println("Booking Status: " + super.bookingStatus);
        if(AC == true) {
            System.out.println("AC BUS");
        }
        else
            System.out.println("NON AC BUS");
    }
    public void bookTicket() {
        StringBuffer sb = new StringBuffer(TicketSplitText);
        int i = sb.indexOf(super.no_busTickets + "");
        System.out.println("Book Tickets");
        System.out.println("i is " + i);
        int bTickets = Integer.parseInt(sb.substring(i, sb.indexOf("\n",i)));
        System.out.println("bTick " + bTickets);
        try {
            if(bTickets >= n) {
                sb.replace(i, sb.indexOf("\n", i), ((super.no_busTickets - n) + ""));
                super.bookingStatus = true;
                super.bookingID =(int) Math.random() * 1000;
            }
            else {
                super.bookingStatus = false;
                throw new TicketNotAvailableException("No tickets available");
            }
        }
        catch(TicketNotAvailableException te) {
            System.out.println(te);
        }
        try {
            PrintWriter pw = new PrintWriter(TicketSplit);
            pw.println(sb);
            pw.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        printTicket();
    }
}

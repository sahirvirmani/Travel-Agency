/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelagency;

/**
 *
 * @author Sahir
 */
abstract class Ticket {
    String name;
    int age;
    double fare;
    boolean bookingStatus;
    int bookingID;
    int no_trainTickets;
    int no_busTickets;
    abstract void printTicket();
    abstract void bookTicket();
}


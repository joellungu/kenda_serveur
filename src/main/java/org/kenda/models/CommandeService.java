package org.kenda.models;

import org.kenda.models.tickets.Ticket;

import java.util.List;

public class CommandeService {

    public String reference;
    public String phone;
    public Double amount;
    public String currency;
    public List<Ticket> ticketList;
}

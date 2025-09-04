package aitov.ticketanalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {
    @JsonProperty("origin")
    private String origin;
    
    @JsonProperty("origin_name")
    private String originName;
    
    @JsonProperty("destination")
    private String destination;
    
    @JsonProperty("destination_name")
    private String destinationName;
    
    @JsonProperty("departure_date")
    private String departureDate;
    
    @JsonProperty("departure_time")
    private String departureTime;
    
    @JsonProperty("arrival_date")
    private String arrivalDate;
    
    @JsonProperty("arrival_time")
    private String arrivalTime;
    
    private String carrier;
    private int stops;
    private int price;

    public String getOrigin() { return origin; }
    public String getOriginName() { return originName; }
    public String getDestination() { return destination; }
    public String getDestinationName() { return destinationName; }
    public String getDepartureDate() { return departureDate; }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalDate() { return arrivalDate; }
    public String getArrivalTime() { return arrivalTime; }
    public String getCarrier() { return carrier; }
    public int getStops() { return stops; }
    public int getPrice() { return price; }

    public void setOrigin(String origin) { this.origin = origin; }
    public void setOriginName(String originName) { this.originName = originName; }
    public void setDestination(String destination) { this.destination = destination; }
    public void setDestinationName(String destinationName) { this.destinationName = destinationName; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
    public void setArrivalDate(String arrivalDate) { this.arrivalDate = arrivalDate; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }
    public void setCarrier(String carrier) { this.carrier = carrier; }
    public void setStops(int stops) { this.stops = stops; }
    public void setPrice(int price) { this.price = price; }
}
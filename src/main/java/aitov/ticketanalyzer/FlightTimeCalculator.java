package aitov.ticketanalyzer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightTimeCalculator {
    
    public static Map<String, Duration> calculateMinFlightTime(List<Ticket> tickets) {
        Map<String, Duration> carrierMinDurations = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");
        
        for (Ticket ticket : tickets) {
            try {
                LocalDateTime departure = LocalDateTime.parse(
                    ticket.getDepartureDate() + " " + ticket.getDepartureTime(), 
                    formatter
                );
                
                LocalDateTime arrival = LocalDateTime.parse(
                    ticket.getArrivalDate() + " " + ticket.getArrivalTime(), 
                    formatter
                );
                
                Duration duration = Duration.between(departure, arrival);
                String carrier = ticket.getCarrier();
                
                if (carrierMinDurations.containsKey(carrier)) {
                    Duration currentMin = carrierMinDurations.get(carrier);
                    if (duration.compareTo(currentMin) < 0) {
                        carrierMinDurations.put(carrier, duration);
                    }
                } else {
                    carrierMinDurations.put(carrier, duration);
                }
                
            } catch (Exception e) {
                System.err.println("Ошибка при обработке билета: " + e.getMessage());
            }
        }
        
        return carrierMinDurations;
    }
    
    public static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        return String.format("%d ч %02d мин", hours, minutes);
    }
}
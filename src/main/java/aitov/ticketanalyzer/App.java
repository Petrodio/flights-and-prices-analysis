package aitov.ticketanalyzer;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Использование: java -jar \"<путь_к_программе>.jar\" \"<путь_к_файлу>.json\"");
            return;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            TicketData ticketData = mapper.readValue(new File(args[0]), TicketData.class);

            List<Ticket> filteredTickets = ticketData.getTickets().stream()
                .filter(t -> "VVO".equals(t.getOrigin()) && "TLV".equals(t.getDestination()))
                .collect(Collectors.toList());

            if (filteredTickets.isEmpty()) {
                System.out.println("Билеты между Владивостоком и Тель-Авивом не найдены.");
                return;
            }


            System.out.println("\n1. Минимальное время полёта по авиаперевозчикам:");
            Map<String, Duration> minDurations = FlightTimeCalculator.calculateMinFlightTime(filteredTickets);
            
            for (Map.Entry<String, Duration> entry : minDurations.entrySet()) {
                System.out.printf("%s: %s%n", 
                    entry.getKey(), 
                    FlightTimeCalculator.formatDuration(entry.getValue()));
            }

            System.out.println("\n2. Сравнение цен:");

            List<Integer> prices = filteredTickets.stream()
                .map(Ticket::getPrice)
                .sorted()
                .collect(Collectors.toList());

            double averagePrice = prices.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);

            double medianPrice;
            int size = prices.size();
            if (size % 2 == 0) {
                medianPrice = (prices.get(size/2 - 1) + prices.get(size/2)) / 2.0;
            } else {
                medianPrice = prices.get(size/2);
            }

            double difference = averagePrice - medianPrice;
            
            System.out.printf("Средняя цена: %.2f руб.%n", averagePrice);
            System.out.printf("Медианная цена: %.2f руб.%n", medianPrice);
            System.out.printf("Разница: %.2f руб.%n", difference);

        } catch (Exception e) {
            System.err.println("Ошибка при обработке файла: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
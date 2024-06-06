package co.develhope.queries.flights.services;

import co.develhope.queries.flights.enums.Status;
import co.develhope.queries.flights.models.Flight;
import co.develhope.queries.flights.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    private String generateRandomString(Random random) {
        return random.ints(3, 65, 90) // A-Z ASCII range
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());
    }

    public List<Flight> addFlight(int n) {
        Random random = new Random();
        List<Flight> flights = IntStream.range(0, n).mapToObj(i -> {
            Flight flight = new Flight();
            flight.setDescription("Flight " + (i + 1));
            flight.setFromAirport(generateRandomString(random));
            flight.setToAirport(generateRandomString(random));
            return flight;
        }).collect(Collectors.toList());
        return flightRepository.saveAll(flights);
    }

    public Page<Flight> readAllFlightsPaged(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "fromAirport");
        Pageable pageable = PageRequest.of(page, size, sort);
        return flightRepository.findAll(pageable);
    }

    public List<Flight> readOnTimeFlights() {
        return flightRepository.findByStatus(Status.ONTIME);
    }

    public List<Flight> readFlightBy(List<Status> statusList) {
        return flightRepository.findByStatusIn(statusList);
    }
}
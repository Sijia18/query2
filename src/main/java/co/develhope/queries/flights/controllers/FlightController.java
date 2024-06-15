package co.develhope.queries.flights.controllers;

import co.develhope.queries.flights.enums.Status;
import co.develhope.queries.flights.services.FlightService;
import co.develhope.queries.flights.models.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping
    public List<Flight> createFlights(@RequestParam(required = false, defaultValue = "100") int n) {
        return flightService.addFlights(n);
    }

    @GetMapping
    public Page<Flight> readAllFlights(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return flightService.readAllFlightsPaged(page, size);
    }

    @GetMapping("/ontime")
    public List<Flight> readOnTimeFlights() {
        return flightService.readOnTimeFlights();
    }

    @GetMapping("/statuses")
    public List<Flight> readFlightsByStatuses(@RequestParam("status1") String status1, @RequestParam("status2") String status2) {
        return flightService.readFlightsByStatuses(status1, status2);
    }
}
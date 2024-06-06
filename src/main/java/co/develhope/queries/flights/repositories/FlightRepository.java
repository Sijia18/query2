package co.develhope.queries.flights.repositories;

import co.develhope.queries.flights.enums.Status;
import co.develhope.queries.flights.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByStatus(Status status);
    List<Flight> findByStatusIn(List<Status> statusList);
}
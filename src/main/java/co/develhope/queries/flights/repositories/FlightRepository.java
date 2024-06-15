package co.develhope.queries.flights.repositories;

import co.develhope.queries.flights.enums.Status;
import co.develhope.queries.flights.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByStatus(Status status);
    @Query("SELECT f FROM Flight f WHERE f.status = :p1 OR f.status = :p2")
    List<Flight> findByStatusIn(@Param("p1") String p1, @Param("p2") String p2);

}
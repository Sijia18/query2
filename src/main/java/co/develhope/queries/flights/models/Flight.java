package co.develhope.queries.flights.models;

import co.develhope.queries.flights.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private String fromAirport;
    private String toAirport;
    @Enumerated(EnumType.STRING)
    private Status status;
}

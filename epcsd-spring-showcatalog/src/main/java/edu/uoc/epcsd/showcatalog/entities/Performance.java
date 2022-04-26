package edu.uoc.epcsd.showcatalog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name = "time")
    private java.sql.Time time;

    @Column(name = "streaming_url")
    private String streamingUrl;

    @Column(name = "remaining_seats")
    private Integer remainingSeats;

    @Column(name = "status")
    private TStatus status;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_show")
    private Show show;
}
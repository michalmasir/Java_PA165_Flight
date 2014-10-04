package cz.muni.fi.PA165.flight.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Steward {

    @Id
    @GeneratedValue
    private long id;
}

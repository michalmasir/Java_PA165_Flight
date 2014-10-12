package cz.muni.fi.PA165.flight.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author  Michal Hruby
 */

@Entity
public class Steward {

    @Id
    @GeneratedValue
    private long id;

    private String first_name;

    private String last_name;


    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }


    public String getFirst_name() {
        return first_name;
    }


    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    public long getId() {
        return id;
    }

   
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Steward steward = (Steward) o;

        if (id != steward.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}

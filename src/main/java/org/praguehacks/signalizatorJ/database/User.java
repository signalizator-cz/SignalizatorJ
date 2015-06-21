package org.praguehacks.signalizatorJ.database;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by kucerajn on 14.6.2015.
 */
@Entity
@Table(name = "users", schema = "", catalog = "signalizator")
public class User {
    private int id;
    private String email;
    private Date registrationDate;
    private Double lowerLeftX;  // south-west long
    private Double lowerLeftY;  // south-west lat
    private Double upperRightX; // north-east long
    private Double upperRightY; // north-east lat
    private Byte authenticated;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email", nullable = false, insertable = true, updatable = true, length = 80)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
//    @Temporal( TemporalType.DATE)
    @Column(name = "registration_date", nullable = false, insertable = true, updatable = true)
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Basic
    @Column(name = "lower_left_x", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getLowerLeftX() {
        return lowerLeftX;
    }

    public void setLowerLeftX(Double lowerLeftX) {
        this.lowerLeftX = lowerLeftX;
    }

    @Basic
    @Column(name = "lower_left_y", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getLowerLeftY() {
        return lowerLeftY;
    }

    public void setLowerLeftY(Double lowerLeftY) {
        this.lowerLeftY = lowerLeftY;
    }

    @Basic
    @Column(name = "upper_right_x", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getUpperRightX() {
        return upperRightX;
    }

    public void setUpperRightX(Double upperRightX) {
        this.upperRightX = upperRightX;
    }

    @Basic
    @Column(name = "upper_right_y", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getUpperRightY() {
        return upperRightY;
    }

    public void setUpperRightY(Double upperRightY) {
        this.upperRightY = upperRightY;
    }

    @Basic
    @Column(name = "authenticated", nullable = true, insertable = true, updatable = true)
    public Byte getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Byte authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (registrationDate != null ? !registrationDate.equals(that.registrationDate) : that.registrationDate != null)
            return false;
        if (lowerLeftX != null ? !lowerLeftX.equals(that.lowerLeftX) : that.lowerLeftX != null) return false;
        if (lowerLeftY != null ? !lowerLeftY.equals(that.lowerLeftY) : that.lowerLeftY != null) return false;
        if (upperRightX != null ? !upperRightX.equals(that.upperRightX) : that.upperRightX != null) return false;
        if (upperRightY != null ? !upperRightY.equals(that.upperRightY) : that.upperRightY != null) return false;
        if (authenticated != null ? !authenticated.equals(that.authenticated) : that.authenticated != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (lowerLeftX != null ? lowerLeftX.hashCode() : 0);
        result = 31 * result + (lowerLeftY != null ? lowerLeftY.hashCode() : 0);
        result = 31 * result + (upperRightX != null ? upperRightX.hashCode() : 0);
        result = 31 * result + (upperRightY != null ? upperRightY.hashCode() : 0);
        result = 31 * result + (authenticated != null ? authenticated.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                ", lowerLeftX=" + lowerLeftX +
                ", lowerLeftY=" + lowerLeftY +
                ", upperRightX=" + upperRightX +
                ", upperRightY=" + upperRightY +
                ", authenticated=" + authenticated +
                '}';
    }
}

package org.praguehacks.signalizatorJ.database;

import javax.persistence.*;

/**
 * Created by kucerajn on 13.6.2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Address.findAll", query = "select a from Address a where a.documentId < 7"),
//        @NamedQuery(name = "Address.findDocumentsInRectangle", query = "select d from Address a, Document d where a.lat >= :lowerLeftY and a.lat <= :upperRightY and a.lon >= :lowerLeftX and a.lon <= :upperRightX and a.documentId = d.id")
})

@Table(name = "addresses_coordinates", schema = "", catalog = "signalizator")
public class Address {
    private int id;
    private Integer documentId;
    private String address;
    private Double lat;
    private Double lon;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "document_id", nullable = true, insertable = true, updatable = true)
    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    @Basic
    @Column(name = "address", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "lat", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "lon", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address that = (Address) o;

        if (id != that.id) return false;
        if (documentId != null ? !documentId.equals(that.documentId) : that.documentId != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (lat != null ? !lat.equals(that.lat) : that.lat != null) return false;
        if (lon != null ? !lon.equals(that.lon) : that.lon != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (documentId != null ? documentId.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (lon != null ? lon.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", documentId=" + documentId +
                ", address='" + address + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}

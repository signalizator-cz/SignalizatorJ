package org.praguehacks.signalizatorJ.database;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by kucerajn on 13.6.2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Document.findDocumentsInRectangle", query = "select d from Address a, Document d where a.lat > :lowerLeftY and a.lat < :upperRightY and a.lon > :lowerLeftX and a.lon < :upperRightX and a.documentId = d.id")
})
@Table(name = "documents", schema = "", catalog = "signalizator")
public class Document {
    private int id;
    private String title;
    private String loc;
    private String source;
    private String text;
    private Date loadedTime;
    private String url;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "loc", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Basic
    @Column(name = "source", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Basic(fetch=FetchType.LAZY)
    @Lob
    @Column(name = "text", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "loadedTime", nullable = true, insertable = true, updatable = true)
    public Date getLoadedTime() {
        return loadedTime;
    }

    public void setLoadedTime(Date loadedTime) {
        this.loadedTime = loadedTime;
    }

    @Basic
    @Column(name = "url", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document that = (Document) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (loc != null ? !loc.equals(that.loc) : that.loc != null) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (loadedTime != null ? !loadedTime.equals(that.loadedTime) : that.loadedTime != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (loc != null ? loc.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (loadedTime != null ? loadedTime.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", loc='" + loc + '\'' +
                ", source='" + source + '\'' +
                ", text='" + text + '\'' +
                ", loadedTime=" + loadedTime +
                ", url='" + url + '\'' +
                '}';
    }
}

package org.praguehacks.signalizatorJ.database;

import javax.persistence.*;

/**
 * Created by kucerajn on 14.6.2015.
 */
@Entity
@Table(name = "email_templates", schema = "", catalog = "signalizator")
public class EmailTemplate {
    private int id;
    private String textBody;
    private String htmlBody;
    private String subject;
    private String type;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic(fetch=FetchType.LAZY)
    @Lob
    @Column(name = "text_body", nullable = true, insertable = true, updatable = true)
    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    @Basic(fetch=FetchType.LAZY)
    @Lob
    @Column(name = "html_body", nullable = true, insertable = true, updatable = true)
    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    @Basic
    @Column(name = "subject", nullable = true, insertable = true, updatable = true, length = 255)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "type", nullable = true, insertable = true, updatable = true, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailTemplate that = (EmailTemplate) o;

        if (id != that.id) return false;
        if (!textBody.equals(that.textBody)) return false;
        if (!htmlBody.equals(that.htmlBody)) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (textBody != null ? textBody.hashCode() : 0);
        result = 31 * result + (htmlBody != null ? htmlBody.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EmailTemplate{" +
                "id=" + id +
                ", textBody=" + textBody +
                ", htmlBody=" + htmlBody +
                ", subject='" + subject + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

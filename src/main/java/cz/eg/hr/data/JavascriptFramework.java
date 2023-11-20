package cz.eg.hr.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class JavascriptFramework {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private Double version;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    public Date deprecatedAt;

    @Column(nullable = false)
    private Integer rating;

    public JavascriptFramework() {
    }

    public JavascriptFramework(Long id, String name, Double version, Date deprecatedAt, Integer rating) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.deprecatedAt = deprecatedAt;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public Date getDeprecatedAt() {
        return deprecatedAt;
    }

    public void setDeprecatedAt(Date deprecatedAt) {
        this.deprecatedAt = deprecatedAt;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "JavaScriptFramework [id=" + id + ", name=" + name + ", version=" + version + ", deprecatedAt=" + deprecatedAt + ", rating=" + rating + "]";
    }

}

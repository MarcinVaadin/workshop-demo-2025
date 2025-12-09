package com.example.github;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "issues")
public class Issue {

    enum Severity {
        Blocker, Major, Minor
    }

    enum Impact {
        High, Low
    }

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "labels")
    private String labels;

    @Column(name = "created_at", nullable = false)
    @Convert(converter = InstantStringConverter.class)
    private Instant createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

@Converter(autoApply = false)
class InstantStringConverter implements AttributeConverter<Instant, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_INSTANT;

    @Override
    public String convertToDatabaseColumn(Instant attribute) {
        return attribute == null ? null : FORMATTER.format(attribute);
    }

    @Override
    public Instant convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Instant.parse(dbData);
    }
}

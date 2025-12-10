package com.example.github;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "issues")
public class Issue {

    public enum Severity {
        Blocker, Major, Minor
    }

    public enum Impact {
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

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant createdAt;

    private Issue.Severity severity;

    private Issue.Impact impact;

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

    public Severity getSeverity() {
        if (labels.contains("Severity: Blocker")) {
            return Severity.Blocker;
        } else if (labels.contains("Severity: Major")) {
            return Severity.Major;
        } else if (labels.contains("Severity: Minor")) {
            return Severity.Minor;
        }
        return null;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public Impact getImpact() {
        if (labels.contains("Impact: High")) {
            return Impact.High;
        } else if (labels.contains("Impact: Low")) {
            return Impact.Low;
        }
        return null;
    }

    public void setImpact(Impact impact) {
        this.impact = impact;
    }
}

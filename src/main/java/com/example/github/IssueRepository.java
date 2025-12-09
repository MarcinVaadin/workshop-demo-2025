package com.example.github;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IssueRepository extends JpaRepository<Issue, Long> {

    long countByLabelsContains(String label);

    List<Issue> findByLabelsContains(String label, Pageable pageable);

    default List<Issue> findByImpact(Pageable pageable, Issue.Impact impact) {
        return findByLabelsContains("Impact: " + impact.name(), pageable);
    }

    default List<Issue> findBySeverity(Pageable pageable, Issue.Severity severity) {
        return findByLabelsContains("Severity: " + severity.name(), pageable);
    }

    default long countBySeverity(Issue.Severity severity) {
        return countByLabelsContains("Severity: " + severity.name());
    }

    default long countByImpact(Issue.Impact impact) {
        return countByLabelsContains("Impact: " + impact.name());
    }

}

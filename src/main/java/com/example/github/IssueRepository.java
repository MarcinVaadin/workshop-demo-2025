package com.example.github;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IssueRepository extends JpaRepository<Issue, Long> {

    long countByLabelsContains(String label);

    List<Issue> findByLabelsContains(String label, Pageable pageable);

    @Query(value = """
            SELECT CAST(created_at AS DATE) AS date, COUNT(*) AS count
            FROM issues
            GROUP BY CAST(created_at AS DATE)
            ORDER BY date DESC
            LIMIt 30
            """, nativeQuery = true)
    List<IssueCountByDate> countGroupedByDate();

    default long countBySeverity(Issue.Severity severity) {
        return countByLabelsContains("Severity: " + severity.name());
    }

    default long countByImpact(Issue.Impact impact) {
        return countByLabelsContains("Impact: " + impact.name());
    }

}

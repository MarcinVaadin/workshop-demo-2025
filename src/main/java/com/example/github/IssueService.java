package com.example.github;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;

import java.util.List;

@Service
public class IssueService {

    private IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Long count() {
        return issueRepository.count();
    }

    public List<Issue> findBySeverityBlocker(Pageable pageable) {
        return issueRepository.findBySeverity(pageable, Issue.Severity.Blocker);
    }

    public List<Issue> findBySeverityMajor(Pageable pageable) {
        return issueRepository.findBySeverity(pageable, Issue.Severity.Major);
    }

    public List<Issue> findBySeverityMinor(Pageable pageable) {
        return issueRepository.findBySeverity(pageable, Issue.Severity.Minor);
    }

    public List<Issue> findByImpactHigh(Pageable pageable) {
        return issueRepository.findByImpact(pageable, Issue.Impact.High);
    }

    public List<Issue> findByImpactLow(Pageable pageable) {
        return issueRepository.findByImpact(pageable, Issue.Impact.Low);
    }

    public Long countBySeverityMinor() {
        return issueRepository.countBySeverity(Issue.Severity.Minor);
    }

    public Long countBySeverityMajor() {
        return issueRepository.countBySeverity(Issue.Severity.Major);
    }

    public Long countBySeverityBlocker() {
        return issueRepository.countBySeverity(Issue.Severity.Blocker);
    }

    public Long countByImpactHigh() {
        return issueRepository.countByImpact(Issue.Impact.High);
    }

    public Long countByImpactLow() {
        return issueRepository.countByImpact(Issue.Impact.Low);
    }

    public DataSeries getIssuesByDataSeries() {
        DataSeries dataSeries = new DataSeries();
        dataSeries.setName("Issues");
        issueRepository.countGroupedByDate().reversed().forEach(
            byDate -> {
                String instant = byDate.getDate().toString();
                dataSeries.add(new DataSeriesItem(instant, byDate.getCount()));
            }
        );
        return dataSeries;
    }

}

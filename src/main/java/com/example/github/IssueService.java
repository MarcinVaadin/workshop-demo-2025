package com.example.github;

import org.springframework.stereotype.Service;

import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;

import java.util.List;
import java.util.stream.Stream;

@Service
public class IssueService {

    private IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Long count() {
        return issueRepository.count();
    }

    public List<Issue> findIssues() {
        return issueRepository.findAll();
    }

    public List<Issue> findIssues(List<String> labels) {
        var data = issueRepository.findAll();
        for (String label : labels) {

            data = data.stream().filter(issue -> issue.getLabels() != null)
                .filter(issue -> issue.getLabels().contains(label))
                .toList();
        }
        return data;
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

    public List<String> filterSeverity() {
        return Stream.of(Issue.Severity.values()).map(Enum::name).toList();
    }

    public List<String> filterImpact() {
        return Stream.of(Issue.Impact.values()).map(Enum::name).toList();
    }

}

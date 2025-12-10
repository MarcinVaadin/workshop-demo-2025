package com.example.github;

import java.time.LocalDate;

public interface IssueCountByDate {
    LocalDate getDate();
    long getCount();
}

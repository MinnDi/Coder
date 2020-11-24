package ru.croc.coder.controller;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ru.croc.coder.domain.SolutionStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SolutionDto {

    private Long id;

    private Long taskId;

    @JsonProperty("submitTime")
    private LocalDateTime time;

    private Boolean passed;

    private SolutionStatus solutionStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long problemId) {
        this.taskId = problemId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public SolutionStatus getSolutionStatus() {
        return solutionStatus;
    }

    public void setSolutionStatus(SolutionStatus solutionStatus) {
        this.solutionStatus = solutionStatus;
    }
}
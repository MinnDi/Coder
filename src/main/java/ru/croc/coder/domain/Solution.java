package ru.croc.coder.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("Solution")
public class Solution {

    @ManyToOne
    private Course course;

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private Task task;

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long solutionId;

    Boolean passed;

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private User author;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime time;

    @Embedded
    private Code code;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Long getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Long solutionId) {
        this.solutionId = solutionId;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
}

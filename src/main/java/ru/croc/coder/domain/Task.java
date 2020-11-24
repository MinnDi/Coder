package ru.croc.coder.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@DiscriminatorValue("Tasks")
public class Task {

    @ManyToOne
    private Course course;

    private LocalDateTime deadline;

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private User author;

    @OneToOne
    private Solution solution;

    @Enumerated
    @Column(nullable = false)
    private TaskDifficulty taskDifficulty;

    @Lob
    @Column(nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private String description;

    @Embedded
    private Code template;

    private Integer maxAttempts;

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        if (deadline.isAfter(LocalDateTime.now()))
            this.deadline = deadline;
        else throw new IllegalArgumentException();
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setTaskDifficulty(TaskDifficulty taskDifficulty) {
        this.taskDifficulty = taskDifficulty;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public Code getTemplate() {
        return template;
    }

    public void setTemplate(Code template) {
        this.template = template;
    }

    public String getDescription() {
        return description;
    }

    public User getAuthor() {
        return author;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public TaskDifficulty getTaskDifficulty() {
        return taskDifficulty;
    }
}

package ru.croc.coder.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Tasks")
public class Task {

    @ManyToOne
    private Course course;

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    @OneToOne
    private Solution solution;

    @Enumerated
    private TaskDifficulty taskDifficulty;

    @Enumerated
    private TaskStatus taskStatus;

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

    public void setTaskDifficulty(TaskDifficulty taskDifficulty) {
        this.taskDifficulty = taskDifficulty;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}

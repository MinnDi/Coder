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

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private User author;

    @OneToOne
    private Solution solution;

    @Enumerated
    @Column(nullable = false)
    private TaskDifficulty taskDifficulty;

    @Enumerated
    private TaskStatus taskStatus;

    @Lob
    @Column(nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private String description;

    @Embedded
    private Code template;

    @Enumerated(EnumType.STRING)
    @Column
    private ProgrammingLanguage programmingLanguage;

    private Integer maxAttempts;

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

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
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

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}

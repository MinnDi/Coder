package ru.croc.coder.domain;

import javax.persistence.*;

@Embeddable
public class Code {

    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    @Lob
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProgrammingLanguage programmingLanguage;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}

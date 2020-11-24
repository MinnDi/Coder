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

    public Code setText(String text) {
        this.text = text;
        return this;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public Code setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
        return this;
    }
}

package ru.cft.starterkit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.UUID;

public class SampleEntity {

    private Long id;

    private String foo;

    private Double bar;

    private String sessionId;

    @JsonIgnore
    private String baz;

    public SampleEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public Double getBar() {
        return bar;
    }

    public void setBar(Double bar) {
        this.bar = bar;
    }

    public String getBaz() {
        return baz;
    }

    public void setBaz(String baz) {
        this.baz = baz;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SampleEntity that = (SampleEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(foo, that.foo) &&
                Objects.equals(bar, that.bar) &&
                Objects.equals(sessionId, that.sessionId) &&
                Objects.equals(baz, that.baz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, foo, bar, sessionId, baz);
    }

    @Override
    public String toString() {
        return "SampleEntity{" +
                "id=" + id +
                ", foo='" + foo + '\'' +
                ", bar=" + bar +
                ", sessionId='" + sessionId + '\'' +
                ", baz=" + baz +
                '}';
    }
}

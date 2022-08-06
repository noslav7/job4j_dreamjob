package ru.job4j.dreamjob.model;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Candidate {
    private int id;
    private String name;
    private String desc;
    private Date created;

    public Candidate() {}


    public Candidate(int id, String name, String desc, Date created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id && Objects.equals(name, candidate.name) && Objects.equals(desc, candidate.desc) && Objects.equals(created, candidate.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

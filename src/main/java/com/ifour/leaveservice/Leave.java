package com.ifour.leaveservice;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Leave {

    @Id
    private int id;

    private String name;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-mm-dd")
    private java.sql.Date startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-mm-dd")
    private java.sql.Date endDate;

    private Integer totalLeave;
    private String status;
    private String type;

    public Leave(Integer id, String name, java.sql.Date startDate, java.sql.Date endDate, Integer totalLeave, String status, String type) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalLeave = totalLeave;
        this.status = status;
        this.type = type;
    }

    public Leave() {
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return startDate;
    }

    public void setStart(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public Date getEnd() {
        return endDate;
    }

    public void setEnd(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    public int getTotalLeave() {
        return totalLeave;
    }

    public void setTotalLeave(Integer totalLeave) {
        this.totalLeave = totalLeave;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Leave { " +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", startDate = " + startDate +
                ", endDate = " + endDate +
                ", totalLeave = " + totalLeave +
                ", status = " + status +
                ", type = " + type +
                '}';
    }
}

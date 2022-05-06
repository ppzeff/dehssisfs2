package com.dehssisfs2.LOTO.models;

import javax.persistence.*;

@Entity
@Table(name = "work_sites")
public class WorkSitesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String workSite;

    public WorkSitesModel() {
    }

    @Override
    public String toString() {
        return "WorkSitesModel{" +
                "id=" + id +
                ", workSite='" + workSite + '\'' +
                '}';
    }

    public WorkSitesModel(String workSite) {
        this.workSite = workSite;
    }

    public Long getId() {
        return id;
    }

    public String getWorkSite() {
        return workSite;
    }

    public void setWorkSite(String workSite) {
        this.workSite = workSite;
    }
}

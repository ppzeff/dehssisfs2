package com.dehssisfs2.LOTO.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "LOTONd")
public class LOTONdModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "some_date")
    private Timestamp someDate;
    @Column(name = "FIO_LOTO_specialist")
    private String specialistFIO;
    private Date dataStart;
    private boolean complexBlocking;
    private String workSite;
    private String fotoName;

    public LOTONdModel() {
    }


    public LOTONdModel(String specialistFIO, boolean complexBlocking, String workSite, String fotoName, Date dataStart) {
        this.specialistFIO = specialistFIO;
        this.complexBlocking = complexBlocking;
        this.workSite = workSite;
        this.fotoName = fotoName;
        this.dataStart=dataStart;
        this.someDate = new Timestamp(new Date().getTime());
    }

    public long getId() {
        return id;
    }

    public String getSpecialistFIO() {
        return specialistFIO;
    }

    public void setSpecialistFIO(String specialistFIO) {
        this.specialistFIO = specialistFIO;
    }

    public boolean isComplexBlocking() {
        return complexBlocking;
    }

    public void setComplexBlocking(boolean complexBlocking) {
        this.complexBlocking = complexBlocking;
    }

    public String getWorkSite() {
        return workSite;
    }

    public void setWorkSite(String workSite) {
        this.workSite = workSite;
    }

    public String getFotoName() {
        return fotoName;
    }

    public void setFotoName(String fotoName) {
        this.fotoName = fotoName;
    }

    public Timestamp getSomeDate() {
        return someDate;
    }

    public Date getDataStart() {
        return dataStart;
    }


    public void setDataStart(Date dataStart) {
        this.dataStart = dataStart;
    }

    @Override
    public String toString() {
        return "LOTONd{" +
                "id=" + id +
                ", someDate=" + someDate +
                ", dataStart=" + dataStart +
                ", specialistFIO='" + specialistFIO + '\'' +
                ", complexBlocking=" + complexBlocking +
                ", workSite='" + workSite + '\'' +
                ", fotoName='" + fotoName + '\'' +
                '}';
    }
}
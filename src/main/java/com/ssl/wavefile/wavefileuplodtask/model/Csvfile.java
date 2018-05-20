package com.ssl.wavefile.wavefileuplodtask.model;

import javax.persistence.*;

@Entity
public class Csvfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String phoneNumber;
    @ManyToOne
    Webfile webfile;

    public Csvfile() {
    }

    public Csvfile(String phoneNumber, Webfile webfile) {
        this.phoneNumber = phoneNumber;
        this.webfile = webfile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Webfile getWebfile() {
        return webfile;
    }

    public void setWebfile(Webfile wmvFile) {
        this.webfile = wmvFile;
    }
}

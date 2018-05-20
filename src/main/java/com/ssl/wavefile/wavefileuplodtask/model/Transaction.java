package com.ssl.wavefile.wavefileuplodtask.model;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Csvfile csvfile;

    public Transaction(Csvfile csvfile) {
        this.csvfile = csvfile ;
    }

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Csvfile getCsvfile() {
        return csvfile;
    }

    public void setCsvfile(Csvfile csvfile) {
        this.csvfile = csvfile;
    }
}

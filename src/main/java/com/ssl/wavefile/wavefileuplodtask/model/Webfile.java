package com.ssl.wavefile.wavefileuplodtask.model;



import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
public class Webfile {
    @Id
    @GeneratedValue
    private  int id;




    private String fileName;



    public Webfile() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}

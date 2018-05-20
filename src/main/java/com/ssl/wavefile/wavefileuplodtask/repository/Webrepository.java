package com.ssl.wavefile.wavefileuplodtask.repository;


import com.ssl.wavefile.wavefileuplodtask.model.Webfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Webrepository extends JpaRepository<Webfile,Integer> {
}

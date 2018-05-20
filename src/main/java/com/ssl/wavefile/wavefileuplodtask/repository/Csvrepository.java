package com.ssl.wavefile.wavefileuplodtask.repository;


import com.ssl.wavefile.wavefileuplodtask.model.Csvfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Csvrepository extends JpaRepository<Csvfile, Integer> {
}

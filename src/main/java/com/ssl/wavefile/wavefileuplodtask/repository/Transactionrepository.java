package com.ssl.wavefile.wavefileuplodtask.repository;


import com.ssl.wavefile.wavefileuplodtask.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Transactionrepository  extends JpaRepository<Transaction,Integer> {
}

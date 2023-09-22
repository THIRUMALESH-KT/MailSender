package com.eidiko.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eidiko.dto.MailTransferDTO;
import com.eidiko.entity.MailTransferDetails;

public interface MailTransferDetailsRepository extends JpaRepository<MailTransferDetails, Long> {


	List<Optional> findAllByToAddr(String mail);
    @Query("SELECT NEW com.eidiko.dto.MailTransferDTO(m.id, m.toAddr, m.subject1, m.date, f.fileName) " +
            "FROM MailTransferDetails m " +
            "LEFT JOIN m.fileNames f " +
            "WHERE m.toAddr = :mail")
     List<MailTransferDTO> find(String mail);
    
}

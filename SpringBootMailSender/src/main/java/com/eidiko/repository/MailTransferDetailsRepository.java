package com.eidiko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eidiko.entity.MailTransferDetails;

public interface MailTransferDetailsRepository extends JpaRepository<MailTransferDetails, Long> {

}

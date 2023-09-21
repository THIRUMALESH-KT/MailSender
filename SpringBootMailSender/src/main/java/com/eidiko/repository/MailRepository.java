package com.eidiko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eidiko.entity.MailEntity;

public interface MailRepository extends JpaRepository<MailEntity, Long> {

	MailEntity findByMail(String mail);

}

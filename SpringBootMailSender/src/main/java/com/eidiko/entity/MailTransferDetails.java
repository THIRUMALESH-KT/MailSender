package com.eidiko.entity;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.sql.Template;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;

@Entity
public class MailTransferDetails {

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String toAddr;
	@Lob
	private Blob file;
	private String subject1;
	@Lob
	private Blob template;
	private String fileName;
	private Date date;
	public MailTransferDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MailTransferDetails(String toAddr, Blob file, String subject1, Blob template) {
		super();
		this.toAddr = toAddr;
		this.file = file;
		this.subject1 = subject1;
		this.template = template;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getToAddr() {
		return toAddr;
	}
	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}
	public Blob getFile() {
		return file;
	}
	public void setFile(byte[] file) throws SerialException, SQLException {
		this.file = new SerialBlob(file);
	}
	public String getSubject1() {
		return subject1;
	}
	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}
	public Blob getTemplate() {
		return template;
	}
	public void setTemplate(byte[] template) throws SerialException, SQLException {
		this.template = new SerialBlob(template);
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}

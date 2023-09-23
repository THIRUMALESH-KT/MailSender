package com.eidiko.entity;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class MailTransferDetails {

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String toAddr;
	@Lob
	@Column(length = 10485760)
	private Blob file[];
	private String subject1;
	@Lob
	@Column(length = 10485760)
	private Blob template;
    @OneToMany(mappedBy = "mailTransferDetails", cascade = CascadeType.ALL)
    private List<FileNames> fileNames=new ArrayList<>();
	private Date date;
	public MailTransferDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public MailTransferDetails(String toAddr, Blob file[], String subject1, Blob template) {
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
	public Blob[] getFile() {
		return file;
	}
	public void setFile(MultipartFile[] file2) throws SerialException, SQLException, IOException {
	       file = new Blob[file2.length];

	        for (int i = 0; i < file2.length; i++) {
	            // Convert MultipartFile to Blob
	            byte[] fileBytes = file2[i].getBytes();
	            file[i] = new SerialBlob(fileBytes);
	        }
	    
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
	public List<FileNames> getFileNames() {
		return fileNames;
	}
//	public void setFileNames(List<FileNames> fileNames) {
//		this.fileNames = fileNames;
//	}
	public void setTemplate(byte[] template) throws SerialException, SQLException {
		this.template = new SerialBlob(template);
	}

	public void setFileNames(MultipartFile[] file2) {
	    for (MultipartFile file : file2) {
	        FileNames fileName = new FileNames();
	        fileName.setFileName(file.getOriginalFilename());
	        fileName.setMailTransferDetails(this); // Associate the FileNames entity with this MailTransferDetails
	        fileNames.add(fileName);
	    }
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}

package com.eidiko.dto;

import java.sql.Date;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.eidiko.entity.FileNames;
@Configuration
public class MailTransferDTO {

	  private Long id;
	    private String toAddr;
	    private String subject1;
	    private java.util.Date date;
	    private List<FileNames> fileName;
		
		
		public MailTransferDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public MailTransferDTO(Long id, String toAddr, String subject1, java.util.Date date2, List<FileNames> list) {
		    this.id = id;
		    this.toAddr = toAddr;
		    this.subject1 = subject1;
		    this.date = date2;
		    this.fileName=list;
		    // Assign fileName to the appropriate field in the DTO, such as adding it to the fileNames list.
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
		public String getSubject1() {
			return subject1;
		}
		public void setSubject1(String subject1) {
			this.subject1 = subject1;
		}
		public java.util.Date getDate() {
			return date;
		}
		public void setDate(java.util.Date date) {
			this.date = date;
		}
		public List<FileNames> getFileNames() {
			return fileName;
		}
		public void setFileNames(List<FileNames> fileName) {
			this.fileName = fileName;
		}
	    
}

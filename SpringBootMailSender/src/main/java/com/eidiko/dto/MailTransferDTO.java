package com.eidiko.dto;

import java.sql.Date;
import java.util.List;

import org.springframework.context.annotation.Configuration;
@Configuration
public class MailTransferDTO {

	  private Long id;
	    private String toAddr;
	    private String subject1;
	    private Date date;
	    private List<String> fileNames;
		public Long getId() {
			return id;
		}
		
		public MailTransferDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public MailTransferDTO(Long id, String toAddr, String subject1, Date date, List<String> fileNames) {
			super();
			this.id = id;
			this.toAddr = toAddr;
			this.subject1 = subject1;
			this.date = date;
			this.fileNames = fileNames;
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
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public List<String> getFileNames() {
			return fileNames;
		}
		public void setFileNames(List<String> fileNames) {
			this.fileNames = fileNames;
		}
	    
}

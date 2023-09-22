package com.eidiko.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FileNames {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String fileName;

	    @ManyToOne
	    @JoinColumn(name = "mailTransferDetails_id")
	    @JsonBackReference
	    private MailTransferDetails mailTransferDetails;


	    public FileNames() {
	        super();
	    }

	    public FileNames(String fileName) {
	        this.fileName = fileName;
	    }

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public MailTransferDetails getMailTransferDetails() {
			return mailTransferDetails;
		}

		public void setMailTransferDetails(MailTransferDetails mailTransferDetails) {
			this.mailTransferDetails = mailTransferDetails;
		}
	    
	    
}

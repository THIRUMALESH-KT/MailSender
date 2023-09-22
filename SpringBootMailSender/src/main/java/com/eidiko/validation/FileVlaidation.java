package com.eidiko.validation;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

public class FileVlaidation {

	public static void validate(MultipartFile[] files) throws FileNotFoundException, FileUploadException {
		// TODO Auto-generated method stub
		for(MultipartFile file: files) {
		String OriginalFileName=file.getOriginalFilename().toLowerCase();
		if(OriginalFileName==null)throw new FileNotFoundException("File name is not found");
		String fileExtention=OriginalFileName.substring(OriginalFileName.lastIndexOf("."));
		
		if(fileExtention.equals(".txt")| fileExtention.equals(".jpg")|fileExtention.equals(".doc")|fileExtention.equals(".docx")|fileExtention.equals(".mp4")) {}
		else throw new FileUploadException("only txt jpg doc docx file are allowed");
		}
	}

}

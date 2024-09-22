package com.praveen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.praveen.model.FileResponse;
import com.praveen.service.FileService;

@RestController
public class FileController {
	
	@Autowired
	private FileService fileService;

	@PostMapping("/upload")
	public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file) {
		try {

			Boolean uploadFile = fileService.uploadFile(file);
			if (uploadFile) {
				return new ResponseEntity<FileResponse>(new FileResponse("File nmae","sucess"), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<FileResponse>(new FileResponse("File nmae","fail"),HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			return new ResponseEntity<FileResponse>(new FileResponse("File nmae","fail"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}

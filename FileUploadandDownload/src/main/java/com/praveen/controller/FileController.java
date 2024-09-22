package com.praveen.controller;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.praveen.model.FileResponse;
import com.praveen.model.Product;
import com.praveen.repository.ProductRepository;
import com.praveen.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FileController {

	@Autowired
	private FileService fileService;
	
	

	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		try {

			Boolean uploadFile = fileService.uploadFile(file);
			if (uploadFile) {
				return new ResponseEntity<FileResponse>(new FileResponse("File nmae", "sucess"), HttpStatus.CREATED);
			} else {

				return new ResponseEntity<FileResponse>(new FileResponse("File nmae", "fail"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			return new ResponseEntity<FileResponse>(new FileResponse("File nmae", "fail"),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("/download")
	public ResponseEntity<?> downloadFile(@RequestParam String file) {
		try {
			byte[] downloadFile = fileService.downloadFile(file);
			String contentType = getContentType(file);

			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.parseMediaType(contentType));
//			header.setContentLength(file.length());
			header.setContentDispositionFormData("attachment", file);

			return ResponseEntity.ok().headers(header).body(downloadFile);

		} catch (FileNotFoundException e) {
			return new ResponseEntity<>("file not found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public String getContentType(String fileName) {
		String extension = FilenameUtils.getExtension(fileName);

		switch (extension) {
		case "pdf":
			return "application/pdf";
		case "xlsx":
			return "application/vnd.openxmlformats-officedocument.spreadsheettml.sheet";
		case "txt":
			return "text/plan";
		case "png":
			return "image/png";
		case "jpeg":
			return "image/jpeg";
		default:
			return "application/octet-stream";

		}
	}
	
	@PostMapping("/upload-data")
	public ResponseEntity<?> uploadFileWithData(@RequestParam String product, @RequestParam MultipartFile file) {

//		log.info("Product:{}", product);
//		log.info("file:{}", file);

		List<String> extension = Arrays.asList( "jpeg", "png", "jpg" );
		if (file.isEmpty()) {
			return new ResponseEntity<>("please file upload", HttpStatus.BAD_REQUEST);
		}else {
			String originalFilename = file.getOriginalFilename();
			String fileExtension = FilenameUtils.getExtension(originalFilename);
			boolean contains = extension.contains(fileExtension);
			if(!contains)
			{
				return new ResponseEntity<>("please upload jpeg/png/jpg image", HttpStatus.BAD_REQUEST);
			}
		}

		try {

			String fileName = fileService.uploadFileWithData(file);
			if (StringUtils.hasText(fileName)) {
				ObjectMapper objectMapper = new ObjectMapper();
				Product productObj = objectMapper.readValue(product, Product.class);
				productObj.setImageName(fileName);

				Boolean saveProduct = fileService.saveProduct(productObj);
				if (saveProduct) {
					return new ResponseEntity<>("Product & Image uploaded", HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>("file uploaded but Product Not saved",
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<>("file upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


}

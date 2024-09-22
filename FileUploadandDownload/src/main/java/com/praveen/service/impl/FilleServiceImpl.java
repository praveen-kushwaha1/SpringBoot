package com.praveen.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.praveen.model.Product;
import com.praveen.repository.ProductRepository;
import com.praveen.service.FileService;

@Service
public class FilleServiceImpl implements FileService {

	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Autowired
	private ProductRepository productRepo;

	@Override
	public Boolean uploadFile(MultipartFile file) throws IOException {

		String fileName = file.getOriginalFilename();
		File savefile = new File(uploadPath);

		if (!savefile.exists()) {
			savefile.mkdir();
		}
		String storePath = uploadPath.concat(fileName);

		long upload = Files.copy(file.getInputStream(), Paths.get(storePath));
		if (upload != 0) {
			return true;
		}
		return false;
	}

	@Override
	public byte[] downloadFile(String file) throws Exception {
		String fullPath = uploadPath.concat(file); // file/spring_rest.pptx
		try {
			InputStream ios = new FileInputStream(fullPath);

			return StreamUtils.copyToByteArray(ios);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public String uploadFileWithData(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		File savefile = new File(uploadPath);

		String rndString = UUID.randomUUID().toString();
		// my_photo.jpeg -> my_photo_jhsfhjkbsf.jpeg -> my_photo.jpeg 
		String removeExtension = FilenameUtils.removeExtension(fileName); // -> my_photo
		String extension = FilenameUtils.getExtension(fileName);
		fileName =removeExtension+"_"+rndString+"."+extension;
		
		
		if (!savefile.exists()) {
			savefile.mkdir();
		}
		String storePath = uploadPath.concat(fileName);

		long upload = Files.copy(file.getInputStream(), Paths.get(storePath));
		if (upload != 0) {
			return fileName;
		}
		return null;
	}


	@Override
	public Boolean saveProduct(Product product) {
	Product save=	productRepo.save(product);
	if(!ObjectUtils.isEmpty(save)) {
		return true;
	}
		return false;
	}

}

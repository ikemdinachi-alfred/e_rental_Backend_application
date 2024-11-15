package com.alfredTech.e_rental_application.services;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GoogleCloudStorage {

    private static final Logger logger = Logger.getLogger(GoogleCloudStorage.class.getName());
    private final Storage storage = StorageOptions.getDefaultInstance().getService();

    public String uploadImage(MultipartFile photo) {
        String fileName = photo.getOriginalFilename();
        if (fileName == null) {
            throw new RuntimeException("File name cannot be null");
        }

        try {
            byte[] bytes = photo.getBytes();
            String BUCKET_NAME = "hospitality-bucket1";
            BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(photo.getContentType())
                    .build();

            storage.create(blobInfo, bytes);
            logger.info("Image uploaded successfully to Google Cloud Storage: " + fileName);
            return String.format("https://storage.googleapis.com/%s/%s", BUCKET_NAME, fileName);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to read file content", e);
            throw new RuntimeException("Unable to read file content", e);
        } catch (StorageException e) {
            logger.log(Level.SEVERE, "Error uploading file to Google Cloud Storage: " + e.getMessage(), e);
            throw new RuntimeException("Error uploading file to Google Cloud Storage", e);
        }
    }
}

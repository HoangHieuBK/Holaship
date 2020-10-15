package vn.vmg.ptdv.hola.common.document.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.vmg.ptdv.hola.common.document.core.DocumentStorageProperties;
import vn.vmg.ptdv.hola.common.document.exception.DocumentStorageException;
import vn.vmg.ptdv.hola.common.document.repository.DocumentRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DocumentStorageService {
    private final Path fileStorageLocation;
    private final DocumentRepository documentRepository;

    public DocumentStorageService(DocumentStorageProperties fileStorageProperties,
            DocumentRepository documentRepository) throws DocumentStorageException {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        this.documentRepository = documentRepository;
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new DocumentStorageException(
                    "Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, String docType) throws DocumentStorageException {
        // Normalize file name
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = "";
        try {
            // Check if the file's name contains invalid characters
            if (originalFileName.contains("..")) {
                throw new DocumentStorageException(
                        "Sorry! Filename contains invalid path sequence " + originalFileName);
            }
            String fileExtension = "";
            try {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            } catch (Exception e) {
                fileExtension = "";
            }
            fileName = String.valueOf(System.nanoTime()) + "_" + originalFileName;

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            //TODO save to database
            DocumentStorageProperties newDoc = new DocumentStorageProperties();
            newDoc.setDocumentFormat(file.getContentType());
            newDoc.setFileName(fileName);
            newDoc.setDocumentType(docType);

            return fileName;
        } catch (DocumentStorageException | IOException ex) {
            throw new DocumentStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) throws Exception {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName);
        }
    }
}

package vn.vmg.ptdv.hola.api.rest.file;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.vmg.ptdv.hola.api.common.APIResponse;
import vn.vmg.ptdv.hola.common.document.exception.DocumentStorageException;
import vn.vmg.ptdv.hola.common.document.service.DocumentStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class FileUploadController {

    private final DocumentStorageService documentStorageService;

    public FileUploadController(DocumentStorageService documentStorageService) {
        this.documentStorageService = documentStorageService;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam("docType") String docType) throws DocumentStorageException {
        String fileName = documentStorageService.storeFile(file, docType);
        String fileDownloadUri = "/downloadFile/".concat(fileName);

        FileUploadJSONResponse fileUploadJSONResponse = new FileUploadJSONResponse(fileName, fileDownloadUri);
        APIResponse<FileUploadJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(fileUploadJSONResponse, HttpStatus.OK.value(), "uploadFile success");
    }

    @GetMapping("/downloadFile/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = null;
        if (fileName != null && !fileName.isEmpty()) {
            try {
                resource = documentStorageService.loadFileAsResource(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Try to determine file's content type
            String contentType = null;
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException ex) {
                //logger.info("Could not determine file type.");
            }
            // Fallback to the default content type if type could not be determined
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

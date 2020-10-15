package vn.vmg.ptdv.hola.api.rest.file;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class FileUploadJSONResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private Long size;

    public FileUploadJSONResponse(String fileName, String fileDownloadUri) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
    }
}

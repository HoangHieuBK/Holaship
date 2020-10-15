package vn.vmg.ptdv.hola.common.document.core;

import lombok.Data;

@Data
public class DocumentStorageProperties {
    private Integer documentId;
    private String fileName;
    private String documentType;
    private String documentFormat;
    private String uploadDir;

    public DocumentStorageProperties() {
    }

    public DocumentStorageProperties(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}

package vn.vmg.ptdv.hola.infra.common.document;

import vn.vmg.ptdv.hola.common.document.repository.DocumentRepository;

public class DocumentRepositoryImpl implements DocumentRepository {
    private final DocumentJDBC documentJDBC;

    public DocumentRepositoryImpl(DocumentJDBC documentJDBC) {
        this.documentJDBC = documentJDBC;
    }
}

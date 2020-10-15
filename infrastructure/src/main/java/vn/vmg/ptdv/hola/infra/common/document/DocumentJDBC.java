package vn.vmg.ptdv.hola.infra.common.document;

import vn.vmg.ptdv.hola.infra.common.document.context.DocumentInfraContext;

import javax.sql.DataSource;

public class DocumentJDBC {
    private final DataSource dataSource;
    private DocumentInfraContext jdbcContext;

    public DocumentJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}

package vn.vmg.ptdv.hola.infra.common.document.context;

import vn.vmg.ptdv.hola.common.document.core.DocumentID;
import vn.vmg.ptdv.hola.infra.shared.InfraContext;

import javax.sql.DataSource;

public class DocumentInfraContext extends InfraContext<DocumentID> {
    public DocumentInfraContext(DataSource ds) {
        super(ds);
    }
}

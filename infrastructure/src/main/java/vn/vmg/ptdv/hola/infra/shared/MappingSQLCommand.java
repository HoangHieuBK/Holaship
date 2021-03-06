package vn.vmg.ptdv.hola.infra.shared;

import javax.sql.DataSource;
import java.util.List;

public interface MappingSQLCommand {
    String prepareSQL();

    List<?> executeCommand(DataSource dataSource);

    void prepareParams(Object... parameters);

    void declareParameters();
}

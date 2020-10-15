package vn.vmg.ptdv.hola.infra.shared;

import javax.sql.DataSource;

public interface UpdateSQLCommand<A> {
    String prepareSQL();

    int updateCommand(DataSource dataSource, A a);

    void prepareParams(Object... parameters);

    void declareParameters();
}

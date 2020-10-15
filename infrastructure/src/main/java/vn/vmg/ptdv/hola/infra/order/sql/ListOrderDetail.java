package vn.vmg.ptdv.hola.infra.order.sql;

import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ListOrderDetail extends MappingSqlQuery implements MappingSQLCommand {
    @Override
    protected Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public String prepareSQL() {
        return null;
    }

    @Override
    public List<?> executeCommand(DataSource dataSource) {
        return null;
    }

    @Override
    public void prepareParams(Object... parameters) {

    }

    @Override
    public void declareParameters() {

    }
}

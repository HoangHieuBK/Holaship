package vn.vmg.ptdv.hola.api.rest.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VerificationJSONResponse {
    private Boolean status;
}

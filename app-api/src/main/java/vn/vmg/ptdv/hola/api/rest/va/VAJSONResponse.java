package vn.vmg.ptdv.hola.api.rest.va;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class VAJSONResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;
    private String userName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String shipCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String shopCode;

    public VAJSONResponse() {
    }


    public VAJSONResponse(String userName, String shipCode, String shopCode) {
        this.userName = userName;
        this.shipCode = shipCode;
        this.shopCode = shopCode;
    }

    public VAJSONResponse(Long userId, String userName, String shipCode, String shopCode) {
        this.userId = userId;
        this.userName = userName;
        this.shipCode = shipCode;
        this.shopCode = shopCode;
    }
}

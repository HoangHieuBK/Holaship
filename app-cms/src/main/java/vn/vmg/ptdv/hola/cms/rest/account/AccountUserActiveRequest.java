package vn.vmg.ptdv.hola.cms.rest.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;

@Data
public class AccountUserActiveRequest {
    private String id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private Instant uTimestamp;
    private Integer status;

    public AccountUserActiveRequest() {
    }

    public AccountUserActiveRequest(
            @RequestBody String id, @RequestBody Instant uTimestamp, @RequestBody Integer status) {
        this.id = id;
        this.uTimestamp = uTimestamp;
        this.status = status;
    }
}

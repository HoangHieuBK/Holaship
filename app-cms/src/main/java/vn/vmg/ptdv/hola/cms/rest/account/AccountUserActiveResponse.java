package vn.vmg.ptdv.hola.cms.rest.account;

import lombok.Data;

import java.time.Instant;

@Data
public class AccountUserActiveResponse {
    private String id;
    private Instant uTimestamp;
    private Integer status;
}

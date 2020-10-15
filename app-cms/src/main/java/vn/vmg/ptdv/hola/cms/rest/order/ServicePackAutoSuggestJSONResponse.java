package vn.vmg.ptdv.hola.cms.rest.order;

import lombok.Data;

import java.util.List;

@Data
public class ServicePackAutoSuggestJSONResponse {
    private List<ServicePackAutoSuggestJSON> listNameCode;
}

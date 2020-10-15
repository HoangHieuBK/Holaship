package vn.vmg.ptdv.hola.cms.rest.groupmanager;

import lombok.Data;
import vn.vmg.ptdv.hola.groupmanager.presentation.GroupManagerAutoSuggestResponse;

import java.util.List;
@Data
public class AutoSuggestResponse {
    List<GroupManagerAutoSuggestResponse> list;
}

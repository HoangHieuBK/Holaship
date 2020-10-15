package vn.vmg.ptdv.hola.cms.rest.groupmanager;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class GroupManagerAutoSuggestRequest {
    private String groupCode;
    private String groupName;

    public GroupManagerAutoSuggestRequest() {
    }

    public GroupManagerAutoSuggestRequest(@RequestParam(required = false) String groupCode, String groupName) {
        this.groupCode = groupCode;
        this.groupName = groupName;
    }
}

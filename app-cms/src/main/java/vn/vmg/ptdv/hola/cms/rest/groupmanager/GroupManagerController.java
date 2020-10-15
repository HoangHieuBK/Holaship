package vn.vmg.ptdv.hola.cms.rest.groupmanager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vmg.ptdv.hola.account.exception.AccountNotFoundException;
import vn.vmg.ptdv.hola.cms.common.APIResponse;
import vn.vmg.ptdv.hola.cms.mapper.GroupManagerJSONMapper;
import vn.vmg.ptdv.hola.exception.EntityUpdateException;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.groupmanager.presentation.GroupManagerRequest;

@RestController
public class GroupManagerController {
    private static final String GROUPMANAGER_ROUTES = "/group-managers";


    @GetMapping(GROUPMANAGER_ROUTES)
    public ResponseEntity<?> getGroupManagerAll(GroupManagerJSONRequest request) {
        GroupManagerRequest groupManagerRequest = GroupManagerJSONMapper.getInstance().fromGroupManagerRequest(request);
        //ToDo call service
        GroupManagerJSONResponse response = GroupManagerData.getInstance().getGroupManagerData();
        APIResponse<GroupManagerJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(response, HttpStatus.OK.value(), "groupmanager.success.get_datas");
    }

    @GetMapping(GROUPMANAGER_ROUTES + "/{code}")
    public ResponseEntity<?> getGroupManagerFindById(@PathVariable("code") String code) {
        //Todo call service

        GroupManagerDetailJSONResponse response = GroupManagerData.getInstance().mapGroupManagerDetailJSONResponse();
        APIResponse<GroupManagerDetailJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(response, HttpStatus.OK.value(), "groupmanager.success.get_data");
    }

    @PostMapping(GROUPMANAGER_ROUTES)
    @ResponseBody
    public ResponseEntity<?> insert(@RequestBody GroupManagerDetailJSONRequest request) throws OrderDomainException {
        //Todo call service
        GroupManagerDetailJSONResponse response = new GroupManagerDetailJSONResponse();
        APIResponse<GroupManagerDetailJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(response, HttpStatus.OK.value(), "groupmanager.success.insert");
    }

    @PutMapping(GROUPMANAGER_ROUTES + "/{code}")
    public ResponseEntity<?> update(@PathVariable("code") String code, GroupManagerDetailJSONRequest request) throws
            EntityUpdateException {
        //Todo call service
        GroupManagerDetailJSONResponse response = new GroupManagerDetailJSONResponse();
        APIResponse<GroupManagerDetailJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(response, HttpStatus.OK.value(), "groupmanager.success.update");
    }

    @PatchMapping(GROUPMANAGER_ROUTES + "/{code}" + "/status")
    @ResponseBody
    public ResponseEntity<?> changeStatus(@PathVariable("code") String code,
            @RequestBody GroupManagerStatusRequest request) {
        GroupManagerDetailJSONResponse response = new GroupManagerDetailJSONResponse();
        APIResponse<GroupManagerDetailJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(response, HttpStatus.OK.value(), "groupmanager.success.change");
    }

    @GetMapping(GROUPMANAGER_ROUTES + "/autoSuggest")
    public ResponseEntity<?> getAutoSuggest(GroupManagerAutoSuggestRequest request) throws AccountNotFoundException {

        AutoSuggestResponse accountResponse = GroupManagerData.getInstance()
                .mapDataAutoSuggestResponse();
        //TODO call Service
        APIResponse<AutoSuggestResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(accountResponse, HttpStatus.OK.value(), "groupmanager.success.autoSuggest");
    }
}

package vn.vmg.ptdv.hola.cms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.cms.common.APIResponse;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;
import vn.vmg.ptdv.hola.exception.EntityNotFoundException;

//@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<APIResponse> handleRuntimeException(RuntimeException e) {
        APIResponse<String> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(SecurityDESException.class)
    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<APIResponse> handleSecurityDESException(SecurityDESException e) {
        APIResponse<String> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(AccountDomainException.class)
    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<APIResponse> handleAccountDomainException(AccountDomainException e) {
        if (e.getErrors() != null && e.getErrors().size() > 0) {
            APIResponse<ErrorsJSONPayload> apiResponse = new APIResponse<>();
            return apiResponse.sendResponse(new ErrorsJSONPayload(e.getErrors()),
                    HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

        APIResponse<ErrorJSON> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(new ErrorJSON(String.valueOf(e.getErrorCode()), e.getMessage()),
                HttpStatus.BAD_REQUEST.value(), e.getMessage());

    }

    @ExceptionHandler(EntityNotFoundException.class)
    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<APIResponse> handleEntityNotFoundException(EntityNotFoundException e) {
//        if (e.getErrors() != null && e.getErrors().size() > 0) {
//            APIResponse<ErrorsJSONPayload> apiResponse = new APIResponse<>();
//            return apiResponse.sendResponse(new ErrorsJSONPayload(e.getErrors()),
//                    HttpStatus.BAD_REQUEST.value(), e.getMessage());
//        }

        APIResponse<ErrorJSON> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(new ErrorJSON(String.valueOf(e.getErrorCode()), e.getMessage()),
                HttpStatus.BAD_REQUEST.value(), e.getMessage());

    }

}

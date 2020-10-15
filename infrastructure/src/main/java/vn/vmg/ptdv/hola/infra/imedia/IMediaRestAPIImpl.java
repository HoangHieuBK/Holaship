package vn.vmg.ptdv.hola.infra.imedia;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import vn.vmg.ptdv.hola.common.des.Commons;
import vn.vmg.ptdv.hola.common.des.TripleDES;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;
import vn.vmg.ptdv.hola.common.otp.factory.OTPVerification;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

import static vn.vmg.ptdv.hola.common.des.TripleDESConfig.KEY3DES;
import static vn.vmg.ptdv.hola.infra.imedia.IMediaAPIConfig.*;
import static vn.vmg.ptdv.hola.infra.imedia.IMediaAPIConfig.SSORequestCode.*;
import static vn.vmg.ptdv.hola.infra.imedia.IMediaStatusResponse.ACCOUNT_ALREADY_EXISTS;
import static vn.vmg.ptdv.hola.infra.imedia.IMediaStatusResponse.SUCCESS;


public class IMediaRestAPIImpl implements IMediaRestAPI {

    private final String REST_URL = "http://14.232.245.102:8086/sandbox/SSO/API";
    private final String TECH_REST_URL = "https://860daf8b6d10.ngrok.io/FIRMBANKING/NAPAS";

    private final HolaRestTemplate holaRestTemplate;

    public IMediaRestAPIImpl() {
        this.holaRestTemplate = new HolaRestTemplate();
    }

    @Override
    public IMediaResponse register(SSORegister register) throws IMediaRestException, SecurityDESException,
            JsonProcessingException {
        SSORequestResponse
                apiRequest = new SSORequestResponse(PR_REGISTER, "", "",
                SERVICE_CODE, CLIENT_TYPE, "1.0.0");
        apiRequest.setData(register.encrypt());
        SSORequestResponse response = holaRestTemplate.post(REST_URL, apiRequest.asJsonString());

        if (response.getStatus() == SUCCESS.code) {
            String dataPure = TripleDES.decrypt(KEY3DES, response.getData());
            JsonParser parser = new JacksonJsonParser();
            Map<String, Object> dataAsJson = parser.parseMap(dataPure);
            IMediaResponse iMediaResponse = new IMediaResponse(dataAsJson);
            iMediaResponse.setStatus(response.getStatus());
            return iMediaResponse;
        }

        if (response.getStatus() == ACCOUNT_ALREADY_EXISTS.code) {
            throw new IMediaRestException(ACCOUNT_ALREADY_EXISTS.code, response.getResponseMsg());
        }

        throw new IMediaRestException(response.getStatus(), response.getResponseMsg());
    }

    @Override
    public IMediaResponse getAccountInfo(SSORegister userData) throws JsonProcessingException, SecurityDESException,
            IMediaRestException {
        SSORequestResponse
                apiRequest = new SSORequestResponse(PR_GET_USER_INF, "", "",
                SERVICE_CODE, CLIENT_TYPE, "1.0.0");
        apiRequest.setData(userData.encryptGetInfo());
        SSORequestResponse response = holaRestTemplate.post(REST_URL, apiRequest.asJsonString());

        if (response.getStatus() == SUCCESS.code) {
            String dataPure = TripleDES.decrypt(KEY3DES, response.getData());
            JsonParser parser = new JacksonJsonParser();
            Map<String, Object> dataAsJson = parser.parseMap(dataPure);
            IMediaResponse iMediaResponse = new IMediaResponse(dataAsJson);
            return iMediaResponse;
        }

        throw new IMediaRestException(response.getStatus(), response.getResponseMsg());
    }

    @Override
    public IMediaResponse login(SSOLogin ssoLogin) throws JsonProcessingException, SecurityDESException,
            IMediaRestException {
        SSORequestResponse
                apiRequest = new SSORequestResponse(PR_LOGIN, ssoLogin.encrypt(), "",
                SERVICE_CODE, CLIENT_TYPE, CLIENT_VERSION);
        SSORequestResponse response = holaRestTemplate.post(REST_URL, apiRequest.asJsonString());

        if (response.getStatus() == SUCCESS.code) {
            String dataPure = TripleDES.decrypt(KEY3DES, response.getData());
            JsonParser parser = new JacksonJsonParser();
            Map<String, Object> dataAsJson = parser.parseMap(dataPure);
            IMediaResponse iMediaResponse = new IMediaResponse(dataAsJson);
            return iMediaResponse;
        }

        throw new IMediaRestException(response.getStatus(), response.getResponseMsg());
    }

    @Override
    public boolean generatorOTP(OTPVerification otpInfo) throws JsonProcessingException, SecurityDESException,
            IMediaRestException {
        IMediaResponse iMediaResponse = new IMediaResponse();
        iMediaResponse.setUsername(otpInfo.getPhone());
        SSORequestResponse
                apiRequest = new SSORequestResponse(PR_GET_OTP, iMediaResponse.encrypt(), "",
                SERVICE_CODE, CLIENT_TYPE, CLIENT_VERSION);
        SSORequestResponse response = holaRestTemplate.post(REST_URL, apiRequest.asJsonString());
        if (response.getStatus() == SUCCESS.code) {
            return true;
        }
        throw new IMediaRestException(response.getStatus(), response.getResponseMsg());
    }

    @Override
    public boolean verifyOTP(OTPVerification otp) throws JsonProcessingException, SecurityDESException,
            IMediaRestException {
        IMediaResponse iMediaResponse = new IMediaResponse();
        iMediaResponse.setUsername(otp.getPhone());
        iMediaResponse.setOtpCode(otp.getOtp());
        SSORequestResponse
                apiRequest = new SSORequestResponse(PR_VERIFICATION_TOP, iMediaResponse.encrypt(), "",
                SERVICE_CODE, CLIENT_TYPE, CLIENT_VERSION,
                "");
        SSORequestResponse response = holaRestTemplate.post(REST_URL, apiRequest.asJsonString());
        if (response.getStatus() == SUCCESS.code) {
            return true;
        }
        throw new IMediaRestException(response.getStatus(), response.getResponseMsg());
    }

    @Override
    public boolean changePassword(IMediaResponse iMediaResponse)
            throws NoSuchPaddingException, JsonProcessingException, UnsupportedEncodingException,
            IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, SecurityDESException {
        boolean isSuccess = false;
        SSORequestResponse
                apiRequest = new SSORequestResponse(PR_CHANGEPASS, iMediaResponse.encrypt(), "",
                SERVICE_CODE, CLIENT_TYPE, CLIENT_VERSION,
                iMediaResponse.getSessionKey());
        SSORequestResponse response = holaRestTemplate.post(REST_URL, apiRequest.asJsonString());
        if (response.getStatus() == SUCCESS.code) {
            isSuccess = true;
        }
        return isSuccess;
    }

    @Override
    public IMediaResponse getUserInfor(IMediaResponse iMediaResponse)
            throws JsonProcessingException, SecurityDESException {

        SSORequestResponse
                apiRequest = new SSORequestResponse(PR_GETUSER_INF, iMediaResponse.encrypt(), "",
                SERVICE_CODE, CLIENT_TYPE, CLIENT_VERSION,
                iMediaResponse.getSessionKey());
        SSORequestResponse response = holaRestTemplate.post(REST_URL, apiRequest.asJsonString());
        if (response.getStatus() == SUCCESS.code) {
            String dataPure = TripleDES.decrypt(KEY3DES, response.getData());
            JsonParser parser = new JacksonJsonParser();
            Map<String, Object> dataAsJson = parser.parseMap(dataPure);
            iMediaResponse = new IMediaResponse(dataAsJson);
        }
        return iMediaResponse;
    }

    @Override
    public boolean updateUserInfor(IMediaResponse iMediaResponse)
            throws JsonProcessingException, SecurityDESException, IMediaRestException {

        SSORequestResponse
                apiRequest = new SSORequestResponse(PR_UPDATE_USER_INFO, iMediaResponse.encrypt(), "",
                SERVICE_CODE, CLIENT_TYPE, CLIENT_VERSION,
                iMediaResponse.getSessionKey());
        SSORequestResponse response = holaRestTemplate.postUpdate(REST_URL, apiRequest.asJsonString());
        if (response.getStatus() == SUCCESS.code) {
            return true;
        }
        throw new IMediaRestException(response.getStatus(), response.getResponseMsg());
    }

    @Override
    public IMediaVAResponse registerVAAccount(SSOVARegister ssovaRegister) throws JsonProcessingException,
            SecurityDESException, IMediaRestException {
        ssovaRegister.setRequestId(Commons.genRequestTime());
        String typeApp = SERVICE_CODE_SHIP;
        if (ssovaRegister.getServiceCode() != null) {
            typeApp = SERVICE_CODE_SHOP;
        }
        SSORequestResponse
                apiRequest = new SSORequestResponse(PR_REGISTER_VA_ACCOUNT, ssovaRegister.encrypt(), "",
                typeApp, CLIENT_TYPE, CLIENT_VERSION);
        SSORequestResponse response = holaRestTemplate.post(REST_URL, apiRequest.asJsonString());
        IMediaVAResponse iMediaVAResponse;
        if (response.getStatus() == SUCCESS.code) {
            String dataPure = TripleDES.decrypt(KEY3DES, response.getData());
            JsonParser parser = new JacksonJsonParser();
            Map<String, Object> dataAsJson = parser.parseMap(dataPure);
            iMediaVAResponse = new IMediaVAResponse(dataAsJson);

            return iMediaVAResponse;
        }
        throw new IMediaRestException(response.getStatus(), response.getResponseMsg());
    }

    @Override
    public SSOForgotPasswordResponse forgotPassword(
            SSOForgotPassword ssoForgotPassword) throws JsonProcessingException, IMediaRestException,
            SecurityDESException {
        SSORequestResponse
                apiRequest = new SSORequestResponse(PR_RESET_PASS, ssoForgotPassword.encrypt(), "",
                SERVICE_CODE_SHIP, CLIENT_TYPE, CLIENT_VERSION);
        SSORequestResponse response = holaRestTemplate.post(REST_URL, apiRequest.asJsonString());
        SSOForgotPasswordResponse
                ssoForgotPasswordResponse = new SSOForgotPasswordResponse();
        if (response.getStatus() == HttpStatus.OK.value()) {
            ssoForgotPasswordResponse.setStatusCode(HttpStatus.OK.value());
            ssoForgotPasswordResponse.setStatus(true);
            return ssoForgotPasswordResponse;
        }

        throw new IMediaRestException(response.getStatus(), response.getResponseMsg());
    }

    @Override
    public TechConfirmInfoResponse confirmInfoCash(TechConfirmInfoRequest request) throws JsonProcessingException,
            IMediaRestException, SecurityDESException {

        request.setRequestId(request.getPartnerCode() + "IMT" + Commons.genRequestTime());
        request.setRequestTime(Commons.genRequestTimeNoUniqueId());
        String encodeSig = request.getRequestId() + "|" + request.getRequestTime() + "|" + request.getPartnerCode() +
                "|"
                + request.getOperation() + "|" + request.getBankNo() + "|" + request.getAccNo() + "|" +
                request.getAccType();
        request.setSignature(TripleDES.encrypt(KEY3DES, encodeSig));
        TechConfirmInfoResponse response = holaRestTemplate
                .iMediaTechConfirmInfo(TECH_REST_URL, request.asJsonString());
        if (response.getResponseCode() == SUCCESS.code) {
            return response;
        }
        if(response.getResponseCode() == 11){
            throw new IMediaRestException(response.getResponseCode(),"Thông tin truy vấn không hợp lệ");
        }
        throw new IMediaRestException(response.getResponseCode(), response.getResponseMessage());
    }

}

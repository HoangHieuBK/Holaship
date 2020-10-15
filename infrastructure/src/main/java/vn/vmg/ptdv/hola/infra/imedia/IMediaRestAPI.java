package vn.vmg.ptdv.hola.infra.imedia;

import com.fasterxml.jackson.core.JsonProcessingException;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;
import vn.vmg.ptdv.hola.common.otp.factory.OTPVerification;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public interface IMediaRestAPI {

    IMediaResponse register(SSORegister register) throws JsonProcessingException, IMediaRestException,
            SecurityDESException;


    IMediaResponse login(SSOLogin ssoLogin) throws JsonProcessingException, SecurityDESException, IMediaRestException;

    IMediaResponse getAccountInfo(SSORegister userData)
            throws JsonProcessingException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, UnsupportedEncodingException,
            SecurityDESException, IMediaRestException;

    boolean generatorOTP(OTPVerification otp) throws JsonProcessingException, SecurityDESException, IMediaRestException;

    boolean verifyOTP(OTPVerification otp) throws JsonProcessingException, SecurityDESException, IMediaRestException;

    boolean changePassword(IMediaResponse iMediaResponse)
            throws NoSuchPaddingException, JsonProcessingException, UnsupportedEncodingException,
            IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, SecurityDESException;

    IMediaResponse getUserInfor(
            IMediaResponse iMediaResponse)
            throws NoSuchPaddingException, JsonProcessingException, UnsupportedEncodingException,
            IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, SecurityDESException;

    boolean updateUserInfor(IMediaResponse iMediaResponse)
            throws JsonProcessingException, SecurityDESException, IMediaRestException;

    IMediaVAResponse registerVAAccount(SSOVARegister ssovaRegister) throws IMediaRestException,
            JsonProcessingException, SecurityDESException;


    SSOForgotPasswordResponse forgotPassword(
            SSOForgotPassword ssoForgotPassword) throws JsonProcessingException, IMediaRestException,
            SecurityDESException;

    TechConfirmInfoResponse confirmInfoCash(TechConfirmInfoRequest request) throws JsonProcessingException,
            IMediaRestException, SecurityDESException;
}

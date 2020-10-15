package vn.vmg.ptdv.hola.common.otp.repository;


import vn.vmg.ptdv.hola.common.exception.OTPVerificationException;
import vn.vmg.ptdv.hola.common.otp.factory.OTPVerification;

public interface OTPVerifyRepository {
    boolean generatorOTP(OTPVerification otp) throws OTPVerificationException;

}

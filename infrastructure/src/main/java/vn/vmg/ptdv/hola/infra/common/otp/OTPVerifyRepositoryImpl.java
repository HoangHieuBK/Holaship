package vn.vmg.ptdv.hola.infra.common.otp;


import com.fasterxml.jackson.core.JsonProcessingException;
import vn.vmg.ptdv.hola.common.exception.OTPVerificationException;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;
import vn.vmg.ptdv.hola.common.otp.factory.OTPVerification;
import vn.vmg.ptdv.hola.common.otp.repository.OTPVerifyRepository;
import vn.vmg.ptdv.hola.infra.imedia.IMediaRestAPI;
import vn.vmg.ptdv.hola.infra.imedia.IMediaRestException;

public class OTPVerifyRepositoryImpl implements OTPVerifyRepository {

    private final IMediaRestAPI iMediaRestAPI;

    public OTPVerifyRepositoryImpl(IMediaRestAPI iMediaRestAPI) {
        this.iMediaRestAPI = iMediaRestAPI;
    }


    @Override
    public boolean generatorOTP(OTPVerification otp) throws OTPVerificationException {
        try {
            return iMediaRestAPI.generatorOTP(otp);
        } catch (JsonProcessingException | SecurityDESException | IMediaRestException e) {
            throw new OTPVerificationException(e.getMessage());
        }
    }

}

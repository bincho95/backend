package api.service;

import api.model.CheckVerification;
import api.model.DefaultResponse;
import api.model.SendVerification;
import common.maria.entity.VerifyCode;
import common.maria.repository.VerifyCodeRepository;
import common.rabbit.model.MessageDto;
import common.rabbit.publisher.SmsMQPublisher;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VerificationSerivce {

    private final VerifyCodeRepository verifyCodeRepository;
    private final SmsMQPublisher smsMQPublisher;

    public VerifyCode sendVerification(SendVerification sendVerification){
        String code = RandomStringUtils.randomNumeric(6);

        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setCode(code);
        verifyCode.setSessionId(sendVerification.getSessionId());
        verifyCode.setMobile(sendVerification.getMobile());
        verifyCode.setExpiredDate(LocalDateTime.now().plusMinutes(5));
        verifyCodeRepository.save(verifyCode);

        String content = "인증번호 [" + code + "] 를 입력해 주세요";
        MessageDto messageDto = new MessageDto(sendVerification.getMobile(), content);
        smsMQPublisher.publish(messageDto);

        return  verifyCode;
    }

    public DefaultResponse checkVerification(CheckVerification checkVerification) throws JSONException {
        VerifyCode verifyCode = verifyCodeRepository.findBySessionIdAndMobileAndCodeAndExpiredDateLessThanEqual(
                checkVerification.getSessionId(),
                checkVerification.getMobile(),
                checkVerification.getCode(),
                LocalDateTime.now()
        );

        if (verifyCode == null){
            return new DefaultResponse(false, "Verification Failed");
        } else {
            return new DefaultResponse(true, "Verification Success");
        }
    }
}
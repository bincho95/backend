package api.controller;

import api.model.CheckVerification;
import api.model.DefaultResponse;
import api.model.SendVerification;
import api.service.VerificationSerivce;
import common.maria.entity.VerifyCode;
import common.rabbit.publisher.SmsMQPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/verification")
@RequiredArgsConstructor
public class VerificationController {

    private final SmsMQPublisher smsMQPublisher;
    private final VerificationSerivce verificationSerivce;

    @PostMapping("/send")
    public ResponseEntity<VerifyCode> sendVerification(@RequestBody SendVerification sendVerification) {
        try {
            VerifyCode verifyCode = verificationSerivce.sendVerification(sendVerification);
            return new ResponseEntity(verifyCode, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/check")
    public ResponseEntity<DefaultResponse> checkVerification(@RequestBody CheckVerification checkVerification) {
        try {
            DefaultResponse defaultResponse = verificationSerivce.checkVerification(checkVerification);
            return new ResponseEntity(defaultResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

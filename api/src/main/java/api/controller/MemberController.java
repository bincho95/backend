package api.controller;

import api.model.ChagePasswordResponse;
import api.model.CheckPassword;
import api.model.DefaultResponse;
import api.service.MemberSerivce;
import common.maria.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberSerivce memberSerivce;

    @PostMapping("/create")
    public ResponseEntity<Member> createAccount(@RequestBody Member newMember) {
        try {
            Member member = memberSerivce.createAccount(newMember);
            return new ResponseEntity(member, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/info/{identifier}")
    public ResponseEntity<Member> memberInfo(@PathVariable String identifier) {
        try {
            Member member = memberSerivce.memberInfo(identifier);
            return new ResponseEntity(member, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Member> eidtMember(@PathVariable Long id, @RequestBody Member editMember) {

        try {
            Member member = memberSerivce.editMember(id, editMember);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/change-password/{mobile}")
    public ResponseEntity<ChagePasswordResponse> changePassword(@PathVariable String mobile) throws JSONException {

        try {
            ChagePasswordResponse chagePasswordResponse = memberSerivce.changePassword(mobile);
            return new ResponseEntity<>(chagePasswordResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/password-check")
    public ResponseEntity<DefaultResponse> checkPassowrd(@RequestBody CheckPassword checkPassword) {

        try {
            DefaultResponse defaultResponse = memberSerivce.checkPassword(checkPassword);
            return new ResponseEntity(defaultResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/exist-email/{email}")
    public ResponseEntity<DefaultResponse> existEmail(@PathVariable String email) {

        try {
            DefaultResponse defaultResponse = memberSerivce.checkDuplicateEmail(email);
            return new ResponseEntity(defaultResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/exist-mobile/{mobile}")
    public ResponseEntity<DefaultResponse> existMobile(@PathVariable String moblie) {

        try {
            DefaultResponse defaultResponse = memberSerivce.checkDuplicateMobile(moblie);
            return new ResponseEntity(defaultResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/exist-nickname/{nickname}")
    public ResponseEntity<DefaultResponse> existNickname(@PathVariable String nickname) {

        try {
            DefaultResponse defaultResponse = memberSerivce.checkDuplicateNickname(nickname);
            return new ResponseEntity(defaultResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package api.service;

import api.model.ChagePasswordResponse;
import api.model.CheckPassword;
import api.model.DefaultResponse;
import common.maria.entity.Member;
import common.maria.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberSerivce {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public Member createAccount(Member newMember) {
        Member member = new Member();
        member.setEmail(newMember.getEmail());
        member.setMobile(newMember.getMobile());
        member.setName(newMember.getName());
        member.setNickname(newMember.getNickname());
        member.setPassword(passwordEncoder.encode(newMember.getPassword()));
        memberRepository.save(member);

        return member;
    }

    public Member memberInfo(String identifier){

        return getMember(identifier);
    }

    private Member getMember(String identifier) {
        if(EmailValidator.getInstance().isValid(identifier)){
            return memberRepository.findByEmail(identifier);
        }else if(StringUtils.isNumeric(identifier)){
            return memberRepository.findByMobile(identifier);
        }else {
            return memberRepository.findBynickname(identifier);
        }
    }

    public Member editMember(Long id, Member editMember){
        memberRepository.findById(id)
                .map(mem -> {
                    mem.setEmail(editMember.getEmail());
                    mem.setMobile(editMember.getMobile());
                    mem.setNickname(editMember.getNickname());
                    mem.setName(editMember.getName());
                    mem.setPassword(passwordEncoder.encode(editMember.getPassword()));
                    return memberRepository.save(mem);
                });
        return memberRepository.findById(id).get();
    }

    public ChagePasswordResponse changePassword(String mobile) {
        String tempPassword = RandomStringUtils.randomAlphanumeric(15);
        Member member = memberRepository.findByMobile(mobile);
        member.setPassword(passwordEncoder.encode(tempPassword));
        return new ChagePasswordResponse(tempPassword);
    }

    public DefaultResponse checkPassword (CheckPassword checkPassword){

        Member member = getMember(checkPassword.getIdentifier());
        if(member == null){
            return new DefaultResponse(false, "Member not exist");
        }

        if(passwordEncoder.matches(checkPassword.getPassword(), member.getPassword())){
            return new DefaultResponse(true, "Password matched");
        } else {
            return new DefaultResponse(false, "Password not matched");
        }
    }

    public DefaultResponse checkDuplicateEmail (String email){

        if(memberRepository.existsByEmail(email)){
            return new DefaultResponse(false, "Email already exist");
        }else{
            return new DefaultResponse(true, "Email can create");
        }
    }

    public DefaultResponse checkDuplicateMobile (String mobile){


        if(memberRepository.existsByMobile(mobile)){
            return new DefaultResponse(false, "Mobile already exist");
        }else{
            return new DefaultResponse(true, "Mobile can create");
        }
    }

    public DefaultResponse checkDuplicateNickname (String nickname){

        if(memberRepository.existsByNickname(nickname)){
            return new DefaultResponse(false, "Nickname already exist");
        }else {
            return new DefaultResponse(true, "Nickname can create");
        }
    }
}

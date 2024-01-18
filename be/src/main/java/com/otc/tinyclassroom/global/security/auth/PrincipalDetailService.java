package com.otc.tinyclassroom.global.security.auth;

import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loginId : {}", username);

//        Member member = (Member) memberRepository.findByMemberId(username);
//        if (member != null){
//            return new PrincipalDetails(member);
//        }

        Optional<Member> member = memberRepository.findByMemberId(username);

        if (member.isPresent()) {
            return new PrincipalDetails(member.get());
        }

        return null;
    }
}

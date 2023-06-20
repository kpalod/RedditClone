package com.example.redditClone.service;

import com.example.redditClone.dto.RegisterRequest;
import com.example.redditClone.exceptions.SpringRedditException;
import com.example.redditClone.model.NotificationEmail;
import com.example.redditClone.model.User;
import com.example.redditClone.model.VerificationToken;
import com.example.redditClone.repository.UserRepository;
import com.example.redditClone.repository.VerificationTokenRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        userRepository.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please click on the below link to activate your account",user.getEmail(),"Thank You for " +
                "signing up on spring reddit, " + " please click on the below link to activate your account : "+
                "https://localhost:8080/api/auth/accountverification/"+token));

    }
    private String generateVerificationToken(User user){
        String Token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(Token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return Token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());
    }
    @Transactional
    private void fetchUserAndEnable(VerificationToken verificationToken){
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User NA"));
        user.setEnabled(true);
        userRepository.save(user);
    }
}

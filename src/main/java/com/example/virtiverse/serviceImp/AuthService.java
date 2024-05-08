package com.example.virtiverse.serviceImp;

import com.example.virtiverse.dto.ReqRes;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.OurUserRepo;

import com.example.virtiverse.util.JWTUtils;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.SybaseDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.UUID;


@AllArgsConstructor
@Service
public class AuthService {
    @Autowired
    private OurUserRepo ourUserRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserService userService ;


    @Autowired
    private JavaMailSender emailSender;


    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }



    public ReqRes signUp(ReqRes registrationRequest) {
        ReqRes resp = new ReqRes();
        try {
            String emailRegex = "^[A-Za-z0-9+_.-]+@esprit\\.tn$";
            String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

            // Validate email format
            if (!registrationRequest.getEmail().matches(emailRegex)) {
                resp.setStatusCode(400); // Bad request
                resp.setMessage("Invalid email format. Email must end with @esprit.tn");
                return resp;
            }

            // Validate password format
            if (!registrationRequest.getPassword().matches(passwordRegex)) {
                resp.setStatusCode(400); // Bad request
                resp.setMessage("Invalid password format. Password must contain at least 8 characters, including letters and numbers.");
                return resp;
            }

            // Check if email already exists
            if (ourUserRepo.existsByEmail(registrationRequest.getEmail())) {
                resp.setStatusCode(400); // Bad request
                resp.setMessage("User with this email already exists");
                return resp;
            }

            User ourUsers = new User();
            ourUsers.setEmail(registrationRequest.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            ourUsers.setRole(registrationRequest.getRole());
            ourUsers.setName(registrationRequest.getName());
            ourUsers.setPhoneNumber(registrationRequest.getPhoneNumber());

            User ourUserResult = ourUserRepo.save(ourUsers);

            if (ourUserResult != null && ourUserResult.getId() > 0) {

                JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
                mailSender.setHost("smtp.gmail.com");
                mailSender.setPort(25);
                mailSender.setUsername("darkamin22@gmail.com");
                mailSender.setPassword("ugap mhkr ouct wtnb");

                // Enable TLS/SSL
                Properties props = mailSender.getJavaMailProperties();
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtp.ssl.trust", "*");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS



                resp.setOurUsers(ourUserResult);
                // Create a simple mail message
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(registrationRequest.getEmail());
                message.setSubject("Registration Successful");
                message.setText("Dear " + registrationRequest.getName() + ",\n\nYour registration was successful.");

                // Send the email
                mailSender.send(message);

                // Set response message
                resp.setMessage("User Saved Successfully. Confirmation email sent.");

                resp.setStatusCode(200);
            }
        } catch (DataIntegrityViolationException e) {
            resp.setStatusCode(500);
            resp.setError("Error saving user: " + e.getMessage());
        }
        return resp;
    }

    public ReqRes signIn(ReqRes signinRequest){
        ReqRes response = new ReqRes();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()));
            var user = ourUserRepo.findByEmail(signinRequest.getEmail()).orElseThrow();
            userService.constructImageUrl(user);
            System.out.println("USER IS: "+ user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setId((user.getId()));
            response.setName(user.getName());
            response.setImage(user.getImage());
            response.setEmail(user.getEmail());
            response.setRole(user.getRole());

            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Signed In");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }
    public ReqRes refreshToken(ReqRes refreshTokenReqiest){
        ReqRes response = new ReqRes();
        String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
        User users = ourUserRepo.findByEmail(ourEmail).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenReqiest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }




    public ReqRes forgotPassword(String email) {
        ReqRes resp = new ReqRes();
        System.out.println(email);
            // Check if user exists with the given email
        User user = ourUserRepo.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found for email: " + email));



            // Generate a new password
            String newPassword = generateNewPassword();

            // Update user's password in the database
            user.setPassword(passwordEncoder.encode(newPassword));
            ourUserRepo.save(user);

            // Send email with the new password
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(25);
            mailSender.setUsername("darkamin22@gmail.com");
            mailSender.setPassword("ugap mhkr ouct wtnb");

            // Enable TLS/SSL
            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.ssl.trust", "*");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS



            resp.setOurUsers(user);
            // Create a simple mail message
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Password Reset");
            message.setText("Your new password is: " + newPassword);

            // Send the email
            mailSender.send(message);

            resp.setStatusCode(200); // OK
            resp.setMessage("New password has been sent to your email");


    return resp;
}
    private String generateNewPassword() {
        String newPassword = UUID.randomUUID().toString().substring(0, 8); // Random UUID as password
        return newPassword;
    }

}

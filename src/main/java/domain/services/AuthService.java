package domain.services;

import Auth.*;
import domain.models.*;
import domain.repositories.*;

import javax.ejb.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.util.logging.*;

@Local
@Stateless
public class AuthService {

    private static final int MIN_VERIFICATION_CODE = 999999;
    private static final int MAX_VERIFICATION_CODE = 100000;

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @EJB
    JWTService jwtService;

    @EJB
    UserRepository userRepository;

    public boolean register (User user) {
        if (user == null)
            return false;

        if (!user.validForRegistration())
            return false;

        try {
            // encode password with SHA256
            user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return false;
        }

        user.setVerification_code(Long.valueOf(0));
        userRepository.create(user);


        return true;
    }

    public String login (String email, String password) {
        if (email.isEmpty() || password.isEmpty())
            return null;

        String passwordHash;

        try {
            // encode password with SHA256
            passwordHash = AuthenticationUtils.encodeSHA256(password);
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }

        User u = getByUsernameAndPassword(email, passwordHash);

        if (u == null)
            return null;

        u.setVerification_code(generateVerificationCode());

        userRepository.update(u);

        try {
            sendEmail(u);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return u.getEmail();
    }

    public User getById(Long id) {
        return userRepository.getById(id);
    }

    public User getByUsernameAndPassword(String username, String password) {
        return userRepository.getByUsernameAndPassword(username, password);
    }

    public Long generateVerificationCode() {
        Random rand = new Random();
        Integer code = rand.nextInt(MIN_VERIFICATION_CODE
                - MAX_VERIFICATION_CODE + 1) + MAX_VERIFICATION_CODE;
        return Long.valueOf(code);
    }

    public void sendEmail(User user) throws MessagingException {

        String username = "javaeegamestore@gmail.com";
        String password = "gamestore!123";

         Properties props = new Properties();
         props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.smtp.host", "smtp.gmail.com");
         props.put("mail.smtp.port", "587");

         Session session = Session.getInstance(props,
                 new Authenticator() {
                     protected PasswordAuthentication getPasswordAuthentication() {
                         return new PasswordAuthentication(username, password);
                     }
                 });

         try {

             Message message = new MimeMessage(session);
             message.setFrom(new InternetAddress(username));
             message.setRecipients(Message.RecipientType.TO,
                     InternetAddress.parse(user.getEmail()));
             message.setSubject("Verification Gamestore");
             message.setText("Dear User,"
                     + "\n\n This is your verification code: "+ user.getVerification_code());

             Transport.send(message);

             System.out.println("Done");

         } catch (MessagingException e) {
             throw new RuntimeException(e);
         }

    }

    public String checkCode(User user) {
        User u = userRepository.verifyCode(user);
        if ( u != null){
            return jwtService.createJWT(u);
        }
        return null;

    }
}

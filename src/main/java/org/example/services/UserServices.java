package org.example.services;

        import org.dizitart.no2.Nitrite;
        import org.dizitart.no2.objects.ObjectRepository;
        import org.example.exceptions.*;
        import org.example.Models.User;

        import java.awt.*;
        import java.nio.charset.StandardCharsets;
        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;
        import java.util.Objects;

public class UserServices {

    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(FileSystemServices.getPathToFile("wera-users.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static ObjectRepository<User> getUserRepository() {
        return userRepository;
    }

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException, EmptyFieldsException {
        checkEmptyFields(password,username,role);
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    public static void checkRole(String role) throws InvalidRoleException{
        if(!(Objects.equals(role, "Admin") )|| (!Objects.equals(role, "Customer"))){
            throw new InvalidRoleException();
        }
    }

    public static void checkEmptyFields(String password, String username, String role ) throws EmptyFieldsException{
        if(Objects.equals(username,""))
          throw new EmptyFieldsException();
        else
            if (Objects.equals(password,""))
                throw new EmptyFieldsException();
            else
                if(!Objects.equals(role,"Admin") && !Objects.equals(role,"Customer"))
                    throw  new EmptyFieldsException();
    }

    public static void verifyUserCredentials(String username,String password,String role) throws UsernameNotFoundException, InvalidRoleException, InvalidPasswordException {
        int usernameMatched = 0;
        int passwordMatched = 0;
        int roleMatched = 0;
        for(User user : userRepository.find()){
            if(Objects.equals(username,user.getUsername())) {
                usernameMatched = 1;
                if(Objects.equals(role,user.getRole()))
                    roleMatched = 1;
            }
            if(Objects.equals(encodePassword(username,password),user.getPassword()))
                passwordMatched = 1;
        }
        if(usernameMatched == 0)
            throw new UsernameNotFoundException();
        if( roleMatched == 0 )
            throw new InvalidRoleException();
        if ( passwordMatched == 0 )
            throw new InvalidPasswordException();

    }

    public static void checkNamefield(String name) throws noNameException {
        if(Objects.equals(name,""))
            throw new noNameException();
    }

    public static void checkAdressField(String adress) throws noAdressException{
        if(Objects.equals(adress,""))
            throw new noAdressException();
    }

    public static String getLoggedUser(String username){
        for(User user:userRepository.find()){
            if (Objects.equals(username, user.getUsername()))
                return username;
        }
        return "";
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


}
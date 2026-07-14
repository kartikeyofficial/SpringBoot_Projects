package IRCTC.Service;

import IRCTC.Entities.user;
import org.apache.catalina.User;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService {
    private User user;

    private  List<user> userList;
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USER_PATH="../LocalDB/users.json";

    public UserBookingService(User user) throws IOException {
          this.user = user;
          File users = new File(USER_PATH);
          userList = objectMapper.readValue(users, new TypeReference<List<user>>() {});
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserService
        })
    }


}

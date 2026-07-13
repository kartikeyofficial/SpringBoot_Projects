package IRCTC.Service;

import IRCTC.Entities.user;
import org.apache.catalina.User;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class UserBookingService {
    private User user;

    private  List<user> userList;
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USER_PATH="../LocalDB/users.json";

    public UserBookingService(User user){
          this.user = user;
          File users = new File(USER_PATH);
          userList = objectMapper.readValue(users, new TypeReference<List<User>>() {

          });
    }


}

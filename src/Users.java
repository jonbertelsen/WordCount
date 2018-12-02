import java.util.HashMap;
import java.util.Map;

public class Users {

    Map<String,String> userCredentials = new HashMap<String,String>();

    public Users() {
        populateWithUsers();
    }

    private void populateWithUsers() {
        userCredentials.put("jon@cph.dk","1234");
        userCredentials.put("nik@cph.dk","1234");
        userCredentials.put("ole@cph.dk","1234");
        userCredentials.put("hans@cph.dk","1234");
        userCredentials.put("birgit@cph.dk","1234");
        userCredentials.put("ida@cph.dk","1234");
        userCredentials.put("tim@cph.dk","1234");
    }

    public boolean isValidUser(String userName, String userPassword){
        String password = "";
        password = userCredentials.get(userName);
        if (password != null){
            return password.equals(userPassword);
        } else
            return false;
    }


}

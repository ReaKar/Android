package DataSources.AndroidClientImpl;

/**
 * Created by giannis on 1/25/16.
 */
public class AndroidClient {
    private String username;
    private String password;

    public AndroidClient(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AndroidClient() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

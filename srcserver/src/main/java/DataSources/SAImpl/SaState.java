package DataSources.SAImpl;

/**
 * Created by giannis on 1/24/16.
 */

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SaState {
    private String hashkey;
    private String state;

    public SaState(String hashkey, String state) {
        this.hashkey = hashkey;
        this.state = state;
    }

    public SaState() {
    }

    public String getHashkey() {
        return hashkey;
    }

    public void setHashkey(String hashkey) {
        this.hashkey = hashkey;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString(){
        return "Name: " + this.hashkey + ", state: " + this.state;
    }
}

package freewilder.rockme.com.freewilder.pojo;

/**
 * Created by su on 24/8/15.
 */
public class SetGetContact {
    String Name;
    String EmailID;
    String PhotoUri;
    boolean check =false;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public SetGetContact() {

    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhotoUri() {
        return PhotoUri;
    }

    public void setPhotoUri(String photoUri) {
        PhotoUri = photoUri;
    }
}

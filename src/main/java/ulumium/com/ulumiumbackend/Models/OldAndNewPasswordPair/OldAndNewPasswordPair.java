package ulumium.com.ulumiumbackend.Models.OldAndNewPasswordPair;

public class    OldAndNewPasswordPair {
    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public OldAndNewPasswordPair setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public OldAndNewPasswordPair setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }
}

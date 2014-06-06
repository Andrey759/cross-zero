package Model.User;

public class AIUser extends CurUser {

    public AIUser() {
        set("Компьютер", "");
    }

    @Override
    public void set(String nick, String hpass) {
        super.set("Компьютер", "");
    }

}

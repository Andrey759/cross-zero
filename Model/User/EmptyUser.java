package Model.User;

public class EmptyUser extends CurUser {

    @Override
    public String getNick() {
        return "Игрок 2";
    }

    @Override
    public int getWins() {
        return 0;
    }

    @Override
    public int getLoses() {
        return 0;
    }

    @Override
    public void addWin() {
        //Ntohing
    }

    @Override
    public void addLose() {
        //Ntohing
    }

    @Override
    public void set(String nick, String hpass) {
        //Ntohing
    }

}

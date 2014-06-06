package Model.User;

import java.util.Observable;

import Model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import Controller.Core;
import Controller.Classes.FileClass;

public class CurUser extends User {
    
    private String nick = "";
    private String hpass = "";
    private int wins = 0;
    private int loses = 0;
    private final String dir = "Users";
    private FileClass file = new FileClass();

    @Override
    public String getNick() {
        return nick;
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public void addWin() {
        wins++;
        save();
    }

    public void addLose() {
        loses++;
        save();
    }
    
    public void set(String nick, String hpass) {
        if(nick.length() > 0) {
            String filename = dir + "\\" + nick + ".txt";
            String filehpass;
            this.nick = nick;
            this.hpass = hpass;
            file.makeDir(dir);
            if (!file.issetFile(filename)) { //Если нету такого пользователя - создаём
                writeNewUser();
                Core.getIntance().successfulLogin();    //Входим
            } else {
                file.openRd(filename);
                if ((filehpass = file.readStr()) == null) writeNewUser();     //Если ошабка чтения файла,
                if ((wins = file.readUnsignedInt()) == -1) writeNewUser();   //то создаём пользователя заново
                if ((loses = file.readUnsignedInt()) == -1) writeNewUser();
                if (hpass.trim().equals(filehpass.trim())) {
                    Model.getIntance().empty();
                    Core.getIntance().successfulLogin();    //Если пароль правильный, то входим
                } else {
                    file.close(); //Если пароль неверный
                    empty();
                    Core.getIntance().update();
                }
            }
        } else
            empty();
    }
    
    private void writeNewUser() {
        wins = 0;
        loses = 0;
        save();
    }

    public void save() {
        file.openWr(dir+"\\"+getNick()+".txt");
        file.write(hpass);
        file.write(getWins());
        file.write(getLoses());
        file.close();
    }

    private void empty() {
        nick = "";
        hpass = "";
        wins = 0;
        loses = 0;
    }
    
}

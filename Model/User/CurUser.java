package Model.User;

import java.util.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import Controller.Core;
import Controller.Classes.FileClass;

public class CurUser extends User {
    
    private String nick = null;
    private String hpass = null;
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
    }

    public void addLose() {
        loses++;
    }
    
    public void set(String nick, String hpass) {
        String filename = dir+"\\"+nick+".txt";
        String filehpass;
        this.nick = nick;
        this.hpass = hpass;
        file.makeDir(dir);
        if(!file.issetFile(filename)) { //Если нету такого пользователя - создаём
            writeNewUser(filename, hpass);
            Core.getIntance().successfulLogin();    //Входим
        } else {
            file.openRd(filename);
            if((filehpass = file.readStr()) == null) writeNewUser(filename, hpass);     //Если ошабка чтения файла,
            if((wins = file.readUnsignedInt()) == -1)  writeNewUser(filename, hpass);   //то создаём пользователя заново
            if((loses = file.readUnsignedInt()) == -1)  writeNewUser(filename, hpass);
            if(hpass.trim().equals(filehpass.trim())) {
                Core.getIntance().successfulLogin();    //Если пароль правильный, то входим
            } else {
                file.close(); //Если пароль неверный
                //Core.getIntance().update();
            }
        }
    }
    
    private void writeNewUser(String filename, String hpass) {
        wins = 0;
        loses = 0;
        file.openWr(filename);
        file.write(hpass);
        file.write(wins);
        file.write(loses);
        file.close();
    }

    private void save() {
        String filename = dir+"\\"+getNick()+".txt";
        writeNewUser(filename, hpass);
    }
    
}

package Model.User;

import Controller.Classes.ExceptionLog;
import Controller.Classes.Listener;
import Controller.Core;
import Controller.Classes.FileClass;

import java.util.ArrayList;
import java.util.List;

public class CurUser extends User {
    
    private String nick = "";
    private String hpass = "";
    private int wins = 0;
    private int loses = 0;
    private final String dir = "Users";
    private FileClass file = new FileClass();
    private List<Listener> listeners = new ArrayList<>();

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
            String filehpass = "";
            this.nick = nick;
            this.hpass = hpass;
            file.makeDir(dir);
            try {
                if (!file.issetFile(filename)) { //Если нету такого пользователя - создаём
                    writeNewUser();
                    Core.getIntance().successfulLogin();    //Входим
                } else {
                    file.openRd(filename);
                    if (!nick.equals("Компьютер")) filehpass = file.readStr();
                    if (filehpass == null) {                    //Если ошабка чтения файла,
                        writeNewUser();                         //то создаём пользователя заново
                        ExceptionLog.println("Ошибка: Не удалось прочитать из файла хэш пароля пользователя " + nick + ". Создан новый профиль пользователя.");
                        return;
                    }
                    if ((wins = file.readUnsignedInt()) == -1) {
                        writeNewUser();
                        ExceptionLog.println("Ошибка: Не удалось прочитать из файла количество побед пользователя " + nick + ". Создан новый профиль пользователя.");
                        return;
                    }
                    if ((loses = file.readUnsignedInt()) == -1) {
                        writeNewUser();
                        ExceptionLog.println("Ошибка: Не удалось прочитать из файла количество проигрышей пользователя " + nick + ". Создан новый профиль пользователя.");
                        return;
                    }
                    file.close();
                    if (!hpass.trim().equals(filehpass.trim()))
                        empty();                                   //Если пароль неверный, то пользователь пустой
                    notifyListeners();
                }
            } catch (Exception e) {
                empty();
                ExceptionLog.println("Ошибка: Не удалось обработать пользователя " + nick);
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
        try {
            file.openWr(dir + "\\" + getNick() + ".txt");
            file.write(hpass);
            file.write(getWins());
            file.write(getLoses());
            file.close();
        } catch (Exception e) {
            empty();
            ExceptionLog.println("Ошибка: Не удалось записать в файл пользователя " + getNick());
        }
    }

    public void empty() {
        nick = "";
        hpass = "";
        wins = 0;
        loses = 0;
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        if(!listeners.isEmpty())
            for(Listener listener : listeners)
                listener.update();
    }
    
}

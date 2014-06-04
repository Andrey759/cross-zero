package Controller.Classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Hash {

    private Hash() { }

    public static String md5(String word) {
        String md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(word.getBytes());
            byte[] bytes = md.digest();
            //char[] a = new char[100];
            //for(int i = 0; i < bytes.length; i++) a[i] = (char)bytes[i];
            Formatter formatter = new Formatter();
            for (byte b : bytes)
                formatter.format("%02x", b);
            md5 = formatter.toString();
            //md5 = String.copyValueOf(a).trim();
        } catch (NoSuchAlgorithmException e) {
            ExceptionLog.println("Ошибка взятия md5 от " + word);
            ExceptionLog.println(e);
        }
        return md5;
    }

}

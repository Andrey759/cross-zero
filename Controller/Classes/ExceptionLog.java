package Controller.Classes;

public class ExceptionLog extends Exception {

    private String errorText;

    public ExceptionLog(String errorText) {
        this.errorText = errorText;
    }

    public void print() {
        println(errorText);
    }

    public static void println(String errorText) {
        System.out.println(errorText);
    }

    public static void println(Exception error) {
        System.out.println(error.toString());
    }

}
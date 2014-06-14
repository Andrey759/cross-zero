package Controller.Classes;

import View.ENum.EForm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimerClass extends Thread {

    private Date startTime = new Date();
    private Date time = new Date();
    private boolean run = false;
    private boolean closed = false;
    private boolean firstStart = true;
    private List<Listener> listeners = new ArrayList<>();

    public TimerClass() {
        super();
        time.setTime(0);
    }

    public TimerClass(int hourh, int minutes, int seconds) {
        super();
        time.setTime(0);
        time.setHours(hourh);
        time.setMinutes(minutes);
        time.setSeconds(seconds);
        startTime = (Date) time.clone();
    }

    public TimerClass(int minutes, int seconds) {
        super();
        time.setTime(0);
        time.setMinutes(minutes);
        time.setSeconds(seconds);
        startTime = (Date) time.clone();
    }

    public TimerClass(int seconds) {
        super();
        time.setTime(0);
        time.setSeconds(seconds);
        startTime = (Date) time.clone();
    }

    @Override
    public void run() {
        try {
            while(!closed) {
                if (run) {
                    this.sleep(1000);
                    while (run) {
                        time.setSeconds(time.getSeconds() - 1);
                        notifyListeners();
                        this.sleep(1000);
                    }
                }
                this.sleep(100);
            }
        } catch (InterruptedException e) {
            ExceptionLog.println("Ошибка: Не удалось вызвать метод sleep для потока таймера.");
            ExceptionLog.println(e);
        }
    }

    @Override
    public void start() {
        run = true;
        if(firstStart) {
            firstStart = false;
            super.start();
        }
    }

    public void finish() {
        run = false;
    }

    public void close() {
        this.finish();
        closed = true;
    }

    public int getHours() {
        return time.getHours();
    }

    public int getMinutes() {
        return time.getMinutes();
    }

    public int getSeconds() {
        return time.getSeconds();
    }

    public long getTime() {
        return time.getTime();
    }

    public void setTime(int hourh, int minutes, int seconds) {
        time.setTime(0);
        time.setHours(hourh);
        time.setMinutes(minutes);
        time.setSeconds(seconds);
        startTime = (Date) time.clone();
    }

    public void setTime(int minutes, int seconds) {
        setTime(0, minutes, seconds);
    }

    public void setTime(int seconds) {
        setTime(0, 0, seconds);
    }

    public void setTimeAndFinich(int hoursd, int minutes, int seconds) {
        finish();
        setTime(hoursd, minutes, seconds);
    }

    public void setTimeAndFinish(int minutes, int seconds) {
        setTimeAndFinich(0, minutes, seconds);
    }

    public void setTimeAndFinish(int seconds) {
        setTimeAndFinich(0, 0, seconds);
    }

    public void reset() {
        time = (Date) startTime.clone();
    }

    public void resetAndFinish() {
        finish();
        reset();
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

package day06;

import java.security.DigestException;

public class DeskLamp {
    // 필드
    private boolean isOn;

    // 메서드
    public DeskLamp(boolean isOn) { this.isOn = isOn;}
    public DeskLamp() { this(false); }

    public DeskLamp turnOn() {
        isOn = true;
        return this;
    }

    public DeskLamp turnOff() {
        isOn = false;
        return this;
    }

    // 출력
    public void printStatus() {
            System.out.println("Status: " + (isOn ? "On" : "Off"));
    }
}

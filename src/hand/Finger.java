package hand;

/**
 * Created by Alex on 09.06.2015.
 */
public class Finger {
    private String name;
    public static enum Finger_State {
        OFF, ON
    }

    private Finger_State fingerState = Finger_State.OFF;

    private int length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Finger_State getFingerState() {
        return fingerState;
    }

    public void setFingerState(Finger_State fingerState) {
        this.fingerState = fingerState;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

import android.widget.Switch;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Controller {
    boolean was_a = false;
    boolean was_y = false;
    boolean was_x = false;
    boolean was_b = false;
    boolean was_lsb = false;
    boolean was_rsb = false;
    boolean was_rb = false;
    boolean was_lb = false;
    boolean was_dup = false;
    boolean was_ddown = false;
    boolean was_dright = false;
    boolean was_dleft    = false;
    Gamepad GamePad;
    public Controller(Gamepad G){
        GamePad = G;

    }
    public boolean CheckButton(String Button){
        switch (Button){
            case "a":
            {
                return(this.GamePad.a&&!was_a);
            }
            case "y": return(this.GamePad.y&&!was_y);
            case "x": return(this.GamePad.x&&!was_x);
            case "b": return(this.GamePad.b&&!was_b);
            case "lsb": return(this.GamePad.left_stick_button&&!was_lsb);
            case "rsb": return(this.GamePad.right_stick_button&&!was_rsb);
            case "rb": return(this.GamePad.right_bumper&&!was_rb);
            case "lb": return(this.GamePad.left_bumper&&!was_lb);
            case "dup": return(this.GamePad.dpad_up&&!was_dup);
            case "ddown": return(this.GamePad.dpad_down&&!was_ddown);
            case "dright": return(this.GamePad.dpad_right&&!was_dright);
            case "dleft": return(this.GamePad.dpad_left&&!was_dleft);
        }
        return(false);
    }
    public boolean GetButtonState(String Button){
        switch (Button){
            case "a": return(this.GamePad.a);
            case "y": return(this.GamePad.y);
            case "x": return(this.GamePad.x);
            case "b": return(this.GamePad.b);
            case "lsb": return(this.GamePad.left_stick_button);
            case "rsb": return(this.GamePad.right_stick_button);
            case "rb": return(this.GamePad.right_bumper);
            case "lb": return(this.GamePad.left_bumper);
            case "dup": return(this.GamePad.dpad_up);
            case "ddown": return(this.GamePad.dpad_down);
            case "dright": return(this.GamePad.dpad_right);
            case "dleft": return(this.GamePad.dpad_left);
        }
        return(false);
    }

    public float GetAnalogs(String Analog){
        switch (Analog){
            case "Lx":return(GamePad.left_stick_x);
            case "Ly":return(GamePad.left_stick_y);
            case "Rx":return(GamePad.right_stick_x);
            case "Ry":return(GamePad.right_stick_y);
            case "Rt":return(GamePad.right_trigger);
            case "Lt":return(GamePad.left_trigger);
        }
        return((float) 0.0);
    }
    public void UpdateAll(){
        this.was_a = GamePad.a;
        this.was_y = GamePad.y;
        this.was_x = GamePad.x;
        this.was_b = GamePad.b;
        this.was_lsb = GamePad.left_stick_button;
        this.was_rsb = GamePad.right_stick_button;
        this.was_rb = GamePad.right_bumper;
        this.was_lb = GamePad.left_bumper;
        this.was_dup = GamePad.dpad_up;
        this.was_ddown = GamePad.dpad_down;
        this.was_dright = GamePad.dpad_right;
        this.was_dleft = GamePad.dpad_left;
    }
}

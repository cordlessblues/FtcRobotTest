import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="ControllerDebug", group="Carl")

public class CarlControllerTest extends OpMode{
    //get the runtime i dunno

    public Controller driver = null;
    private final ElapsedTime runtime = new ElapsedTime();
    //import Hardware interface classes
    @Override
    public void init () {
        telemetry.addData("Status", "Please wait robo is starting up");
        driver = new Controller(gamepad1);
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).


        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips



        // Tell the driver that initialization is complete.
        telemetry.clearAll();
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit START
     */
    @Override
    public void init_loop () {

    }

    /*
     * Code to run ONCE when the driver hits START
     */
    @Override
    public void start () {
        runtime.reset();
    }


    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */
    public int i;
    public int c;
    public int a;
    @Override
    public void loop () {

        if(driver.toggleButtonState(Controller.Button.a)){
            i++;
        }
        if(driver.onButtonHold(Controller.Button.a)){
            a+=1;
        }
        if(driver.onButtonPress(Controller.Button.a)){
           c+=1;
       }
        if(driver.onButtonPress(Controller.Button.b)){
            i=0;
            c=0;
            a=0;
        }


        telemetry.addData("driver.getPressState(a): ",i);
        telemetry.addData("driver.ToggleButtonState(a): ",c);
        telemetry.addData("driver.GetToggleState(a): ",a);
        driver.updateAll();
    }

    /*-+

     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop () {

    }
}

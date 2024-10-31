/*
 * ┌──────────────────────────────────────────────────────────────────────────────────────────┐
 * │███╗   ███╗ █████╗ ██████╗ ███████╗        ██████╗ ██╗   ██╗                              │
 * │████╗ ████║██╔══██╗██╔══██╗██╔════╝        ██╔══██╗╚██╗ ██╔╝                              │
 * │██╔████╔██║███████║██║  ██║█████╗          ██████╔╝ ╚████╔╝                               │
 * │██║╚██╔╝██║██╔══██║██║  ██║██╔══╝          ██╔══██╗  ╚██╔╝                                │
 * │██║ ╚═╝ ██║██║  ██║██████╔╝███████╗        ██████╔╝   ██║                                 │
 * │╚═╝     ╚═╝╚═╝  ╚═╝╚═════╝ ╚══════╝        ╚═════╝    ╚═╝                                 │
 * │                                                                                          │
 * │ ██████╗ █████╗ ██████╗ ██╗                ██╗  ██╗██╗██╗     ██╗      █████╗ ███╗   ███╗ │
 * │██╔════╝██╔══██╗██╔══██╗██║                ██║  ██║██║██║     ██║     ██╔══██╗████╗ ████║ │
 * │██║     ███████║██████╔╝██║                ███████║██║██║     ██║     ███████║██╔████╔██║ │
 * │██║     ██╔══██║██╔══██╗██║                ██╔══██║██║██║     ██║     ██╔══██║██║╚██╔╝██║ │
 * │╚██████╗██║  ██║██║  ██║███████╗           ██║  ██║██║███████╗███████╗██║  ██║██║ ╚═╝ ██║ │
 * │ ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝           ╚═╝  ╚═╝╚═╝╚══════╝╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝ │
 * └──────────────────────────────────────────────────────────────────────────────────────────┘
 */

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;



@TeleOp(name="ControllerDebug", group="Carl")

public class CarlControllerTest extends OpMode{
    //get the runtime i dunno
    public Controller driver = null;
    private final ElapsedTime runtime = new ElapsedTime();
    public CRServo Seervo = null;
    //import Hardware interface classes
    @Override
    public void init () {
        telemetry.addData("Status", "Please wait robo is starting up");
        driver = new Controller(gamepad1);
        Seervo = hardwareMap.get(CRServo.class,"Claw");


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
    public void start () {runtime.reset();}


    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */
    @Override
    public void loop () {
        double ServoPow = 0.0;
        double pow = 0.1;

        telemetry.addData("Debug Servo | ", ServoPow);
        //Seervo.setPower(ServoPow);

        driver.updateAll();
    }

    /*-+

     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop () {

    }
}

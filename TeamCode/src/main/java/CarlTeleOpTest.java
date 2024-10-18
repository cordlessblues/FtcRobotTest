
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
/*
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@TeleOp(name="Carls OpMoode", group="Carl")

public class CarlTeleOpTest extends OpMode{
       // Declare OpMode members.
        public Controller driver = null;
        public DriveTrain driveTrain;
        /*
         * Code to run ONCE when the driver hits INIT
         */
        @Override
        public void init () {
            telemetry.addData("Status", "Initialized");
            driveTrain = new DriveTrain(this);
            driver = new Controller(gamepad1);

            // Initialize the hardware variables. Note that the strings used here as parameters
            // to 'get' must correspond to the names assigned during the robot configuration
            // step (using the FTC Robot Controller app on the phone).
            // Tell the driver that initialization is complete.
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
            driveTrain.DebugWheels(12.0,0.5);
        }

        /*
         * Code to run REPEATEDLY after the driver hits START but before they hit STOP
         */
        @Override
        public void loop () {
            double Power = 1.0;

            double x = -driver.getAnalogValue(Controller.Joystick.LeftX)   *  1.5;
            double y = driver.getAnalogValue(Controller.Joystick.LeftY)    *  1.5;
            double rx = driver.getAnalogValue(Controller.Joystick.RightX)  /  1.2;

            driveTrain.KenematicMove(y,x,rx,Power);
        }

        /*-+

         * Code to run ONCE after the driver hits STOP
         */
        @Override
        public void stop () {

        }

    }

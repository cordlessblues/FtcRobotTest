
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
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
        public arm Arm = null;

        //public DriveTrain driveTrain;
        //public DcMotor LFDrive = null;
        //public CRServo Claw = null;

        /*
         * Code to run ONCE when the driver hits INIT
         */
        @Override
        public void init () {
            telemetry.addData("Status", "Initialized");
            telemetry.addData("Status", "Still warming up, sir.");
            Arm = new arm(this,0.5);
            //Claw = hardwareMap.get(CRServo.class, "Claw");
            //driveTrain = new DriveTrain(this);
            telemetry.addData("Status", "Warming up? THE SUN IS WARMING UP?!");

            driver = new Controller(gamepad1);//219A

            telemetry.addData("Status", "Just a little more and... ");
            // Initialize the hardware variables. Note that the strings used here as parameters
            // to 'get' must correspond to the names assigned during the robot configuration
            // step (using the FTC Robot Controller app on the phone).
            // Tell the driver that initialization is complete.
            //driveTrain.DebugWheels(12.0,0.5);

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
            //driveTrain.DebugWheels(12.0,0.5);
        }

        /*
         * Code to run REPEATEDLY after the driver hits START but before they hit STOP
         */
        @Override
        public void loop () {
            //double ClawPower = 0.0;


            if (driver.onButtonPress(Controller.Button.dPadUp)){
                Arm.RunToTarget(2190);
            }
            if (driver.onButtonPress(Controller.Button.dPadDown)) {
                Arm.RunToTarget(0);
            }


            /*
            if(driver.onButtonHold(Controller.Button.a)){ClawPower = 1.0;}
            else if(driver.onButtonHold(Controller.Button.b)){ClawPower = -1.0;}
            else{ClawPower = 0.0;}
            Claw.setPower(ClawPower);
            */
            telemetry.addData("Current Pip           | ",Arm.Pips);
            telemetry.addData("RightArmMotor Power   | ",Arm.RightArmMotor.getPower());
            telemetry.addData("RightArmMotor encoder | ",Arm.RightArmMotor.getCurrentPosition());
            telemetry.addLine();
            telemetry.addData("LeftArmMotor Power    | ",Arm.LeftArmMotor.getPower());
            telemetry.addData("LeftArmMotor encoder  | ",Arm.LeftArmMotor.getCurrentPosition());
            driver.updateAll();
            Arm.MotorCheck();
        }

        /*-+

         * Code to run ONCE after the driver hits STOP
         */
        @Override
        public void stop () {

        }

    }

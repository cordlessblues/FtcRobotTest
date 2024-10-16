
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;
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

@TeleOp(name="Carls  OpMoode", group="Carl")

public class CarlTeleOpTest extends OpMode{
       // Declare OpMode members.
        private ElapsedTime runtime = new ElapsedTime();
        private DcMotor LFDrive = null;
        private DcMotor RFDrive = null;
        private DcMotor LBDrive = null;
        private DcMotor RBDrive = null;
        private Servo servo = null;
        /*
         * Code to run ONCE when the driver hits INIT
         */
        @Override
        public void init () {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        LFDrive = hardwareMap.get(DcMotor.class, "fl");
        RFDrive = hardwareMap.get(DcMotor.class, "fr");
        LBDrive = hardwareMap.get(DcMotor.class, "bl");
        RBDrive = hardwareMap.get(DcMotor.class, "br");
        servo = hardwareMap.get(Servo.class,"s");
        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        LFDrive.setDirection(DcMotor.Direction.REVERSE);
        LBDrive.setDirection(DcMotor.Direction.REVERSE);
        RFDrive.setDirection(DcMotor.Direction.FORWARD);
        RBDrive.setDirection(DcMotor.Direction.FORWARD);
        LFDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LBDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RFDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RBDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Tell the driver that initialization is complete.
            servo.setPosition(-1.0);
        telemetry.addData("Status", "Initialized");
    }

        /*
         * Code to run REPEATEDLY after the driver hits INIT, but before they hit START
         */
        @Override
        public void init_loop () {


            /*
            LFDrive.setPower(0.2);
            RFDrive.setPower(0.2);
            LBDrive.setPower(0.2);
            RBDrive.setPower(0.2);
            */

        }

        /*
         * Code to run ONCE when the driver hits START
         */
        @Override
        public void start () {
            runtime.reset();
            DebugWheels(12.0,0.5);
        }


        public void DebugWheels(double Dist, double Pow){
            double mm = Dist * (25.4 / 1);
            int Counts = (int) (CalcMotorRatio(100,537.7) * mm);
            telemetry.addData("CurrentPowerLevel",Pow);
            telemetry.addData("CurrentDist (IN | MM)",Dist+" | "+mm);
            telemetry.addData("Counts",Counts);

            RunToTargetAndWait(RFDrive,Counts,Pow);
            RunToTargetAndWait(LFDrive,Counts,Pow);
            RunToTargetAndWait(RBDrive,Counts,Pow);
            RunToTargetAndWait(LBDrive,Counts,Pow);
        }

        public void MoveToPos(double Dist, double Pow){
            double mm = Dist * (25.4 / 1);
            int Counts = (int) (CalcMotorRatio(100,537.7) * mm);
            telemetry.addData("CurrentPowerLevel",Pow);
            telemetry.addData("CurrentDist (IN | MM)",Dist+" | "+mm);
            telemetry.addData("Counts",Counts);

            RunToTarget(RFDrive,Counts,Pow);
            RunToTarget(LFDrive,Counts,Pow);
            RunToTarget(RBDrive,Counts,Pow);
            RunToTarget(LBDrive,Counts,Pow);
        }

        public void RunToTargetAndWait(DcMotor Drive,int Counts, double Pow){
            Drive.setTargetPosition(Drive.getCurrentPosition() + Counts);
            Drive.setPower(Pow);
            Drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while(Drive.isBusy()){}
        }
        public void RunToTarget(DcMotor Drive,int Counts, double Pow){
            Drive.setTargetPosition(Drive.getCurrentPosition() + Counts);
            Drive.setPower(Pow);
            Drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        public int CalcMotorRatio(double C,Double PPR){
            double CircPi = C * Math.PI;
            return((int) ((1 / CircPi) * (PPR)));
        }







        /*
         * Code to run REPEATEDLY after the driver hits START but before they hit STOP
         */
        @Override
        public void loop () {
            // Setup a variable for each drive wheel to save power level for telemetry`

            DebugWheels(12,0.5);


           // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double Power = 1.0;

            double x = -gamepad1.left_stick_x * 1.5;
            double y = gamepad1.left_stick_y * 1.5;
            double rx = gamepad1.right_stick_x/1.2;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), Power);
            double frontLeftPower = (y + x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;

            // Send calculated power to wheels
            LFDrive.setPower(frontLeftPower);
            LBDrive.setPower(backLeftPower);
            RFDrive.setPower(frontRightPower);
            RBDrive.setPower(backRightPower);
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front Motors", "Front Left (%.2f), Front Right (%.2f)", frontLeftPower, frontRightPower);
            telemetry.addData("Front Motors", "Front Left (%.2f), Front Right (%.2f)", frontLeftPower, frontRightPower);
            telemetry.addData("Back  Motors", "Back  Left (%.2f), Back  Right (%.2f)", backLeftPower, backRightPower);
        }

        /*-+

         * Code to run ONCE after the driver hits STOP
         */
        @Override
        public void stop () {

        }

    }


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.Math;

public class DriveTrain{
    public DcMotor LFDrive = null;
    public DcMotor RFDrive = null;
    public DcMotor LBDrive = null;
    public DcMotor RBDrive = null;

    public DriveTrain(OpMode op) {



        LFDrive = op.hardwareMap.get(DcMotor.class, "fl");
        RFDrive = op.hardwareMap.get(DcMotor.class, "fr");
        LBDrive = op.hardwareMap.get(DcMotor.class, "bl");
        RBDrive = op.hardwareMap.get(DcMotor.class, "br");


        LFDrive.setDirection(DcMotor.Direction.REVERSE);
        LBDrive.setDirection(DcMotor.Direction.REVERSE);
        RFDrive.setDirection(DcMotor.Direction.FORWARD);
        RBDrive.setDirection(DcMotor.Direction.FORWARD);
        LFDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LBDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RFDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RBDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
    public void MoveToPos(double Dist, double Pow){
        double mm = Dist * (25.4 / 1);
        int Counts = (int) (CalcMotorRatio(100,537.7) * mm);

        RunToTarget(RFDrive,Counts,Pow);
        RunToTarget(LFDrive,Counts,Pow);
        RunToTarget(RBDrive,Counts,Pow);
        RunToTarget(LBDrive,Counts,Pow);
    }

    public void DebugWheels(double Dist, double Pow){
        double mm = Dist * (25.4 / 1);
        int Counts = (int) (CalcMotorRatio(100,537.7) * mm);


        RunToTargetAndWait(RFDrive,Counts,Pow);
        RunToTargetAndWait(LFDrive,Counts,Pow);
        RunToTargetAndWait(RBDrive,Counts,Pow);
        RunToTargetAndWait(LBDrive,Counts,Pow);
    }
    public void KenematicMove(double y, double x, double turn, double Power){
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), Power);
        double frontLeftPower = (y + x + turn) / denominator;
        double frontRightPower = (y - x - turn) / denominator;
        double backRightPower = (y + x - turn) / denominator;
        double backLeftPower = (y - x + turn) / denominator;

        LFDrive.setPower(frontLeftPower);
        LBDrive.setPower(backLeftPower);
        RFDrive.setPower(frontRightPower);
        RBDrive.setPower(backRightPower);
    }
    // Send calculated power to wheels

}

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DriveTrain{
    public DriveTrain(OpMode op) {
        DcMotor LFDrive = null;
        DcMotor RFDrive = null;
        DcMotor LBDrive = null;
        DcMotor RBDrive = null;


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
}
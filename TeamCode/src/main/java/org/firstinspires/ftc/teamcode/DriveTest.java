package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by owner on 10/21/2017.
 */
@TeleOp(name = "Drive", group = "Test")
public class DriveTest extends LinearOpMode {

    //DriveTrain
    DcMotor right;
    DcMotor left;
    double driveX;
    double driveY;
    double expoCurve;

    public void runOpMode(){

        roboInit();

        waitForStart();

        while (opModeIsActive()){

            drive();

        }

    }

    public void roboInit(){

        //DriveTrain
        right     = hardwareMap.dcMotor.get("RIGHT_MOTOR");
        left      = hardwareMap.dcMotor.get("LEFT_MOTOR");
        driveX    = 0.0;
        driveY    = 0.0;
        expoCurve = 1.0;

    }

    public void drive(){

        driveX = gamepad1.left_stick_x;
        driveY = gamepad1.left_stick_y;

        arcadeDrive(driveX, driveY);

    }

    public void arcadeDrive(double x, double y){

        right.setPower(expo(constrain(y + x),expoCurve));
        left.setPower(expo(constrain(-y + x),expoCurve));

    }

    double expo(double x, double a){

        double y = x;
        y = a * Math.pow(y, 3) + (1-a)*y;
        return y;

    }

    double constrain(double x){

        double speed;
        speed = x;

        speed = Range.clip(speed, -1, 1);

        return speed;

    }

}

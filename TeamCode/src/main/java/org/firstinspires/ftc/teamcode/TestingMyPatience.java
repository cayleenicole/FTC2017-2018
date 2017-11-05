package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by owner on 10/21/2017.
 */
@TeleOp(name = "TestingMyPatience", group = "Cayles")
@Disabled
public class TestingMyPatience extends LinearOpMode {

    //DriveTrain
    DcMotor right;
    DcMotor left;
    double driveX;
    double driveY;
    double expoCurve;
    double power;
    boolean speedUpButton;

    //Arm Mech
    DcMotor extendingArm;
    DcMotor elevatingArm;
    int currentArmEncoder;
    int armEncoder;
    int elevating;

    //Relic
    Servo relic;

    /*//Glyph
    Servo glyphOne;
    Servo glyphTwo;*/

    //Wedges
    Servo dropDownWedge1;
    Servo dropDownWedge2;
    double dropDownPosIn;
    double dropDownPosOut;
    double dropDownButtonPos;
    boolean dropDownCurrent;
    boolean dropDownPrevious;
    boolean dropDownButton;

    //Time
    double currentTime;
    double roundTime;
    double currentRoundTime;

    public void runOpMode(){

        roboInit();

        waitForStart();

        while (opModeIsActive()&&  getRuntime() - currentRoundTime < roundTime){

            drive();
            armMech();
            //dropDownMech();
            debug();

        }

    }

    public void roboInit(){

        //DriveTrain
        right     = hardwareMap.dcMotor.get("RIGHT_MOTOR");
        left      = hardwareMap.dcMotor.get("LEFT_MOTOR");
        driveX        = 0.0;
        driveY        = 0.0;
        expoCurve     = 1.0;
        power         = 0.0;
        speedUpButton = false;


        //ArmMech
        extendingArm = hardwareMap.dcMotor.get("EXTENDING");
        elevatingArm = hardwareMap.dcMotor.get("ELEVATING");

        //RelicMech
        relic = hardwareMap.servo.get("RELIC");

        /*//Glyph
        glyphOne = hardwareMap.servo.get("GLYPH1");
        glyphTwo = hardwareMap.servo.get("GLYPH2");*/

        //DropDownWedge
        /*dropDownWedge1 = hardwareMap.servo.get("DROP_DOWN_WEDGE1");
        dropDownWedge2 = hardwareMap.servo.get("DROP_DOWN_WEDGE2");
        dropDownPosIn     = 0.3;
        dropDownPosOut    = 0.75;
        dropDownButtonPos = dropDownPosIn;
        dropDownCurrent   = false;
        dropDownPrevious  = false;
        dropDownButton    = false;*/

        //Time
        roundTime        = 120.0;
        currentRoundTime = 0.0;

        currentArmEncoder = armEncoder;

    }

    public void drive(){

        driveX = gamepad1.left_stick_x;
        driveY = gamepad1.left_stick_y;
        speedUpButton = gamepad1.left_bumper;

        arcadeDrive(driveX, driveY);

    }

    public void arcadeDrive(double x, double y){

            right.setPower(expo(constrain(y + x), expoCurve)*power);
            left.setPower(expo(constrain(-y + x), expoCurve)*power);

        if(speedUpButton){

            power = 1.0;

        } else {

            power = 0.5;

        }

    }

    public void armMech(){

        currentArmEncoder = elevatingArm.getCurrentPosition();
        extendingArm.setPower(gamepad1.right_stick_x);
        elevatingArm.setPower(gamepad1.right_stick_y);

        if(currentArmEncoder - armEncoder >= elevating){

            extendingArm.setPower(-1.0);

        } else if(currentArmEncoder - armEncoder < elevating){

            extendingArm.setPower(0.0);

        }

    }

   public void dropDownMech(){

        dropDownButton = gamepad1.right_bumper;

        if(dropDownCurrent && !dropDownPrevious && dropDownWedge1.getPosition() == dropDownPosIn){

            dropDownButtonPos = dropDownPosOut;

        } else if (dropDownCurrent && !dropDownPrevious && dropDownWedge1.getPosition() == dropDownPosOut){

            dropDownButtonPos = dropDownPosIn;

        }

        dropDownPrevious = dropDownCurrent;
        dropDownWedge1.setPosition(dropDownButtonPos);
        dropDownWedge2.setPosition(dropDownButtonPos);

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

    public void debug(){

        //DriveTrain
        telemetry.addData("RIGHT_MOTOR", right.getPower());
        telemetry.addData("LEFT_MOTOR", left.getPower());

        //DropDownWedges
        telemetry.addData("DROP_DOWN", dropDownWedge1.getPosition());
        telemetry.addData("DROP_DOWN2", dropDownWedge2.getPosition());

        //Time
        telemetry.addData("CURRENT_TIME", (getRuntime() - currentTime));

        telemetry.update();

    }

}


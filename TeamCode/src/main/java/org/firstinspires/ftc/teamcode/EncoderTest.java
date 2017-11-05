package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by owner on 11/3/2017.
 */
@TeleOp(name = "Encoder", group = "Cayles")
public class EncoderTest extends LinearOpMode {

    //Arm Mech
    DcMotor extendingArm;
    DcMotor elevatingArm;
    int currentArmEncoder;
    int armEncoder;
    int elevating;
    double joystickValue;

    public void runOpMode(){

        roboInit();

        waitForStart();

        while (opModeIsActive()){

            armMech();
            debug();

        }


    }

    public void roboInit(){

        //ArmMech
        extendingArm  = hardwareMap.dcMotor.get("EXTENDING");
        elevatingArm  = hardwareMap.dcMotor.get("ELEVATING");
        elevating     = 20;

        currentArmEncoder = armEncoder;

    }

    public void armMech(){


        currentArmEncoder = elevatingArm.getCurrentPosition();
        extendingArm.setPower(gamepad1.right_stick_x);
        elevatingArm.setPower(-gamepad1.right_stick_y);
        joystickValue = gamepad1.left_stick_y;

        if(currentArmEncoder - armEncoder > elevating /*&& currentArmEncoder != armEncoder*/ && joystickValue > 0.3 ){

            extendingArm.setPower(1.0);
            armEncoder = currentArmEncoder;


        } else if(currentArmEncoder - armEncoder < elevating /*&& currentArmEncoder != armEncoder*/ && joystickValue < -0.3){

            extendingArm.setPower(-1.0);
            armEncoder = currentArmEncoder;

        } else if(currentArmEncoder == armEncoder){

            extendingArm.setPower(0.0);
            armEncoder = currentArmEncoder;

        } else{

            extendingArm.setPower(0.0);

        }
        //armEncoder = currentArmEncoder;

    }

    public void debug(){

        telemetry.addData("Elevating", elevatingArm.getCurrentPosition());
        telemetry.addData("ArmEncoder", armEncoder);
        telemetry.addData("Joystick", gamepad1.right_stick_y);
        telemetry.addData("Extending", extendingArm.getCurrentPosition());
        telemetry.update();

    }
}

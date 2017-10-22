package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by owner on 10/14/2017.
 */
@TeleOp(name = "Test", group = "Cayles")
@Disabled
public class HubTest extends LinearOpMode {

    DcMotor motor;

    public void runOpMode(){

        roboInit();

        waitForStart();

        while (opModeIsActive()){

            drive();

        }

    }

    public void roboInit(){

        motor = hardwareMap.dcMotor.get("MOTOR");

    }

    public void drive(){

        motor.setPower(gamepad1.left_stick_y);

    }

}

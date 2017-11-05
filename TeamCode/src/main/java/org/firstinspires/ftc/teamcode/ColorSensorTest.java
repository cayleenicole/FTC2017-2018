package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by owner on 10/21/2017.
 */
@TeleOp(name = "Color", group = "Cayles")
@Disabled
public class ColorSensorTest extends LinearOpMode {

    ColorSensor sensor;

    public void runOpMode(){

        roboInit();

        waitForStart();

        while (opModeIsActive()){

            debug();

        }


    }

    public void roboInit(){

        sensor = hardwareMap.colorSensor.get("SENSOR");

    }

    public void debug(){

        telemetry.addData("SENSOR", sensor.red());telemetry.addData("SENSOR", sensor.blue());
        telemetry.update();

    }

}

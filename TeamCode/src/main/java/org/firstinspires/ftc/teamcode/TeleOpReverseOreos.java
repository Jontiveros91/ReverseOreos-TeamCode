
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//@Disabled
@TeleOp(name="Teleop Reverse Oreos", group="Ontiveros")
//@Disabled
public class TeleOpReverseOreos extends LinearOpMode {

    /* Declare OpMode members. */
    Hardware_ReverseOreos robot  = new Hardware_ReverseOreos();   // Use the updated Mecanum's hardware

    double howfast;
    double shift;
    double turn;

    @Override
    public void runOpMode() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap, telemetry);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // In this mode the Left stick moves the robot fwd and back, the Right stick turns left and right.
            // This way it's also easy to just drive straight, or just turn.
            howfast = gamepad1.left_stick_y;
            shift = gamepad1.left_stick_x;
            turn = gamepad1.right_stick_x;

            Hardware_ReverseOreos.leftFront.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x);
            Hardware_ReverseOreos.rightFront.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x);
            Hardware_ReverseOreos.leftBack.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x);
            Hardware_ReverseOreos.rightBack.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x);

            if (gamepad1.left_stick_x > 0)
                robot.goRight(shift);
            else if (gamepad1.left_stick_x < 0)
                robot.goRight(shift);
            else if (gamepad1.left_stick_x == 0)
                robot.goRight(0);

            //use trigger to lift
            if (gamepad1.left_trigger > 0)
                robot.lift.setPower(gamepad1.left_trigger); // lift up
            else if (gamepad1.right_trigger > 0)
                robot.lift.setPower(-1 * gamepad1.right_trigger); // lift down
            else
                robot.lift.setPower(0);

           // use right & left bumper to open/close claw
            if (gamepad1.right_bumper){
                robot.claw.setPower(1);
            }
            else if (gamepad1.left_bumper){
                robot.claw.setPower(-1);
            }
            else {
                robot.claw.setPower(1);
            }
            if(gamepad1.a){
                robot.claw.setPower(-1);
            }
            // Send telemetry message to signify robot running;
            // telemetry.addData("claw",  "Offset = %.2f", clawOffset);
            // telemetry.addData("left",  "%.2f", left);
            //telemetry.addData("right", "%.2f", right);
            //telemetry.update();

        }

    }
}


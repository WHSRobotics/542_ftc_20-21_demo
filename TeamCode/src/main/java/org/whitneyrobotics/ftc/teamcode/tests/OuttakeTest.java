package org.whitneyrobotics.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.whitneyrobotics.ftc.teamcode.lib.geometry.Coordinate;
import org.whitneyrobotics.ftc.teamcode.subsys.WHSRobotImpl;

@TeleOp(name = "Outtake Test", group = "Tests")
public class OuttakeTest extends OpMode {

    public WHSRobotImpl robot;

    double power;

    int i = 0;
    FtcDashboard dashboard ;
    Telemetry dashboardTelemetry;
    TelemetryPacket packet = new TelemetryPacket();
    @Override
    public void init() {
        dashboard = FtcDashboard.getInstance();
        dashboardTelemetry = dashboard.getTelemetry();;
        dashboardTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        dashboard.sendTelemetryPacket(packet);

        robot = new WHSRobotImpl(hardwareMap);
        dashboardTelemetry.setMsTransmissionInterval(10);
    }

    @Override
    public void loop() {
        robot.shootHighGoal(gamepad1.x);
        packet.put("Target Velocity", robot.outtake.targetVelocityDebug);
        packet.put("Current Velocity", robot.outtake.currentVelocityDebug);
        dashboard.sendTelemetryPacket(packet);
    }
}

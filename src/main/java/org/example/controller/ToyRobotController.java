package org.example.controller;

import io.javalin.Javalin;
import org.example.TemplateApp;

public class ToyRobotController {
    private final Javalin server;

    public ToyRobotController(){
        this.server = Javalin.create();

        this.server.get("/hello", context -> new TemplateApp().start(context));
    }

    public static void main(String[] args) {
        var ser = new ToyRobotController();
        ser.start();
    }

    private void start(){
        this.server.start(5500);
    }
}

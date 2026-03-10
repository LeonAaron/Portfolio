package org.example;

public class FrontCamera implements Camera{
    private static FrontCamera singleton;

    private FrontCamera() {
        //
    }

    public static FrontCamera create() {
        if (singleton == null) {
            singleton = new FrontCamera();
        }
        return singleton;
    }

    public static void main(String[] args) {
        Camera c1 = FrontCamera.create();
        Camera c2 = FrontCamera.create();
    }
}

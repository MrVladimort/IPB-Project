package pl.pjatk.ipb.project.controller.exceptions;

public class TestsFailedException extends RuntimeException {
    public TestsFailedException() {
        super("Tests failed");
    }
}
package pl.pjatk.ipb.project.control.exceptions;

public class TestsFailedException extends RuntimeException {
    public TestsFailedException() {
        super("Tests failed");
    }
}
package pl.pjatk.ipb.project.controller.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super("Entity not found");
    }
}
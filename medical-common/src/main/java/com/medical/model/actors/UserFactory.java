package com.medical.model.actors;

public class UserFactory {

    private UserFactory() {
    }

    public static AbstractUser createUser(final Role role) {

        if (role == Role.ROLE_USER) {
            return new SimpleUser();
        }
        if (role == Role.ROLE_DOCTOR) {
            return new Doctor();
        }
        if (role == Role.ROLE_ADMIN) {
            return new Admin();
        }
        throw new UnsupportedOperationException();

    }
}

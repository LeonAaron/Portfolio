package org.example;

public class Notification {
    public enum Preference {
        TEXT,
        PUSH,
        EMAIL;
    }

    protected Notification() {}

    public static Notification create(Preference p) {
        if (p == null) {
            return null;
        }

        return switch (p) {
            case TEXT -> new TextNotification();
            case PUSH -> new PushNotification();
            case EMAIL -> new EmailNotification();
            default -> throw new IllegalArgumentException();
        };
    }
}


class TextNotification extends Notification{
    protected TextNotification(){}
}
class PushNotification extends Notification{
    protected PushNotification(){}
}
class EmailNotification extends Notification{
    protected EmailNotification(){}
}

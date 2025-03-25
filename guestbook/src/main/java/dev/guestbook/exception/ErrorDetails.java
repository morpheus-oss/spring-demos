package dev.guestbook.exception;

import java.io.Serializable;
import java.util.Date;

public record ErrorDetails(Date timestamp, String message, String details) implements Serializable {
}

package com.example.calendar.service.email;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}

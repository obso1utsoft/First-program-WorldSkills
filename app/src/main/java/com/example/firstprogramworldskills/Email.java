package com.example.firstprogramworldskills;

import com.google.gson.annotations.SerializedName;

public class Email {
    @SerializedName("email")
    private String email;
    @SerializedName("message")
    private String message;
    @SerializedName("errors")
    private String errors;

    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}

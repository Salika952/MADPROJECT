package com.example.fitzky2.Feedback;

public class Feedback {
    private String email;
    private String title;
    private String description;

    public Feedback() {
    }


    public Feedback(String email, String title, String description) {
        this.email = email;
        this.title = title;
        this.description = description;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

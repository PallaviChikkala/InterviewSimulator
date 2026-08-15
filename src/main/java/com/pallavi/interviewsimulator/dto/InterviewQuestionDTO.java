package com.pallavi.interviewsimulator.dto;



public class InterviewQuestionDTO {

    private Long id;

    private String question;

    private String category;

    private String difficulty;

    public String getDifficulty() {
        return difficulty;
    }

    public Long getId() {
        return id;
    }


    public String getQuestion() {
        return question;
    }

    public String getCategory() {
        return category;
    }


    public InterviewQuestionDTO(Long id, String question, String category, String difficulty)
    {
        this.id = id;
        this.question = question;
        this.category = category;
        this.difficulty = difficulty;
    }



}

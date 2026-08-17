package com.pallavi.interviewsimulator.controller;

import com.pallavi.interviewsimulator.dto.InterviewQuestionDTO;
import com.pallavi.interviewsimulator.model.InterviewQuestion;
import com.pallavi.interviewsimulator.service.InterviewQuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.*;



@RestController
@RequestMapping("/questions")
public class InterviewQuestionController {

    private final InterviewQuestionService questionService;

    public InterviewQuestionController(InterviewQuestionService questionService)
    {
        this.questionService = questionService;
    }

    @GetMapping("/")
    public List<InterviewQuestionDTO> getQuestions()
    {
        return questionService.getQuestions();
    }

    @GetMapping("/{id}")
    public Optional<InterviewQuestionDTO> getQuestionById(@PathVariable Long id)
    {
        System.out.println("ID received: "+id);
        return questionService.getQuestionById(id);
    }

    @PostMapping
    public InterviewQuestion addQuestion(@RequestBody InterviewQuestion question)
    {
        return questionService.addQuestion(question);
    }

    @PutMapping("/{id}")
    public InterviewQuestion updateQuestion(@PathVariable Long id,
                                            @RequestBody InterviewQuestion question)
    {
        return questionService.updateQuestion(question, id);
    }

    @DeleteMapping("/{id}")
    public InterviewQuestion deleteQuestion(@PathVariable Long id)
    {
        return questionService.deleteQuestion(id);
    }
}

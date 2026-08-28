package com.pallavi.interviewsimulator.service;

import java.util.*;

import com.pallavi.interviewsimulator.dto.InterviewQuestionDTO;
import com.pallavi.interviewsimulator.exception.QuestionNotFoundException;
import com.pallavi.interviewsimulator.model.InterviewQuestion;
import com.pallavi.interviewsimulator.repository.InterviewQuestionRepository;
import org.springframework.stereotype.Service;



@Service
public class InterviewQuestionService {
    private final InterviewQuestionRepository repository;

    public InterviewQuestionService(InterviewQuestionRepository repository)
    {
        this.repository = repository;
    }

    public List<InterviewQuestionDTO> getQuestions()
    {
        return repository.findAll()
                .stream()
                .map(question -> new InterviewQuestionDTO(
                        question.getId(),
                        question.getQuestion(),
                        question.getCategory(),
                        question.getDifficulty()
                ))
                .toList();
    }

    public InterviewQuestionDTO getQuestionById(Long id)
    {

        InterviewQuestion question = repository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(
                        "Question with ID "+id+" not found."
                ));

       return new InterviewQuestionDTO(
                question.getId(),
                question.getQuestion(),
                question.getCategory(),
                question.getDifficulty()
        );

    }

    public InterviewQuestion addQuestion(InterviewQuestion question)
    {
        repository.save(question);
        return question;
    }

    public InterviewQuestion updateQuestion(InterviewQuestion question, Long id)
    {
        InterviewQuestion updatingQuestion = repository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(
                        "Question with ID" + id + " not found."
                        ));
        updatingQuestion.setQuestion(question.getQuestion());
        updatingQuestion.setCategory(question.getCategory());
        updatingQuestion.setDifficulty(question.getDifficulty());
        updatingQuestion.setCorrectAnswer(question.getCorrectAnswer());

        return repository.save(updatingQuestion);
    }

    public InterviewQuestion deleteQuestion(Long id)
    {
        InterviewQuestion deleteQuestion = repository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(
                        "Question with ID "+id+ " not found."
                ));
        repository.deleteById(id);
        return deleteQuestion;
    }

        public List<InterviewQuestionDTO> filterByCategory(String category)
        {
            return repository.findByCategory(category)
                    .stream()
                    .map(question -> new InterviewQuestionDTO(
                            question.getId(),
                            question.getQuestion(),
                            question.getCategory(),
                            question.getDifficulty()
                    ))
                    .toList();
        }

}

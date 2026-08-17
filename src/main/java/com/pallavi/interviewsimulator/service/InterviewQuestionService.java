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

    public Optional<InterviewQuestionDTO> getQuestionById(Long id)
    {

        InterviewQuestion question = repository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(
                        "Question with ID "+id+" not found."
                ));

        InterviewQuestionDTO dto = new InterviewQuestionDTO(
                question.getId(),
                question.getQuestion(),
                question.getCategory(),
                question.getDifficulty()
        );

            return Optional.of(dto);

    }

    public InterviewQuestion addQuestion(InterviewQuestion question)
    {
        repository.save(question);
        return question;
    }

    public InterviewQuestion updateQuestion(InterviewQuestion question, Long id)
    {
        Optional<InterviewQuestion> optionalUpdate = repository.findById(id);

        if(optionalUpdate.isPresent())
        {
            InterviewQuestion updatingQuestion = optionalUpdate.get();

            updatingQuestion.setQuestion(question.getQuestion());
            updatingQuestion.setCategory(question.getCategory());
            updatingQuestion.setDifficulty(question.getDifficulty());
            updatingQuestion.setCorrectAnswer(question.getCorrectAnswer());

            repository.save(updatingQuestion);

            return updatingQuestion;

        }

        return null;
    }

    public InterviewQuestion deleteQuestion(Long id) {
        Optional<InterviewQuestion> optionalDelete = repository.findById(id);

        if (optionalDelete.isPresent()) {

            InterviewQuestion deleteQuestion = optionalDelete.get();

            repository.deleteById(id);

            return deleteQuestion;
        }

        return null;
    }

}

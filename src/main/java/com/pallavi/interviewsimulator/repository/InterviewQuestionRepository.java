package com.pallavi.interviewsimulator.repository;

import com.pallavi.interviewsimulator.model.InterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion, Long>{

}

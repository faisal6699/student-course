package com.example.studentcourse.service;

import com.example.studentcourse.entity.IdCard;
import com.example.studentcourse.entity.Student;
import com.example.studentcourse.repository.IdCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IdCardService {

    private final IdCardRepository idCardRepository;
    @Autowired
    public IdCardService(IdCardRepository idCardRepository) {
        this.idCardRepository = idCardRepository;
    }

    public List<IdCard> getAllIDCards() {
        return idCardRepository.findAll();
    }

    public IdCard save(IdCard idCard) {
        return idCardRepository.save(idCard);
    }
}

package org.kiraell.prog.service;


import org.kiraell.prog.models.User;
import org.kiraell.prog.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {


    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public List<User> findAll() { // получение всех людей
        return usersRepository.findAll();
    }

    public User findOne(int id) { // получение человека по айди
        Optional<User> foundPerson = usersRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional // добавить человека
    public void save(User user) {
        usersRepository.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) { // методы такие же как в дао
        updatedUser.setId(id);  //соглашение что что для обновлдения тоже используют сэйв но тут метод увидит ч
        // то передаётся человек с тем айди кторое уже есть и поэтому он просто обновит
        usersRepository.save(updatedUser);
    }

    @Transactional
    public void delete(int id) {
        usersRepository.deleteById(id);
    }
}

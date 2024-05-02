package com.viamatica.viamatica.business.service;

import com.viamatica.viamatica.business.port.IUserService;
import com.viamatica.viamatica.domain.dto.Person;
import com.viamatica.viamatica.domain.dto.User;
import com.viamatica.viamatica.domain.repository.IUserRepository;
import com.viamatica.viamatica.errors.EntityNotFoundException;
import com.viamatica.viamatica.utils.ErrorCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id).orElseThrow(() -> new EntityNotFoundException(ErrorCatalog.USER_NOT_FOUND));
    }

    @Override
    public User create(User entity) {
        //TODO: ADD PASSWORD ENCODING
        //Create email.
        if (entity.getEmail() == null) {
            entity.setEmail(generateEmail(entity.getPerson()));
        }
        return userRepository.create(entity);
    }

    @Override
    public User update(Long id, User entity) {
        return userRepository.getById(id)
                .map(u -> {
                    u.setUsername(entity.getUsername());
//                    u.setPassword(entity.getPassword());
//                    u.setEmail(entity.getEmail());
                    u.setRoles(entity.getRoles());
                    return userRepository.update(u);
                }).orElseThrow(() -> new EntityNotFoundException(ErrorCatalog.USER_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }


    private String generateEmail(Person person) {
        String firstCharName = (person.getNames().charAt(0) + "").toLowerCase();
        String lastNames = person.getLastNames().toLowerCase();
        String firstLastName = lastNames.split(" ")[0];
        String firstChartSecondLastName = lastNames.split(" ")[1].charAt(0) + "";

        String baseEmail = firstCharName + firstLastName + firstChartSecondLastName;
        String email = baseEmail + "@mail.com";
        boolean emailExist = userRepository.existsByEmail(email);
        if (emailExist) {
            int i = 1;
            while (userRepository.existsByEmail(baseEmail + i + "@mail.com")) {
                i++;
            }
            email = baseEmail + i + "@mail.com";
        }
        return email;
    }
}

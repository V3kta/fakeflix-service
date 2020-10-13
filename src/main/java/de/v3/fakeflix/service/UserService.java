package de.v3.fakeflix.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import de.v3.fakeflix.dto.LoginDataDTO;
import de.v3.fakeflix.dto.RegisterDataDTO;
import de.v3.fakeflix.dto.UserDTO;
import de.v3.fakeflix.entity.User;
import de.v3.fakeflix.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {



    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean checkData(LoginDataDTO loginData) {
        if (loginData.getLogin().contains("@")) {
            return userRepository.existsByEmailAndPassword(loginData.getLogin(), loginData.getPassword());
        }
        return userRepository.existsByUsernameAndPassword(loginData.getLogin(), loginData.getPassword());
    }

    public Boolean checkData(RegisterDataDTO registerData) {
        return userRepository.existsByUsernameOrEmail(registerData.getUsername(), registerData.getEmail());
    }

    public UserDTO getUser(LoginDataDTO loginData) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<User> OptUser;

        if (loginData.getLogin().contains("@")) {
            OptUser = userRepository.findByEmailAndPassword(loginData.getLogin(), loginData.getPassword());
        } else {
            OptUser = userRepository.findByUsernameAndPassword(loginData.getLogin(), loginData.getPassword());
        }

        return OptUser.map(user -> modelMapper.map(user, UserDTO.class)).orElse(null);

    }

    public void saveUser(RegisterDataDTO registerDataDTO) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(registerDataDTO, User.class);

        userRepository.save(user);
    }
}

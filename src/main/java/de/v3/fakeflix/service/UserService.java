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

@Service
@Slf4j
public class UserService {



    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean checkData(LoginDataDTO loginData) {
        return userRepository.existsByUsernameOrEmailAndPassword(loginData.getLogin(), loginData.getLogin(), loginData.getPassword());
    }

    public Boolean checkData(RegisterDataDTO registerData) {
        return userRepository.existsByUsername(registerData.getUsername());
    }

    public UserDTO getUser(LoginDataDTO loginData) {
        ModelMapper modelMapper = new ModelMapper();

        User user = userRepository.findByUsernameOrEmailAndPassword(loginData.getLogin(), loginData.getLogin(), loginData.getPassword());

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        log.info(userDTO.getEmail());

        return userDTO;
    }
}

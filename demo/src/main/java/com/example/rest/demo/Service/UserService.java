package com.example.rest.demo.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest.demo.DTO.UserRequestDto;
import com.example.rest.demo.DTO.UserResponseDto;
import com.example.rest.demo.Model.User;
import com.example.rest.demo.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
            .map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail()))
            .collect(Collectors.toList());
    }
    public UserResponseDto createUser(UserRequestDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        // i can set the admin status as false
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    public Optional<UserResponseDto> getUserById(Long id) {
        return userRepository.findById(id)
            .map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail()));
    }
    public UserResponseDto updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        User updatedUser = userRepository.save(user);
        return new UserResponseDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail());
    }


    public UserResponseDto updateUserPartially(Long id, UserPatchDto patch){
          User existing = userRepository.findById(id);
          if(patch.getName() != null){
              existing.setName(patch.getName());
          }

          if(patch.getEmail() != null){
              existing.setEmail(patch.getEmail());
          }
          User updatedUser = userRepository.save(existing);
          return new UserResponseDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail());
    }

    public UserResponseDto patchUser(Long id, UserRequestDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        User updatedUser = userRepository.save(user);
        return new UserResponseDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

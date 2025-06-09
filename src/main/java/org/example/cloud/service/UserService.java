package org.example.cloud.service;

import org.example.cloud.dto.UserRequestDto;
import org.example.cloud.dto.UserResponseDto;
import org.example.cloud.entity.Transaction;
import org.example.cloud.entity.TaxReport;
import org.example.cloud.entity.User;
import org.example.cloud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Cacheable("users")
    public List<User> getAllUsers() {
        System.out.println("Looking into the database...");
        return userRepository.findAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public UserResponseDto createUser(UserRequestDto dto) {
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());

        user = userRepository.save(user);

        UserResponseDto response = new UserResponseDto();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());

        response.setTransactionIds(
                user.getTransactions().stream()
                        .map(Transaction::getId)
                        .collect(Collectors.toList())
        );

        response.setTaxReportIds(
                user.getTaxReports().stream()
                        .map(TaxReport::getId)
                        .collect(Collectors.toList())
        );

        return response;
    }
}


package com.gordeev.taskmanager.initializer;

import com.gordeev.taskmanager.users.entity.User;
import com.gordeev.taskmanager.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername(adminUsername).isEmpty()) {
            String actualPassword = adminPassword.isBlank() ? "TempPassword12345!" : adminPassword;

            User admin = User.builder()
                    .username(adminUsername)
                    .email(adminEmail)
                    .password(passwordEncoder.encode(adminPassword))
                    .role("ADMIN")
                    .build();

            userRepository.save(admin);

            System.out.println("=============================================");
            System.out.println("Администратор программы успешно создан: " + adminUsername);
            System.out.println("=============================================");
            if (adminPassword.isBlank()) {
                System.err.println("ВНИМАНИЕ: Администратору задан пароль по-умолчанию, необходимо сменить его!");
            }
        } else {
            System.out.println("=============================================");
            System.out.println("Администратор программы: " + adminUsername);
            System.out.println("=============================================");
        }
    }
}

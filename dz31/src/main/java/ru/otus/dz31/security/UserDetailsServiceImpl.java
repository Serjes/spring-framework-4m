package ru.otus.dz31.security;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import ru.otus.dz31.domain.User;
import ru.otus.dz31.repository.UserRepository;
import ru.otus.dz31.service.LibraryServiceImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String SECURITY_HYSTRIX_TIMEOUT = "2000";

    @Autowired
    private UserRepository userRepository;

    @Override
    @HystrixCommand(commandKey = "loadUser", groupKey = "SecurityService",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = SECURITY_HYSTRIX_TIMEOUT)
            }
    )
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        User user = userRepository.findByName(name);
        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(name);
            builder.password(user.getPassword());
            builder.roles(user.getRole());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }

}

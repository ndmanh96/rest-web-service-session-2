package com.manhcode.rest.demo;


import com.manhcode.rest.demo.dao.OrderRepository;
import com.manhcode.rest.demo.dao.UserRepository;
import com.manhcode.rest.demo.entity.Order;
import com.manhcode.rest.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource bundleMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Override
	public void run(String... args) throws Exception {
		Order order1 = new Order("Order1");
		Order order2 = new Order("Order2");
		Order order3 = new Order("Order3");
		Order order4 = new Order("Order4");
		Order order5 = new Order("Order5");
		Order order6 = new Order("Order6");

		User user1 = new User("manh.nd4","Nguyen","Manh","manh.nd4@samsung.com", "admin", "ssn01");
		User user2 = new User("manh.nd5","Nguyen2","Manh2","manh.nd5@samsung.com", "admin", "ssn02");
		User user3 = new User("manh.nd6","Nguyen3","Manh3","manh.nd6@samsung.com", "admin", "ssn03");

		order1.setUser(user1);
		order2.setUser(user1);
		order3.setUser(user1);
		order4.setUser(user2);
		order5.setUser(user2);
		order6.setUser(user3);

		user1.addOrder(order1);
		user1.addOrder(order2);
		user1.addOrder(order3);
		user2.addOrder(order4);
		user2.addOrder(order5);
		user3.addOrder(order6);

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);

		orderRepository.save(order1);
		orderRepository.save(order2);
		orderRepository.save(order3);
		orderRepository.save(order4);
		orderRepository.save(order5);
		orderRepository.save(order6);
	}
}

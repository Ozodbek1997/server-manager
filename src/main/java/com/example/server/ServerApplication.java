package com.example.server;

import com.example.server.enumaration.Status;
import com.example.server.model.Server;
import com.example.server.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
// When we start the application we save this data in our database
	@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return args -> {
			serverRepo.save(new Server(null,"192.68.1.160","Ubuntu Linux","16 GB","Personal Computer","http://localhost:8080/server/image/server.png", Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.68.1.58","Fedora Linux","16 GB","Dell Computer","http://localhost:8080/server/image/server.png", Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.68.1.21","MS 2009","32 GB","Lenovo Computer","http://localhost:8080/server/image/server.png", Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.68.1.32","Red Hat Enterprise ","16 GB","Personal Computer","http://localhost:8080/server/image/server.png", Status.SERVER_UP));
		};
	}
}

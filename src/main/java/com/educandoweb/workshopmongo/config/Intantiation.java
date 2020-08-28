package com.educandoweb.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.educandoweb.workshopmongo.domain.Post;
import com.educandoweb.workshopmongo.domain.User;
import com.educandoweb.workshopmongo.repository.PostRepository;
import com.educandoweb.workshopmongo.repository.UserRepository;

@Configuration
public class Intantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User franco = new User(null, "Felipe Franco", "franco@gmail.com");
		User banban = new User(null, "Banban", "monstro@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "trapezera buscando caraio!",
				"sabe o que é isso? trapézio descendente! é ele que a gente quer, é ele que a gente vai buscar!",
				franco);
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "BORAA HORA DO SHOW PORRA", "ó o homi ali porra, é 13 porra!",
				banban);

		userRepository.saveAll(Arrays.asList(franco, banban, bob));
		postRepository.saveAll(Arrays.asList(post1, post2));

	}

}

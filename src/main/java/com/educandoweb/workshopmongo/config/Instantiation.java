package com.educandoweb.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.educandoweb.workshopmongo.domain.Post;
import com.educandoweb.workshopmongo.domain.User;
import com.educandoweb.workshopmongo.dto.AuthorDTO;
import com.educandoweb.workshopmongo.dto.CommentDTO;
import com.educandoweb.workshopmongo.repository.PostRepository;
import com.educandoweb.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

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

		userRepository.saveAll(Arrays.asList(franco, banban, bob));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "trapezera buscando caraio!",
				"sabe o que é isso? trapézio descendente! é ele que a gente quer, é ele que a gente vai buscar!",
				new AuthorDTO(franco));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "BORAA HORA DO SHOW PORR*",
				"ó o homi ali porr*, é 13 porr*!", new AuthorDTO(banban));
		Post post3 = new Post(null, sdf.parse("23/03/2018"), "HUUUUUUUUUUUUUUUUUURRRRRR", "QUERO MAIS, QUERO MAIS!",
				new AuthorDTO(banban));

		CommentDTO c1 = new CommentDTO("é ele né monstro?", sdf.parse("21/03/2018"), new AuthorDTO(banban));
		CommentDTO c2 = new CommentDTO("aqui nós constroi fibra carai! não é agua com musculo", sdf.parse("23/03/2018"),
				new AuthorDTO(franco));

		post1.getComments().addAll(Arrays.asList(c1));
		post2.getComments().addAll(Arrays.asList(c2));

		postRepository.saveAll(Arrays.asList(post1, post2, post3));

		franco.getPosts().addAll(Arrays.asList(post1));
		banban.getPosts().addAll(Arrays.asList(post2, post3));

		userRepository.saveAll(Arrays.asList(franco, banban));

	}

}

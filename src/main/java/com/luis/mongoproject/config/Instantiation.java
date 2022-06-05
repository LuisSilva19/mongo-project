package com.luis.mongoproject.config;

import com.luis.mongoproject.domain.Post;
import com.luis.mongoproject.domain.User;
import com.luis.mongoproject.dto.AuthorDTO;
import com.luis.mongoproject.dto.CommentDTO;
import com.luis.mongoproject.repository.PostRepository;
import com.luis.mongoproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Configuration
@RequiredArgsConstructor
public class Instantiation implements CommandLineRunner {

	private final UserRepository userRepository;
	private final PostRepository postRepository;

	@Override
	public void run(String... arg0) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();

		var maria = User.builder().name("Maria Brown").email("maria@gmail.com").build();
		var alex = User.builder().name("Alex Green").email("alex@gmail.com").build();
		var bob = User.builder().name("Bob Grey").email("bob@gmail.com").build();

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		var mariaAuthor = AuthorDTO.builder().id(maria.getId()).name(maria.getName()).build();
		var alexAuthor = AuthorDTO.builder().id(alex.getId()).name(alex.getName()).build();
		var bobAuthor = AuthorDTO.builder().id(bob.getId()).name(bob.getName()).build();

		var c1 = CommentDTO.builder().text("Boa viagem mano!").date( sdf.parse("21/03/2018")).author(alexAuthor).build();
		var c2 = CommentDTO.builder().text("Aproveite").date( sdf.parse("22/03/2018")).author(bobAuthor).build();
		var c3 = CommentDTO.builder().text("Tenha um ótimo dia!").date( sdf.parse("23/03/2018")).author(alexAuthor).build();

		List<CommentDTO> coments = new ArrayList<>();
		coments.add(c1);
		coments.add(c2);

		List<CommentDTO> coments2 = new ArrayList<>();
		coments2.add(c3);

		var post1 = Post.builder().date(sdf.parse("21/03/2018")).title("Partiu viagem").body("Vou viajar para São Paulo. Abraços!").author(mariaAuthor).comments(coments).build();
		var post2 = Post.builder().date(sdf.parse("23/03/2018")).title("Bom dia").body("Acordei feliz hoje!").author(mariaAuthor).comments(coments2).build();
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		User save = userRepository.save(maria);

		var mariaUpdate = User.builder().id(save.getId()).name("Maria Brown").email("maria@gmail.com").posts(Arrays.asList(post1, post2)).build();

		userRepository.save(mariaUpdate);
	}
}

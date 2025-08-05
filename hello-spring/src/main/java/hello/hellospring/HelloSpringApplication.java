package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
		//넣어준 class 실행
		//어노테이션을 통해 스프링부트 어플리케이션이 실행됨
		//내장된 톰캣 웹서버를 자체적으로 띄우면서 스프링이 올라감
	}

}

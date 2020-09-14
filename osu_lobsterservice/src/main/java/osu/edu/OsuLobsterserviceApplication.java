package osu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import osu.edu.Data.bo.LobsterAccessBO;
import osu.edu.Data.bo.LobsterAccessBOImpl;
import osu.edu.Data.dao.LobsterTransaction;
import osu.edu.Data.dao.LobsterTransactionImpl;
import osu.edu.Service.LobsterService;
import osu.edu.Service.LobsterServiceImpl;

@SpringBootApplication
public class OsuLobsterserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsuLobsterserviceApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public LobsterService getLobsterService() { 
		return new LobsterServiceImpl(); 
	}
	
	@Bean
	public LobsterTransaction getLobsterTransaction() { 
		return new LobsterTransactionImpl(); 
	}
	
	@Bean
	public LobsterAccessBO getLobsterAccess() { 
		return new LobsterAccessBOImpl(); 
	}
	

	
}

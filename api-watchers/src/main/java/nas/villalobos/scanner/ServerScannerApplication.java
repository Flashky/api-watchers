package nas.villalobos.scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import brv.tools.daemons.manager.ScanDaemonManager;
import brv.tools.daemons.manager.ScanDaemonManagerMap;

@SpringBootApplication
//@EnableScheduling
@EnableJpaRepositories
public class ServerScannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerScannerApplication.class, args);
	}
	
	@Bean
	ScanDaemonManager getScanDaemonManager() {
		return new ScanDaemonManagerMap();
	}
	
}

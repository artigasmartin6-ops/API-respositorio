package Payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductoRepository repository) {

        return args -> {

            log.info("Cargando " + repository.save(
                    new Producto("Cemento 50kg", 38.90, 40, "Materiales básicos")
            ));

            log.info("Cargando " + repository.save(
                    new Producto("Ladrillo cerámico", 2.50, 500, "Albañilería")
            ));

            log.info("Cargando " + repository.save(
                    new Producto("Pintura acrílica 18L", 289.90, 15, "Pintura")
            ));

            log.info("Cargando " + repository.save(
                    new Producto("Mortero 20kg", 29.90, 35, "Materiales básicos")
            ));
        };
    }
}
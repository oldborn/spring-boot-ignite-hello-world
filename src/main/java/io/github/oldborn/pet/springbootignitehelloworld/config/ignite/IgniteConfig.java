package io.github.oldborn.pet.springbootignitehelloworld.config.ignite;

import io.github.oldborn.pet.springbootignitehelloworld.resource.something.SomethingDTO;
import io.github.oldborn.pet.springbootignitehelloworld.resource.something.SomethingRepository;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.springdata20.repository.config.EnableIgniteRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@EnableIgniteRepositories(basePackageClasses = SomethingRepository.class)
public class IgniteConfig {

    @Bean
    public void somrere() throws ClassNotFoundException, SQLException {

        Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
        Connection conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1/");
    }

    @Bean
    public Ignite igniteInstance() {
        IgniteConfiguration config = new IgniteConfiguration();

        CacheConfiguration cache = new CacheConfiguration("somethingCache");
        cache.setIndexedTypes(Integer.class, SomethingDTO.class);

        config.setCacheConfiguration(cache);
        return Ignition.start(config);
    }
}

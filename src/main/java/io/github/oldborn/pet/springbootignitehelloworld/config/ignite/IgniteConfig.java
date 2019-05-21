package io.github.oldborn.pet.springbootignitehelloworld.config.ignite;

import io.github.oldborn.pet.springbootignitehelloworld.resource.something.SomethingDTO;
import io.github.oldborn.pet.springbootignitehelloworld.resource.something.SomethingRepository;
import lombok.Getter;
import lombok.Setter;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.springdata20.repository.config.EnableIgniteRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@EnableIgniteRepositories(basePackageClasses = SomethingRepository.class)
@ConfigurationProperties("ignite")
public class IgniteConfig {


    @Getter @Setter
    private Integer nofClusterNodes = 3;

    @Autowired
    private Environment environment;

    @Bean
    public Ignite igniteInstance() {
        if(environment.getActiveProfiles()[0].equalsIgnoreCase("server")){
            Ignite firstIgnite = null;
            for (int i = 0; i<=(nofClusterNodes); i++){
                IgniteConfiguration config = new IgniteConfiguration();
                config.setIgniteInstanceName("something-node-server-"+i);
                CacheConfiguration cache = new CacheConfiguration("somethingCache");
                cache.setIndexedTypes(Integer.class, SomethingDTO.class);
                config.setCacheConfiguration(cache);
                Ignite ignited = Ignition.start(config);
                if (firstIgnite == null) firstIgnite = ignited;
            }
            igniteInstanceClient();
            return firstIgnite;
        }else{
            return igniteInstanceClient();
        }
    }

    public Ignite igniteInstanceClient() {
        IgniteConfiguration config = new IgniteConfiguration();
        config.setClientMode(true);
        config.setIgniteInstanceName("something-node-client");

        CacheConfiguration cache = new CacheConfiguration("somethingCache");
        cache.setIndexedTypes(Integer.class, SomethingDTO.class);

        config.setCacheConfiguration(cache);
        return Ignition.start(config);
    }
}

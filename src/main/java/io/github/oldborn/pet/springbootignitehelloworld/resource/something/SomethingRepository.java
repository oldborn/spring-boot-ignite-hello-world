package io.github.oldborn.pet.springbootignitehelloworld.resource.something;


import org.apache.ignite.springdata20.repository.IgniteRepository;
import org.apache.ignite.springdata20.repository.config.RepositoryConfig;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryConfig(cacheName = "somethingCache")
public interface SomethingRepository extends IgniteRepository<SomethingDTO,Integer> {

}

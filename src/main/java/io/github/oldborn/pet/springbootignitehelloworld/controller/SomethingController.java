package io.github.oldborn.pet.springbootignitehelloworld.controller;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import io.github.oldborn.pet.springbootignitehelloworld.resource.something.SomethingDTO;
import io.github.oldborn.pet.springbootignitehelloworld.resource.something.SomethingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/something")
public class SomethingController {

    @Autowired
    private SomethingRepository somethingRepository;

    @PostConstruct
    private void fill(){
        Lorem lorem = LoremIpsum.getInstance();
        Random random = new Random();
        for (int i = 0; i < 1000; i ++){
            somethingRepository.save(i, SomethingDTO.builder()
                    .aBoolean(random.nextBoolean())
                    .anIndexedString(lorem.getCity())
                    .aNoneIndexedString(lorem.getCity())
                    .id(i)
                    .build());
        }
    }


    @PostMapping
    public void addSomething(@RequestBody SomethingDTO something){
        somethingRepository.save(something.getId(),something);
    }

    @GetMapping("/{something-id}")
    public SomethingDTO getSomething(@PathVariable("something-id") Integer somethingId){
        return somethingRepository.findById(somethingId).orElseThrow(()-> new RuntimeException("Could not find entity"));
    }

    @GetMapping
    public List<SomethingDTO> getAllSomethings(){
        List<SomethingDTO> somethings = new ArrayList<>();
        somethingRepository.findAll().forEach(somethings::add);
        return somethings;
    }

    @DeleteMapping("/{something-id}")
    public void deleteSomething(@PathVariable("something-id") Integer somethingId){
        somethingRepository.deleteById(somethingId);
    }

    @DeleteMapping
    public void deleteAllSomethings(){
        somethingRepository.deleteAll();
    }

}

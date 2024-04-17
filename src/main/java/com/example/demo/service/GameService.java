package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.demo.dto.GameDTO;
import com.example.demo.entity.Franchise;
import com.example.demo.entity.Game;
import com.example.demo.entity.Person;
import com.example.demo.entity.Status;
import com.example.demo.persistence.FranchiseRepository;
import com.example.demo.persistence.GameRepository;
import com.example.demo.persistence.PersonRepository;

@Service
public class GameService extends ItemService<Game, GameDTO> {
    
    private final GameRepository repository;

    public GameService(GameRepository repository, FranchiseRepository franchiseRepository, PersonRepository personRepository) {
        super(franchiseRepository, personRepository);
        this.repository = repository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public GameRepository getRepository() {
        return repository;
    }

    @Override
    public GameDTO toDTO(Game entity) {
        return new GameDTO(entity);
    }

    @Override
    public GameDTO save(GameDTO object) {
        Person developer = getPerson(object.developer());
        Person publisher = getPerson(object.publisher());
        Franchise franchise = getFranchise(object.franchise());
        Game movie = new Game();
        movie.setFranchise(franchise);
        movie.setName(object.title());
        movie.setDeveloper(developer);
        movie.setCategory(object.genre());
        movie.setPublisher(publisher);
        movie.setReleaseDate(LocalDate.of(object.year(),1,1));
        movie.setInstallment(object.installment());
        movie.setStatus(Status.valueOf(object.status()));
        return toDTO(repository.save(movie));
    }
}

package com.example.demo.service;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.demo.dto.MovieDTO;
import com.example.demo.entity.Franchise;
import com.example.demo.entity.Movie;
import com.example.demo.entity.Person;
import com.example.demo.entity.Status;
import com.example.demo.persistence.FranchiseRepository;
import com.example.demo.persistence.MovieRepository;
import com.example.demo.persistence.PersonRepository;

@Service
public class MovieService extends ItemService<Movie, MovieDTO> {
    
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository, FranchiseRepository franchiseRepository, PersonRepository personRepository) {
        super(franchiseRepository, personRepository);
        this.movieRepository = movieRepository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public MovieRepository getRepository() {
        return movieRepository;
    }

    @Override
    public MovieDTO toDTO(Movie entity) {
        return new MovieDTO(entity.getId(), 
            entity.getFranchise().getName(), 
            entity.getName(), 
            entity.getDirector().getName(), 
            entity.getProducer().getName(), 
            entity.getCategory(), 
            entity.getActors().stream().map(Person::getName).toList(), 
            entity.getReleaseDate().getYear(), 
            entity.getDuration(), 
            entity.getStatus().name());
    }

    @Override
    public MovieDTO save(MovieDTO object) {
        Person director = getPerson(object.director());
        Person producer = getPerson(object.producer());
        Franchise franchise = getFranchise(object.franchise());
        Movie movie = new Movie();
        movie.setFranchise(franchise);
        movie.setName(object.title());
        movie.setDirector(director);
        movie.setCategory(object.genre());
        Collection<Person> actors = object.cast().stream().map(this::getPerson).toList();
        movie.setActors(actors);
        movie.setProducer(producer);
        movie.setReleaseDate(LocalDate.of(object.year(),1,1));
        movie.setDuration(object.duration());
        movie.setStatus(Status.valueOf(object.status()));
        return toDTO(movieRepository.save(movie));
    }
}

package com.example.demo.service;

import java.time.LocalDate;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.entity.Franchise;
import com.example.demo.entity.Person;
import com.example.demo.entity.Status;
import com.example.demo.persistence.BookRepository;
import com.example.demo.persistence.FranchiseRepository;
import com.example.demo.persistence.PersonRepository;

@Service
public class BookService extends ItemService<Book, BookDTO> {
    
    private final BookRepository repository;

    public BookService(BookRepository repository, FranchiseRepository franchiseRepository, PersonRepository personRepository) {
        super(franchiseRepository, personRepository);
        this.repository = repository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BookRepository getRepository() {
        return repository;
    }

    @Override
    public BookDTO toDTO(Book entity) {
        return new BookDTO(entity.getId(), 
            entity.getFranchise().getName(), 
            entity.getName(), 
            entity.getAuthor().getName(), 
            entity.getCategory(), 
            entity.getPublisher(), 
            entity.getReleaseDate().getYear(), 
            entity.getIsbn(), 
            entity.getStatus().name());
    }

    @Override
    public BookDTO save(BookDTO object) {
        Book book = new Book();
        Franchise franchise = getFranchise(object.franchise());
        Person author = getPerson(object.author());
        book.setFranchise(franchise);
        book.setName(object.title());
        book.setAuthor(author);
        book.setCategory(object.genre());
        book.setPublisher(object.publisher());
        book.setReleaseDate(LocalDate.of(object.year(), 1, 1));
        book.setIsbn(object.isbn());
        book.setStatus(Status.valueOf(object.status()));
        return toDTO(repository.save(book));
    }
}

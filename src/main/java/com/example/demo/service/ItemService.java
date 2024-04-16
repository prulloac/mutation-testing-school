package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Franchise;
import com.example.demo.entity.Item;
import com.example.demo.entity.Person;
import com.example.demo.entity.Status;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.persistence.FranchiseRepository;
import com.example.demo.persistence.PersonRepository;

public abstract class ItemService<E extends Item, D> implements RepositoryService<E, D, Integer> {

    protected final FranchiseRepository franchiseRepository;
    protected final PersonRepository personRepository;

    public ItemService(FranchiseRepository franchiseRepository, PersonRepository personRepository) {
        this.franchiseRepository = franchiseRepository;
        this.personRepository = personRepository;
    }

    @Override
    public D findById(Integer id) {
        return getRepository().findById(id).map(this::toDTO).orElseThrow(() -> new ItemNotFoundException("%s not found".formatted(id)));
    }

    protected Franchise getFranchise(String name) {
        Optional<Franchise> franchise = franchiseRepository.findByName(name);
        if (franchise.isEmpty()) {
            Franchise newFranchise = new Franchise();
            newFranchise.setName(name);
            return franchiseRepository.save(newFranchise);
        }
        return franchise.get();
    }

    protected Person getPerson(String name) {
        Optional<Person> person = personRepository.findByName(name);
        if (person.isEmpty()) {
            Person newPerson = new Person();
            newPerson.setName(name);
            newPerson.setBirthDate(null);
            newPerson.setNationality("Unknown");
            return personRepository.save(newPerson);
        }
        return person.get();
    }

    public D updateStatus(Integer id, Status status) {
        Optional<E> item = getRepository().findById(id);
        if (item.isEmpty()) {
            throw new ItemNotFoundException("%s not found".formatted(id));
        }
        item.get().setStatus(status);
        return toDTO(getRepository().save(item.get()));
    }

}

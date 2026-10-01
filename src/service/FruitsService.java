package service;

import entity.Fruits;
import exception.FruitNotFoundException;
import repository.FruitsRepository;

import java.util.List;

public class FruitsService {
    private final FruitsRepository fruitsRepository = new FruitsRepository();

    public void addFruit(Fruits fruits) {
        if (fruits.getName() == null || fruits.getName().isBlank()) {
            throw new IllegalArgumentException("Fruit name cannot be empty");
        }
        if (fruits.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        if (fruits.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        fruitsRepository.save(fruits);
    }

    public List<Fruits> getAllFruits() {
        return fruitsRepository.findAll();
    }

    public Fruits getFruitByID(int id) {
        Fruits fruits = fruitsRepository.findById(id);
        if (fruits == null) {
            throw new FruitNotFoundException("Fruit not found");
        }
        return fruits;
    }

    public void updateFruit(Fruits fruits) {
        if (fruits.getName() == null || fruits.getName().isBlank()) {
            throw new IllegalArgumentException("Fruit name cannot be empty");
        }
        if (fruits.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        if (fruits.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (fruitsRepository.findById(fruits.getId()) == null) {
            throw new FruitNotFoundException("Fruit not found");
        }
        fruitsRepository.update(fruits);
    }

    public void deleteFruit(int id) {
        if (fruitsRepository.findById(id) == null) {
            throw new FruitNotFoundException("Fruit not found");
        }
        fruitsRepository.delete(id);
    }
}

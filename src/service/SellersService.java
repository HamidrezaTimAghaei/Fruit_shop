package service;

import entity.Sellers;
import exception.SellerNotFoundException;
import exception.WrongPasswordException;
import repository.SellersRepository;

public class SellersService {
    private final SellersRepository sellersRepository = new SellersRepository();

    public Sellers login(String username, String password) {
        Sellers sellers = sellersRepository.findByUsername(username);
        if (sellers == null) {
            throw new SellerNotFoundException("Seller not found");
        }
        if (!sellers.getPassword().equals(password)) {
            throw new WrongPasswordException("Wrong password");
        }
        return sellers;
    }
}

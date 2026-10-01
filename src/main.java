import entity.Customers;
import entity.Fruits;
import entity.Sellers;
import service.CustomersService;
import service.FruitsService;
import service.SellersService;

import java.util.Scanner;
import java.sql.SQLException;

Scanner scanner = new Scanner(System.in);
SellersService sellersService = new SellersService();
CustomersService customersService = new CustomersService();
FruitsService fruitsService = new FruitsService();

void main() {

    while (true) {

        System.out.println("1. Sellers login");
        System.out.println("2. Customers login");
        System.out.println("3. Customers register");
        System.out.println("4. Exit");
        System.out.println("Choose");

        if (!scanner.hasNextInt()) {
            System.out.println("Please enter a number");
            scanner.nextLine();
            continue;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {

            switch (choice) {

                case 1:
                    sellersLogin();
                    break;

                case 2:
                    customersLogin();
                    break;

                case 3:
                    customersRegister();
                    break;

                case 4:
                    System.out.println("Goodbye");
                    return;

                default:
                    System.out.println("Invalid choice");
            }

        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

void sellersLogin() {

    System.out.println("Username: ");
    String username = scanner.nextLine();

    System.out.println("Password: ");
    String password = scanner.nextLine();

    Sellers sellers = sellersService.login(username, password);

    System.out.println("Welcome " + sellers.getName());

    sellerMenu();
}

void sellerMenu() {

    while (true) {


        System.out.println("1. Add Fruit");
        System.out.println("2. Show Fruits");
        System.out.println("3. Update Fruit");
        System.out.println("4. Delete Fruit");
        System.out.println("5. Logout");
        System.out.println("Choose");

        if (!scanner.hasNextInt()) {
            System.out.println("Please enter a number");
            scanner.nextLine();
            continue;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {

            switch (choice) {

                case 1:
                    addFruit();
                    break;

                case 2:
                    showFruits();
                    break;

                case 3:
                    updateFruit();
                    break;

                case 4:
                    deleteFruit();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice");
            }

        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

private void showFruits() {
    List<Fruits> fruits = fruitsService.getAllFruits();
    if (fruits.isEmpty()) {
        System.out.println("no fruits found");
        return;
    }
    for (Fruits fruit : fruits) {
        System.out.println("ID :" + fruit.getId() + "| Name: " + fruit.getName() +
                "| Description: " + fruit.getDescription() + "| Stock: " + fruit.getStock() + "| price: " + fruit.getPrice());

    }

}

private void updateFruit() {
    System.out.println("Fruit ID: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    Fruits oldFruit = fruitsService.getFruitById(id);

    System.out.println("New name: ");
    String name = scanner.nextLine();

    System.out.println("New description: ");
    String description = scanner.nextLine();

    System.out.println("New stock: ");
    double stock = scanner.nextDouble();

    System.out.println("New price: ");
    double price = scanner.nextDouble();
    scanner.nextLine();

    Fruits fruit = new Fruits(
            oldFruit.getId(),
            name,
            description,
            stock,
            price
    );

    fruitsService.updateFruit(fruit);

    System.out.println("Fruit updated successfully");

}

private void deleteFruit() {

    System.out.println("Fruit ID: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    fruitsService.deleteFruit(id);

    System.out.println("Fruit deleted successfully");
}


private void addFruit() {
    System.out.println("Fruit name: ");
    String name = scanner.nextLine();

    System.out.println("Description: ");
    String description = scanner.nextLine();

    System.out.println("Stock :");
    double stock = scanner.nextDouble();

    System.out.println("Preice :");
    double price = scanner.nextDouble();
    scanner.nextLine();

    Fruits fruit = new Fruits(
            0,
            name,
            description,
            stock,
            price
    );
    fruitsService.addFruit(fruit);
    System.out.println("Fruit added successfully");
}


void customersRegister() {

    System.out.println("Name: ");
    String name = scanner.nextLine();

    System.out.println("Phone: ");
    String phone = scanner.nextLine();

    System.out.println("Address: ");
    String address = scanner.nextLine();

    System.out.println("Username: ");
    String username = scanner.nextLine();

    System.out.println("Password: ");
    String password = scanner.nextLine();

    Customers customers = new Customers(
            0,
            name,
            phone,
            address,
            username,
            password
    );

    customersService.register(customers);

    System.out.println("Registration successful");
}


void customersLogin() {

    System.out.println("Username: ");
    String username = scanner.nextLine();

    System.out.println("Password: ");
    String password = scanner.nextLine();

    Customers customers = customersService.login(username, password);

    System.out.println("Welcome " + customers.getName());

    customerMenu();
}

void customerMenu() {

    while (true) {

        System.out.println("1. Show Fruits");
        System.out.println("2. Logout");
        System.out.println("Choose:");

        if (!scanner.hasNextInt()) {
            System.out.println("Please enter a number");
            scanner.nextLine();
            continue;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {

            case 1:
                showFruits();
                break;

            case 2:
                return;

            default:
                System.out.println("Invalid choice");
        }
    }
}




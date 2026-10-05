# Fast Car Rent

A desktop car rental system written in Java with a Swing graphical interface. Customers can rent and return cars, and staff can see which cars are available and which are currently rented.

## Features

- **Rent a Car**: enter your name, NID and phone number, pick an available car, choose the number of rental days, and confirm the total price.
- **Return a Car**: return a car by entering the NID used when renting.
- **Available Cars**: list every car that is currently free to rent.
- **Rented Cars**: view all active rentals with the car, customer details, days and total price.

## Built-in Cars

| ID   | Brand    | Model  | Price per day |
|------|----------|--------|---------------|
| C001 | Toyota   | Camry  | $60           |
| C002 | Honda    | Accord | $70           |
| C003 | Mahindra | Thar   | $150          |

## Project Structure

| File / Class        | Purpose                                                        |
|---------------------|----------------------------------------------------------------|
| `Main.java`         | Contains all classes and the `main` method that starts the app |
| `Car`               | Car details, availability and price calculation                |
| `Customer`          | Customer name, NID and phone number                            |
| `Rental`            | Links a car, a customer and the number of rental days          |
| `CarRentalSystem`   | Core logic for adding cars, renting and returning              |
| `CarRentalSystemGUI`| Swing window and dialogs                                       |
| `logo.png`          | Window icon                                                    |

## Requirements

- Java Development Kit (JDK) 8 or newer

## How to Run

```bash
git clone https://github.com/mahir2345/Car_Rent.git
cd Car_Rent
javac Main.java
java Main
```

Run the commands from the project folder so the app can find `logo.png`.

## Notes

- Data is kept in memory only, so rentals are cleared when the app closes.
- The project can also be opened in IntelliJ IDEA using the included `fast car rent.iml` file.

## Author

Mahir Khan ([@mahir2345](https://github.com/mahir2345))

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Car {
    private String carId;
    private String brand;
    private String model;
    private double pricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double pricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double calculatePrice(int rentalDays) {
        return pricePerDay * rentalDays;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return carId + " - " + brand + " " + model;
    }
}

class Customer {
    private String customerId;
    private String name;
    private String nid;
    private int number;

    public Customer(String customerId, String name, String nid, int number) {
        this.customerId = customerId;
        this.name = name;
        this.nid = nid;
        this.number = number;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getNid() {
        return nid;
    }
    public int getNumber() {
        return number;
    }
}

class Rental {
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}

class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            JOptionPane.showMessageDialog(null, "Car is not available for rent.");
        }
    }

    public void returnCar(String nid) {
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCustomer().getNid().equals(nid)) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentalToRemove.getCar().returnCar();
            rentals.remove(rentalToRemove);
            JOptionPane.showMessageDialog(null, "Car returned successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No rental record found for this NID.");
        }
    }

    public List<Car> getAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    public Car getCarById(String carId) {
        for (Car car : cars) {
            if (car.getCarId().equals(carId) && car.isAvailable()) {
                return car;
            }
        }
        return null;
    }

    public List<Rental> getRentals() {
        return rentals;
    }
}

class CarRentalSystemGUI {
    private CarRentalSystem rentalSystem;

    public CarRentalSystemGUI(CarRentalSystem rentalSystem) {
        this.rentalSystem = rentalSystem;

        JFrame frame = new JFrame("Fast Car Rental");
        ImageIcon imgi = new ImageIcon("logo.png");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400); // Increased size to accommodate new buttons
        frame.setLayout(new BorderLayout());
        frame.setIconImage(imgi.getImage());
        frame.getContentPane().setBackground(new Color(35, 25, 112));

        // Create a panel to hold the label
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(new Color(35, 25, 112)); // Match the frame background
        labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel l = new JLabel();
        l.setText("Welcome to Fast Car Rent");
        l.setForeground(Color.white);
        l.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 20));

        labelPanel.add(l);
        frame.add(labelPanel, BorderLayout.NORTH); // Add label panel to the top (NORTH)

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1)); // Adjusted grid layout for more buttons

        JButton rentCarButton = new JButton("Rent a Car");
        JButton returnCarButton = new JButton("Return a Car");
        JButton availableCarsButton = new JButton("Available Cars");
        JButton rentedCarsButton = new JButton("Rented Cars");

        panel.add(rentCarButton);
        panel.add(returnCarButton);
        panel.add(availableCarsButton);
        panel.add(rentedCarsButton);

        frame.add(panel, BorderLayout.CENTER);

        rentCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rentCar();
            }
        });

        returnCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnCar();
            }
        });

        availableCarsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAvailableCars();
            }
        });

        rentedCarsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRentedCars();
            }
        });

        frame.setVisible(true);
    }

    private void rentCar() {
        List<Car> availableCars = rentalSystem.getAvailableCars();

        if (availableCars.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No cars available for rent.");
            return;
        }

        String name = JOptionPane.showInputDialog("Enter your name:");
        String nid = JOptionPane.showInputDialog("Enter your NID:");
        int number = Integer.parseInt(JOptionPane.showInputDialog("Enter your pone number:"));


        JComboBox<Car> carComboBox = new JComboBox<>(availableCars.toArray(new Car[0]));
        JTextField rentalDaysField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Select a car:"));
        panel.add(carComboBox);
        panel.add(new JLabel("Enter rental days:"));
        panel.add(rentalDaysField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Rent a Car", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Car selectedCar = (Car) carComboBox.getSelectedItem();
            int rentalDays = Integer.parseInt(rentalDaysField.getText());
            double totalPrice = selectedCar.calculatePrice(rentalDays);

            int confirm = JOptionPane.showConfirmDialog(null, "Total Price: $" + totalPrice + "\nConfirm rental?", "Confirm Rental", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                Customer newCustomer = new Customer("CUS" + (rentalSystem.getAvailableCars().size() + 1), name, nid, number);
                rentalSystem.addCustomer(newCustomer);
                rentalSystem.rentCar(selectedCar, newCustomer, rentalDays);
                JOptionPane.showMessageDialog(null, "Car rented successfully.");
            }
        }
    }

    private void returnCar() {
        String nid = JOptionPane.showInputDialog("Enter your NID:");
        rentalSystem.returnCar(nid);
    }

    private void showAvailableCars() {
        List<Car> availableCars = rentalSystem.getAvailableCars();
        if (availableCars.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No cars available.");
        } else {
            StringBuilder carList = new StringBuilder("Available Cars:\n");
            for (Car car : availableCars) {
                carList.append(car.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, carList.toString());
        }
    }

    private void showRentedCars() {
        List<Rental> rentals = rentalSystem.getRentals();
        if (rentals.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No cars rented currently.");
        } else {
            StringBuilder rentalDetails = new StringBuilder("Rented Cars Details:\n");
            for (Rental rental : rentals) {
                Car car = rental.getCar();
                Customer customer = rental.getCustomer();
                rentalDetails.append("Car: ").append(car.getBrand()).append(" ").append(car.getModel())
                        .append(", Customer: ").append(customer.getName()).append(", NID: ").append(customer.getNid()).append(", Phone Number: ").append(customer.getNumber())
                        .append(", Days: ").append(rental.getDays())
                        .append(", Total Price: $").append(car.calculatePrice(rental.getDays()))
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, rentalDetails.toString());
        }
    }
}



public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Car car1 = new Car("C001", "Toyota", "Camry", 60.0);
        Car car2 = new Car("C002", "Honda", "Accord", 70.0);
        Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);

        new CarRentalSystemGUI(rentalSystem);
    }
}

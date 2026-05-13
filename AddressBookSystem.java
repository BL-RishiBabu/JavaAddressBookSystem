import java.util.Scanner;

public class AddressBookSystem {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter First Name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter Last Name:");
        String lastName = scanner.nextLine();

        System.out.println("Enter Address:");
        String address = scanner.nextLine();

        System.out.println("Enter City:");
        String city = scanner.nextLine();

        System.out.println("Enter State:");
        String state = scanner.nextLine();

        System.out.println("Enter Zip:");
        String zip = scanner.nextLine();

        System.out.println("Enter Phone Number:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        Contact person = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);

        System.out.println("\n--- Contact Added Successfully ---");
        System.out.println(person);

        scanner.close();
    }
}

class Contact {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;

    public Contact(String firstName, String lastName, String address, String city, 
                   String state, String zip, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact Details:" + 
               "\nName: " + firstName + " " + lastName + 
               "\nAddress: " + address + ", " + city + ", " + state + " - " + zip + 
               "\nPhone: " + phoneNumber + 
               "\nEmail: " + email + "\n";
    }
}
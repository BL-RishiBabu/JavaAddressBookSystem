import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    private String firstName, lastName, address, city, state, zip, phoneNumber, email;

    public Contact(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
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
        return "Name: " + firstName + " " + lastName + " | Phone: " + phoneNumber + " | Email: " + email;
    }
}

class AddressBook {
    private ArrayList<Contact> contactList = new ArrayList<>();

    public void addContact(Contact contact) {
        contactList.add(contact);
    }

    public void displayBook() {
        System.out.println("\n--- Current Address Book ---");
        for (Contact c : contactList) {
            System.out.println(c);
        }
    }
}

public class AddressBookSystem {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        AddressBook addressBook = new AddressBook();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter details for a new contact:");
        System.out.print("First Name: "); String fName = sc.nextLine();
        System.out.print("Last Name: "); String lName = sc.nextLine();
        System.out.print("Address: "); String addr = sc.nextLine();
        System.out.print("City: "); String city = sc.nextLine();
        System.out.print("State: "); String state = sc.nextLine();
        System.out.print("Zip: "); String zip = sc.nextLine();
        System.out.print("Phone: "); String phone = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();

        Contact newPerson = new Contact(fName, lName, addr, city, state, zip, phone, email);
        addressBook.addContact(newPerson);

        addressBook.displayBook();
        sc.close();
    }
}
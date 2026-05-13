import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    private String firstName, lastName, address, city, state, zip, phoneNumber, email;

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

    public String getFirstName() { return firstName; }
    public void setAddress(String address) { this.address = address; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setZip(String zip) { this.zip = zip; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + " | City: " + city + " | Phone: " + phoneNumber;
    }
}

class AddressBook {
    private ArrayList<Contact> contactList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void addContact(Contact contact) {
        contactList.add(contact);
        System.out.println("Contact added successfully!");
    }

    public void askToAddMultipleContacts() {
        boolean adding = true;
        while (adding) {
            System.out.println("\nEnter Details for New Contact:");
            System.out.print("First Name: "); String fName = sc.nextLine();
            System.out.print("Last Name: "); String lName = sc.nextLine();
            System.out.print("Address: "); String addr = sc.nextLine();
            System.out.print("City: "); String city = sc.nextLine();
            System.out.print("State: "); String state = sc.nextLine();
            System.out.print("Zip: "); String zip = sc.nextLine();
            System.out.print("Phone: "); String phone = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();

            addContact(new Contact(fName, lName, addr, city, state, zip, phone, email));

            System.out.print("Do you want to add another contact? (yes/no): ");
            String response = sc.nextLine();
            if (response.equalsIgnoreCase("no")) {
                adding = false;
            }
        }
    }

    public void editContact(String name) {
        boolean found = false;
        for (Contact contact : contactList) {
            if (contact.getFirstName().equalsIgnoreCase(name)) {
                found = true;
                boolean editing = true;
                while (editing) {
                    System.out.println("\nEditing " + contact.getFirstName() + ". Select field:");
                    System.out.println("1. Address\n2. City\n3. State\n4. Zip\n5. Phone\n6. Email\n7. Done");
                    int choice = sc.nextInt(); sc.nextLine();
                    switch (choice) {
                        case 1: System.out.print("New Address: "); contact.setAddress(sc.nextLine()); break;
                        case 2: System.out.print("New City: "); contact.setCity(sc.nextLine()); break;
                        case 3: System.out.print("New State: "); contact.setState(sc.nextLine()); break;
                        case 4: System.out.print("New Zip: "); contact.setZip(sc.nextLine()); break;
                        case 5: System.out.print("New Phone: "); contact.setPhoneNumber(sc.nextLine()); break;
                        case 6: System.out.print("New Email: "); contact.setEmail(sc.nextLine()); break;
                        case 7: editing = false; break;
                    }
                }
                break;
            }
        }
        if (!found) System.out.println("Contact not found.");
    }

    public void displayBook() {
        if (contactList.isEmpty()) System.out.println("Address Book is empty.");
        else {
            System.out.println("\n--- All Contacts ---");
            contactList.forEach(System.out::println);
        }
    }
}

public class AddressBookSystem {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        AddressBook addressBook = new AddressBook();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            // Updated Menu to support UC-5 flow
            System.out.println("\n1. Add Contact(s)\n2. Edit Contact\n3. Display All\n4. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addressBook.askToAddMultipleContacts();
                    break;
                case 2:
                    System.out.print("Enter First Name to edit: ");
                    addressBook.editContact(sc.nextLine());
                    break;
                case 3:
                    addressBook.displayBook();
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }
        sc.close();
    }
}
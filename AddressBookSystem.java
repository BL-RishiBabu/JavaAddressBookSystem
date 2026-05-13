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
    }

    public void editContact(String name) {
        boolean found = false;
        for (Contact contact : contactList) {
            if (contact.getFirstName().equalsIgnoreCase(name)) {
                found = true;
                boolean editing = true;
                
                while (editing) {
                    System.out.println("\nSelect field to edit for " + contact.getFirstName() + ":");
                    System.out.println("1. Address\n2. City\n3. State\n4. Zip\n5. Phone\n6. Email\n7. Done Editing");
                    int choice = sc.nextInt();
                    sc.nextLine(); // consume newline

                    switch (choice) {
                        case 1:
                            System.out.print("Enter new Address: ");
                            contact.setAddress(sc.nextLine());
                            break;
                        case 2:
                            System.out.print("Enter new City: ");
                            contact.setCity(sc.nextLine());
                            break;
                        case 3:
                            System.out.print("Enter new State: ");
                            contact.setState(sc.nextLine());
                            break;
                        case 4:
                            System.out.print("Enter new Zip: ");
                            contact.setZip(sc.nextLine());
                            break;
                        case 5:
                            System.out.print("Enter new Phone: ");
                            contact.setPhoneNumber(sc.nextLine());
                            break;
                        case 6:
                            System.out.print("Enter new Email: ");
                            contact.setEmail(sc.nextLine());
                            break;
                        case 7:
                            editing = false;
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                    if (choice >= 1 && choice <= 6) {
                        System.out.println("Field updated successfully!");
                    }
                }
                break;
            }
        }
        if (!found) System.out.println("Contact not found.");
    }

    public void deleteContact(String name) {
        boolean removed = contactList.removeIf(contact -> contact.getFirstName().equalsIgnoreCase(name));
        System.out.println("Contact" + (removed ? " deleted successfully!" : " not found."));
    }

    public void displayBook() {
        if (contactList.isEmpty()) System.out.println("Address Book is empty.");
        else contactList.forEach(System.out::println);
    }
}

public class AddressBookSystem {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        AddressBook addressBook = new AddressBook();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. Add Contact\n2. Edit Contact\n3. Display\n4. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("First Name: "); String fName = sc.nextLine();
                    System.out.print("Last Name: "); String lName = sc.nextLine();
                    System.out.print("Address: "); String addr = sc.nextLine();
                    System.out.print("City: "); String city = sc.nextLine();
                    System.out.print("State: "); String state = sc.nextLine();
                    System.out.print("Zip: "); String zip = sc.nextLine();
                    System.out.print("Phone: "); String phone = sc.nextLine();
                    System.out.print("Email: "); String email = sc.nextLine();
                    addressBook.addContact(new Contact(fName, lName, addr, city, state, zip, phone, email));
                    break;
                case 2:
                    System.out.print("Enter the First Name of the contact to edit: ");
                    String nameToEdit = sc.nextLine();
                    addressBook.editContact(nameToEdit);
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
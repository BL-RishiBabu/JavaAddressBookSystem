import java.util.*;

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
    public String getLastName() { return lastName; }
    public void setAddress(String address) { this.address = address; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setZip(String zip) { this.zip = zip; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact other = (Contact) obj;
        return firstName.equalsIgnoreCase(other.firstName)
                && lastName.equalsIgnoreCase(other.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName.toLowerCase(), lastName.toLowerCase());
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + " | City: " + city + " | Phone: " + phoneNumber;
    }
}

class AddressBook {
    private ArrayList<Contact> contactList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void addContact() {
        System.out.println("\nEnter Details for New Contact:");
        System.out.print("First Name: "); String fName = sc.nextLine();
        System.out.print("Last Name: "); String lName = sc.nextLine();
        System.out.print("Address: "); String addr = sc.nextLine();
        System.out.print("City: "); String city = sc.nextLine();
        System.out.print("State: "); String state = sc.nextLine();
        System.out.print("Zip: "); String zip = sc.nextLine();
        System.out.print("Phone: "); String phone = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();

        Contact newContact = new Contact(fName, lName, addr, city, state, zip, phone, email);
        boolean duplicate = contactList.stream().anyMatch(contact -> contact.equals(newContact));
        if (duplicate) {
            System.out.println("Duplicate contact found. Contact not added.");
            return;
        }

        contactList.add(newContact);
        System.out.println("Contact added successfully!");
    }

    public void editContact(String name) {
        for (Contact contact : contactList) {
            if (contact.getFirstName().equalsIgnoreCase(name)) {
                System.out.println("Updating " + contact.getFirstName() + ". Enter new City:");
                contact.setCity(sc.nextLine());
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void displayBook() {
        if (contactList.isEmpty()) System.out.println("Address Book is empty.");
        else contactList.forEach(System.out::println);
    }
}

public class AddressBookSystem {
    private static Map<String, AddressBook> addressBookMap = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Address Book System ---");
            System.out.println("1. Create New Address Book");
            System.out.println("2. Access Existing Address Book");
            System.out.println("3. Display All Address Books");
            System.out.println("4. Exit");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter unique name for the new Address Book: ");
                    String bookName = sc.nextLine();
                    if (addressBookMap.containsKey(bookName)) {
                        System.out.println("An Address Book with this name already exists.");
                    } else {
                        addressBookMap.put(bookName, new AddressBook());
                        System.out.println("Address Book '" + bookName + "' created.");
                    }
                    break;
                case 2:
                    System.out.print("Enter the name of the Address Book to access: ");
                    String name = sc.nextLine();
                    AddressBook currentBook = addressBookMap.get(name);
                    if (currentBook != null) {
                        accessBookMenu(currentBook);
                    } else {
                        System.out.println("Address Book not found.");
                    }
                    break;
                case 3:
                    if (addressBookMap.isEmpty()) System.out.println("No Address Books available.");
                    else addressBookMap.keySet().forEach(key -> System.out.println("- " + key));
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }
    }

    private static void accessBookMenu(AddressBook book) {
        boolean back = false;
        while (!back) {
            System.out.println("\n1. Add Contact\n2. Edit Contact\n3. Display Contacts\n4. Back to Main Menu");
            int choice = sc.nextInt(); sc.nextLine();
            switch (choice) {
                case 1: book.addContact(); break;
                case 2: 
                    System.out.print("Enter First Name to edit: ");
                    book.editContact(sc.nextLine()); 
                    break;
                case 3: book.displayBook(); break;
                case 4: back = true; break;
            }
        }
    }
}
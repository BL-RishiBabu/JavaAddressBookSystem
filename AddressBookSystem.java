import java.io.*;
import java.util.*;
import java.util.stream.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

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
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZip() { return zip; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
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
    private Map<String, List<Contact>> cityPersonMap = new HashMap<>();
    private Map<String, List<Contact>> statePersonMap = new HashMap<>();
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
        addToCityDictionary(city, newContact);
        addToStateDictionary(state, newContact);
        System.out.println("Contact added successfully!");
    }

    public void editContact(String name) {
        for (Contact contact : contactList) {
            if (contact.getFirstName().equalsIgnoreCase(name)) {
                System.out.println("Updating " + contact.getFirstName() + ". Enter new City:");
                String oldCity = contact.getCity();
                String newCity = sc.nextLine();
                if (!newCity.equalsIgnoreCase(oldCity)) {
                    removeFromCityDictionary(oldCity, contact);
                    contact.setCity(newCity);
                    addToCityDictionary(newCity, contact);
                } else {
                    contact.setCity(newCity);
                }
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public List<Contact> searchByCity(String city) {
        return cityPersonMap.getOrDefault(city.toLowerCase(), Collections.emptyList());
    }

    public List<Contact> searchByState(String state) {
        return statePersonMap.getOrDefault(state.toLowerCase(), Collections.emptyList());
    }

    private void addToCityDictionary(String city, Contact contact) {
        cityPersonMap.computeIfAbsent(city.toLowerCase(), key -> new ArrayList<>()).add(contact);
    }

    private void addToStateDictionary(String state, Contact contact) {
        statePersonMap.computeIfAbsent(state.toLowerCase(), key -> new ArrayList<>()).add(contact);
    }

    private void removeFromCityDictionary(String city, Contact contact) {
        List<Contact> contacts = cityPersonMap.get(city.toLowerCase());
        if (contacts != null) {
            contacts.remove(contact);
            if (contacts.isEmpty()) {
                cityPersonMap.remove(city.toLowerCase());
            }
        }
    }

    private void removeFromStateDictionary(String state, Contact contact) {
        List<Contact> contacts = statePersonMap.get(state.toLowerCase());
        if (contacts != null) {
            contacts.remove(contact);
            if (contacts.isEmpty()) {
                statePersonMap.remove(state.toLowerCase());
            }
        }
    }

    public void displayBook() {
        if (contactList.isEmpty()) System.out.println("Address Book is empty.");
        else contactList.forEach(System.out::println);
    }

    public void sortContactsByName() {
        if (contactList.isEmpty()) {
            System.out.println("Address Book is empty.");
            return;
        }
        System.out.println("Contacts sorted by name:");
        contactList.stream()
                .sorted(Comparator.comparing(Contact::getFirstName, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Contact::getLastName, String.CASE_INSENSITIVE_ORDER))
                .forEach(System.out::println);
    }

    public void sortContactsByCity() {
        if (contactList.isEmpty()) {
            System.out.println("Address Book is empty.");
            return;
        }
        System.out.println("Contacts sorted by city:");
        contactList.stream()
                .sorted(Comparator.comparing(Contact::getCity, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Contact::getFirstName, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Contact::getLastName, String.CASE_INSENSITIVE_ORDER))
                .forEach(System.out::println);
    }

    public void sortContactsByState() {
        if (contactList.isEmpty()) {
            System.out.println("Address Book is empty.");
            return;
        }
        System.out.println("Contacts sorted by state:");
        contactList.stream()
                .sorted(Comparator.comparing(Contact::getState, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Contact::getFirstName, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Contact::getLastName, String.CASE_INSENSITIVE_ORDER))
                .forEach(System.out::println);
    }

    public void sortContactsByZip() {
        if (contactList.isEmpty()) {
            System.out.println("Address Book is empty.");
            return;
        }
        System.out.println("Contacts sorted by zip:");
        contactList.stream()
                .sorted(Comparator.comparing(Contact::getZip, Comparator.nullsLast(String::compareTo))
                        .thenComparing(Contact::getFirstName, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Contact::getLastName, String.CASE_INSENSITIVE_ORDER))
                .forEach(System.out::println);
    }

    public void writeToFile(String filePath) throws IOException {
        try (Writer writer = new FileWriter(filePath);
             CSVWriter csvWriter = new CSVWriter(writer)) {
            for (Contact contact : contactList) {
                String[] data = {
                        contact.getFirstName(),
                        contact.getLastName(),
                        contact.getAddress(),
                        contact.getCity(),
                        contact.getState(),
                        contact.getZip(),
                        contact.getPhoneNumber(),
                        contact.getEmail()
                };
                csvWriter.writeNext(data);
            }
        }
    }

    public void readFromFile(String filePath) throws IOException {
        try (Reader reader = new FileReader(filePath);
             CSVReader csvReader = new CSVReader(reader)) {
            String[] nextRecord;
            try {
                while ((nextRecord = csvReader.readNext()) != null) {
                    if (nextRecord.length == 8) {
                        Contact contact = new Contact(nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], nextRecord[4], nextRecord[5], nextRecord[6], nextRecord[7]);
                        if (!contactList.contains(contact)) {
                            contactList.add(contact);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error parsing CSV: " + e.getMessage());
            }
        }
        rebuildDictionaries();
    }

    private void rebuildDictionaries() {
        cityPersonMap.clear();
        statePersonMap.clear();
        for (Contact contact : contactList) {
            addToCityDictionary(contact.getCity(), contact);
            addToStateDictionary(contact.getState(), contact);
        }
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
            System.out.println("4. Search Persons by City or State");
            System.out.println("5. Count Persons by City or State");
            System.out.println("6. Save Address Book to File");
            System.out.println("7. Load Address Book from File");
            System.out.println("8. Exit");
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
                    searchPersonAcrossBooks();
                    break;
                case 5:
                    countPersonsAcrossBooks();
                    break;
                case 6:
                    saveBookToFile();
                    break;
                case 7:
                    loadBookFromFile();
                    break;
                case 8:
                    exit = true;
                    break;
            }
        }
    }

    private static void saveBookToFile() {
        System.out.print("Enter the name of the Address Book to save: ");
        String bookName = sc.nextLine();
        AddressBook book = addressBookMap.get(bookName);
        if (book == null) {
            System.out.println("Address Book not found.");
            return;
        }
        System.out.print("Enter file name to save to (for example, " + bookName + ".txt): ");
        String filePath = sc.nextLine();
        try {
            book.writeToFile(filePath);
            System.out.println("Address Book saved to " + filePath + " successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void loadBookFromFile() {
        System.out.print("Enter the name of the Address Book to load into: ");
        String bookName = sc.nextLine();
        System.out.print("Enter file path to load from: ");
        String filePath = sc.nextLine();
        AddressBook book = addressBookMap.computeIfAbsent(bookName, key -> new AddressBook());
        try {
            book.readFromFile(filePath);
            System.out.println("Address Book loaded from " + filePath + " into '" + bookName + "'.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    private static void searchPersonAcrossBooks() {
        System.out.println("\nSearch by:");
        System.out.println("1. City");
        System.out.println("2. State");
        int choice = sc.nextInt(); sc.nextLine();
        System.out.print("Enter search value: ");
        String searchValue = sc.nextLine();

        List<String> foundContacts = addressBookMap.entrySet().stream()
                .flatMap(entry -> {
                    AddressBook book = entry.getValue();
                    if (choice == 1) {
                        return book.searchByCity(searchValue).stream()
                                .map(contact -> "[" + entry.getKey() + "] " + contact);
                    } else if (choice == 2) {
                        return book.searchByState(searchValue).stream()
                                .map(contact -> "[" + entry.getKey() + "] " + contact);
                    }
                    return Stream.<String>empty();
                })
                .collect(Collectors.toList());

        if (foundContacts.isEmpty()) {
            System.out.println("No contacts found for '" + searchValue + "'.");
        } else {
            System.out.println("Found contacts:");
            foundContacts.forEach(System.out::println);
        }
    }

    private static void countPersonsAcrossBooks() {
        System.out.println("\nCount by:");
        System.out.println("1. City");
        System.out.println("2. State");
        int choice = sc.nextInt(); sc.nextLine();
        System.out.print("Enter value: ");
        String value = sc.nextLine();

        long totalCount = addressBookMap.values().stream()
                .flatMap(book -> {
                    if (choice == 1) {
                        return book.searchByCity(value).stream();
                    } else if (choice == 2) {
                        return book.searchByState(value).stream();
                    }
                    return Stream.<Contact>empty();
                })
                .count();

        String category = choice == 1 ? "city" : "state";
        System.out.println("Total contacts in " + category + " '" + value + "': " + totalCount);
    }

    private static void accessBookMenu(AddressBook book) {
        boolean back = false;
        while (!back) {
            System.out.println("\n1. Add Contact\n2. Edit Contact\n3. Display Contacts\n4. Sort Contacts by Name\n5. Sort Contacts by City\n6. Sort Contacts by State\n7. Sort Contacts by Zip\n8. Back to Main Menu");
            int choice = sc.nextInt(); sc.nextLine();
            switch (choice) {
                case 1: book.addContact(); break;
                case 2:
                    System.out.print("Enter First Name to edit: ");
                    book.editContact(sc.nextLine());
                    break;
                case 3: book.displayBook(); break;
                case 4: book.sortContactsByName(); break;
                case 5: book.sortContactsByCity(); break;
                case 6: book.sortContactsByState(); break;
                case 7: book.sortContactsByZip(); break;
                case 8: back = true; break;
            }
        }
    }
}
# Address Book System

Welcome to the Address Book System. This is a Java-based console application designed to manage personal contacts efficiently using Object-Oriented Programming (OOP) principles.

## 🚀 Project Overview

The system allows users to create, view, edit, and delete contact information. The project is developed using IntelliJ IDEA and follows a strict Git Branching Strategy, where each feature is developed in a dedicated branch before being merged into the master branch.

## 🛠 Features (Use Cases)

### UC 1: Contact Creation
- Ability to create a `Contact` object.
- Fields: First Name, Last Name, Address, City, State, Zip, Phone Number, and Email.
- Ensures clean code hygiene and naming conventions.

### UC 2: Add New Contact
- Integrated `AddressBookMain` class to handle user input via the console.
- Uses OOP concepts to manage the relationship between the AddressBook and Contact persons.

### UC 3: Edit Existing Contact
- Functionality to locate a contact by their name.
- Allows the user to update specific details of an existing contact through the console.

### UC 4: Delete Contact
- Ability to remove a person's record from the Address Book using their name.

## 💻 Tech Stack

- Language: Java 17+
- IDE: IntelliJ IDEA
- Version Control: Git

## 📂 Project Structure

```
AddressBook/
├── src/
│   ├── com.bridgelabz.addressbook/
│   │   ├── Contact.java            # POJO class for contact details
│   │   ├── AddressBook.java       # Logic for managing contacts
│   │   └── AddressBookMain.java   # Entry point & Console UI
└── README.md
```

## 🌿 Git Workflow

This project follows a feature-branch workflow:

1. START on master branch.
2. Create a branch for each UC (e.g., `git checkout -b uc1-create-contact`).
3. Commit changes with meaningful messages.
4. Merge the branch back into master after completion.

## 📝 How to Run

Clone the repository:

```bash
git clone https://github.com/your-username/address-book-system.git
```

Open the project in IntelliJ IDEA.

Navigate to `AddressBookMain.java`.

Run the main method.

Follow the console prompts to manage your contacts.

package se.iths.asli.biblioteksapp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Lists lists = new Lists();
        list(lists);

        while(true){
            boolean loggedIn = false;
            System.out.println("Ange användarnamn:");
            String user = scanner.nextLine();

            System.out.println("Ange Lösen");
            String password = scanner.nextLine();

            Borrower currentBorrower = confirmUser(user, password, lists);
            if(currentBorrower == null){
                System.out.println("Felaktigt");
            }
            loggedIn = true;

            //Testa vidare, blir fel i output
            System.out.println("Inloggad: " + currentBorrower);

            while(loggedIn){
                menu();
                String chosenOption = scanner.nextLine();

                switch(chosenOption){
                    case "1":
                        //Testa vidare, blir fel i output
                        listAllBooks(lists);
                        break;
                    case "2":
                        borrowBook(scanner, lists, currentBorrower);
                        break;
                    case "3":
                        returnBook(scanner, lists, currentBorrower);
                        break;
                    case "4":
                        //Testa vidare, blir fel i output
                        listUserBooks(currentBorrower);
                        break;
                    case "5":
                        loggedIn = false;
                        break;
                    case "6":
                        //Avslutar ej korrekt
                        System.out.println("Avslutar");
                        loggedIn = false;
                        break;
                    default:
                        System.out.println("Ogiltigt val");
                }

            }
        }


    }


    static void list(Lists lists){
        Author author1 = new Author("Astrid Lindgren");
        Author author2 = new Author("Tove Jansson");

        lists.books.add(new Book("Pippi Långstrump","424242",author1));
        lists.books.add(new Book("Sommarboken", "241451", author2));
        lists.books.add(new Book("TBD","4111",new Author("Aslihan Taskin")));

        lists.borrowers.add(new Borrower("Amanda","1234","Amanda Thorildsson"));
        lists.borrowers.add(new Borrower("Jonte","1234ärdumt","Jonathan Jonsson"));

    }

    static Borrower confirmUser(String username, String password, Lists lists){
        for(Borrower borrow : lists.getBorrowers()){
            if(borrow.getUsername().equals(username) && borrow.checkPassword(password)){
                return borrow;
            }
        }
        return null;
    }

    static void listAllBooks(Lists list){
        System.out.println("Här är alla böcker:");
        for(Book book: list.books){
            String availability = list.isBookBorrowed(book) ? "Ej tillgänglig" : "Tillgänglig";
            System.out.println(book + " Tillgänglig: " + availability);
        }
    }

    static void menu(){
        System.out.println("Välj ett alternativ:");
        System.out.println("1. Visa alla böcker");
        System.out.println("2. Låna bok");
        System.out.println("3. Returnera bok");
        System.out.println("4. Visa mina lån");
        System.out.println("5. Logga ut");
        System.out.println("6. Avsluta program");
    }

    static void borrowBook(Scanner scanner, Lists lists, Borrower currentUser){
        System.out.println("ISBN: ");
        String isbn = scanner.nextLine();
        Book book = lists.findBookByISBN(isbn);
        if(book == null){
            System.out.println("Ingen bok hittades");
        }
        else if(lists.isBookBorrowed(book)){
            System.out.println("Boken är utlånad");
        }
        currentUser.borrowBook(book);
        System.out.println("Du lånade: " + book.getTitle());
    }

    static void returnBook(Scanner scanner, Lists lists, Borrower currentBorrower){
        System.out.println("Ange ISBN:");
        String isbn = scanner.nextLine();
        Book book = lists.findBookByISBN(isbn);
        if(book == null){
            System.out.println("Hittade ingen bok");
        }
        else if(!currentBorrower.hasBorrowed(book)){
            System.out.println("Inte lånad");
        }
        currentBorrower.returnBook(book);
        System.out.println("Du returnerade: " + book.getTitle());
    }

    static void listUserBooks(Borrower currentBorrower){
        if(currentBorrower.borrowedBooks.isEmpty()){
            System.out.println("Inga lånade böcker");
        }
        for(Book book: currentBorrower.borrowedBooks){
            System.out.println(book);
        }
    }

}


package se.iths.asli.biblioteksapp;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Borrower {

    ArrayList<Book> borrowedBooks = new ArrayList<>();

    private String username;
    private String password;
    private String name;
    private LocalDateTime loanDate;

    public Borrower(String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public boolean checkPassword(String passAttempt){
        return password.equals(passAttempt);
    }

    public String getUsername(){
        return username;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Book> getLoans(){
        return borrowedBooks;
    }

    public boolean borrowBook(Book book){
        if(borrowedBooks.contains(book)){
            return false;
        }
        else{
            borrowedBooks.add(book);
            return true;
        }

    }

    public void returnBook(Book book){
        borrowedBooks.remove(book);
    }

    public boolean hasBorrowed(Book book){
        return borrowedBooks.contains(book);
    }

}

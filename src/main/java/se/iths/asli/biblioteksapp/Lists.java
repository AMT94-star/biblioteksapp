package se.iths.asli.biblioteksapp;

import java.util.ArrayList;
import java.util.List;

public class Lists {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Borrower> borrowers = new ArrayList<>();

    public void addBooks(Book book){
        this.books.add(book);
    }

    public void addBorrowers(Borrower borrowers){
        this.borrowers.add(borrowers);
    }

    public List<Borrower> getBorrowers(){
        return borrowers;
    }

    public Book findBookByISBN(String isbn){
        for(Book book : books){
            if(book.getISBN().equalsIgnoreCase(isbn)){
                return book;
            }
        }
        return null;
    }

    public boolean isBookBorrowed(Book book){
        for(Borrower borr : borrowers){
            if(borr.borrowedBooks.contains(book)){
                return true;
            }
        }
        return false;
    }
}

package fr.d2factory.libraryapp.book;

import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.member.Member;
import fr.d2factory.libraryapp.member.ResidentMembers;
import fr.d2factory.libraryapp.member.StudentMembers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository {
    private Map<ISBN, Book> availableBooks = new HashMap<>();
    private Map<Book, LocalDate> borrowedBooks = new HashMap<>();

    public void addBooks(List<Book> books){

        if (books.size() > 0) {
            for (Book book : books) {
                getAvailableBooks().put(book.getIsbn(), book);
            }
        }
    }


    public Book findBook(long isbnCode) {
        for (Map.Entry<ISBN, Book> entry : availableBooks.entrySet()) {
            if (entry.getKey().getIsbnCode() == isbnCode)
                return entry.getValue();
        }

        return null;
    }

    public Map<ISBN, Book> getAvailableBooks() {
        return availableBooks;
    }

    public void setAvailableBooks(Map<ISBN, Book> availableBooks) {
        this.availableBooks = availableBooks;
    }

    public Map<Book, LocalDate> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Map<Book, LocalDate> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void saveBookBorrow(Book book, LocalDate borrowedAt) {
        if (book != null) {
            if (borrowedAt != null) {
                getBorrowedBooks().put(book, borrowedAt);
                getAvailableBooks().remove(book.getIsbn(), book);
            } else {
                getBorrowedBooks().remove(book);
                getAvailableBooks().put(book.getIsbn(), book);
            }
        }

    }

    public LocalDate findBorrowedBookDate(Book book) {

        return getBorrowedBooks().get(book);
    }


    public Book borrowBook(long isbnCode, Member member, LocalDate borrowedAt) throws HasLateBooksException {
        Book book = findBook(isbnCode);
        if (!member.isLate()) {
            if (book != null) {
                saveBookBorrow(findBook(isbnCode), borrowedAt);
            }
        } else {
            throw new HasLateBooksException(member);
        }
        return book;
    }


    public void returnBookBecauseOfLate(Book book, Member member, int numbersOfDays) {

        boolean isLate  = false;
        if (member instanceof StudentMembers) {

            if(numbersOfDays>30)
                isLate =true;


        }else if(member instanceof ResidentMembers){
            if(numbersOfDays>60)
                isLate =true;
        }

        member.payBook(numbersOfDays,isLate);

    }
}

package fr.d2factory.libraryapp.library;

import fr.d2factory.libraryapp.member.Member;

/**
 * This exception is thrown when a member who owns late books tries to borrow another book
 */
public class HasLateBooksException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public HasLateBooksException(Member member) {
        System.out.println("Member is Late");
    }
}


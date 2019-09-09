package fr.d2factory.libraryapp.member;

import java.time.LocalDate;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.Library;

public class StudentMembers extends Member {
    /**
     * the Date of entering to the Library
     * it's used to compute the seniority of the student
     */
    private LocalDate accessDate;

    public LocalDate getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(LocalDate accessDate) {
        this.accessDate = accessDate;
    }

    public StudentMembers(LocalDate accessDate, float wallet) {
        super(wallet, accessDate);
        this.accessDate = accessDate;

    }

    @Override
    public void payBook(int numberOfDays, boolean isLate) {
        float chargeDay = 0f;
        float extraCharge = 0f;
        float fullCharge = 0f;

        if (numberOfDays <= 30) {
            chargeDay = 0.10f;
            fullCharge = chargeDay * numberOfDays;
        } else {
            chargeDay = 0.10f;
            extraCharge = 0.15f;
            fullCharge = (chargeDay * 30)+extraCharge*(numberOfDays-30);

        }
        setLate(isLate);
        setWallet(getWallet() - fullCharge);

    }

}
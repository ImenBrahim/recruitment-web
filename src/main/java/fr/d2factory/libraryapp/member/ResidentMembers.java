package fr.d2factory.libraryapp.member;

import java.time.LocalDate;

public class ResidentMembers extends Member {
    public ResidentMembers(LocalDate accessDate,float wallet) {
        super(wallet,accessDate);
    }

    @Override
    public void payBook(int numberOfDays, boolean isLate) {
        float chargeDay = 0f;
        float extraCharge = 0f;
        float fullCharge = 0f;

        if (numberOfDays <= 60) {
            chargeDay = 0.10f;
            fullCharge = chargeDay * numberOfDays;
        } else {
            chargeDay = 0.10f;
            extraCharge = 0.20f;
            fullCharge = (chargeDay * 60)+extraCharge*(numberOfDays-60);
        }
        setLate(isLate);
        setWallet(getWallet()-fullCharge);

    }

}
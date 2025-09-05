package Model;

import java.time.LocalDate;

public class Transfer {

    private String transferId;
    private double amount;
    private LocalDate date;

    public Transfer(String id, double amount, LocalDate date)
    {
        this.transferId = id;
        this.amount = amount;
        this.date = date;
    }

    public Transfer(double amount, LocalDate date)
    {
        this.amount = amount;
        this.date = date;
    }

    public Transfer()
    {

    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

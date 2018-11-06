package by.bsuir.cinema.entity.user;

import java.math.BigDecimal;
import java.util.Objects;

public class Client extends User {
    private BigDecimal money;

    public Client() {
        super();
    }

    public Client(BigDecimal money) {
        this.money = money;
    }

    public Client(int id, String login, BigDecimal money) {
        super(id, login);
        this.money = money;
    }

    public Client(String login, TypeUser type, BigDecimal money) {
        super(login, type);
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(getMoney(), client.getMoney());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getMoney());
    }

    @Override
    public String toString() {
        return "Client{" +
                "money=" + money +
                '}';
    }
}
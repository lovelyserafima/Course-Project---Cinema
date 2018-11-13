package by.bsuir.cinema.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Session extends Entity {
    private int filmId;
    private String dateTime;
    private BigDecimal price;

    public Session(int id, int filmId, String dateTime, BigDecimal price) {
        super(id);
        this.filmId = filmId;
        this.dateTime = dateTime;
        this.price = price;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        if (!super.equals(o)) return false;
        Session session = (Session) o;
        return getFilmId() == session.getFilmId() &&
                Objects.equals(getDateTime(), session.getDateTime()) &&
                Objects.equals(getPrice(), session.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFilmId(), getDateTime(), getPrice());
    }

    @Override
    public String toString() {
        return "Session{" +
                "filmId=" + filmId +
                ", dateTime='" + dateTime + '\'' +
                ", price=" + price +
                '}';
    }
}

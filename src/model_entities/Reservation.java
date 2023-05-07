package model_entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

	private Integer roomNumber;
	private Date checkin;
	private Date checkout;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation() {
	}

	public Reservation(Integer roomNumber, Date checkin, Date checkout) {
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public long duration() {
		// calcula a diferença das duas datas em milisegundos
		long diff = checkout.getTime() - checkin.getTime();
		// converte os milisegundos em Dias
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public String updateDates(Date checkin, Date checkout) {

		Date now = new Date();
		if (checkin.before(now) || checkout.before(now)) {
			return "Reservation dates for update must be future dates";
		} 
		if (checkout.before(checkin)) {
			return "Check-out date must be after check-in date";
		}

		this.checkin = checkin;
		this.checkout = checkout;
		//se retorna nulo é porque nao deu nenhum erro
		return null; 
	}

	@Override
	public String toString() {
		return "Room " + roomNumber + ", checkin: " + sdf.format(checkin) + ", checkout: " + sdf.format(checkout) + ", "
				+ duration() + " nights";
	}
}

package servlet;

public class Customer {
	private String name;
	private String roomNumber;
	private String arrivalDate;
	private int stayDuration;
	String roomType;
	
	public Customer(String name, String roomNumber, String arrivalDate, int stayDuration, String roomType) {
		this.name = name;
		this.roomNumber = roomNumber;
		this.arrivalDate = arrivalDate;
		this.stayDuration = stayDuration;
		this.roomType = roomType;
	}
	
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("<tr>"
				+ "<td>" + name +"</td>"
				+ "<td>" + roomNumber + "</td>"
				+ "<td>" + arrivalDate + "</td>"
				+ "<td>" + stayDuration + "</td>"
				+ "<td>" + roomType + "</td>"
				+ "</tr>");
		return str.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public int getStayDuration() {
		return stayDuration;
	}

	public void setStayDuration(int stayDuration) {
		this.stayDuration = stayDuration;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	
}

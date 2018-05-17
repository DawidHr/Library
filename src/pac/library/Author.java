package pac.library;

import java.util.List;

public class Author {

private int id;
private String firstName;
private String lastName;
private List<Book> book;

public Author(int int1, String string, String string2) {
	id=int1;
	firstName=string;
	lastName=string2;
}

public int getId() {
	return id;
}

public String getFirstName() {
	return firstName;
}

public String getLastName() {
	return lastName;
}

public List<Book> getBook() {
	return book;
}

@Override
public String toString() {
	return "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName;
}

}

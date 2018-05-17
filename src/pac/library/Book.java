package pac.library;

import java.util.List;

public class Book {

private int id;
private String title;
private int year;
private boolean isAvailable;
private List<Author> author;

public Book(int int1, String string, int int2, boolean boolean1, List<Author> authorForIdBook) {
	id=int1;
	title=string;
	year=int2;
	isAvailable=boolean1;
	author=authorForIdBook;
}

public int getId() {
	return id;
}

public String getTitle() {
	return title;
}

public int getYear() {
	return year;
}

public boolean isAvailable() {
	return isAvailable;
}

public List<Author> getAuthor() {
	return author;
}

@Override
public String toString() {
	return "Book [id=" + id + ", title=" + title + ", year=" + year + ", isAvailable=" + isAvailable + ", author="
			+ author + "]";
}

}

package pac.library;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Library {
	DataBase db;

	Library() {
		db = new DataBase();
	}
	
	/*
	 * Dodawanie do Bazy danych Ksiazki
	 */
	
	//Dodawanie ksiazki
	public void addBookToDB(String title, int year, int id_author) {
		if(title=="") {
			System.out.println("Tytul jest pusty");
			return;
		}
		db.addNewBook(title, year, id_author);
	}
	
	/*
	 * Kasacja Ksiazki z Bazy danych
	 */
	
	//Kasacja ksiazki o id
	public void deleteBookFromLibrary(int id){
		if (db.isExsist(id)) {
			if (!db.isLent(id)) {
				System.out.print("Ksiazka jest wypozyczona. Przez : ");
				User user = db.getLendUser(id);
				System.out.println(user.getName() + " " + user.getName2());
			} else {
				db.deleteBook(id);
				System.out.println("Ksiazka o id =" + id + " zostala usunieta");
			}
		} else {
			System.out.println("Ksiazka o id =" + id + " nie istnieje");
		}
	}
	
	/*
	 * Wyœwietlanie listy ksi¹¿ek
	 */
	
	public void viewAllBooks() {
			List<Book> list = db.getAllBooks();
			if (list.isEmpty()) {
				System.out.println("System biblioteka nie posiada ksiazek");
			} else {
				for (Book c : list) {
					System.out.println(c.toString());
				}
				System.out.println("Wyporzyczonych ksiazek jest: " + db.getLentBooks());
				System.out.println("Dostepnych ksiazek jest: " + db.getAvailableBooks());
			}
	}
	
	/*
	 * Wypo¿yczanie ksia¿ki
	 */
	
	//Wypozyczanie ksiazki
	public void setLendBook(int id_book, int id_user) {

		Book book = db.getBookById(id_book);
		if (book == null) {
			System.out.println("Bledne id Ksiazki");
		} 
		else if(book.isAvailable() == false) {
			System.out.println("Ksiazka zostala wypozyczona");
		}
		else {
			User user = db.getUserById(id_user);
			if (user == null) {
				System.out.println("Uzytkownik o id=" + id_user + " nie istnieje");
			} else {
				db.setLendBook(id_book, id_user);
				db.setNoAvailableBook(id_book);
				System.out.println("Wyporzyczono");
			}

		}
	}
	
	/*
	 * Wyœwietlanie informacji o wybranej ksi¹¿ce
	 */
	
	
	public void getInformationAboutBook(int id) {
		Book book = db.getBookById(id);
		if (book != null) {
			System.out.println("Id: " + book.getId() + "\nTytul: " + book.getTitle() + "\nRok: " + book.getYear()
					+ "\nDostepne: " + book.isAvailable());
			if (!book.isAvailable()) {
				User user = db.getLendUser(book.getId());
				System.out.println("Wyporzyczyl Uzytkownik: " + user.getName() + " " + user.getName2());
			}
			for (int i = 0; i < book.getAuthor().size(); i++) {
				System.out.println("Äutor: " + book.getAuthor().get(i).getFirstName() + " "
						+ book.getAuthor().get(i).getLastName());
			}
		} else {
			System.out.println("Ksiazka o id =" + id + " nie istnieje");
		}

	}
	
	/*
	 * Wyszukiwanie ksiazki 
	 */
	
	// Wyszukiwanie po tytule
	public void searchByTitle(String title) {
		List<Book> book = db.search1Option(title);
		if (book.isEmpty()) {
			System.out.println("Ksiazka o danym tytule nie istnieje");
		} else {
			for (Book c : book) {
				System.out.println("Id: " + c.getId() + "\nTytul: " + c.getTitle() + "\nRok: " + c.getYear()
						+ "\nDostepne: " + c.isAvailable());
				for (int i = 0; i < c.getAuthor().size(); i++) {
					System.out.println("Äutor: " + c.getAuthor().get(i).getFirstName() + " "
							+ c.getAuthor().get(i).getLastName());
				}
			}
		}
	}
	
	//Wyszukiwanie po id Autora
	public void searchByAuthor(int id_autor) {
		Author author = db.getSelectedAuthorById(id_autor);
		if (author == null) {
			System.out.println("Autor o podanym id nie istnieje");
		} else {
			List<Book> book = db.search2Option(id_autor);
			if (book.isEmpty()) {
				System.out.println("Ksiazka o danym tytule nie istnieje");
			} else {
				for (Book c : book) {
				
				
				System.out.println("Id: " + c.getId() + "\nTytul: " + c.getTitle() + "\nRok: " + c.getYear()
						+ "\nDostepne: " + c.isAvailable());
				for (int i = 0; i < c.getAuthor().size(); i++) {
					System.out.println("Äutor: " + c.getAuthor().get(i).getFirstName() + " "
							+ c.getAuthor().get(i).getLastName());
				}}
			}
		}
	}
	
	
	public void searchByYear(int year) {
		List<Book> book = db.search3Option(year);
		if (book.isEmpty()) {
			System.out.println("Ksiazka o danym tytule nie istnieje");
		} else {
			for (Book c : book) {
				System.out.println("Id: " + c.getId() + "\nTytul: " + c.getTitle() + "\nRok: " + c.getYear()
						+ "\nDostepne: " + c.isAvailable());
				for (int i = 0; i < c.getAuthor().size(); i++) {
					System.out.println("Äutor: " + c.getAuthor().get(i).getFirstName() + " "
							+ c.getAuthor().get(i).getLastName());
				}
			}
		}
	}
	
	//Wyszukiwanie po tytule i autorze
	public void searchByTitleAndAuthor(int id_author, String title) {
		Author author = db.getSelectedAuthorById(id_author);
		if(author == null) {
			System.out.println("Autor o podanym id nie istneje");
		}
		else {
			if(title.isEmpty()) {
				System.out.println("Podany tytul jest pusty");
			}
			else {
				List<Book> book = db.search4Option(id_author, title);
				if(book.isEmpty()) {
					System.out.println("Ksiazek o podanych kryteriach nie ma");
				}
				else {
					for (Book c : book) {
						System.out.println("Id: " + c.getId() + "\nTytul: " + c.getTitle() + "\nRok: " + c.getYear()
								+ "\nDostepne: " + c.isAvailable());
						for (int i = 0; i < c.getAuthor().size(); i++) {
							System.out.println("Äutor: " + c.getAuthor().get(i).getFirstName() + " "
									+ c.getAuthor().get(i).getLastName());
						}}
				}
			}
		}
		
	}
	
}

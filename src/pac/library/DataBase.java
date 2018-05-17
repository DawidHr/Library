package pac.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;



public class DataBase {
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:Library_application_Database.s3db";
	private Statement stat;

	Connection conn;

	DataBase() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL);
			stat = conn.createStatement();
			System.out.println("Po³aczono");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Dodawanie ksiazki do Bazy danych
	public void addNewBook(String title, int year, int id_author) {
		try {
			String query = "insert into Book(title, year, isAvailabe) values(?,?,?)";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setString(1, title);
			prs.setInt(2, year);
			prs.setBoolean(3, true);
			prs.executeUpdate();
			String query2 = "select id from Book where title=? and year=? and isAvailabe=?";
			PreparedStatement prStatement = conn.prepareStatement(query2);
			prStatement.setString(1, title);
			prStatement.setInt(2, year);
			prStatement.setBoolean(3, true);
			ResultSet rs = prStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String query3 = "insert into BookAndAuthor(id_ksiazka, id_author) values(?,?)";
				PreparedStatement prs2 = conn.prepareStatement(query3);
				prs2.setInt(1, id);
				prs2.setInt(2, id_author);
				prs2.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Kasacja Ksiazki
	 */

	// Sprawdzenie czy ksiazka o podanym id istnieje
	public boolean isExsist(int id) {
		boolean isExsist = false;
		String query = "select * from Book where id=?";
		PreparedStatement prs;
		try {
			prs = conn.prepareStatement(query);
			prs.setInt(1, id);
			ResultSet rs = prs.executeQuery();
			while (rs.next()) {
				isExsist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isExsist;
	}

	// Sprawdzenie czy ksiazka jest wypozyczona
	public boolean isLent(int id) {
		boolean isAvailable = true;
		String query = "select isAvailabe from Book where id=?";
		PreparedStatement prStatement;
		try {
			prStatement = conn.prepareStatement(query);
			prStatement.setInt(1, id);
			ResultSet rs = prStatement.executeQuery();
			while (rs.next()) {
				if (rs.getBoolean("isAvailabe") == false) {
					isAvailable = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAvailable;
	}

	// Pobranie uzytkownika ktory wypozyczyl ksiazke
	public User getLendUser(int id) {
		String query = "select * from Land where id_book=?";
		PreparedStatement prs;
		try {
			prs = conn.prepareStatement(query);
			prs.setInt(1, id);
			ResultSet rs = prs.executeQuery();
			while (rs.next()) {
				int id_user = rs.getInt("id_user");
				String query1 = "select * from user where id=?";
				PreparedStatement prs1 = conn.prepareStatement(query1);
				prs1.setInt(1, id_user);
				ResultSet rs1 = prs1.executeQuery();
				while (rs1.next()) {
					return new User(rs1.getInt("id"), rs1.getString("name"), rs1.getString("name2"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Kasacja ksiazki o id
	public void deleteBook(int id) {
		String query = "delete from Book where id=?";
		PreparedStatement prs;
		try {
			prs = conn.prepareStatement(query);
			prs.setInt(1, id);
			prs.executeUpdate();
			String query2 = "delete from BookAndAuthor where id_ksiazka=?";
			PreparedStatement prs1 = conn.prepareStatement(query2);
			prs1.setInt(1, id);
			prs1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Wyœwietlanie listy ksi¹¿ek
	 */

	// Pobranie listy wszystkich ksia¿ek
	public List<Book> getAllBooks() {
		List<Book> list = new LinkedList<>();
		String query = "Select * from Book";
		Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while (rs.next()) {
				list.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getInt("year"),
						rs.getBoolean("isAvailabe"), getAuthorForIdBook(rs.getInt("id"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// Pobranie Autora dla ksiazki
	private List<Author> getAuthorForIdBook(int int1) {
		List<Author> list = new LinkedList<>();
		String query = "Select * from BookAndAuthor where id_ksiazka=?";
		PreparedStatement prs;
		try {
			prs = conn.prepareStatement(query);
			prs.setInt(1, int1);
			ResultSet rs = prs.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id_author");
				String query2 = "select * from Author where id=?";
				PreparedStatement pstat = conn.prepareStatement(query2);
				pstat.setInt(1, id);
				ResultSet rs1 = pstat.executeQuery();
				while (rs1.next()) {
					list.add(new Author(rs1.getInt("id"), rs1.getString("name"), rs1.getString("name2")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// Funkcja Zwracajaca ilosc wypozyczonych ksiazek
	public int getLentBooks() {
		int lentBooks = 0;
		String query = "Select * from Book where isAvailabe=?";
		PreparedStatement prs;
		try {
			prs = conn.prepareStatement(query);
			prs.setBoolean(1, false);
			ResultSet rs = prs.executeQuery();
			while (rs.next()) {
				lentBooks++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lentBooks;
	}

	// Funkcja Zwracajaca ilosc dostepnych ksiazek
	public int getAvailableBooks() {
		int availableBooks = 0;
		String query = "Select * from Book where isAvailabe=?";
		PreparedStatement prs;
		try {
			prs = conn.prepareStatement(query);
			prs.setBoolean(1, true);
			ResultSet rs = prs.executeQuery();
			while (rs.next()) {
				availableBooks++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return availableBooks;
	}

	/*
	 * Wypo¿yczenie ksia¿ki
	 */

	// Pobranie ksi¹¿ki o id
	public Book getBookById(int id) {
		String query = "select * from Book where id=?";
		PreparedStatement prs;
		try {
			prs = conn.prepareStatement(query);
			prs.setInt(1, id);
			ResultSet rs = prs.executeQuery();
			while (rs.next()) {
				return new Book(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getBoolean("isAvailabe"),
						getAuthorForIdBook(rs.getInt("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Pobranie U¿ytkownika o id
	public User getUserById(int id_user) {
		try {
			String query = "select * from User where id=?";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, id_user);
			ResultSet rs = prs.executeQuery();
			while (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("name"), rs.getString("name2"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Zapisanie wypo¿yczenia ksi¹¿ki przez
	public void setLendBook(int id_book, int id_user) {
		try {
			String query3 = "insert into Land(id_book, id_user) values(?,?)";
			PreparedStatement prs2 = conn.prepareStatement(query3);
			prs2.setInt(1, id_book);
			prs2.setInt(2, id_user);
			prs2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Zapisanie w ksiazce ze jest nie dostepna
	public void setNoAvailableBook(int id_book) {
		String query = "Update Book Set isAvailabe=? where id=?";
		PreparedStatement prs;
		try {
			prs = conn.prepareStatement(query);
			prs.setBoolean(1, false);
			prs.setInt(2, id_book);
			prs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Wyszukiwanie ksiazek po tytule
	public List<Book> search1Option(String title) {
		List<Book> list = new LinkedList<>();
		try {
			String query = "select * from Book where title=?";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setString(1, title);
			ResultSet rs = prs.executeQuery();
			while (rs.next()) {
				list.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getBoolean("isAvailabe"),
						getAuthorForIdBook(rs.getInt("id"))));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//Pobranie autora
	public Author getSelectedAuthorById(int id_autor) {
		try {
		String query2 = "select * from Author where id=?";
		PreparedStatement pstat = conn.prepareStatement(query2);
		pstat.setInt(1, id_autor);
		ResultSet rs1 = pstat.executeQuery();
		while (rs1.next()) {
			return new Author(rs1.getInt("id"), rs1.getString("name"), rs1.getString("name2"));
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Pobranie listy ksiazek dla autora o id
	public List<Book> search2Option(int id_autor) {
		List<Book> list = new LinkedList<>();
		try {
			String query = "Select * from BookAndAuthor where id_author=?";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, id_autor);
			ResultSet rs = prs.executeQuery();
			while (rs.next()) {
				int id_book = rs.getInt("id_ksiazka");
				String query2 = "select * from Book where id=?";
				PreparedStatement prs1 = conn.prepareStatement(query2);
				prs1.setInt(1, id_book);
				ResultSet rs2 = prs1.executeQuery();
				while(rs2.next()) {
					list.add(new Book(rs2.getInt("id"), rs2.getString("title"), rs2.getInt("year"), rs2.getBoolean("isAvailabe"),
							getAuthorForIdBook(rs2.getInt("id"))));
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//Pobieranie ksi¹¿ek wydanych w roku
	public List<Book> search3Option(int year) {
		List<Book> list = new LinkedList<>();
		try {
			String query = "select * from Book where year=?";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, year);
			ResultSet rs = prs.executeQuery();
			while (rs.next()) {
				list.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getBoolean("isAvailabe"),
						getAuthorForIdBook(rs.getInt("id"))));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	//Wyszukiwanie ksi¹¿ek po tytule i autorze
	public List<Book> search4Option(int id_author, String title) {
		List<Book> list = new LinkedList<>();
		try {
			String query = "Select * from BookAndAuthor where id_author=?";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, id_author);
			ResultSet rs = prs.executeQuery();
			while(rs.next()) {
				int id_book = rs.getInt("id_ksiazka");
				String query2 = "select * from Book where id=?";
				PreparedStatement prs2 = conn.prepareStatement(query2);
				prs2.setInt(1, id_book);
				ResultSet rs2 = prs2.executeQuery(); 
				while(rs2.next()) {
					list.add(new Book(rs2.getInt("id"), rs2.getString("title"), rs2.getInt("year"), rs2.getBoolean("isAvailabe"),
							getAuthorForIdBook(rs2.getInt("id"))));
				}
			}
			return list;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
		
	
}

# Library
Library requirements
 
1. Library contains books to lend (at the beginning there are no books in the library)

Istnieje klasa Book. Argumenty to id, title, year, isAvailable, author.
Title = tytu� ksi��ki.
Year = rok wydania ksi��ki.
isAvailable = informacja czy wypo�yczona czy nie.
Author = autor.

2. Book consist of title, year and author.

Istnieje klasa Book. Argumenty to id, title, year, isAvailable, author.
Title = tytu� ksi��ki.
Year = rok wydania ksi��ki.
isAvailable = informacja czy wypo�yczona czy nie.
Author = autor.

4. Each book should have unique identifier (ID) across application.

W bazie danych tabela z ksi��kami tworzy unikatowy id dla dodawanej ksi��ki.

5. Should be possible to add new book to the library. ID should not be passed as argument. ID should be generated inside Library.

Do dodania ksi��ki nale�y u�y� funkcji 
addBookToDB(String title, int year, int id_author)
jako argumenty nale�y poda� tytu� ksi��ki, rok oraz identyfikator autora.


6. Should be possible to remove given book from the library (by ID) (such action should be possible only if the book with such ID exists and it is not currently lent).

Do kasacji ksi��ki o danym id nale�y u�y� funkcji
deleteBookFromLibrary(int id)
jako argument nale�y poda� id ksi��ki. Funkcja sprawdza czy ksi��ka taka istnieje oraz czy nie zosta�a wypo�yczona.

7. Should allow to list all books in the library (distinctly). Returned information should contain information how many is available or lent. You can use simply System.out.println

Funkcja
viewAllBooks()
wy�wietli wszystkie ksi��ki z bazy danych. Poda informacje o ilo�ci dost�pnych i wypo�yczonych ksi��kach.

8. Should allow to search book by title, author, year (also other combinations like title AND author).

Do wyszukiwania po tytule nale�y u�y� funkcji
searchByTitle(String title)
Nale�y poda� tytu� ksi��ki.

Do wyszukiwania po autorze nale�y u�y� funkcji
searchByAuthor(int id_autor)
Nale�y poda� id autora.

Do wyszukiwania po roku nale�y u�y� funkcji
searchByYear(int year)
Nale�y poda� rok.

Do wyszukiwania po tytule i autorze nale�y u�y� funkcji
searchByTitleAndAuthor(int id_author, String title)
Nale�y poda� tytu� i id autora.

9. Should allow to lent a book by ID ( should be forbidden if copy with given ID is already lent). Should allow to pass the name of the person who lend the book.

Do wypo�yczenia nale�y u�y� funkcji
setLendBook(int id_book, int id_user)
z argumentami id ksi��ki i id autora.

10. Should allow to see all book's details by ID (title, author, year, information if it is available or lent together with person name).
Do wy�wietlenia informacji o ksi��ce nale�y u�y� funkcji
getInformationAboutBook(int id)
Nale�y poda� id ksiazki.



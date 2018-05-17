# Library
Library requirements
 
1. Library contains books to lend (at the beginning there are no books in the library)

Istnieje klasa Book. Argumenty to id, title, year, isAvailable, author.
Title = tytu³ ksi¹¿ki.
Year = rok wydania ksi¹¿ki.
isAvailable = informacja czy wypo¿yczona czy nie.
Author = autor.

2. Book consist of title, year and author.

Istnieje klasa Book. Argumenty to id, title, year, isAvailable, author.
Title = tytu³ ksi¹¿ki.
Year = rok wydania ksi¹¿ki.
isAvailable = informacja czy wypo¿yczona czy nie.
Author = autor.

4. Each book should have unique identifier (ID) across application.

W bazie danych tabela z ksi¹¿kami tworzy unikatowy id dla dodawanej ksi¹¿ki.

5. Should be possible to add new book to the library. ID should not be passed as argument. ID should be generated inside Library.

Do dodania ksi¹¿ki nale¿y u¿yæ funkcji 
addBookToDB(String title, int year, int id_author)
jako argumenty nale¿y podaæ tytu³ ksi¹¿ki, rok oraz identyfikator autora.


6. Should be possible to remove given book from the library (by ID) (such action should be possible only if the book with such ID exists and it is not currently lent).

Do kasacji ksi¹¿ki o danym id nale¿y u¿yæ funkcji
deleteBookFromLibrary(int id)
jako argument nale¿y podaæ id ksi¹¿ki. Funkcja sprawdza czy ksi¹¿ka taka istnieje oraz czy nie zosta³a wypo¿yczona.

7. Should allow to list all books in the library (distinctly). Returned information should contain information how many is available or lent. You can use simply System.out.println

Funkcja
viewAllBooks()
wyœwietli wszystkie ksi¹¿ki z bazy danych. Poda informacje o iloœci dostêpnych i wypo¿yczonych ksi¹¿kach.

8. Should allow to search book by title, author, year (also other combinations like title AND author).

Do wyszukiwania po tytule nale¿y u¿yæ funkcji
searchByTitle(String title)
Nale¿y podaæ tytu³ ksi¹¿ki.

Do wyszukiwania po autorze nale¿y u¿yæ funkcji
searchByAuthor(int id_autor)
Nale¿y podaæ id autora.

Do wyszukiwania po roku nale¿y u¿yæ funkcji
searchByYear(int year)
Nale¿y podaæ rok.

Do wyszukiwania po tytule i autorze nale¿y u¿yæ funkcji
searchByTitleAndAuthor(int id_author, String title)
Nale¿y podaæ tytu³ i id autora.

9. Should allow to lent a book by ID ( should be forbidden if copy with given ID is already lent). Should allow to pass the name of the person who lend the book.

Do wypo¿yczenia nale¿y u¿yæ funkcji
setLendBook(int id_book, int id_user)
z argumentami id ksi¹¿ki i id autora.

10. Should allow to see all book's details by ID (title, author, year, information if it is available or lent together with person name).
Do wyœwietlenia informacji o ksi¹¿ce nale¿y u¿yæ funkcji
getInformationAboutBook(int id)
Nale¿y podaæ id ksiazki.



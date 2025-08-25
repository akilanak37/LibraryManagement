package LibraryManagement;

import java.util.*;
import LibraryManagement.DTO.*;
import java.sql.SQLException;

public class BookService {
	private final JDBCRepository repo = new JDBCRepository();
	private final List<Book> bookCache = new ArrayList<>();

	public BookService() {
		try {
			bookCache.addAll(repo.getAllBooks());
		} catch (SQLException e) {
			System.err.println("Cache warm-up failed: " + e.getMessage());
		}
	}

	public List<Book> getBookCache() {
		return Collections.unmodifiableList(bookCache);
	}

	public void addAuthor(Author a) {
		try {
			int n = repo.addAuthor(a);
			System.out.println(n > 0 ? "Author added" : "Failed to add Author");
		} catch (SQLException e) {
			printConstraintHint(e);
		}

	}

	public void updateAuthor(Author a) {
		try {
			int n = repo.updateAuthor(a);
			System.out.println(n > 0 ? "Author Updated" : "No Auhtor Found");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void deleteAuthor(int aid) {
		try {
			int n = repo.deleteAuthor(aid);
			System.out.println(n > 0 ? "Author Deleted" : "Author Not Found in DB");
		} catch (SQLException e) {
			printConstraintHint(e);
		}
	}

	public void viewAuthors() {
		try {
			repo.getAllAuthors().forEach(System.out::println);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void addBook(Book b) {
		try {
			repo.addBook(b);
			bookCache.add(b); // keep cache in sync
			System.out.println("Book added.");
		} catch (SQLException e) {
			printConstraintHint(e);
		}
	}

	public void updateBook(Book b) {
		try {
			int n = repo.updateBook(b);
			if (n > 0) {
				// sync cache
				for (int i = 0; i < bookCache.size(); i++) {
					if (bookCache.get(i).getBid() == b.getBid()) {
						bookCache.set(i, b);
						break;
					}
				}
				System.out.println("Book updated.");
			} else
				System.out.println("No book found.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteBook(int bid) {
		try {
			int n = repo.deleteBook(bid);
			if (n > 0) {
				bookCache.removeIf(x -> x.getBid() == bid);
				System.out.println("Book deleted.");
			} else
				System.out.println("No book found.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void viewBooks() {
		try {
			repo.getAllBooks().forEach(System.out::println);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void viewBooksFromCache() { // purely from in-memory list
		bookCache.forEach(System.out::println);
	}

	private void printConstraintHint(SQLException e) {
		System.out.println(e.getMessage());
		System.out.println("Hint: Check duplicate IDs or foreign key (AID) exists in Author.");
	}
}

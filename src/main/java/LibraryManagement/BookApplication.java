package LibraryManagement;

import java.util.*;

import LibraryManagement.DTO.*;

public class BookApplication {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BookService service = new BookService();
		while (true) {
			System.out.println("\n=== Library Menu ===");
			System.out.println("1. Add Author");
			System.out.println("2. Remove Author");
			System.out.println("3. Update Author");
			System.out.println("4. View Authors");
			System.out.println("5. Add Book");
			System.out.println("6. Update Book");
			System.out.println("7. Remove Book");
			System.out.println("8. View Books (DB)");
			System.out.println("9. View Books (Cache/List<Book>)");
			System.out.println("10. Exit");
			System.out.print("Enter choice: ");

			int ch = safeInt(sc);

			switch (ch) {
			case 1:{
				System.out.println("Enter AID Name Address: ");
				int aid = safeInt(sc);
				String name = sc.next();
				String address = sc.next();
				service.addAuthor(new Author(aid, name, address));
				break;
			}
			case 2:{
				System.out.println("Enter AID to Delete: ");
				service.deleteAuthor(safeInt(sc));
				break;
			}
			case 3:{
				System.out.println("Enter AID NewName NewAdress: ");
				int aid = safeInt(sc);
				String name = sc.next();
				String address = sc.next();
				service.updateAuthor(new Author(aid, name, address));
				break;
			}
			case 4:{
				service.viewAuthors();
				break;
			}
			case 5:{
				System.out.println("Enter BID Name Price AID: ");
				int bid = safeInt(sc);
				String name = sc.next();
				double price = safeDouble(sc);
				int aid = safeInt(sc);
				service.addBook(new Book(bid, name, price, aid));
				break;
			}
			case 6:{
				System.out.println("Enter BID NewName NewPrice AID: ");
				int bid = safeInt(sc);
				String name = sc.next();
				double price = safeDouble(sc);
				int aid = safeInt(sc);
				service.updateBook(new Book(bid, name, price, aid));
				break;
			}
			case 7:{
				System.out.println("BEnter ID to Delete");
				service.deleteBook(safeInt(sc));
				break;
			}
			case 8:{
				service.viewBooks();
				break;
			}
			case 9:{
				service.viewBooksFromCache();
				break;
			}
			case 10:{
				System.out.println("Bye!");
				sc.close();
				return;
			}
			default:{
				System.out.println("Invalid Choice");
			}
			}
		}
	}

	private static int safeInt(Scanner sc) {
		while(!sc.hasNextInt()) {
			System.out.println("Enter a number: ");
			sc.next();
		}
		return sc.nextInt();
	}
		private static double safeDouble(Scanner sc) {
			while(!sc.hasNextDouble()) {
				System.out.println("Enter a Number: ");
				sc.next();
			}
			return sc.nextDouble();
		}
	}

package LibraryManagement;

import java.sql.*;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.sql.*;
import LibraryManagement.DTO.Author;
import LibraryManagement.DTO.Book;

public class JDBCRepository {
	private final Connection con;

	public JDBCRepository() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/librarydb?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
		} catch (Exception e) {
			throw new RuntimeException("DB Connection Failed");
		}
	}

	public int addAuthor(Author a) throws SQLException {
		String sql = "INSERT INTO Author (aid, name, address) VALUES (?,?,?)";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, a.getAid());
			ps.setString(2, a.getName());
			ps.setString(3, a.getAddress());
			return ps.executeUpdate();

		}

	}

	public int updateAuthor(Author a) throws SQLException {
		String sql = "UPDATE Author SET aname=?, address=? WHERE aid=?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, a.getName());
			ps.setString(2, a.getAddress());
			ps.setInt(3, a.getAid());
			return ps.executeUpdate();
		}
	}

	public int deleteAuthor(int aid) throws SQLException {
		String sql = "DELETE FROM Author WHERE aid=?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, aid);
			return ps.executeUpdate();
		}
	}

	public List<Author> getAllAuthors() throws SQLException {
		List<Author> list = new ArrayList<>();
		String sql = "SELECT aid, name, address FROM Author ORDER BY aid";
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				list.add(new Author(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		}
		return list;
	}

	public int addBook(Book b) throws SQLException {
		String sql = "INSERT INTO Book(bid, bname, price, aid) VALUES(?,?,?,?)";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, b.getBid());
			ps.setString(2, b.getBname());
			ps.setDouble(3, b.getPrice());
			ps.setInt(4, b.getAid());
			return ps.executeUpdate();
		}
	}

	public int updateBook(Book b) throws SQLException {
		String sql = "UPDATE Book SET bname=?, price=?, aid=? WHERE bid=?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, b.getBname());
			ps.setDouble(2, b.getPrice());
			ps.setInt(3, b.getAid());
			ps.setInt(4, b.getBid());
			return ps.executeUpdate();
		}
	}

	public int deleteBook(int bid) throws SQLException {
		String sql = "DELETE FROM Book WHERE bid=?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, bid);
			return ps.executeUpdate();
		}
	}

	public List<Book> getAllBooks() throws SQLException {
		List<Book> list = new ArrayList<>();
		String sql = "SELECT bid, bname, price, aid FROM Book ORDER BY bid";
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				list.add(new Book(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4)));
			}
		}
		return list;
	}
}

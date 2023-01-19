package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dto.ProductExam;

public class ProductExamDAO {

	private static Connection getConnection() throws URISyntaxException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	
	public static int registerEmploymentExam(ProductExam exam) {
		String sql = "INSERT INTO product_exam VALUES(default, ?, ?, ?, ?, current_timestamp)";
		int result = 0;
				
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, exam.getProductName());
			pstmt.setObject(2, LocalDate.parse(exam.getProductDate()));
			pstmt.setInt(3, exam.getProductId());
			pstmt.setString(4, exam.getNote());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "件更新しました。");
		}
		return result;
	}
	public static void deleteProduct(String productName) {
		String sql = "DELETE FROM product_exam WHERE product_name = ?";
		int result = 0;

		try (
				Connection con =getConnection();	// DB接続
				PreparedStatement pstmt = con.prepareStatement(sql);			// 構文解析
				){
			
			pstmt.setString(1, productName);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
		} finally {
			System.out.println(result + "件削除しました。");
		}
	}
	public static List<ProductExam> searchProductByName(String keyword){
		
		// 実行するSQL
		String sql = "SELECT * FROM product_exam WHERE product_name LIKE ?";
		// ダメな例 String sql = "SELECT * FROM employee WHERE name LIKE %?%";
		// なぜなら値を埋め込むとSELECT * FROM employee WHERE name LIKE %'keyword'%となるから。
		
		// 返却用のListインスタンス
		List<ProductExam> result = new ArrayList<>();
				
		try (
				Connection con = getConnection();	// DB接続
				PreparedStatement pstmt = con.prepareStatement(sql);			// 構文解析
				){
			
			// %や_はここで文字列結合する。そうすると'%keyword%'となる。
			pstmt.setString(1, "%" + keyword + "%");
			
			// SQL実行！
			// ResultSetもcloseする必要があるのでtry-with-resources文を使う
			try (ResultSet rs = pstmt.executeQuery()){
				
				// next()がfalseを返すまでループ
				while(rs.next()) {

					// n行目のデータを取得
					int id = rs.getInt("id");
					String productName = rs.getString("product_name");
					 String productDate =rs.getString("product_date");
					 int productId =rs.getInt("product_id");
					 String note=rs.getString("note");
					String createdAt=rs.getString("created_at");
					// n件目のインスタンスを作成
					ProductExam product = new ProductExam(id,productName,productDate,productId,note, createdAt);
					
					// インスタンスをListに追加
					result.add(product);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		// Listを返却する。0件の場合は空のListが返却される。
		return result;
	}
	// ログインしているユーザの全受験状況を取得
	public static List<ProductExam> selectAllExam(){
		
		// 実行するSQL
		String sql = "SELECT * FROM product_exam";
		
		// 返却用のListインスタンス
		List<ProductExam> result = new ArrayList<>();
				
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			
			
			
			try (ResultSet rs = pstmt.executeQuery()){
				
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("product_name");
					String product_date = rs.getString("product_date");
					int product_id=rs.getInt("product_id");
					String note = rs.getString("note");
					String createdAt = rs.getString("created_at");

					ProductExam exam = new ProductExam(id, name, product_date, product_id, note, createdAt);
					result.add(exam);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (URISyntaxException e) {
			e.printStackTrace();
		}

		// Listを返却する。0件の場合は空のListが返却される。
		return result;
	}

}

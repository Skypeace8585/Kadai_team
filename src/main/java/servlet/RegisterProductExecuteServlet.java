package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductExamDAO;
import dto.Account;
import dto.ProductExam;

/**
 * Servlet implementation class RegisterCompanyExecuteServlet
 */
@WebServlet("/RegisterProductExecuteServlet")
public class RegisterProductExecuteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterProductExecuteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//処理の始めにログイン状態のチェックを行う。
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("root");

		if(account == null){
			//セッションの中身がnullであれば不正アクセスと判断し
			//ログイン画面へ戻る
			String view = "./";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
			return;
		}
		
		// 入力されたパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String companyName = request.getParameter("company");
		String examDate = request.getParameter("date");
		String note = request.getParameter("note");
		
		// セッションからログインしているユーザのIDを取得
		int id = account.getId();
		
		ProductExam exam = new ProductExam(-1, companyName, examDate, id, note, null);
		
		int result = ProductExamDAO.registerEmploymentExam(exam);

		String view = "";
		if(result==0) {
			view = "WEB-INF/view/sample-register.jsp?error=1";
		} else {
			view = "WEB-INF/view/sample-register-result.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

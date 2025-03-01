package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.paypal.base.rest.PayPalRESTException;

import entity.OrderDetail;
import entity.TbAccount;
import service.PaymentServices;

/**
 * Servlet implementation class AuthorizePaymentServlet
 */
@WebServlet("/authorize_payment")
public class AuthorizePaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AuthorizePaymentServlet() {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        TbAccount account = (TbAccount) session.getAttribute("acc");
        String product = request.getParameter("product");
        String subtotal = request.getParameter("subtotal");
        String shipping = request.getParameter("shipping");
        String tax = request.getParameter("tax");
        String total = request.getParameter("total");
        OrderDetail orderDetail = new OrderDetail(product, subtotal, shipping, tax, total);
        try {
            PaymentServices paymentServices = new PaymentServices();
            String approvalLink = paymentServices.authorizePayment(orderDetail);
            response.sendRedirect(approvalLink);
        }
        catch (PayPalRESTException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Thanh Toán Không Hợp Lệ. Vui Lòng Thử Lại Sau");
            request.getRequestDispatcher("ErrorPayment.jsp").forward(request, response);
        }
        
    }

}
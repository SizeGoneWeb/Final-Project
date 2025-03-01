package control.user;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.TbAccount;
import entity.TbDiaChiKH;
import entity.TbTinhThanh;
import model.AddressDAO;
import model.BookDao;
import model.UserDao;

@WebServlet("/user/address")
public class AddressControl extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    HttpSession session = request.getSession();
		TbAccount account = (TbAccount) session.getAttribute("acc");
		 String MaTK = account.getMaTK().toString();
		if (account == null) {
		    response.sendRedirect("../login");
		}
		else {
		    //Sync data account
		    TbAccount user = UserDao.selectAccount(Long.toString(account.getMaTK()));
		    session.setAttribute("acc", user);
		    
		    String type = checkNullString(request.getParameter("type"));
		    System.out.println(type);
		    
		    if (type.equals("delete")) {
		        String id = checkNullString(request.getParameter("id"));
		        TbDiaChiKH deleteObject = new TbDiaChiKH();
		        deleteObject.setId(Integer.parseInt(id));
		        
		        AddressDAO.deleteAddress(deleteObject);
		    }else if(type.equals("SetDefault")) {
		        String id = checkNullString(request.getParameter("id"));
		        List<TbDiaChiKH> diaChiKHs = AddressDAO.selectAddress(account);
		        for (TbDiaChiKH tbDiaChiKH : diaChiKHs) {
                    if (tbDiaChiKH.getId() == Long.valueOf(id).intValue()) {
                        tbDiaChiKH.setMacDinh(1);
                        AddressDAO.updateAddress(tbDiaChiKH);
                    }else if(tbDiaChiKH.getMacDinh()==1) {
                        tbDiaChiKH.setMacDinh(0);
                        AddressDAO.updateAddress(tbDiaChiKH);
                    }
                }
		    }
		    else if (!type.isEmpty()) {
		        
		        String fullName = checkNullString(request.getParameter("fullName"));
	            String phone = checkNullString(request.getParameter("phone"));
	            String province = checkNullString(request.getParameter("province"));
	            String city = checkNullString(request.getParameter("city"));
	            String ward = checkNullString(request.getParameter("ward"));
	            String street = checkNullString(request.getParameter("street"));
	            
	            if (fullName.equals("") && phone.equals("") && city.equals("") && ward.equals("") && street.equals("")) {
	                //Do nothing
	            }
	            else {
	                TbDiaChiKH address = new TbDiaChiKH();
	                TbTinhThanh provinceTB = new TbTinhThanh();
	                
	                provinceTB.setId(Integer.parseInt(province));
	                
	                address.setTbAccount(user);
	                address.setHoVaTen(fullName);
	                address.setSdt(phone);
	                address.setTbTinhThanh(provinceTB);
	                address.setTenQuan(city);
	                address.setTenPhuong(ward);
	                address.setDiaChi(street);
	                if(user.getDiaChiMacDinh() ==0) address.setMacDinh(1); 
	                
	                if (type.equals("add")) {
	                    AddressDAO.insertAddress(address);
	                }
	                else if (type.equals("update")) {
	                    String id = checkNullString(request.getParameter("id"));
	                    if (id.equals("")) {
	                        //Do nothing
	                    }
	                    else {
	                        address.setId(Integer.parseInt(id));
	                        
	                        AddressDAO.updateAddress(address);
	                    }
	                }
	                else {
	                    //Do nothing
	                }
	            }
		    }
		    else {
                //Do nothing
            }
		    response.sendRedirect("./loadAddress");
   
	       // getServletContext().getRequestDispatcher("/user/loadAddress").forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	String checkNullString(String string) {
	    if (string == null) {
	        return "";
	    }
	    return string;
	}
}
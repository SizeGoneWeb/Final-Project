<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Yêu thích</title>

    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
    <div id="preloder">
        <div class="loader"></div>
    </div>
<header class="header">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="header__logo">
						<a href="./index.jsp"><img src="img/logo.png" alt=""></a>
					</div>
				</div>
				<div class="col-lg-6">
					<nav class="header__menu">
						<ul>
							<li class="active"><a href="loadSach">Home</a></li>
							<li><a href="search?key=">Shop</a></li>
							<li><a href="FAQControl">FAQ</a></li>
							<li><a href="AdminProduct">Admin</a></li>
						</ul>
					</nav>
				</div>
				<div class="col-lg-3">
					<div class="header__cart">
						<ul>
							<c:if test="${sessionScope.acc != null}">
							<li><a href="taoSach"><i class="fa fa-book"></i> <span>${demdb}</span></a></li>
							<li><a href="yeuthich"><i class="fa fa-heart"></i> <span>${demyt}</span></a></li>
							<li><a href="cart"><i class="fa fa-shopping-bag"></i> <span>${demgh}</span></a></li>
						</c:if>
						</ul>
						<div class="header__cart__price">
						<c:if test="${sessionScope.acc != null}">
							item: <span><a href="NapTien.jsp">${sessionScope.acc.tien} USD</a></span>
						</c:if>
						<c:if test="${sessionScope.acc == null}">
							item: <span>$0.00</span>
						</c:if>
							
						</div>
					</div>
				</div>
			</div>
			<div class="humberger__open">
				<i class="fa fa-bars"></i>
			</div>
		</div>
	</header>
	<section class="hero hero-normal">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="hero__categories">
						<div class="hero__categories__all">
							<i class="fa fa-bars"></i> <span>All departments</span>
						</div>
						<ul>
							<c:forEach items="${category}" var="o">
							<li><a href="categorysearch?keycategory=${o.getTenTheLoai() }">${o.getTenTheLoai()}</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col-lg-9">
					<div class="hero__search">
						<div class="hero__search__form">

							<form action="search" method="get">

								<input type="text" placeholder="Tìm sách gì dợ?"  name="key">
								<button type="submit" class="site-btn">Tìm kiếm</button>
							</form>

						</div>
						<div class="hero__search__login">
							<div class="hero__search__login__icon">
								<i class="fa fa-user"></i>
							</div>
							<c:if test="${sessionScope.acc != null}">
								<div class="hero__search__login__text hello_user">
									<h5>Xin chào ${sessionScope.acc.UName}</h5>
								</div>
								<br>
								<div class="hero__search__login__text">
									<h5>
										<a href="logout">Đăng Xuất</a>
									</h5>
								</div>


							</c:if>
							<c:if test="${sessionScope.acc == null}">
								<div class="hero__search__login__text">
									<h5>
										<a href="Login.jsp">Đăng Nhập</a>
									</h5>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
	</section>
    <!-- Hero Section End -->


    <!-- Breadcrumb Section End -->

    <!-- Shoping Cart Section Begin -->
    <section class="shoping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th class="shoping__product">Sản phẩm</th>
                                    <th>Giá</th>

                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                           	 	<c:forEach items="${getyeuthich}" var="o">
                                <tr>
                                    <td class="shoping__cart__item">
                                    <a href="detail?pid=${o.maSach }">
                                    <img src="${o.getAnhs().get(0).getAnh()}" width="130"  height="150" alt="">
                                     <h5>${o.getTenSach() }</h5>
                                    </a>
                                    </td>
                                    <td class="shoping__cart__price">
                                       ${o.getDonGia()}VNĐ
                                    </td>
                                    <td class="shoping__cart__item__close">
                                        <a href="removeytControl?MaSach=${o.maSach }"><span class="icon_close"></span></a>
                                    </td>
                                </tr>
                               	</c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__btns">
                        <a href="./index.jsp" class="primary-btn cart-btn">Tiếp tục mua sắm</a>
                        <a href="yeuthich?MaTK=${sessionScope.acc.maTK}" class="primary-btn cart-btn cart-btn-right"><span class="icon_loading"></span>
                            Làm mới</a>
                    </div>
                </div>
          
            </div>
        </div>
    </section>
    <!-- Shoping Cart Section End -->

    <!-- Footer Section Begin -->
 	<footer class="footer spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="footer__about">
						<div class="footer__about__logo">
							<a href="./index.jsp"><img src="img/logo.png" alt=""></a>
						</div>
						<ul>
							<li>Address: Số 1 Võ Văn Ngân,<br> Thành phố Thủ Đức
							</li>
							<li>Phone: +65 11.188.888</li>
							<li>Email: sizegonebook@gmail.com</li>
						</ul>
					</div>
				</div>
				
				
			</div>
			
		</div>
	</footer>
    <!-- Footer Section End -->

    <!-- Js Plugins -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>


</body>

</html>
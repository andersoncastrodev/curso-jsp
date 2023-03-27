<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-BR">

<!-- Incluindo a pagina -->
<jsp:include page="head.jsp"></jsp:include>

<body>

	<!-- Incluindo a pagina -->
	<jsp:include page="theme-loader.jsp"></jsp:include>

	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<!-- Incluindo a pagina -->
			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<!-- Incluindo a pagina -->
					<jsp:include page="navbarmenulateral.jsp"></jsp:include>


					<div class="pcoded-content">

						<!-- Incluindo a pagina -->
						<jsp:include page="page-header.jsp"></jsp:include>

						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row">
											<!-- Aqui ontem vai ficar as paginas do sistema-->

											<h1>Paginas do Sistemas</h1>

											<!-- Fim das Paginas do Sistemas -->
										</div>
									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Incluindo a pagina -->
	<jsp:include page="javascriptfile.jsp"></jsp:include>

</body>
</html>
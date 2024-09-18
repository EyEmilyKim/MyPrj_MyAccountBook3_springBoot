<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	
	<div id="c_values">
		<c:set var="inex" value="${DTO.inex}" />
		<c:set var="list" value="${DTO.list }" />
		<c:set var="totalCount" value="${DTO.totalCount }" />
	    <c:set var="totalPages" value="${DTO.totalPages }" />
		<c:set var="rowCount" value="${DTO.rowCount }" />
	    <c:set var="currentPage" value="${DTO.currentPage }" />
	    <c:set var="currentSet" value="${DTO.currentSet }" />
	    <c:set var="startPage" value="${DTO.startPage }" />
	    <c:set var="endPage" value="${DTO.endPage }" />
			<c:set var="startCount" value="${(currentPage - 1) * rowCount + 1}" />
			<c:set var="endCount" value="${startCount + rowCount - 1}" />
			<c:if test="${endCount > totalCount }">
				<c:set var="endCount" value="${totalCount}" />
			</c:if>
		<c:set var="optionString" value="${DTO.rowCount_option }" />
		<c:set var="optionList" value="${fn:split(optionString, ',')}" />
	</div>

<main>
	
	<div class="contMain">
		<h2 class="title ">
			<c:if test="${inex == 'ALL'}">가계부 목록 : <span class="">전체</span></c:if>
			<c:if test="${inex == 'IN'}">가계부 목록 : <span class="in">수입</span></c:if>
			<c:if test="${inex == 'EX'}">가계부 목록 : <span class="ex">지출</span></c:if>
		</h2>
		
	<!-- 1. 검색 블럭 -->
		<div id="searchBlock">
		<!-- 1-1. 검색 form -->
			<form name="fmSRCH" id="fmSRCH">
				<input type="date" name="D_FROM" value="${param.D_FROM}" id="search_d_from" />
				 ~ <input type="date" name="D_TO" value="${param.D_TO}" id="search_d_to" /> 
				<input type="text" name="ITEM" value="${param.ITEM}" placeholder="--내용--" id="search_item" />
				<select name="CATE_NAME" id="slct_cname">
						<option value="">--카테고리--</option>
					<c:forEach var="option" items="${CNAME_LIST}">
						<option value="${option}" <c:if test="${option == param.CATE_NAME}">selected</c:if>>
				            ${option}
				        </option>
					</c:forEach>
				</select>
			<c:if test="${inex == 'EX' }">
				<select name="METH_NAME" id="slct_mname">
						<option value="">--결제수단--</option>
					<c:forEach var="option" items="${MNAME_LIST}">
					<c:if test="${option != '해당없음' }">
						<option value="${option}" <c:if test="${option == param.METH_NAME}">selected</c:if>>
				            ${option}
				        </option>
					</c:if>
					</c:forEach>
				</select>
			</c:if>
				<input type="text" name="INEX" value="${inex }" id="search_inex" placeholder="INEX 자동" />
				<input type="text" name="RC" value="${rowCount }" id="search_rc" placeholder="RC 자동" />
				<input type="text" name="PG" value="${currentPage }" id="search_page" placeholder="PG 자동" />
				<input type="submit" value="조회하기" id="search_submit" />
			</form>
		<!-- 1-2. 검색 결과 표시 -->
			<c:if test="${not empty param.D_FROM || not empty param.D_TO || not empty param.ITEM
			 || not empty param.CATE_NAME || not empty param.METH_NAME}">
				<div id="searchResult">
					<p>기간 : [<span> ${param.D_FROM } </span> ~ <span> ${param.D_TO } </span>] / 
						내용 : [<span> ${param.ITEM } </span>] / 
						카테고리 : [<span> ${param.CATE_NAME} </span>] 
					<c:if test="${inex == 'EX' }">
						 / 결제수단 : [<span> ${param.METH_NAME} </span>] 
					</c:if>
						검색 결과입니다.</p> 
					<input type="button" value="전체보기" id="search_reset" />		
				</div>
			</c:if>
			
		</div>	<!-- 검색 블럭 끝 -->
	
	<!-- 2. 전체 건 수 -->
		<div class="count ">총 <span class="cnt">${totalCount }</span> 건</div>
	
	<!-- 3. 실제 목록 -->
		<div class="listMain">
		<!-- 3-1. 거래내역 없음 -->
			<c:if test="${totalCount == 0 }">
				<div class="noTran">
					<p>거래내역이 없습니다.</p>
					<a href="/tran/crt">가계부 쓰러 가기</a>
				</div>
			</c:if>
			
		<!-- 3-2. 거래내역 테이블 -->
			<c:if test="${totalCount > 0 }">
				<table class="tranTable" >
				
				<thead class="">
					<tr>
						<td class="tran_id">tran_id</td>
						<td>날짜</td>
						<td>구분</td>
						<td class="tran_cate">카테고리</td>
						<td class="tran_amount">내용</td>
						<td class="tran_item">금액</td>
						<td class="tran_meth">결제수단</td>
						<td>btn</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="t">
						<c:set var="urlUpd" value="upd?TRAN_ID=${t.tran_id }" />
						<c:set var="urlDel" value="del?TRAN_ID=${t.tran_id }" />
						<tr>
							<td class="tran_id">${t.tran_id }</td>
							<td><fmt:formatDate value="${t.tran_date}" pattern="yyyy-MM-dd" /></td>
							<c:if test="${t.inex == 'EX' }">
								<td class="ex">지출</td>
								<td class="ex">${t.cate_name}</td>
							</c:if>
							<c:if test="${t.inex == 'IN' }">
								<td class="in">수입</td>
								<td class="in">${t.cate_name}</td>
							</c:if>
							<td class="item">${t.item }</td>
							<td class="amount"><fmt:formatNumber value="${t.amount}" type="number" pattern="#,##0" /> 원</td>
							<c:if test="${t.inex == 'EX' }"><td>${t.meth_name }</td></c:if>
							<c:if test="${t.inex == 'IN' }"><td> - </td></c:if>
							<td><a class="btn" href="${urlUpd }" >수정</a>
								<a class="btn" href="${urlDel }" >삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>
				
				</table>
			</c:if>
		
		</div> <!-- 실제 목록 끝 -->
	
	<!-- 4. 목록 컨트롤 -->
		<c:if test="${totalCount > 0 }">
			<div id="controlViewBlock" class="">
	
			<!-- 4-1. 페이지 블럭 -->
				<div id="pageBlock" class="">
		        <!-- 이전 페이지 세트로 이동 -->
			        <c:if test="${currentSet > 1}">
			            <fmt:formatNumber value="${startPage - 1}" type="number" var="prevPage" />
			            <span class="paging" data-page="${prevPage}">&laquo; </span>
			        </c:if>
		        <!-- 페이지 번호 생성 -->	
			        <c:forEach var="i" begin="${startPage}" end="${endPage}">
			            <c:choose>
			                <c:when test="${i == currentPage}">
			                    <span class="current" >${i}</span>
			                </c:when>
			                <c:otherwise>
			                    <span class="paging" data-page="${i}">${i}</span>
			                </c:otherwise>
			            </c:choose>
			        </c:forEach>
		        <!-- 다음 페이지 세트로 이동 -->
			        <c:if test="${endPage < totalPages}">
			            <fmt:formatNumber value="${endPage + 1}" type="number" var="nextPage" />
			            <span class="paging" data-page="${nextPage}"> &raquo;</span>
			        </c:if>
				</div> <!-- 페이지 블럭 끝 -->
		
			<!-- 4-2. 서브카운트 블럭 -->
				<div class="subCountBlock ">
					<span class="cnt">${startCount }</span>
					 ~ <span class="cnt">${endCount }</span>
					 / <span>${totalCount }</span> 
				</div>
			
			<!-- 4-3. N줄보기 블럭 -->
				<div id="rowCountBlock" class="">
				<!-- N줄보기 form -->
					<select name="RC" id="slct_rc">
						<c:forEach var="option" items="${optionList }">
							<option value="${option }" <c:if test="${option == rowCount}">selected</c:if>>
							${option }줄 보기
							</option>
						</c:forEach>
					</select>
				</div> <!-- N줄보기 블럭 끝 -->
				
			</div>	<!-- 목록 컨트롤 끝 -->	
		</c:if>
	
	</div> <!-- contMain 끝 -->
</main>

<script src="../../js/tran/list.js"></script>

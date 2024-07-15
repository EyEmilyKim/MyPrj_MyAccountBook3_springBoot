package com.EyEmilyKim.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EyEmilyKim.config.ClientViewDefault;
import com.EyEmilyKim.dao.TransactionDao;
import com.EyEmilyKim.dto.TranPageDto;
import com.EyEmilyKim.dto.TranSearchDto;
import com.EyEmilyKim.dto.TransactionDto;
import com.EyEmilyKim.entity.Transaction;
import com.EyEmilyKim.util.DateUtil;

@Service
public class TransactionServiceImp implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private ClientViewDefault clv;
	
	@Override
	public TranPageDto getList(TranSearchDto searchDto, int user_id, String inex) throws ParseException {
		System.out.println("TranService > getList() called");
		
		// 실제 데이터 가져오기
		searchDto = this.populateTranSearchDto(searchDto, user_id, inex);
		List<TransactionDto> list = transactionDao.getList(searchDto);
		// 전체 데이터 수 가져오기
		int totalCount = transactionDao.getCount(searchDto);
		// 데이터 전달해서 페이징 변수까지 모두 담아오기
		TranPageDto resultDto = this.populateTranPageDto(list, totalCount, searchDto, inex);
		
		return resultDto;
	}
	
	@Override
	public TransactionDto getOne(String tran_id) {
		System.out.println("TranService > getOne() called");
		return transactionDao.getOne(tran_id);
	}

	@Override
	public int delete(String tran_id) {
		System.out.println("TranService > delete() called");
		return transactionDao.delete(tran_id);
	}

	@Override
	public Integer getMaxMySqn(int user_id) {
		System.out.println("TranService > getMaxMySqn() called");
		
		Integer maxMySqn = transactionDao.getMaxMySqn(user_id);
		if(maxMySqn == null) maxMySqn = 0;
		return maxMySqn;
	}

	@Override
	public int insert(Map<String, String> fm, int user_id) throws Exception {
		System.out.println("TranService > insert() called");

		Timestamp reg_date = DateUtil.dateToTimestamp(new Date());
		
		Transaction tran = populateTransactionCommonField(fm);
		tran.setUser_id(user_id);
		tran.setMy_seqno(Integer.parseInt(fm.get("MY_SEQNO")));
		tran.setTran_id(user_id + "_" + fm.get("MY_SEQNO"));
		tran.setReg_date(reg_date);
		
		return transactionDao.insert(tran);
	}

	@Override
	public int update(Map<String, String> fm) throws ParseException {
		System.out.println("TranService > update() called");
		
		Transaction tran = populateTransactionCommonField(fm);
		tran.setTran_id(fm.get("TRAN_ID"));
		
		return transactionDao.update(tran);
	}

	/* ------------- 공통 함수 ------------- */
	
	private Transaction populateTransactionCommonField(Map<String, String> fm) throws ParseException {
		Timestamp tran_date = DateUtil.stringToTimestamp(fm.get("DATE"));
		String inex = fm.get("INEX");
		String ccode = fm.get("CCODE").isEmpty() ? "caNN0" : fm.get("CCODE");
		String item = fm.get("ITEM").trim().isEmpty() ? null : fm.get("ITEM");
		Integer amount = Integer.parseInt(fm.get("AMOUNT"));
		String mncrd = inex.equals("IN") ? "none" : fm.get("MNCRD");
		if(inex.equals("EX") && mncrd.isEmpty()) mncrd = "meNN"; 
		String mcode = inex.equals("IN") ? "none" : fm.get("MCODE");
		if(inex.equals("EX") && mcode.isEmpty()) mcode = "meNN0"; 
		
		Transaction tran = new Transaction();
		tran.setTran_date(tran_date);
		tran.setInex(inex);
		tran.setCate_code(ccode);
		tran.setItem(item);
		tran.setAmount(amount);
		tran.setMncrd(mncrd);
		tran.setMeth_code(mcode);
		
		return tran;
	}
	
	private TranSearchDto populateTranSearchDto(TranSearchDto searchDto, int user_id, String inex) throws ParseException {
		// 검색 조건 설정
		String d_from = searchDto.getD_FROM();
		String d_to = searchDto.getD_TO();
		searchDto.setTs_from( (d_from != null && d_from != "") ? DateUtil.stringToTimestamp(d_from) : null);
		searchDto.setTs_to( (d_to != null && d_to != "") ? DateUtil.stringToTimestamp(d_to) : null);
		searchDto.setInex( (inex != "ALL") ? inex : null);
		searchDto.setUser_id(user_id);
		// 페이지 설정
		if(searchDto.getPG() == null) searchDto.setPG(1);
		int pg = searchDto.getPG();
		// N줄 보기 설정
		if(searchDto.getRC() == null) searchDto.setRC(clv.getDefault_rowCount());
		int rc = searchDto.getRC();
		// DB 조회할 덩어리(offset, limit) 설정
		int start = (pg - 1) * rc;
		searchDto.setStart(start);
		
		return searchDto;
	}
	
	private TranPageDto populateTranPageDto(List<TransactionDto> list, int totalCount, TranSearchDto searchDto, String inex) {
			// 페이징 변수 계산
			int totalPages = (int) Math.ceil((double) totalCount / searchDto.getRC());
			int currentPage = searchDto.getPG();
			int pagesPerSet = clv.getFinal_pagesPerSet();
			int currentSet = (currentPage - 1) / pagesPerSet + 1;
			int startPage = (currentSet - 1) * pagesPerSet + 1;
			int endPage = Math.min( (startPage + pagesPerSet - 1), totalPages);
			// 필요한 정보 DTO 에 설정해서 반환
			TranPageDto resultDto = new TranPageDto();
			resultDto.setList(list);
			resultDto.setTotalCount(totalCount);
			resultDto.setTotalPages(totalPages);
			resultDto.setCurrentPage(currentPage);
			resultDto.setRowCount(searchDto.getRC());
			resultDto.setCurrentSet(currentSet);
			resultDto.setStartPage(startPage);
			resultDto.setEndPage(endPage);
			resultDto.setRowCount_option(clv.getDefault_rowCount_optionString());
			resultDto.setInex((inex != "") ? inex : null);
			
			return resultDto;
	}
}

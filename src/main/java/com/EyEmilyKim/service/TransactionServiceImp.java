package com.EyEmilyKim.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EyEmilyKim.config.properties.ClientViewProperties;
import com.EyEmilyKim.dao.TransactionDao;
import com.EyEmilyKim.dto.TransactionDto;
import com.EyEmilyKim.dto.request.tran.TranCreateRequestDto;
import com.EyEmilyKim.dto.request.tran.TranListPopulatedRequestDto;
import com.EyEmilyKim.dto.request.tran.TranListRequestDto;
import com.EyEmilyKim.dto.request.tran.TranPostRequestDto;
import com.EyEmilyKim.dto.request.tran.TranUpdateRequestDto;
import com.EyEmilyKim.dto.response.tran.TranListResponseDto;
import com.EyEmilyKim.entity.Transaction;
import com.EyEmilyKim.util.DateUtil;

@Service
public class TransactionServiceImp implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private ClientViewProperties config;
	
	@Override
	public TranListResponseDto getList(TranListRequestDto requestDto, int user_id, String inex) throws ParseException {
		System.out.println("TranService > getList() called");

		// RequestDto 변수 처리
		TranListPopulatedRequestDto populatedRequestDto = this.populateTranListRequestDto(requestDto, user_id, inex);
		// 실제 데이터 가져오기
		List<TransactionDto> list = transactionDao.getList(populatedRequestDto);
		// 전체 데이터 수 가져오기
		int totalCount = transactionDao.getCount(populatedRequestDto);
		// 데이터, 페이징 변수 포함한 ResponseDto 준비
		TranListResponseDto responseDto = this.populateTranListResponseDto(list, totalCount, populatedRequestDto, inex);
		
		return responseDto;
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
	public int insert(TranCreateRequestDto requestDto, int user_id) throws Exception {
		System.out.println("TranService > insert() called");

		Timestamp reg_date = DateUtil.dateToTimestamp(new Date());
		
		Transaction tran = this.populateTransactionCommonFields(requestDto);
		tran.setUser_id(user_id);
		tran.setMy_seqno(requestDto.getMY_SEQNO());
		tran.setTran_id(user_id + "_" + requestDto.getMY_SEQNO());
		tran.setReg_date(reg_date);
		
		return transactionDao.insert(tran);
	}

	@Override
	public int update(TranUpdateRequestDto requestDto) throws ParseException {
		System.out.println("TranService > update() called");
		
		Transaction tran = this.populateTransactionCommonFields(requestDto);
		tran.setTran_id(requestDto.getTRAN_ID());
		
		return transactionDao.update(tran);
	}

	/* ------------- 공통 함수 ------------- */
	
	private Transaction populateTransactionCommonFields(TranPostRequestDto requestDto) throws ParseException {
		Timestamp tran_date = DateUtil.stringToTimestamp(requestDto.getDATE());
		String inex = requestDto.getINEX();
		String ccode = requestDto.getCCODE().isEmpty() ? "caNN0" : requestDto.getCCODE();
		String item = requestDto.getITEM().trim().isEmpty() ? null : requestDto.getITEM();
		Integer amount = requestDto.getAMOUNT();
		String mncrd = inex.equals("IN") ? "none" : requestDto.getMNCRD();
		if(inex.equals("EX") && mncrd.isEmpty()) mncrd = "meNN"; 
		String mcode = inex.equals("IN") ? "none" : requestDto.getMCODE();
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
	
	private TranListPopulatedRequestDto populateTranListRequestDto(TranListRequestDto requestDto, int user_id, String inex) throws ParseException {

		TranListPopulatedRequestDto resultDto = new TranListPopulatedRequestDto();
		
		// 검색 조건 설정
		resultDto.setUser_id(user_id);
		String d_from = requestDto.getD_FROM();
		String d_to = requestDto.getD_TO();
		resultDto.setTs_from( (d_from != null && d_from != "") ? DateUtil.stringToTimestamp(d_from) : null);
		resultDto.setTs_to( (d_to != null && d_to != "") ? DateUtil.stringToTimestamp(d_to) : null);
		resultDto.setINEX( (inex != "ALL") ? inex : null);
		// 페이지 설정
		int pg = requestDto.getPG() == null ? 1 : requestDto.getPG();
		resultDto.setPG(pg);
		// N줄 보기 설정
		int rc = requestDto.getRC() == null ? config.getDefault_rowCount() : requestDto.getRC();
		resultDto.setRC(rc);
		// DB 조회할 덩어리(offset, limit) 설정
		int start = (pg - 1) * rc;
		resultDto.setStart(start);
		
		return resultDto;
	}
	
	private TranListResponseDto populateTranListResponseDto(List<TransactionDto> list, int totalCount, TranListRequestDto requestDto, String inex) {
			// 페이징 변수 계산
			int totalPages = (int) Math.ceil((double) totalCount / requestDto.getRC());
			int currentPage = requestDto.getPG();
			int pagesPerSet = config.getFinal_pagesPerSet();
			int currentSet = (currentPage - 1) / pagesPerSet + 1;
			int startPage = (currentSet - 1) * pagesPerSet + 1;
			int endPage = Math.min( (startPage + pagesPerSet - 1), totalPages);
			// 필요한 정보 DTO 에 설정해서 반환
			TranListResponseDto responseDto = new TranListResponseDto();
			responseDto.setList(list);
			responseDto.setTotalCount(totalCount);
			responseDto.setTotalPages(totalPages);
			responseDto.setCurrentPage(currentPage);
			responseDto.setRowCount(requestDto.getRC());
			responseDto.setCurrentSet(currentSet);
			responseDto.setStartPage(startPage);
			responseDto.setEndPage(endPage);
			responseDto.setRowCount_option(config.getDefault_rowCount_optionString());
			responseDto.setInex((inex != "") ? inex : null);
			
			return responseDto;
	}
}

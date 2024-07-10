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
	public TranPageDto getListAll(TranSearchDto searchDto, int user_id) throws ParseException {
		System.out.println("TranService > getListAll() called");
		
		// 실제 데이터 가져오기
		searchDto = this.setTranSearchDto(searchDto, user_id);
		List<TransactionDto> list = transactionDao.getListAll(searchDto);
		// 전체 데이터 수 가져오기
		int totalCount = transactionDao.getCount(searchDto);
		// 페이징 관련 계산
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
		
		return resultDto;
	}

	@Override
	public Integer getCount(TranSearchDto searchDto, int user_id) throws ParseException {
		System.out.println("TranService > getCount() called");
		
		searchDto = this.setTranSearchDto(searchDto, user_id);
		return transactionDao.getCount(searchDto);
	}

	@Override
	public Transaction getOne(String tran_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String tran_id) {
		// TODO Auto-generated method stub
		return 0;
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

		Timestamp tran_date = DateUtil.stringToTimestamp(fm.get("DATE"));
		String inex = fm.get("INEX");
		String ccode = fm.get("CCODE") == "" ? "caNN0" : fm.get("CCODE");
		String item = fm.get("ITEM").trim() == "" ? null : fm.get("ITEM");
		String mncrd = fm.get("MNCRD") == "" ? null : fm.get("MNCRD");
		if(inex == "EX" && mncrd == null) mncrd = "meNN"; 
		String mcode = fm.get("MCODE") == "" ? "meNN0" : fm.get("MCODE");
		Timestamp reg_date = DateUtil.dateToTimestamp(new Date());
		
		Transaction tran = new Transaction();
		tran.setUser_id(user_id);
		tran.setMy_seqno(Integer.parseInt(fm.get("MY_SEQNO")));
		tran.setTran_id(user_id + "_" + fm.get("MY_SEQNO"));
		tran.setTran_date(tran_date);
		tran.setInex(inex);
		tran.setCate_code(ccode);
		tran.setItem(item);
		tran.setAmount(Integer.parseInt(fm.get("AMOUNT")));
		tran.setMncrd(mncrd);
		tran.setMeth_code(mcode);
		tran.setReg_date(reg_date);
		
		return transactionDao.insert(tran);
	}

	@Override
	public int update(Map<String, String> fm) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* ------------- 공통 함수 ------------- */
	
	private TranSearchDto setTranSearchDto(TranSearchDto searchDto, int user_id) throws ParseException {
		// 검색 조건 설정
		String d_from = searchDto.getD_FROM();
		String d_to = searchDto.getD_TO();
		if (d_from != null && d_from != "") searchDto.setTS_FROM(DateUtil.stringToTimestamp(d_from));
		if (d_from == "") searchDto.setTS_FROM(null);
		if (d_to != null && d_to != "") searchDto.setTS_TO(DateUtil.stringToTimestamp(d_to));
		if (d_to == "") searchDto.setTS_TO(null);
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
}

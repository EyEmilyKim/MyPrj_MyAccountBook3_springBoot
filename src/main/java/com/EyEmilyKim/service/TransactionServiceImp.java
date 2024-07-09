package com.EyEmilyKim.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EyEmilyKim.dao.TransactionDao;
import com.EyEmilyKim.dto.TransactionDto;
import com.EyEmilyKim.entity.Transaction;
import com.EyEmilyKim.util.DateUtil;

@Service
public class TransactionServiceImp implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;
	
	@Override
	public List<TransactionDto> getListAll(int user_id) {
		System.out.println("TranService > getListAll() called");
		
		List<TransactionDto> list = transactionDao.getListAll(user_id);
		return list;
	}

	@Override
	public Integer getCount(int user_id) {
		System.out.println("TranService > getCount() called");
		
		return transactionDao.getCount(user_id);
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

}

package com.cm.manage.dao.account.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.account.IDrawTransferRecordDao;
import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.model.account.DrawTransferRecord;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.account.BatchTransferVO;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
@Repository("drawTransferDao")
public class DrawTransferRecordDaoImpl implements IDrawTransferRecordDao {

	@Autowired
    private IBaseDao<DrawTransferRecord> drawTransferDao;
	
	@Autowired
    private IBaseDao<Map> transferMapDao;

	@Override
	public void save(DrawTransferRecord record) {
		drawTransferDao.save(record);
		
	}

	@Override
	public void update(DrawTransferRecord record) {
		drawTransferDao.update(record);
		
	}

	@Override
	public DrawTransferRecord findByBatchNo(String batchNo) {
		String hql = "from DrawTransferRecord d where d.batchNo = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(batchNo);
		List<DrawTransferRecord> results = drawTransferDao.find(hql, param);
		if(results != null && results.size() > 0){
			return results.get(0);
		}
		return null;
	}

	@Override
	public EasyuiDataGridJson transferList(EasyuiDataGrid dg,BatchTransferVO transferVO) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder("select t.batch_no as batchNo,t.create_time as createTime,");
		sb.append("t.accept_time as acceptTime,t.total_amount as totalAmount,t.total_number as totalNumber,");
		sb.append("t.status as status,t.success_amount as successAmount,t.success_number as successNumber,");
		sb.append("t.fail_amount as failAmount,t.fail_number as failNumber,t.people_accepted as peopleAccepted,");
		sb.append("t.transfer_filename as transferFilename,t.error_code as errorCode from account_draw_record t where 1=1 ");
		List<Object> values = new ArrayList<Object>();
		if(transferVO != null){
			boolean flag = transferVO.getFlag();
			if (CommonUtil.isNotEmpty(transferVO.getBatchNo())) {
                if (flag) {
                    sb.append(" and t.batch_no like ? ");
                    values.add("%" + transferVO.getBatchNo() + "%");
                } else {
                    sb.append(" and t.batch_no = ? ");
                    values.add(transferVO.getBatchNo());
                }
            }
			if (CommonUtil.isNotEmpty(transferVO.getStatus())) {
				sb.append(" and t.status = ?");
                values.add(transferVO.getStatus());
            }
			String fromSeconds = " 00:00:00";
			String toSeconds = " 23:59:59";
			 if (CommonUtil.isNotEmpty(transferVO.getCreateStartDate())) {
                sb.append(" and t.create_time >= ?");
                values.add(DateUtil.format(transferVO.getCreateStartDate() + fromSeconds, "yy-MM-dd HH:mm:ss"));
            }
            if (CommonUtil.isNotEmpty(transferVO.getCreateEndDate())) {
                sb.append(" and t.create_time <= ?");
                values.add(DateUtil.format(transferVO.getCreateEndDate() + toSeconds, "yy-MM-dd HH:mm:ss"));
            }

            if (CommonUtil.isNotEmpty(transferVO.getAcceptStartDate())) {
                sb.append(" and t.accept_time >= ?");
                values.add(DateUtil.format(transferVO.getAcceptStartDate() + fromSeconds, "yy-MM-dd HH:mm:ss"));
            }
            if (CommonUtil.isNotEmpty(transferVO.getAcceptEndDate())) {
                sb.append(" and t.accept_time <= ?");
                values.add(DateUtil.format(transferVO.getAcceptEndDate() + toSeconds, "yy-MM-dd HH:mm:ss"));
            }
		}
		 String totalHql = " select count(*)  from (" + sb + ")";
	     j.setTotal(transferMapDao.countBySql(totalHql, values).longValue());// 设置总记录数
	     sb.append(" order by t.create_time desc ");
	     List<Map> transferList = transferMapDao.findBySql(sb.toString(), values, dg.getPage(), dg.getRows());
	     List<BatchTransferVO> transferVOList = new ArrayList<BatchTransferVO>();
	     if(transferList != null && transferList.size() > 0){
	    	 for(Map map : transferList){
	    		 BatchTransferVO batchTransferVO = new BatchTransferVO();
	    		 batchTransferVO.setBatchNo((String)map.get("BATCHNO"));
	    		 Date createTime = (Date) map.get("CREATETIME");
	             Date acceptTime = (Date) map.get("ACCEPTTIME");
	             batchTransferVO.setCreateTime(DateUtil.format(createTime));
	             batchTransferVO.setAcceptTime(DateUtil.format(acceptTime));
	             batchTransferVO.setTotalAmount(CommonUtil.formatDouble(map.get("TOTALAMOUNT")).doubleValue());
	             batchTransferVO.setTotalNumber(CommonUtil.formatDouble(map.get("TOTALNUMBER")).intValue());
	             batchTransferVO.setSuccessAmount(CommonUtil.formatDouble(map.get("SUCCESSAMOUNT")).doubleValue());
	             batchTransferVO.setSuccessNumber(CommonUtil.formatDouble(map.get("SUCCESSNUMBER")).intValue());
	             batchTransferVO.setFailAmount(CommonUtil.formatDouble(map.get("FAILAMOUNT")).doubleValue());
	             batchTransferVO.setFailNumber(CommonUtil.formatDouble(map.get("FAILNUMBER")).intValue());
	             batchTransferVO.setPeopleAccepted((String)map.get("PEOPLEACCEPTED"));
	    		 batchTransferVO.setTransferFilename((String)map.get("TRANSFERFILENAME"));
	    		 batchTransferVO.setStatus(CommonUtil.formatDouble(map.get("STATUS")).intValue());
	    		 transferVOList.add(batchTransferVO);
	    	 }
	     }
	     j.setRows(transferVOList);
		return j;
	}

	@Override
	public List<DrawTransferRecord> getTransferByStatus(Integer status) {
		String hql = "from DrawTransferRecord d where d.status = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(status);
		List<DrawTransferRecord> results = drawTransferDao.find(hql, param);
		return results;
	}
	
}

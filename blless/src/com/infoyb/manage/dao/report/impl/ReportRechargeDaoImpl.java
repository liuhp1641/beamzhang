package com.cm.manage.dao.report.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.constant.IReportConstants;
import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.report.IReportRechargeDao;
import com.cm.manage.model.report.ReportRecharge;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.report.ReportRechargeVO;

@Repository("reportRechargeDao")
public class ReportRechargeDaoImpl implements IReportRechargeDao {

	@Autowired
	private IBaseDao<Object> reportRechargeDao;
	@Override
	public boolean saveReportRecharge() {
		StringBuffer str=new StringBuffer(" select a.fill_resources fill_resources, sum(a.real_amount) amount ");
		str.append(" from account_fill a where a.status = 1");
		str.append(" and a.accept_time >= to_date(?, 'yyyy-mm-dd')");
		str.append(" and a.accept_time < to_date(?, 'yyyy-mm-dd')");
		str.append(" group by a.fill_resources");
		str.append(" order by a.fill_resources");
		
		List<Object> values = new ArrayList<Object>();
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		String endTime=DateUtil.format(date.getTime(),"yyyy-MM-dd");
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String beginTime=DateUtil.format(date.getTime(),"yyyy-MM-dd");
		values.add(beginTime);
		values.add(endTime);
//		values.add("2014-11-28");
//		values.add("2014-11-29");
		List<Map> resultMaps=reportRechargeDao.findBySql(str.toString(),values);
		ReportRecharge reportRecharge=new ReportRecharge();
		Double sumRecharge=0.0;
        if (resultMaps != null && resultMaps.size() > 0) {// 转换模型
            for (Map map : resultMaps) {
            	String fillResources = map.get("FILL_RESOURCES") == null ? "" : (String) map.get("FILL_RESOURCES");
            	BigDecimal amount = map.get("AMOUNT") == null ? new BigDecimal(0) : (BigDecimal) map.get("AMOUNT");
            	if(IReportConstants.FILL_FROM_ALIPAY_CLIENT.equals(fillResources)){//支付宝
            		reportRecharge.setZhiFuBao(amount.doubleValue());
            		sumRecharge=sumRecharge+amount.doubleValue();
            	}else if(IReportConstants.FILL_FROM_SZF_CLIENT.equals(fillResources)){//神州付
            		reportRecharge.setShenZhouFu(amount.doubleValue());
            		sumRecharge=sumRecharge+amount.doubleValue();
            	}else if(IReportConstants.FILL_FROM_LLF_CLIENT .equals(fillResources)){//连连付
            		reportRecharge.setLianLianZhifu(amount.doubleValue());
            		sumRecharge=sumRecharge+amount.doubleValue();
            	}else if(IReportConstants.FILL_FROM_BFB_CLIENT .equals(fillResources)){//百付宝
            		reportRecharge.setBaiFuBao(amount.doubleValue());
            		sumRecharge=sumRecharge+amount.doubleValue();
            	}else if(IReportConstants.FILL_FROM_ALIPAY_WAP.equals(fillResources)){//支付宝wap
            		reportRecharge.setZhiFuBaoWap(amount.doubleValue());
            		sumRecharge=sumRecharge+amount.doubleValue();
            	}else if(IReportConstants.FILL_FROM_LLF_WAP .equals(fillResources)){//连连付wap
            		reportRecharge.setZhiFuBaoWap(amount.doubleValue());
            		sumRecharge=sumRecharge+amount.doubleValue();
            	}
            }
            reportRecharge.setReportDate(date.getTime());
            reportRecharge.setXianxiRecharge(0.0);
            reportRecharge.setSumRecharge(sumRecharge);//累计
            reportRechargeDao.save(reportRecharge);
        }		
		return true;
	}

	@Override
	public List<ReportRechargeVO> getReportRecharge(String year,String month) {
		StringBuffer str=new StringBuffer("select   sum(t.zhi_fu_bao) zhi_fu_bao ");
		str.append(" ,sum(t.bai_fu_bao)bai_fu_bao ,sum(t.lian_lian_zhifu) lian_lian_zhifu, ");
		str.append(" sum(t.shen_zhou_fu) shen_zhou_fu,sum(t.xianxi_recharge) xianxi_recharge,");
		str.append(" sum(t.sum_recharge) sum_recharge, sum(t.zhi_fu_bao_wap) zhi_fu_bao_wap, ");
		str.append(" sum(t.bai_fu_bao_wap) bai_fu_bao_wap from report_recharge t where 1=1 ");
		str.append(" and t.report_date >= to_date(?, 'yyyy-mm')");
		str.append(" and t.report_date < to_date(?, 'yyyy-mm')");
		
		List<Object> values = new ArrayList<Object>();
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, Integer.valueOf(year));
		date.set(Calendar.MONTH, Integer.valueOf(month));
		String endMonth=DateUtil.format(date.getTime(),"yyyy-MM");
		values.add(year+"-"+month);	
		values.add(endMonth);
		List<Map> reportRechargeMaps=reportRechargeDao.findBySql(str.toString(),values);
		
		List<ReportRechargeVO> reportRechargeList = new ArrayList<ReportRechargeVO>();
        if (reportRechargeMaps != null && reportRechargeMaps.size() > 0) {// 转换模型
            for (Map map : reportRechargeMaps) {
            	ReportRechargeVO a = new ReportRechargeVO();
            	BigDecimal zhiFuBao = map.get("ZHI_FU_BAO") == null ? new BigDecimal(0) : (BigDecimal) map.get("ZHI_FU_BAO");
            	BigDecimal baiFuBao = map.get("BAI_FU_BAO") == null ? new BigDecimal(0) : (BigDecimal) map.get("BAI_FU_BAO");
            	BigDecimal baiFuBaoWap = map.get("BAI_FU_BAO_WAP") == null ? new BigDecimal(0) : (BigDecimal) map.get("BAI_FU_BAO_WAP");
            	BigDecimal zhiFuBaoWap = map.get("ZHI_FU_BAO_WAP") == null ? new BigDecimal(0) : (BigDecimal) map.get("ZHI_FU_BAO_WAP");
            	BigDecimal lianLianZhifu = map.get("LIAN_LIAN_ZHIFU") == null ? new BigDecimal(0) : (BigDecimal) map.get("LIAN_LIAN_ZHIFU");
            	BigDecimal shenZhouFu = map.get("SHEN_ZHOU_FU") == null ? new BigDecimal(0) : (BigDecimal) map.get("SHEN_ZHOU_FU");
            	BigDecimal sumRecharge = map.get("SUM_RECHARGE") == null ? new BigDecimal(0) : (BigDecimal) map.get("SUM_RECHARGE");
            	BigDecimal xianxiaRecharge = map.get("XIANXI_RECHARGE") == null ? new BigDecimal(0) : (BigDecimal) map.get("XIANXI_RECHARGE");
            	a.setZhiFuBao(zhiFuBao.doubleValue()+zhiFuBaoWap.doubleValue());
            	a.setBaiFuBao(baiFuBao.doubleValue()+ baiFuBaoWap.doubleValue());
            	a.setLianLianZhifu(lianLianZhifu.doubleValue());
            	a.setShenZhouFu( shenZhouFu.doubleValue());
            	a.setSumRecharge( sumRecharge.doubleValue());
            	a.setXianxiRecharge(xianxiaRecharge.doubleValue());
                Date reportDate = (Date) map.get("REPORT_DATE");
                a.setReportDate(DateUtil.format(reportDate));
                reportRechargeList.add(a);
            }
        }
		return reportRechargeList;
	}

	@Override
	public List<ReportRechargeVO> getReportRechargeDetail(String year,String month) {
		StringBuffer str=new StringBuffer("select  t.report_date, t.zhi_fu_bao,");
		str.append(" t.bai_fu_bao,t.lian_lian_zhifu, ");
		str.append(" t.shen_zhou_fu,t.xianxi_recharge,");
		str.append(" t.sum_recharge, t.zhi_fu_bao_wap, ");
		str.append(" t.bai_fu_bao_wap from report_recharge t where 1=1 ");
		str.append(" and t.report_date >= to_date(?, 'yyyy-mm')");
		str.append(" and t.report_date < to_date(?, 'yyyy-mm')");
		str.append(" order by t.report_date");
		
		List<Object> values = new ArrayList<Object>();
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, Integer.valueOf(year));
		date.set(Calendar.MONTH, Integer.valueOf(month));
		String endMonth=DateUtil.format(date.getTime(),"yyyy-MM");
		values.add(year+"-"+month);	
		values.add(endMonth);
		List<Map> reportRechargeMaps=reportRechargeDao.findBySql(str.toString(),values);
		
		List<ReportRechargeVO> reportRechargeList = new ArrayList<ReportRechargeVO>();
        if (reportRechargeMaps != null && reportRechargeMaps.size() > 0) {// 转换模型
            for (Map map : reportRechargeMaps) {
            	ReportRechargeVO a = new ReportRechargeVO();
            	BigDecimal zhiFuBao = map.get("ZHI_FU_BAO") == null ? new BigDecimal(0) : (BigDecimal) map.get("ZHI_FU_BAO");
            	BigDecimal baiFuBao = map.get("BAI_FU_BAO") == null ? new BigDecimal(0) : (BigDecimal) map.get("BAI_FU_BAO");
            	BigDecimal baiFuBaoWap = map.get("BAI_FU_BAO_WAP") == null ? new BigDecimal(0) : (BigDecimal) map.get("BAI_FU_BAO_WAP");
            	BigDecimal zhiFuBaoWap = map.get("ZHI_FU_BAO_WAP") == null ? new BigDecimal(0) : (BigDecimal) map.get("ZHI_FU_BAO_WAP");
            	BigDecimal lianLianZhifu = map.get("LIAN_LIAN_ZHIFU") == null ? new BigDecimal(0) : (BigDecimal) map.get("LIAN_LIAN_ZHIFU");
            	BigDecimal shenZhouFu = map.get("SHEN_ZHOU_FU") == null ? new BigDecimal(0) : (BigDecimal) map.get("SHEN_ZHOU_FU");
            	BigDecimal sumRecharge = map.get("SUM_RECHARGE") == null ? new BigDecimal(0) : (BigDecimal) map.get("SUM_RECHARGE");
            	BigDecimal xianxiaRecharge = map.get("XIANXI_RECHARGE") == null ? new BigDecimal(0) : (BigDecimal) map.get("XIANXI_RECHARGE");
            	a.setZhiFuBao(zhiFuBao.doubleValue()+zhiFuBaoWap.doubleValue());
            	a.setBaiFuBao(baiFuBao.doubleValue()+ baiFuBaoWap.doubleValue());
            	a.setLianLianZhifu(lianLianZhifu.doubleValue());
            	a.setShenZhouFu( shenZhouFu.doubleValue());
            	a.setSumRecharge( sumRecharge.doubleValue());
            	a.setXianxiRecharge(xianxiaRecharge.doubleValue());
                Date reportDate = (Date) map.get("REPORT_DATE");
                a.setReportDate(DateUtil.format(reportDate,"yyyy-MM-dd"));
                reportRechargeList.add(a);
            }
        }
		return reportRechargeList;
	}

	@Override
	public Map<String, Object> prepareExcelData(String year, String month) {
		List<ReportRechargeVO> reportRechargeList =this.getReportRechargeDetail(year, month);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("drawList", reportRechargeList);
		String date=year+"-"+month;
		resMap.put("date", date);
		return resMap;
	}
	
	}

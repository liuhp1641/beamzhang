package com.cm.manage.dao.account.impl;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.dao.account.IDrawDao;
import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.model.account.Draw;
import com.cm.manage.model.account.DrawBindBank;
import com.cm.manage.model.member.Member;
import com.cm.manage.util.alipay.AlipayOrderItem;
import com.cm.manage.util.base.CommonBusiUtil;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.account.DrawVO;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("drawDao")
public class DrawDaoImpl implements IDrawDao {
    @Autowired
    private IBaseDao<Map> drawDao;
    @Autowired
    private IBaseDao<Draw> drawBeanDao;
    @Autowired
    private IBaseDao<DrawBindBank> bankDao;
    @Autowired
    private IBaseDao<Member> memberDao;

    /**
     * 用户资金查询
     *
     * @param dg
     * @param draw
     * @return
     */
    public EasyuiDataGridJson drawList(EasyuiDataGrid dg, DrawVO draw) {
        EasyuiDataGridJson j = new EasyuiDataGridJson();
        StringBuilder sb = new StringBuilder("select u.user_code as userCode,u.user_name as userName,u.real_name as realName,u.card_code as cardCode,u.vip as vip,");
        sb.append("d.create_time as createTime,d.accept_time as acceptTime,d.amount as amount,d.bonus_amount_new as bonus,d.fee as fee,d.draw_info as drawInfo,d.status as status,");
        sb.append("d.people_accepted as peopleAccepted,d.memo as memo,d.order_id as orderId,d.error_msg as errorMsg,d.real_fee as realFee,d.draw_limit as drawBeforeLimit ");
        sb.append(" from account_draw d,user_member u where d.user_code = u.user_code ");
        List<Object> values = new ArrayList<Object>();
        if (draw != null) {// 添加查询条件
            boolean flag = draw.isFlag();

            if (CommonUtil.isNotEmpty(draw.getUserCode())) {
                if (flag) {
                    sb.append(" and u.user_code like ? ");
                    values.add("%" + draw.getUserCode() + "%");
                } else {
                    sb.append(" and u.user_code = ? ");
                    values.add(draw.getUserCode());
                }
            }

            if (CommonUtil.isNotEmpty(draw.getUserName())) {
                if (flag) {
                    sb.append(" and u.user_name like ? ");
                    values.add("%" + draw.getUserName() + "%");
                } else {
                    sb.append(" and u.user_name = ? ");
                    values.add(draw.getUserName());
                }
            }

            if (CommonUtil.isNotEmpty(draw.getRealName())) {
                if (flag) {
                    sb.append(" and u.real_name like ? ");
                    values.add("%" + draw.getRealName() + "%");
                } else {
                    sb.append(" and u.real_name = ? ");
                    values.add(draw.getRealName());
                }
            }

            if (CommonUtil.isNotEmpty(draw.getCardCode())) {
                if (flag) {
                    sb.append(" and u.card_code like ? ");
                    values.add("%" + draw.getCardCode() + "%");
                } else {
                    sb.append(" and u.card_code = ? ");
                    values.add(draw.getCardCode());
                }
            }

            if (CommonUtil.isNotEmpty(draw.getCreateStartDate())) {
                sb.append(" and d.create_time >= ?");
                values.add(DateUtil.format(draw.getCreateStartDate(), "yy-MM-dd HH:mm:ss"));
            }
            if (CommonUtil.isNotEmpty(draw.getCreateEndDate())) {
                sb.append(" and d.create_time <= ?");
                values.add(DateUtil.format(draw.getCreateEndDate(), "yy-MM-dd HH:mm:ss"));
            }

            if (CommonUtil.isNotEmpty(draw.getAcceptStartDate())) {
                sb.append(" and d.accept_time >= ?");
                values.add(DateUtil.format(draw.getAcceptStartDate(), "yy-MM-dd HH:mm:ss"));
            }
            if (CommonUtil.isNotEmpty(draw.getAcceptEndDate())) {
                sb.append(" and d.accept_time <= ?");
                values.add(DateUtil.format(draw.getAcceptEndDate(), "yy-MM-dd HH:mm:ss"));
            }

            if (CommonUtil.isNotEmpty(draw.getOrderId())) {
                if (flag) {
                    sb.append(" and d.order_id like ? ");
                    values.add("%" + draw.getOrderId() + "%");
                } else {
                    sb.append(" and d.order_id = ? ");
                    values.add(draw.getOrderId());
                }
            }

            if (CommonUtil.isNotEmpty(draw.getTransferBatchId())) {
                if (flag) {
                    sb.append(" and d.transfer_batch_id like ? ");
                    values.add("%" + draw.getTransferBatchId() + "%");
                } else {
                    sb.append(" and d.transfer_batch_id = ? ");
                    values.add(draw.getTransferBatchId());
                }
            }

            if (CommonUtil.isNotEmpty(draw.getStatus())) {
                sb.append(" and d.status = ?");
                values.add(draw.getStatus());
            }

            if (CommonUtil.isNotEmpty(draw.getAmountMin())) {
                sb.append(" and d.amount >= ?");
                values.add(draw.getAmountMin());
            }
            if (CommonUtil.isNotEmpty(draw.getAmountMax())) {
                sb.append(" and d.amount <= ?");
                values.add(draw.getAmountMax());
            }

            if (CommonUtil.isNotEmpty(draw.getDrawInfo())) {
                sb.append(" and d.draw_info like ? ");
                values.add("%" + draw.getDrawInfo() + "%");
            }

            if (CommonUtil.isNotEmpty(draw.getMemo())) {
                sb.append(" and d.memo like ? ");
                values.add("%" + draw.getMemo() + "%");
            }
        }
        String totalHql = " select count(*)  from (" + sb + ")";
        j.setTotal(drawDao.countBySql(totalHql, values).longValue());// 设置总记录数
        sb.append(" order by d.create_time desc ");

        List<Map> drawMapList = drawDao.findBySql(sb.toString(), values, dg.getPage(), dg.getRows());// 查询分页
        List<DrawVO> drawVOList = new ArrayList<DrawVO>();
        if (drawMapList != null && drawMapList.size() > 0) {// 转换模型
            for (Map map : drawMapList) {
                DrawVO drawVO = new DrawVO();
                drawVO.setUserCode((String) map.get("USERCODE"));
                drawVO.setUserName((String) map.get("USERNAME"));
                drawVO.setRealName((String) map.get("REALNAME"));
                drawVO.setOrderId((String) map.get("ORDERID"));

                Date createTime = (Date) map.get("CREATETIME");
                Date acceptTime = (Date) map.get("ACCEPTTIME");
                drawVO.setCreateTime(DateUtil.format(createTime));
                drawVO.setAcceptTime(DateUtil.format(acceptTime));

                drawVO.setAmount(CommonUtil.formatDouble(map.get("AMOUNT")).doubleValue());
                drawVO.setFee(CommonUtil.formatDouble(map.get("FEE")).doubleValue());
                String drawInfo = (String) map.get("DRAWINFO");

                drawVO.setDrawInfo(drawInfo);
 
                int vip = CommonUtil.formatDouble(map.get("VIP")).intValue();
                int status = CommonUtil.formatDouble(map.get("STATUS")).intValue();
                drawVO.setVip(vip);
           	 	if(vip > 0 && CommonConstants.DRAW_WAIT_REVIEW == status){//是vip,操作前实扣手续费显示0
           	 		drawVO.setRealFee(0.0);
           	 	}else{
           	 		drawVO.setRealFee(CommonUtil.formatDouble(map.get("REALFEE")));
           	 	}
                drawVO.setBonusAmountNew(CommonUtil.formatDouble(map.get("BONUS")));
                drawVO.setStatus(status);
                drawVO.setMemo((String) map.get("MEMO"));
                drawVO.setErrorMsg(CommonUtil.trim((String)map.get("ERRORMSG")));
                drawVO.setPeopleAccepted((String) map.get("PEOPLEACCEPTED"));
                drawVO.setDrawBeforeLimit(CommonUtil.formatDouble(map.get("DRAWBEFORELIMIT")));
                drawVOList.add(drawVO);
            }
        }
        j.setRows(drawVOList);// 设置返回的行
        return j;
    }

    @Override
    public Map drawCount(DrawVO draw) {
        StringBuilder sb = new StringBuilder("select sum(CASE WHEN d.status = 0 THEN d.amount ELSE 0 END) as amount0,");
        sb.append(" sum(CASE WHEN d.status = 1 THEN d.amount ELSE 0 END) as amount1,");
        sb.append(" sum(CASE WHEN d.status = 2 THEN d.amount ELSE 0 END) as amount2,");
        sb.append(" sum(CASE WHEN d.status = 3 THEN d.amount ELSE 0 END) as amount3,");
        sb.append(" sum(CASE WHEN d.status = 4 THEN d.amount ELSE 0 END) as amount4,");
        sb.append(" sum(CASE WHEN d.status = 5 THEN d.amount ELSE 0 END) as amount5");
        sb.append(" from account_draw d,user_member u where d.user_code = u.user_code ");
        List<Object> values = new ArrayList<Object>();
        if (draw != null) {// 添加查询条件
            boolean flag = draw.isFlag();

            if (CommonUtil.isNotEmpty(draw.getUserCode())) {
                if (flag) {
                    sb.append(" and u.user_code like ? ");
                    values.add("%" + draw.getUserCode() + "%");
                } else {
                    sb.append(" and u.user_code = ? ");
                    values.add(draw.getUserCode());
                }
            }

            if (CommonUtil.isNotEmpty(draw.getUserName())) {
                if (flag) {
                    sb.append(" and u.user_name like ? ");
                    values.add("%" + draw.getUserName() + "%");
                } else {
                    sb.append(" and u.user_name = ? ");
                    values.add(draw.getUserName());
                }
            }

            if (CommonUtil.isNotEmpty(draw.getRealName())) {
                if (flag) {
                    sb.append(" and u.real_name like ? ");
                    values.add("%" + draw.getRealName() + "%");
                } else {
                    sb.append(" and u.real_name = ? ");
                    values.add(draw.getRealName());
                }
            }

            if (CommonUtil.isNotEmpty(draw.getCardCode())) {
                if (flag) {
                    sb.append(" and u.card_code like ? ");
                    values.add("%" + draw.getCardCode() + "%");
                } else {
                    sb.append(" and u.card_code = ? ");
                    values.add(draw.getCardCode());
                }
            }

            if (CommonUtil.isNotEmpty(draw.getCreateStartDate())) {
                sb.append(" and d.create_time >= ?");
                values.add(DateUtil.format(draw.getCreateStartDate(), "yy-MM-dd HH:mm:ss"));
            }
            if (CommonUtil.isNotEmpty(draw.getCreateEndDate())) {
                sb.append(" and d.create_time <= ?");
                values.add(DateUtil.format(draw.getCreateEndDate(), "yy-MM-dd HH:mm:ss"));
            }

            if (CommonUtil.isNotEmpty(draw.getAcceptStartDate())) {
                sb.append(" and d.accept_time >= ?");
                values.add(DateUtil.format(draw.getAcceptStartDate(), "yy-MM-dd HH:mm:ss"));
            }
            if (CommonUtil.isNotEmpty(draw.getAcceptEndDate())) {
                sb.append(" and d.accept_time <= ?");
                values.add(DateUtil.format(draw.getAcceptEndDate(), "yy-MM-dd HH:mm:ss"));
            }

            if (CommonUtil.isNotEmpty(draw.getOrderId())) {
                if (flag) {
                    sb.append(" and d.order_id like ? ");
                    values.add("%" + draw.getOrderId() + "%");
                } else {
                    sb.append(" and d.order_id = ? ");
                    values.add(draw.getOrderId());
                }
            }

            if (CommonUtil.isNotEmpty(draw.getTransferBatchId())) {
                if (flag) {
                    sb.append(" and d.transfer_batch_id like ? ");
                    values.add("%" + draw.getTransferBatchId() + "%");
                } else {
                    sb.append(" and d.transfer_batch_id = ? ");
                    values.add(draw.getTransferBatchId());
                }
            }

            if (CommonUtil.isNotEmpty(draw.getStatus())) {
                sb.append(" and d.status <= ?");
                values.add(draw.getStatus());
            }

            if (CommonUtil.isNotEmpty(draw.getAmountMin())) {
                sb.append(" and d.amount >= ?");
                values.add(draw.getAmountMin());
            }
            if (CommonUtil.isNotEmpty(draw.getAmountMax())) {
                sb.append(" and d.amount <= ?");
                values.add(draw.getAmountMax());
            }

            if (CommonUtil.isNotEmpty(draw.getDrawInfo())) {
                sb.append(" and d.draw_info like ? ");
                values.add("%" + draw.getDrawInfo() + "%");
            }

            if (CommonUtil.isNotEmpty(draw.getMemo())) {
                sb.append(" and d.memo like ? ");
                values.add("%" + draw.getMemo() + "%");
            }
        }
        List<Map> mapList = drawDao.findBySql(sb.toString(), values);
        if (CommonUtil.isNotEmpty(mapList)) {
            return mapList.get(0);
        }
        return null;
    }

	@SuppressWarnings("unchecked")
	@Override
	public Map drawDetail(DrawVO draw) {
		StringBuilder sb = new StringBuilder("select u.user_code as userCode,u.user_name as userName,u.real_name as realName,u.mobile as mobile,u.card_code as cardCode,u.vip as vip,");
        sb.append("to_char(d.create_time,'yyyy-mm-dd hh24:mi:ss') as createTime,to_char(d.accept_time,'yyyy-mm-dd hh24:mi:ss') as acceptTime,d.amount as amount,d.fee as fee,d.draw_info as drawinfo,d.status as status,");
        sb.append("d.memo as memo,d.order_id as orderId,d.real_fee as realFee,d.draw_limit as drawBeforeLimit");
        sb.append(" from account_draw d,user_member u ");
        sb.append(" where d.order_id = ? and d.user_code = u.user_code");
        List<Object> argsList = new ArrayList<Object>();
        argsList.add(draw.getOrderId());
        List<Map> resultList = drawDao.findBySql(sb.toString(), argsList);
        if (CommonUtil.isNotEmpty(resultList)) {
        	 Map map = resultList.get(0);
        	 Double amount = CommonUtil.formatDouble(map.get("AMOUNT"));//提现金额
        	 Double drawBeforeLimit = CommonUtil.formatDouble(map.get("DRAWBEFORELIMIT"));//提现前限额
        	 Double drawAfterLimit = CommonBusiUtil.sub(drawBeforeLimit, amount);
        	 if(drawAfterLimit < 0.0){
        		 drawAfterLimit = 0.0;
        	 }
        	 int status = CommonUtil.formatDouble(map.get("STATUS")).intValue();
        	 int vip = CommonUtil.formatDouble(map.get("VIP")).intValue();
        	 if(vip > 0 && CommonConstants.DRAW_WAIT_REVIEW == status){//未任何操作前vip实扣手续费显示0
        		 map.put("REALFEE",0.0);
        	 }
        	 map.put("drawBeforeLimit", drawBeforeLimit);
             map.put("drawAfterLimit",drawAfterLimit);
             return map;
        }
        return null;
	}

	@Override
	public boolean drawVerify(DrawVO draw) {
		StringBuilder sb = new StringBuilder("update account_draw t set t.memo=?,t.accept_time=?,t.people_accepted=?,t.status=?");
		List<Object> argsList = new ArrayList<Object>();
		argsList.add(draw.getMemo());
		argsList.add(DateUtil.format(draw.getAcceptTime(),"yyyy-MM-dd HH:mm:ss"));
		argsList.add(draw.getPeopleAccepted());
		argsList.add(draw.getStatus());
		if(draw.getRealFee() != null && ! "".equals(draw.getRealFee())){
			argsList.add(draw.getRealFee());
			sb.append(",t.real_fee=? where t.order_id=?");
		}else{
			sb.append(" where t.order_id=?");
		}
		
		argsList.add(draw.getOrderId());
		int result = drawDao.executeSql(sb.toString(), argsList);
		if(result > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean drawReject(DrawVO draw) {
		String sql = "update account_draw t set t.memo=?,t.accept_time=?,t.people_accepted=?,t.status=? where t.order_id=?";
		List<Object> argsList = new ArrayList<Object>();
		argsList.add(draw.getMemo());
		argsList.add(DateUtil.format(draw.getAcceptTime(),"yyyy-MM-dd HH:mm:ss"));
		argsList.add(draw.getPeopleAccepted());
		argsList.add(draw.getStatus());
		argsList.add(draw.getOrderId());
		int result = drawDao.executeSql(sql, argsList);
		if(result > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean drawBatchHandle(List<String> orderIdList, DrawVO draw) {

		if(orderIdList != null && orderIdList.size() > 0){
			StringBuilder sb = new StringBuilder("update account_draw t set t.memo=?,t.accept_time=?,t.people_accepted=?,t.status=? where 1=1 ");
			sb.append("and t.order_id in (");
			for(int i=0;i<orderIdList.size();i++){
				if(i == orderIdList.size() - 1){
					sb.append("?");
				}else{
					sb.append("?,");
				}
			}
			sb.append(")");
			List<Object> argsList = new ArrayList<Object>();
			argsList.add(draw.getMemo());
			argsList.add(DateUtil.format(draw.getAcceptTime(),"yyyy-MM-dd HH:mm:ss"));
			argsList.add(draw.getPeopleAccepted());
			argsList.add(draw.getStatus());
			argsList.addAll(orderIdList);
			int result = drawDao.executeSql(sb.toString(), argsList);
			if(result > 0){
				return true;
			}
		}
		return false;
	}
	@Override
	public Draw getDrawByOrderId(String orderId){
		String hql = "from Draw d where d.orderId=?";
		List<Draw> results = drawBeanDao.find(hql, new Object[]{orderId});
		if(results != null && results.size() > 0){
			Draw d = results.get(0);
			return d;
		}
		return null;
	}

	@Override
	public List<Draw> findDrawList(List<String> argsList) {
		if(argsList != null && argsList.size() > 0){
			StringBuilder sb = new StringBuilder("from Draw d ");
			sb.append("where d.orderId in (");
			for(int i=0;i<argsList.size();i++){
				if(i == argsList.size() - 1){
					sb.append("?");
				}else{
					sb.append("?,");
				}
			}
			sb.append(")");
			List<Draw> drawList = drawBeanDao.find(sb.toString(), argsList.toArray());
			return drawList;
		}
		return null;
	}

	@Override
	public void update(Draw d) {
		drawBeanDao.update(d);
		
	}

	@Override
	public void updateByBatchNo(String batchNo, int status,String errorMsg) {
		String sql = "update account_draw t set t.status=?,t.error_msg=? where t.transfer_batch_id=?";
		List<Object> param = new ArrayList<Object>();
		param.add(status);
		param.add(errorMsg);
		param.add(batchNo);
		drawDao.executeSql(sql, param);
	}

	@Override
	public void updateDrawInfo(int status,String alipayDrawNo,String errorMsg,String orderId) {
//		StringBuilder sb = new StringBuilder("update account_draw t set ");
//		if(params != null && params.size() > 0){
//			Iterator<String> it = params.keySet().iterator();
//			while(it.hasNext()){
//				String columnName = it.next();
//				String value = params.get(columnName);
//				sb.append("t.");
//				sb.append(columnName);
//				sb.append("=?,");
//			}
//		}
		String sql = "update account_draw t set t.status=?,t.alipay_draw_no=?,t.error_msg=?,t.accept_time=? where t.order_id=?";
		List<Object> param = new ArrayList<Object>();
		Date date = new Date();
		String nowStr = DateUtil.format(date);
		param.add(status);
		param.add(alipayDrawNo);
		param.add(errorMsg);
		param.add(DateUtil.format(nowStr,"yyyy-MM-dd HH:mm:ss"));
		param.add(orderId);
		drawDao.executeSql(sql, param);
	}

	@Override
	public boolean drawReverify(DrawVO draw) {
		String sql = "update account_draw t set t.memo=?,t.accept_time=?,t.people_accepted=?,t.status=?,t.real_fee=? where t.order_id=?";
		List<Object> argsList = new ArrayList<Object>();
		argsList.add(draw.getMemo());
		argsList.add(DateUtil.format(draw.getAcceptTime(),"yyyy-MM-dd HH:mm:ss"));
		argsList.add(draw.getPeopleAccepted());
		argsList.add(draw.getStatus());
		argsList.add(draw.getRealFee());
		argsList.add(draw.getOrderId());
		int result = drawDao.executeSql(sql, argsList);
		if(result > 0){
			return true;
		}
		return false;
	}

	@Override
	public void updateDrawBankInfo(AlipayOrderItem item) {
		
	}

	@Override
	public DrawBindBank findDrawBankByUsercode(String userCode) {
		String hql = "from DrawBindBank b where b.userCode = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(userCode);
		List<DrawBindBank> bankList = bankDao.find(hql, params);
		if(bankList != null && bankList.size() > 0){
			return bankList.get(0);
		}
		return null;
	}

	@Override
	public void saveBankInfo(DrawBindBank bank) {
		bankDao.save(bank);
	}

	@Override
	public boolean isVip(String orderId) {
		String sql = "select u.vip as vip from user_member u ,account_draw d where d.order_id=? and u.user_code=d.user_code ";
		List<Object> param = new ArrayList<Object>();
		param.add(orderId);
		List<Map> resList = drawDao.findBySql(sql, param);
		if(resList != null && resList.size() > 0){
			Map first = resList.get(0);
			if(CommonUtil.isNotEmpty(first.get("VIP"))){
				int vipRank = CommonUtil.formatDouble(first.get("VIP")).intValue();
				if(vipRank > 0){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void updateBankInfo(DrawBindBank bank) {
		bankDao.update(bank);
		
	}

	@Override
	public void updateDrawList(List<Draw> drawList, DrawVO drawVO) {
		for(Draw draw : drawList){
			draw.setMemo(drawVO.getMemo());
			draw.setStatus(drawVO.getStatus());
			draw.setAcceptTime(DateUtil.format(drawVO.getAcceptTime(),"yyyy-MM-dd HH:mm:ss"));
			draw.setPeopleAccepted(drawVO.getPeopleAccepted());
			drawBeanDao.update(draw);
		}
		
	}
	@Override
	public void updateDraw(Draw draw, DrawVO drawVO) {
		draw.setRealFee(drawVO.getRealFee());
		draw.setMemo(drawVO.getMemo());
		draw.setStatus(drawVO.getStatus());
		draw.setAcceptTime(DateUtil.format(drawVO.getAcceptTime(),"yyyy-MM-dd HH:mm:ss"));
		draw.setPeopleAccepted(drawVO.getPeopleAccepted());
		drawBeanDao.update(draw);
	}

	@Override
	public int getVipRank(String userCode) {
		String hql = "from Member m where m.userCode=?";
		List<Object> param = new ArrayList<Object>();
		param.add(userCode);
		List<Member> memberList = memberDao.find(hql, param);
		if(memberList != null && memberList.size() > 0){
			return memberList.get(0).getVip();
		}
		return 0;
	}

	@Override
	public String getUserName(String userCode) {
		String hql = "from Member m where m.userCode=?";
		List<Object> param = new ArrayList<Object>();
		param.add(userCode);
		List<Member> memberList = memberDao.find(hql, param);
		if(memberList != null && memberList.size() > 0){
			return memberList.get(0).getUserName();
		}
		return "";
	}

	@Override
	public Member findMemberByUserCode(String userCode) {
		String hql = "from Member m where m.userCode=?";
		List<Object> param = new ArrayList<Object>();
		param.add(userCode);
		List<Member> memberList = memberDao.find(hql, param);
		if(memberList != null && memberList.size() > 0){
			return memberList.get(0);
		}
		return null;
	}

	@Override
	public List<Map> getSubbankNameList(String province, String city,
			String bankname, String subbankName) {
		StringBuilder sb = new StringBuilder("select subbank_name as subbankName from info_subbank_all t");
		sb.append(" where 1=1 ");
		List<Object> values = new ArrayList<Object>();
		if (CommonUtil.isNotEmpty(province)) {
			sb.append(" and t.province like ? ");
            values.add(province + "%");
        }
		if (CommonUtil.isNotEmpty(city)) {
			sb.append(" and t.city like ? ");
            values.add(city + "%");
        }
		if (CommonUtil.isNotEmpty(bankname)) {
			sb.append(" and t.bank_name like ? ");
            values.add("%" + bankname + "%");
        }
		if (CommonUtil.isNotEmpty(subbankName)) {
			sb.append(" and t.subbank_name like ? ");
            values.add("%" + subbankName + "%");
        }
		List<Map> resList = drawDao.findBySql(sb.toString(), values);
		return resList;
	}
	
	
}

package com.cm.manage.dao.system.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.system.IDictDao;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.system.DictDetail;
import com.cm.manage.vo.system.DictType;
@Repository("dictDao")
public class DictDaoImpl implements IDictDao {
	@Autowired
	private IBaseDao<Object> baseDao;
	@Override
	public List<EasyuiTreeNode> getDictType(String model) {
		List<EasyuiTreeNode> tree = new ArrayList<EasyuiTreeNode>();
		String tableName="";
		if(CommonUtil.isNotEmpty(model)){
			//0：账号;1： 订单; 2： 用户
			if(model.equals("0")){
				tableName="ACCOUNT_DICT_TYPE";
			}
			if(model.equals("1")){
				tableName="TMS_DICT_TYPE";
			}
			if(model.equals("2")){
				tableName="USER_DICT_TYPE";
			}
			String sql="select ID,CODE,NAME,STATUS,CREATE_TIME,UPDATE_TIME from "+tableName;
			List<Map> dictList=baseDao.findBySql(sql);
			if (dictList != null && dictList.size() > 0) {
			for (Map map : dictList) {
				EasyuiTreeNode node = new EasyuiTreeNode();
				BigDecimal id = (BigDecimal) map.get("ID");
				node.setId(id.toString());
				node.setIconCls("ext-icon-bullet_wrench");
				node.setText((String)map.get("NAME"));
				tree.add(node);
			}
			}
		}
		return tree;
	}
	@Override
	public EasyuiDataGridJson getDictDetail(EasyuiDataGrid dg,
			String model, Long type_id) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		String tableName="";
		if(CommonUtil.isNotEmpty(model)){
			//0：账号;1： 订单; 2： 用户
			if(model.equals("0")){
				tableName="ACCOUNT_DICT_DETAIL";
			}
			if(model.equals("1")){
				tableName="TMS_DICT_DETAIL";
			}
			if(model.equals("2")){
				tableName="USER_DICT_DETAIL";
			}
			String sql="select ID,TYPE_ID,CODE,NAME,STATUS,CREATE_TIME,UPDATE_TIME from "+tableName+" where 1=1 ";
			List<Object> values = new ArrayList<Object>();
			if(CommonUtil.isNotEmpty(type_id)){
				sql+=" and TYPE_ID= ?";
				values.add(type_id);
			}
			String totalHql = " select count(*)  from (" + sql + ")";
	        j.setTotal(baseDao.countBySql(totalHql,values).longValue());// 设置总记录数
	        if (dg.getSort() != null) {// 设置排序
	            String sort = "";
	           
	            if (dg.getSort().toString().equalsIgnoreCase("createTime")) {
	                sort = " CREATE_TIME";
	            }
	            if (dg.getSort().toString().equalsIgnoreCase("updateTime")) {
	                sort = " UPDATE_TIME";
	            }
	            if (!sort.equals("")) {
	                sql += " order by " + sort + " " + dg.getOrder();
	            }
	        }
	        List<Map> dictList = baseDao.findBySql(sql,values, dg.getPage(), dg.getRows());// 查询分页
	        List<DictDetail> details = new ArrayList<DictDetail>();
	        if (dictList != null && dictList.size() > 0) {
			for (Map map : dictList) {
				DictDetail vo=new DictDetail();
				BigDecimal id = (BigDecimal) map.get("ID");
				vo.setId(id.longValue());
				vo.setCode((String)map.get("CODE"));
				Date createTime = (Date) map.get("CREATE_TIME");
	            vo.setCreateTime(DateUtil.format(createTime, "yyyy-MM-dd HH:mm:ss"));
	            Date updateTime = (Date) map.get("UPDATE_TIME");
	            vo.setUpdateTime(DateUtil.format(updateTime, "yyyy-MM-dd HH:mm:ss"));
	            vo.setName((String)map.get("NAME"));
	            Object typeId =  map.get("TYPE_ID");
	            if(typeId instanceof BigDecimal){
	            	vo.setTypeId(((BigDecimal) typeId).longValue());
	            }
	            if(typeId instanceof String){
	            	vo.setTypeId(Long.parseLong((String)typeId));
	            }
	            details.add(vo);
			}
	        }
	        j.setRows(details);
		}
		return j;
	}
	
	@Override
	public DictType typeAdd(DictType dict) {
		String tableName="";
		String model=dict.getModel();
		String seqId="";
		if(CommonUtil.isNotEmpty(model)){
			//0：账号;1： 订单; 2： 用户
			if(model.equals("0")){
				tableName="ACCOUNT_DICT_TYPE";
				seqId="SEQ_ACCOUNT_DICT_TYPE.NEXTVAL";
			}
			if(model.equals("1")){
				tableName="TMS_DICT_TYPE";
				seqId="SEQ_TMS_DICT_TYPE.NEXTVAL";
			}
			if(model.equals("2")){
				tableName="USER_DICT_TYPE";
				seqId="SEQ_USER_DICT_TYPE.NEXTVAL";
			}
			 Long id=dict.getId();
			 List<Object> values = new ArrayList<Object>();
			 values.add(dict.getCode());
			 values.add(dict.getName());
			 if(id==null){
			 String sql=" insert into "+tableName+"(ID,CODE,NAME,STATUS,CREATE_TIME,UPDATE_TIME) values ("+seqId+",?,?,0,sysdate,sysdate)";
             baseDao.executeSql(sql,values);
			 }else{
				 String sql="update "+tableName+" set CODE= ? ,NAME=?,UPDATE_TIME=sysdate where ID="+id;
				 baseDao.executeSql(sql,values);
			 }
		}
		return dict;
	}
	/**
	 * 字典类型信息
	 * @param model
	 * @param id
	 * @return
	 */
	public DictType typeInfo(String model,Long id){
		String tableName="";
		DictType t=new DictType();
		if(CommonUtil.isNotEmpty(model)){
			//0：账号;1： 订单; 2： 用户
			if(model.equals("0")){
				tableName="ACCOUNT_DICT_TYPE";
			}
			if(model.equals("1")){
				tableName="TMS_DICT_TYPE";
			}
			if(model.equals("2")){
				tableName="USER_DICT_TYPE";
			}
			String sql="select ID,CODE,NAME,STATUS,CREATE_TIME,UPDATE_TIME from "+tableName+" where ID="+id;
			List<Map> dictList=baseDao.findBySql(sql);
			if (dictList != null && dictList.size() > 0) {
			     Map map=dictList.get(0);
				 t.setId(id.longValue());
				 t.setCode((String)map.get("CODE"));
				 t.setName((String)map.get("NAME"));
				 Date createTime = (Date) map.get("CREATE_TIME");
	             t.setCreateTime(DateUtil.format(createTime, "yyyy-MM-dd HH:mm:ss"));
	             Date updateTime = (Date) map.get("UPDATE_TIME");
	             t.setUpdateTime(DateUtil.format(updateTime, "yyyy-MM-dd HH:mm:ss"));
			}
		}
		return t;
	}
	/**
	 * 字典类型删除
	 * @param id
	 */
	public void typeDel(String model,Long id){
		String tableName="";
		String detailTable="";
		if(CommonUtil.isNotEmpty(model)){
			//0：账号;1： 订单; 2： 用户
			if(model.equals("0")){
				tableName="ACCOUNT_DICT_TYPE";
				detailTable="ACCOUNT_DICT_DETAIL";
			}
			if(model.equals("1")){
				tableName="TMS_DICT_TYPE";
				detailTable="TMS_DICT_DETAIL";
			}
			if(model.equals("2")){
				tableName="USER_DICT_TYPE";
				detailTable="USER_DICT_DETAIL";
			}
			String sql=" delete from "+tableName+" where ID="+id;
			baseDao.executeSql(sql);
			sql=" delete from "+detailTable+" where TYPE_ID="+id;
			baseDao.executeSql(sql);
		}
	}
	
	@Override
	public DictDetail detailAdd(DictDetail dict) {
		String tableName="";
		String model=dict.getModel();
		String seqId="";
		if(CommonUtil.isNotEmpty(model)){
			//0：账号;1： 订单; 2： 用户
			if(model.equals("0")){
				tableName="ACCOUNT_DICT_DETAIL";
				seqId="SEQ_ACCOUNT_DICT_DETAIL.NEXTVAL";
			}
			if(model.equals("1")){
				tableName="TMS_DICT_DETAIL";
				seqId="SEQ_TMS_DICT_DETAIL.NEXTVAL";
			}
			if(model.equals("2")){
				tableName="USER_DICT_DETAIL";
				seqId="SEQ_USER_DICT_DETAIL.NEXTVAL";
			}
			Long id=dict.getId();
			List<Object> values = new ArrayList<Object>();
			values.add(dict.getTypeId());
			values.add(dict.getCode());
			values.add(dict.getName());
			if(id==null){
				String sql=" insert into "+tableName+"(ID,TYPE_ID,CODE,NAME,STATUS,CREATE_TIME,UPDATE_TIME) values ("+seqId+",?,?,?,0,sysdate,sysdate)";
				baseDao.executeSql(sql,values);
				
			}else{
				 String sql="update "+tableName+" set TYPE_ID=?,CODE= ? ,NAME=?,UPDATE_TIME=sysdate where ID="+id;
				 baseDao.executeSql(sql,values);
			 }
		}
		return dict;
	}
	public void detailDel(String model,String ids){
		String tableName="";
		if(CommonUtil.isNotEmpty(model)){
			//0：账号;1： 订单; 2： 用户
			if(model.equals("0")){
				tableName="ACCOUNT_DICT_DETAIL";
			}
			if(model.equals("1")){
				tableName="TMS_DICT_DETAIL";
			}
			if(model.equals("2")){
				tableName="USER_DICT_DETAIL";
			}
			for (String id : ids.split(",")) {
				String sql=" delete from "+tableName+" where id="+id;
				baseDao.executeSql(sql);
			}
		}
	}

    @Override
    public List getDictDetailList(Long typeId, String model) {
        String tableName = "";
        if (CommonUtil.isNotEmpty(model)) {
            //0：账号;1： 订单; 2： 用户
            if (model.equals("0")) {
                tableName = "ACCOUNT_DICT_DETAIL";
            }
            if (model.equals("1")) {
                tableName = "TMS_DICT_DETAIL";
            }
            if (model.equals("2")) {
                tableName = "USER_DICT_DETAIL";
            }
        }
        String sql = "select * from " + tableName + " where type_id = ?";
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(typeId);
        return baseDao.findBySql(sql, paramList);
    }
}

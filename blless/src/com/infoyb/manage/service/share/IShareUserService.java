package com.cm.manage.service.share;

import java.util.List;
import java.util.Map;

import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.share.JoinUserRecordVO;
import com.cm.manage.vo.share.ShareUserInfoVO;
import com.cm.manage.vo.share.ShareUserRecordExtraVO;

public interface IShareUserService extends IBaseService {
	/**
	 * 分享奖励
	 * @param dg
	 * @param shareUser
	 * @return
	 */
	public EasyuiDataGridJson shareUserList(EasyuiDataGrid dg,ShareUserInfoVO shareUser) ;
	
	/**
	 * 参与人奖励汇总
	 * @param shareUser
	 * @return
	 */
	public Map shareUserCount(ShareUserInfoVO shareUser);
	
	/**
	 * 分享用户基本奖励
	 * @param dg
	 * @param shareUserId
	 * @return
	 */
	public EasyuiDataGridJson shareUserRecordBase(EasyuiDataGrid dg,String shareUserId) ;
	
	/**
	 * 分享用户额外奖励
	 * @param dg
	 * @param shareUserId
	 * @return
	 */
	public List<ShareUserRecordExtraVO> shareUserRecordExtra(String shareUserId) ;
	
	/**
	 * 参与人奖励
	 * @param dg
	 * @param joinUser
	 * @return
	 */
	public EasyuiDataGridJson joinUserList(EasyuiDataGrid dg,JoinUserRecordVO joinUser) ;
}

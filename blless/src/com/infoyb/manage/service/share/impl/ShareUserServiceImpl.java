package com.cm.manage.service.share.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.share.IShareUserDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.share.IShareUserService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.share.JoinUserRecordVO;
import com.cm.manage.vo.share.ShareUserInfoVO;
import com.cm.manage.vo.share.ShareUserRecordExtraVO;
@Service("shareUserService")
public class ShareUserServiceImpl extends BaseServiceImpl implements
		IShareUserService {

	@Autowired
	private IShareUserDao shareUserDao;
	@Override
	public EasyuiDataGridJson shareUserList(EasyuiDataGrid dg,
			ShareUserInfoVO shareUser) {
		return shareUserDao.shareUserList(dg, shareUser);
	}

	/**
	 * 参与人奖励汇总
	 * @param shareUser
	 * @return
	 */
	public Map shareUserCount(ShareUserInfoVO shareUser){
		return shareUserDao.shareUserCount(shareUser);
	}
	/**
	 * 分享用户基本奖励
	 * @param dg
	 * @param shareUserId
	 * @return
	 */
	public EasyuiDataGridJson shareUserRecordBase(EasyuiDataGrid dg,String shareUserId) {
		return shareUserDao.shareUserRecordBase(dg, shareUserId);
	}
	/**
	 * 分享用户额外奖励
	 * @param dg
	 * @param shareUserId
	 * @return
	 */
	public List<ShareUserRecordExtraVO> shareUserRecordExtra(String shareUserId) {
		return shareUserDao.shareUserRecordExtra(shareUserId);
	}
	/**
	 * 参与人奖励
	 * @param dg
	 * @param joinUser
	 * @return
	 */
	public EasyuiDataGridJson joinUserList(EasyuiDataGrid dg,JoinUserRecordVO joinUser) {
		return shareUserDao.joinUserList(dg, joinUser);
	}
}

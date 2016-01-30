package com.cm.manage.service.share.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.share.IShareDao;
import com.cm.manage.model.share.JoinReward;
import com.cm.manage.model.share.ShareModule;
import com.cm.manage.model.share.ShareReward;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.share.IShareService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.share.ShareInfoVO;
@Service("shareService")
public class ShareServiceImpl extends BaseServiceImpl implements IShareService {

	@Autowired
	private IShareDao shareDao;
	@Override
	public EasyuiDataGridJson shareList(EasyuiDataGrid dg, ShareInfoVO shareVo) {
		return shareDao.shareList(dg, shareVo);
	}
	/**
	 * 分享活动保存
	 * @param share
	 * @param options
	 */
	public void saveActivity(ShareInfoVO share,String options){
		shareDao.saveActivity(share, options);
	}
	/**
	 * 分享活动详情
	 * @param shareId
	 * @return
	 */
	public ShareInfoVO shareInfo(String shareId){
		return shareDao.shareInfo(shareId);
	}
	/**
	 * 分享人奖励
	 * @param shareId
	 * @param baseType
	 * @return
	 */
	public List<ShareReward> shareRewardInfo(String shareId,Integer baseType){
		return shareDao.shareRewardInfo(shareId, baseType);
	}
	/**
	 * 参与人奖励
	 * @param shareId
	 * @return
	 */
	public JoinReward joinRewardInfo(String shareId){
		return shareDao.joinRewardInfo(shareId);
	}
	/**
	 * 更改分享活动状态
	 * @param shareId
	 * @param status
	 * @return
	 */
	public boolean updateActivityStatus(String shareId,Integer status){
		return shareDao.updateActivityStatus(shareId, status);
	}
	
	/**
	 * 分享模板列表
	 * @return
	 */
	public List<ShareModule> shareModuleList(){
		return shareDao.shareModuleList();
	}
	/**
	 * 分享模块分享设置
	 * @param shareType
	 * @param shareName
	 * @param status
	 * @return
	 */
	public boolean moduleShare(String shareType,String shareName,Integer status){
		return shareDao.moduleShare(shareType, shareName, status);
	}
	
	/**
	 * 额外奖励删除
	 * @param shareRewardId
	 * @return
	 */
	public boolean deleteExtraReward(String shareRewardId){
		return shareDao.deleteExtraReward(shareRewardId);
	}
	/**
	 * 分享活动删除
	 * @param shareId
	 * @return
	 */
	public boolean deleteShareActivity(String shareId){
		return shareDao.deleteShareActivity(shareId);
	}
}

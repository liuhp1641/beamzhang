package com.cm.manage.dao.share;

import java.util.List;

import com.cm.manage.model.share.JoinReward;
import com.cm.manage.model.share.ShareModule;
import com.cm.manage.model.share.ShareReward;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.share.ShareInfoVO;

public interface IShareDao {
	/**
	 * 分享活动列表
	 * @param dg
	 * @param shareVo
	 * @return
	 */
	public EasyuiDataGridJson shareList(EasyuiDataGrid dg,ShareInfoVO shareVo) ;
	/**
	 * 分享活动保存
	 * @param share
	 * @param options
	 */
	public void saveActivity(ShareInfoVO share,String options);
	/**
	 * 分享活动详情
	 * @param shareId
	 * @return
	 */
	public ShareInfoVO shareInfo(String shareId);
	
	/**
	 * 分享人奖励
	 * @param shareId
	 * @param baseType
	 * @return
	 */
	public List<ShareReward> shareRewardInfo(String shareId,Integer baseType);
	/**
	 * 参与人奖励
	 * @param shareId
	 * @return
	 */
	public JoinReward joinRewardInfo(String shareId);
	/**
	 * 更改分享活动状态
	 * @param shareId
	 * @param status
	 * @return
	 */
	public boolean updateActivityStatus(String shareId,Integer status);
	
	/**
	 * 分享模板列表
	 * @return
	 */
	public List<ShareModule> shareModuleList();
	/**
	 * 分享模块分享设置
	 * @param shareType
	 * @param shareName
	 * @param status
	 * @return
	 */
	public boolean moduleShare(String shareType,String shareName,Integer status);
	/**
	 * 额外奖励删除
	 * @param shareRewardId
	 * @return
	 */
	public boolean deleteExtraReward(String shareRewardId);
	/**
	 * 分享活动删除
	 * @param shareId
	 * @return
	 */
	public boolean deleteShareActivity(String shareId);
	
}

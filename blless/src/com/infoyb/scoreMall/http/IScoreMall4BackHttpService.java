package com.cm.scoreMall.http;

import java.util.List;

import com.cm.scoreMall.http.bean.CCNVo4Back;
import com.cm.scoreMall.http.bean.CommodityAndPro4Back;
import com.cm.scoreMall.http.bean.CommodityLogMsg4Back;
import com.cm.scoreMall.http.bean.CommodityLogVo4Back;
import com.cm.scoreMall.http.bean.CommodityMsg4Back;
import com.cm.scoreMall.http.bean.CommodityVo4Back;

public interface IScoreMall4BackHttpService {

	/**
	 * 保存商品和概率
	 * @param commodityAndPro4Back 商品和概率封装对象
	 * @return
	 */
	public boolean saveCommodityAndPro(CommodityAndPro4Back commodityAndPro4Back);
	/**
	 * 修改商品和概率
	 * @param commodityAndPro4Back 商品和概率封装对象
	 * @return
	 */
	public boolean updateCommodityAndPro(CommodityAndPro4Back commodityAndPro4Back);
	/**
	 * 根据分页查询商品信息
	 * @param pageId 页标
	 * @param pageSize 每页大小
	 * @return
	 */
	public CommodityMsg4Back queryCommodityMsg4BackList(int pageId,int pageSize,CommodityVo4Back commodityVo4Back);
	/**
	 * 根据分页和查询条件 查询商品log信息
	 * @param pageId 页标
	 * @param pageSize 每页大小
	 * @param commodityLogVo4Back 查询条件
	 * @return
	 */
	public CommodityLogMsg4Back queryCommodityLogMsg4BackList(int pageId,int pageSize,CommodityLogVo4Back commodityLogVo4Back);
	/**
	 * 根据商品code  查询商品详情和概率
	 * @param commodityCode
	 * @return
	 */
	public CommodityAndPro4Back queryCommoditVo4Back(String commodityCode);
	/**
	 * 修改商品-----目前所涉的是修改商品上下线
	 * @param commodityVo4Back
	 * @return
	 */
	public String updateCommodity(CommodityVo4Back commodityVo4Back);
	
	/**
	 * 查询商品code 和name 列表
	 * @return
	 */
	public List<CCNVo4Back> queryCCNVo4BackList();
	/**
	 * 检查商品name 是否存在
	 * @param commodityName
	 * @return
	 */
	public boolean queryCommodityNameExist(String commodityName);
	
	/**
	 * 批量修改权重
	 * @param commodityVo4BackList
	 * @return
	 */
	public boolean updateCommodityWeight(List<CommodityVo4Back> commodityVo4BackList);
	
	public long queryOnLineCommodityNum();
}

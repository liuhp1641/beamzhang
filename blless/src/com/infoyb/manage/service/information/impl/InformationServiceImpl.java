package com.cm.manage.service.information.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.information.IInformationDao;
import com.cm.manage.dao.operate.IScoreQutzDao;
import com.cm.manage.dao.order.ILotteryControlDao;
import com.cm.manage.model.information.Cooperation4Info;
import com.cm.manage.model.information.ThemeCooperation;
import com.cm.manage.model.information.Url;
import com.cm.manage.model.operate.SoftwareVersion;
import com.cm.manage.model.order.LotteryControl;
import com.cm.manage.model.score.ScoreQutz;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.information.IInformationService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.information.HtmlContentVo;
import com.cm.manage.vo.information.AppParaVo;
import com.cm.manage.vo.information.ThemeSum;
import com.cm.manage.vo.information.ThemeVo;

@Service
public class InformationServiceImpl extends BaseServiceImpl implements IInformationService {
	@Autowired
	private IInformationDao informationDao;
	@Autowired
	private ILotteryControlDao lotteryControlDao;
	@Autowired
	private IScoreQutzDao scoreQutzDao;
	@Override
	public EasyuiDataGridJson selectThemeList(EasyuiDataGrid dg, ThemeVo themeVo) {
		return informationDao.selectThemeList(dg,themeVo);
	}

	@Override
	public List<Cooperation4Info> selectCooperation4InfoList(String platform) {
		return informationDao.selectCooperation4InfoList(platform);
	}

	@Override
	public ThemeSum getNum(ThemeVo themeVo) {
		return informationDao.getNum(themeVo);
	}

	@Override
	public ThemeVo selectThemeByThemeCode(String themeCode) {
		return informationDao.selectThemeByThemeCode(themeCode);
	}

	@Override
	public HtmlContentVo selectHtmlContentByHtmlCode(String htmlCode) {
		return informationDao.selectHtmlContentByHtmlCode(htmlCode);
	}

	@Override
	public String editTheme(HttpServletRequest request, ThemeVo themeVo,
			HtmlContentVo contentVo,int flag)throws Exception {
		return informationDao.editTheme(request,themeVo,contentVo,flag);
	}

	@Override
	public void updateState(ThemeVo themeVo) {
		informationDao.updateState(themeVo);
		
	}

	@Override
	public void updatehidden(ThemeVo themeVo) {
		informationDao.updatehidden(themeVo);		
	}

	@Override
	public void updateWeight(ThemeVo themeVo) {
		informationDao.updateWeight(themeVo);	
	}

	@Override
	public List<ThemeCooperation> selectThemeCooperationByThemeCode(
			String theme_code) {
		return informationDao.selectThemeCooperationByThemeCode(theme_code);
	}

	@Override
	public List<HtmlContentVo> selectHtmlContent10ByType(int type) {
		return informationDao.selectHtmlContent10ByType(type);
	}

	@Override
	public EasyuiDataGridJson selectHtmlContentList(EasyuiDataGrid dg,
			HtmlContentVo htmlContentVo) {
		return informationDao.selectHtmlContentList(dg,htmlContentVo);
	}

	@Override
	public ThemeSum getNumHtml(HtmlContentVo htmlContentVo) {
		return informationDao.getNumHtml(htmlContentVo);
	}

	@Override
	public List<Url> selectUrlByType(String type) {
		return informationDao.selectUrlByType(type);
	}

	@Override
	public Url addUrl(Url url) {
		return informationDao.addUrl(url);
	}

	@Override
	public void delUrl(Url url) {
		informationDao.delUrl(url);
		
	}

	@Override
	public void editUrl(Url url) {
		informationDao.editUrl(url);
		
	}

	@Override
	public List<ThemeVo> selectThemeVoByParent(String themeCode) {
		return informationDao.selectThemeVoByParent(themeCode);
	}

	@Override
	public void updateActivityState(ThemeVo themeVo) {
		informationDao.updateActivityState(themeVo);
	}

	@Override
	public String getTowPic(String htmlCode) {
		return informationDao.getTowPic(htmlCode);
	}

	@Override
	public void deleteTheme(ThemeVo themeVo) {
		informationDao.deleteTheme(themeVo);
		
	}

	@Override
	public void deleteHtmlContent(HtmlContentVo htmlContentVo) {
		informationDao.deleteHtmlContent(htmlContentVo);
		
	}

	@Override
	public List<SoftwareVersion> selectSV4recommendList(String platform) {
		return informationDao.selectSV4recommendList(platform);
	}

	@Override
	public List<AppParaVo> selectAppParaByCode(String appCode) {
		List<AppParaVo> linkParaVoList = new ArrayList<AppParaVo>();
		if(appCode!=null&&!appCode.equals("")){
			if(appCode.equals("001")){
				List<LotteryControl> list=	lotteryControlDao.getLoteryControlList();
				if(list!=null){
					for (LotteryControl lotteryControl : list) {
						AppParaVo lpv = new AppParaVo();
						lpv.setAppParaCode(lotteryControl.getLotteryCode());
						lpv.setAppParaName(lotteryControl.getLotteryName());
						linkParaVoList.add(lpv);
					}
				}
			}else if(appCode.equals("003")){
				List<ScoreQutz> list=scoreQutzDao.selectAllScoreQutz();
				if(list!=null){
					for (ScoreQutz scoreQutz : list) {
						AppParaVo lpv = new AppParaVo();
						lpv.setAppParaCode(scoreQutz.getQutzId());
						lpv.setAppParaName(scoreQutz.getQutzName());
						linkParaVoList.add(lpv);
					}
				}
			}
		}
		return linkParaVoList;
	}

}

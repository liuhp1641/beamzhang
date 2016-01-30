package com.cm.manage.service.information;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cm.manage.model.information.Cooperation4Info;
import com.cm.manage.model.information.ThemeCooperation;
import com.cm.manage.model.information.Url;
import com.cm.manage.model.operate.SoftwareVersion;
import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.information.HtmlContentVo;
import com.cm.manage.vo.information.AppParaVo;
import com.cm.manage.vo.information.ThemeSum;
import com.cm.manage.vo.information.ThemeVo;

public interface IInformationService  extends IBaseService{

	EasyuiDataGridJson selectThemeList(EasyuiDataGrid dg, ThemeVo themeVo);
	
	List<Cooperation4Info>  selectCooperation4InfoList(String platform);

	ThemeSum getNum(ThemeVo themeVo);

	ThemeVo selectThemeByThemeCode(String themeCode);

	HtmlContentVo selectHtmlContentByHtmlCode(String htmlCode);

	String editTheme(HttpServletRequest request, ThemeVo themeVo,
			HtmlContentVo contentVo,int flag)throws Exception;

	void updateState(ThemeVo themeVo);

	void updatehidden(ThemeVo themeVo);

	void updateWeight(ThemeVo themeVo);

	List<ThemeCooperation> selectThemeCooperationByThemeCode(String theme_code);

	List<HtmlContentVo> selectHtmlContent10ByType(int type);

	EasyuiDataGridJson selectHtmlContentList(EasyuiDataGrid dg,
			HtmlContentVo htmlContentVo);

	ThemeSum getNumHtml(HtmlContentVo htmlContentVo);

	List<Url> selectUrlByType(String type);

	Url addUrl(Url url);

	void delUrl(Url url);

	void editUrl(Url url);

	List<ThemeVo> selectThemeVoByParent(String themeCode);

	void updateActivityState(ThemeVo themeVo);

	String getTowPic(String htmlCode);

	void deleteTheme(ThemeVo themeVo);

	void deleteHtmlContent(HtmlContentVo htmlContentVo);

	List<SoftwareVersion> selectSV4recommendList(String platform);

	List<AppParaVo> selectAppParaByCode(String appCode);
}

package com.cm.manage.dao.information.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.information.IInformationDao;
import com.cm.manage.model.information.Cooperation4Info;
import com.cm.manage.model.information.HtmlContent;
import com.cm.manage.model.information.Theme;
import com.cm.manage.model.information.ThemeCooperation;
import com.cm.manage.model.information.ThemeSV;
import com.cm.manage.model.information.ThemeUserRead;
import com.cm.manage.model.information.Url;
import com.cm.manage.model.operate.SoftwareVersion;
import com.cm.manage.util.ConfigUtils;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.util.base.FileUtil;
import com.cm.manage.util.base.ResourceUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.information.HtmlContentVo;
import com.cm.manage.vo.information.ThemeSum;
import com.cm.manage.vo.information.ThemeVo;
import com.cm.manage.vo.system.SessionInfo;
import com.cm.manage.vo.system.User;

@Repository
public class InformationDaoImpl implements IInformationDao {
	@Autowired
	private IBaseDao<Theme> themeDao;

	@Autowired
	private IBaseDao<HtmlContent> htmlContentDao;
	@Autowired
	private IBaseDao<Cooperation4Info> cooperation4InfoDao;
	@Autowired
	private IBaseDao<Url> urlDao;

	@Autowired
	private IBaseDao<ThemeUserRead> themeUserReadDao;

	@Autowired
	private IBaseDao<ThemeCooperation> themeCooperationDao;
	
	@Autowired
	private IBaseDao<SoftwareVersion> softwareVersionDao;
	
	@Autowired
	private IBaseDao<ThemeSV> themeSVDao;

	private final static String head1 = "<!DOCTYPE html><html><head><title>";
	private final static String head2 = "</title><meta name=\"content-type\" content=\"text/html; charset=UTF-8\"> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"></head><body>";
	private final static String foot = " </body></html>";

	@Override
	public List<Cooperation4Info> selectCooperation4InfoList(String platform) {
		if(platform!=null&&!platform.equals("00")){
			return cooperation4InfoDao.find("from Cooperation4Info where platform= ?",platform);
		}else{
			return cooperation4InfoDao.find("from Cooperation4Info");
		}
	}

	@Override
	public List<SoftwareVersion> selectSV4recommendList(String platform) {
		if(platform!=null&&!platform.equals("00")){
			return softwareVersionDao.find("from SoftwareVersion where svType= ?",platform);
		}else{
			return softwareVersionDao.find("from SoftwareVersion");
		}
	}
	
	@Override
	public EasyuiDataGridJson selectThemeList(EasyuiDataGrid dg, ThemeVo themeVo) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder(
				" from INFO_THEME q join sys_user u on q.USER_ID= u.ID  and q.PARENT_CODE is null");
		StringBuilder sb1 = new StringBuilder(
				"select q.tid,  q.theme_code,q.user_id,q.createdate,q.publishdate,q.onlinedate,q.offlinedate,q.theme_title,q.describe,q.state,q.picture_url,q.program_type, q.lottery_code,q.hidden_flag,q.PARENT_CODE,q.STYLE,q.IS_RECOMMEND,q.IS_ALL_COOPERATION,q.ACTIVITYSTATE,q.HTML_CODE,q.WEIGHT,q.HEADSTYLE ");
		List<Object> values = new ArrayList<Object>();
		if (themeVo != null) {// 添加查询条件
			if (themeVo.getProgramType() != 0) {
				sb.append(" and q.PROGRAM_TYPE = ? ");
				values.add(themeVo.getProgramType());
			}
			if (themeVo.getProgramType() == 4) {
				sb1.append(",l.LOTTERY_NAME ");
				sb.append(" join TMS_LOTTERY_CONTROL l on q.LOTTERY_CODE= l.LOTTERY_CODE ");
			}
			// 栏目标题
			if (CommonUtil.isNotEmpty(themeVo.getThemeTitle())) {
				sb.append(" and q.theme_title like ? ");
				values.add("%" + themeVo.getThemeTitle() + "%");
			}
			// 发布状态
			if (themeVo.getState() != 3) {
				sb.append(" and q.state = ? ");
				values.add(themeVo.getState());

			}

			// 发布状态
			if (themeVo.getActivityState() != 3) {
				sb.append(" and q.ACTIVITYSTATE = ? ");
				values.add(themeVo.getActivityState());

			}

			// 创建时间
			if (CommonUtil.isNotEmpty(themeVo.getCreateDateStart())) {
				sb.append(" and q.createdate >= ? ");
				values.add(DateUtil.format(themeVo.getCreateDateStart(),
						"yy-MM-dd"));
			}
			// 发布时间
			if (CommonUtil.isNotEmpty(themeVo.getCreateDateEnd())) {
				sb.append(" and q.createdate < ? ");
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtil.format(themeVo.getCreateDateEnd(),
						"yy-MM-dd"));

				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0); // 设置时分秒都为0
				cal.add(Calendar.DAY_OF_YEAR, 1); // 小时加1
				values.add(cal.getTime());
			}
			// 最后回复时间
			if (CommonUtil.isNotEmpty(themeVo.getPublishDateStart())) {
				sb.append(" and q.publishdate >= ? ");
				values.add(DateUtil.format(themeVo.getPublishDateStart(),
						"yy-MM-dd"));
			}
			if (CommonUtil.isNotEmpty(themeVo.getPublishDateEnd())) {
				sb.append(" and q.publishdate < ? ");
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtil.format(themeVo.getPublishDateEnd(),
						"yy-MM-dd"));

				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0); // 设置时分秒都为0
				cal.add(Calendar.DAY_OF_YEAR, 1); // 小时加1
				values.add(cal.getTime());
			}

			// 样式
			if (themeVo.getStyle() != 0) {
				sb.append(" and q.STYLE = ? ");
				values.add(themeVo.getStyle());
			}
			// 渠道
			if (CommonUtil.isNotEmpty(themeVo.getSid())) {

				sb.append(" join info_theme_COOPERATION e on q.theme_code= e.theme_code  and e.COOPERATION_sid =? ");
				values.add(themeVo.getSid());
			}
			// 彩种
			if (CommonUtil.isNotEmpty(themeVo.getLotteryCode())) {

				sb.append("  and q.lottery_code =? ");
				values.add(themeVo.getLotteryCode());
			}
			// 推荐
			if (themeVo.getIsRecommend() != 3) {
				sb.append(" and IS_RECOMMEND =? ");
				values.add(themeVo.getIsRecommend());
			}
		}
		String sb2 = sb1.append(sb).toString();
		String totalHql = " select count(*)  from (" + sb2 + ")";
		j.setTotal(themeDao.countBySql(totalHql, values).longValue());// 设置总记录数

		if (dg.getSort() != null) {// 设置排序
			String sort = "";
			if (dg.getSort().toString().equalsIgnoreCase("createDate")) {
				sort = " q.CREATEDATE ";
			}

			if (dg.getSort().toString().equalsIgnoreCase("publishDate")) {
				sort = " q.PUBLISHDATE ";
			}
			if (dg.getSort().toString().equalsIgnoreCase("state")) {
				sort = " q.STATE ";
			}

			if (dg.getSort().toString().equalsIgnoreCase("weight")) {
				sort = " q.weight ";
			}
			if (!sort.equals("")) {
				sb2 = sb1.append(" order by " + sort + " " + dg.getOrder())
						.toString();
			}
		}

		List<Map> themeMapList = themeDao.findBySql(sb2, values, dg.getPage(),
				dg.getRows());// 查询分页
		List<ThemeVo> themeVoList = new ArrayList<ThemeVo>();
		if (themeMapList != null && themeMapList.size() > 0) {// 转换模型
			for (Map map : themeMapList) {
				ThemeVo vo = new ThemeVo();
				vo.setTheme_code((String) map.get("THEME_CODE"));
				String thTitle = (String) map.get("THEME_TITLE");
				if (thTitle != null && thTitle.length() > 20) {
					thTitle = thTitle.substring(0, 19);
					thTitle += "...";
				}
				vo.setThemeTitle(thTitle);
				vo.setStyle(((BigDecimal) map.get("STYLE")).intValue());
				if (map.get("CREATEDATE") != null)
					vo.setCreateDate(DateUtil.format((Date) map
							.get("CREATEDATE")));
				if (map.get("PUBLISHDATE") != null)
					vo.setPublishDate(DateUtil.format((Date) map
							.get("PUBLISHDATE")));
				if (map.get("ONLINEDATE") != null)
					vo.setOnlineDate(DateUtil.format((Date) map
							.get("ONLINEDATE")));
				if (map.get("OFFLINEDATE") != null)
					vo.setOffLineDate(DateUtil.format((Date) map
							.get("OFFLINEDATE")));
				vo.setState(((BigDecimal) map.get("STATE")).intValue());
				vo.setPictureUrl((String) map.get("PICTURE_URL"));
				String thDescribe = (String) map.get("DESCRIBE");
				if (thDescribe != null && thDescribe.length() > 20) {
					thDescribe = thDescribe.substring(0, 19);
					thDescribe += "...";
				}
				vo.setDescribe(thDescribe);
				vo.setLotteryName((String) map.get("LOTTERY_NAME"));// 彩种
				vo.setIsRecommend(((BigDecimal) map.get("IS_RECOMMEND"))
						.intValue());// 推荐预测
				vo.setHidden_flag(((BigDecimal) map.get("HIDDEN_FLAG"))
						.intValue());
				vo.setActivityState(((BigDecimal) map.get("ACTIVITYSTATE"))
						.intValue());
				vo.setHtmlCode((String) map.get("HTML_CODE"));
				vo.setHeadStyle(((BigDecimal) map.get("HEADSTYLE")).intValue());
				vo.setWeight(((BigDecimal) map.get("WEIGHT")).intValue());
				if (((BigDecimal) map.get("IS_ALL_COOPERATION")).intValue() != 1) {
					List<Object> objlist = new ArrayList<Object>();
					objlist.add((String) map.get("THEME_CODE"));
					StringBuffer cooStr = new StringBuffer();
					List<Map> list = themeCooperationDao
							.findBySql(
									"select q.ID,q.THEME_CODE,q.COOPERATION_SID,q.CREATEDATE ,u.NAME from info_theme_cooperation q join user_cooperation u on q.cooperation_sid =u.sid  and q.theme_code=?",
									objlist);
					for (Map map2 : list) {
						cooStr.append((String) map2.get("NAME") + ",");
					}
					String cooStr1 = cooStr.toString();
					if (!cooStr1.equals("")) {
						cooStr1 = cooStr1.substring(0, cooStr1.length() - 1);
					}
					if (cooStr1.length() > 20) {
						cooStr1 = cooStr1.substring(0, 19);
						cooStr1 += "...";
					}
					vo.setCooperationName(cooStr1);
				} else {
					vo.setCooperationName("全部");
				}
				themeVoList.add(vo);
			}
		}
		j.setRows(themeVoList);// 设置返回的行
		return j;
	}

	@Override
	public ThemeSum getNum(ThemeVo themeVo) {
		String sb = "select q.tid,  q.theme_code,q.user_id,q.createdate,q.publishdate,q.onlinedate,q.offlinedate,q.theme_title,q.describe,q.state,q.picture_url,q.program_type, q.lottery_code,q.hidden_flag,q.PARENT_CODE,q.STYLE,q.IS_RECOMMEND from info_theme q  where q.PARENT_CODE is null";
		List<Object> values = new ArrayList<Object>();
		ThemeSum sum = new ThemeSum();
		if (themeVo != null) {// 添加查询条件
			if (themeVo.getProgramType() != 0) {
				sb += " and q.PROGRAM_TYPE = ? ";
				values.add(themeVo.getProgramType());
			}
			if (themeVo.getProgramType() == 4) {
				if (themeVo.getIsRecommend() != 3) {
					sb += " and q.IS_RECOMMEND = ? ";
					values.add(themeVo.getIsRecommend());
				}
			}
			String allSql = " select count(*)  from (" + sb + ")";
			String publishedSql = " select count(*)  from (" + sb
					+ " and q.state = 1 )";
			String unpublishSql = " select count(*)  from (" + sb
					+ " and q.state = 2 )";
			String wPublishSql = " select count(*)  from (" + sb
					+ " and q.state = 0 )";
			long all = themeDao.countBySql(allSql, values).longValue();
			long published = themeDao.countBySql(publishedSql, values)
					.longValue();
			long unPublish = themeDao.countBySql(unpublishSql, values)
					.longValue();
			long wPublish = themeDao.countBySql(wPublishSql, values)
					.longValue();
			sum.setAll(all);
			sum.setPublished(published);
			sum.setUnPublish(unPublish);
			sum.setwPublish(wPublish);
		}
		return sum;
	}

	@Override
	public ThemeVo selectThemeByThemeCode(String themeCode) {
		Theme theme = (Theme) themeDao.get("from Theme where theme_code =?",
				themeCode);
		ThemeVo themeVo = null;
		if (theme != null) {
			themeVo = new ThemeVo();
			themeVo.setTid(theme.getTid());
			themeVo.setTheme_code(theme.getTheme_code());
			themeVo.setIsTop(theme.getIsTop());
			themeVo.setDescribe(theme.getDescribe());
			themeVo.setHtmlCode(theme.getHtmlCode());
			themeVo.setThemeTitle(theme.getThemeTitle());
			themeVo.setStyle(theme.getStyle());
			themeVo.setIsAllCooperation(theme.getIsAllCooperation());
			if (theme.getOnlineDate() != null)
				themeVo.setOnlineDate(DateUtil.format(theme.getOnlineDate()));
			if (theme.getOffLineDate() != null)
				themeVo.setOffLineDate(DateUtil.format(theme.getOffLineDate()));
			if (theme.getIsAllCooperation() != 1) {
				StringBuffer cooStr = new StringBuffer();
				List<ThemeCooperation> list = themeCooperationDao.find(
						"from ThemeCooperation where themeCode=?",
						theme.getTheme_code());
				for (ThemeCooperation themeCooperation : list) {
					cooStr.append(themeCooperation.getSid() + ",");
				}
				String cooStr1 = cooStr.toString();
				if (!cooStr1.equals("")) {
					cooStr1 = cooStr1.substring(0, cooStr1.length() - 1);
				}
				themeVo.setCooperationName(cooStr1);
			}
			themeVo.setPictureUrl(theme.getPictureUrl());
			themeVo.setWeight(theme.getWeight());
			themeVo.setLotteryCode(theme.getLotteryCode());
			themeVo.setHeadStyle(theme.getHeadStyle());
			themeVo.setIsAllSV(theme.getIsAllSV());
			themeVo.setPlatform(theme.getPlatform());
			if(theme.getIsRecommend()==1){
				if(theme.getIsAllSV()==1){
					StringBuffer svStr = new StringBuffer();
					List<ThemeSV> svs = themeSVDao.find("from ThemeSV where themeCode=?", theme.getTheme_code());
					for (ThemeSV themeSV : svs) {
						svStr.append(themeSV.getSvCode() + ",");
					}
					String svStr1 = svStr.toString();
					if (!svStr1.equals("")) {
						svStr1 = svStr1.substring(0, svStr1.length() - 1);
					}
					themeVo.setSvStr(svStr1);
				}
			}
		}
		return themeVo;
	}

	@Override
	public HtmlContentVo selectHtmlContentByHtmlCode(String htmlCode) {
		HtmlContent htmlContent = (HtmlContent) htmlContentDao.get(
				"from HtmlContent where htmlCode=?", htmlCode);
		HtmlContentVo htmlContentVo = null;
		if (htmlContent != null) {
			htmlContentVo = new HtmlContentVo();
			htmlContentVo.setHtmlTitle(htmlContent.getHtmlTitle());
			htmlContentVo.setHid(htmlContent.getHid());
			htmlContentVo.setHtmlCode(htmlContent.getHtmlCode());
			htmlContentVo.setContent(htmlContent.getContent());
			htmlContentVo.setIsOutLink(htmlContent.getIsOutLink());
			htmlContentVo.setHtmlUrl(htmlContent.getHtmlUrl());
			htmlContentVo.setHtmlState(htmlContent.getHtmlState());
			htmlContentVo.setIsOpenApp(htmlContent.getIsOpenApp());
			htmlContentVo.setIsAddButton(htmlContent.getIsAddButton());
			htmlContentVo.setAppCode(htmlContent.getAppCode());
			htmlContentVo.setAppPara(htmlContent.getAppPara());
			htmlContentVo.setButtonText(htmlContent.getButtonText());
			htmlContentVo.setBusinessType(htmlContent.getBusinessType());
			htmlContentVo.setIsShare(htmlContent.getIsShare());
		}
		return htmlContentVo;
	}

	public String editTheme(HttpServletRequest request, ThemeVo themeVo,
			HtmlContentVo htmlContentVo, int flag) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat dff = new SimpleDateFormat("yyyyMMddHHHmmssS");
		String ymd = df.format(new Date());
		String ymdhms = dff.format(new Date());
		SessionInfo sessionInfo = (SessionInfo) request.getSession()
				.getAttribute(ResourceUtil.getSessionInfoName());
		User user = sessionInfo.getUser();
		int max = 2000;
		int min = 1000;
		Random random = new Random();
		String htmlUrl = null;
		if (htmlContentVo.getIsOutLink() != 1) {

			// 生成html
			String body = htmlContentVo.getContent();
			String titleHtml = themeVo.getThemeTitle();
			if (titleHtml == null) {
				titleHtml = "";
			}
			String html = head1 + titleHtml + head2 + body + foot;

			String htmlPath = ConfigUtils.getValue("htmlPath");

			int programType = themeVo.getProgramType();
			if (themeVo.getProgramType() == 0) {
				programType = htmlContentVo.getBusinessType();
			}
			String programTypeStr = "";
			switch (programType) {
			case 1:
				programTypeStr = "news";
				break;
			case 2:
				programTypeStr = "activity";
				break;
			case 3:
				programTypeStr = "commercials";
				break;
			case 4:
				programTypeStr = "predict";
				break;
			case 5:
				programTypeStr = "announce";
				break;
			default:
				break;
			}
			if (htmlContentVo.getIsOutLink() != 1
					&& CommonUtil.isNotEmpty(htmlContentVo.getHtmlUrl())) {
				File fileOld = new File(htmlPath + htmlContentVo.getHtmlUrl());
				if (fileOld.exists()) {
					fileOld.delete();
				}
			}

			String dir = htmlPath + programTypeStr + "/" + ymd;
			File dirFile = new File(dir);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			String fileName = programTypeStr + ymdhms + ".html";
			htmlUrl = "/" + programTypeStr + "/" + ymd + "/" + fileName;
			String filePath = dir + "/" + fileName;
			FileUtil.writeFile(html, filePath, true);
		}

		String htmlCode = null;
		HtmlContent htmlContent = null;
		if (themeVo.getProgramType() != 1 && themeVo.getProgramType() != 2
				&& themeVo.getProgramType() != 3) {
			if (themeVo.getIsRecommend() != 1) {
				// 封装 htmlContent
				if (htmlContentVo.getHtmlCode() != null
						&& !htmlContentVo.getHtmlCode().equals("")) {
					htmlCode = htmlContentVo.getHtmlCode();
					htmlContent = htmlContentDao.get(
							"from HtmlContent where htmlCode=? ", htmlCode);
				} else {
					int s = random.nextInt(max) % (max - min + 1) + min;
					htmlCode = ymdhms + s;
					htmlContent = new HtmlContent();
				}
				htmlContent.setHtmlCode(htmlCode);
				htmlContent.setHtmlCreateDate(new Date());
				htmlContent.setHtmlTitle(htmlContentVo.getHtmlTitle());
				htmlContent.setIsOutLink(htmlContentVo.getIsOutLink());
				if (htmlContentVo.getIsOutLink() == 1) {
					htmlContent.setHtmlUrl(htmlContentVo.getHtmlUrl());
				} else {
					htmlContent.setHtmlUrl(htmlUrl);
				}
				htmlContent.setBusinessType(htmlContentVo.getBusinessType());
				htmlContent.setContent(htmlContentVo.getContent());
				htmlContent.setHtmlState(htmlContentVo.getHtmlState());
				htmlContent.setIsOpenApp(htmlContentVo.getIsOpenApp());
				htmlContent.setIsAddButton(htmlContentVo.getIsAddButton());
				htmlContent.setAppCode(htmlContentVo.getAppCode());
				htmlContent.setAppPara(htmlContentVo.getAppPara());
				htmlContent.setButtonText(htmlContentVo.getButtonText());
				htmlContent.setIsShare(htmlContentVo.getIsShare());
			}
		}

		String themeCode = null;
		if (flag == 1) {
			// 封装theme
			Theme theme = null;
			String oldHtmlCode = null;
			if (themeVo.getTheme_code() != null
					&& !themeVo.getTheme_code().equals("")) {
				themeCode = themeVo.getTheme_code();
				theme = themeDao
						.get("from Theme where theme_code=?", themeCode);
				oldHtmlCode = theme.getHtmlCode();
			} else {
				int s = random.nextInt(max) % (max - min + 1) + min;
				themeCode = ymdhms + s;
				theme = new Theme();
				theme.setCreateDate(new Date());
			}
			theme.setTheme_code(themeCode);
			theme.setUser_Id(user.getId());
			theme.setThemeTitle(themeVo.getThemeTitle());
			if (themeVo.getOnlineDate() != null
					&& !themeVo.getOnlineDate().equals(""))
				theme.setOnlineDate(DateUtil.format(themeVo.getOnlineDate(),
						"yy-MM-dd"));
			if (themeVo.getOffLineDate() != null
					&& !themeVo.getOffLineDate().equals(""))
				theme.setOffLineDate(DateUtil.format(themeVo.getOffLineDate(),
						"yy-MM-dd"));
			theme.setIsTop(themeVo.getIsTop());
			theme.setWeight(themeVo.getWeight());
			theme.setHidden_flag(themeVo.getHidden_flag());
			theme.setIsAllCooperation(themeVo.getIsAllCooperation());
			theme.setIsRecommend(themeVo.getIsRecommend());
			if(themeVo.getIsRecommend()==1){
				theme.setPlatform(themeVo.getPlatform());
				theme.setIsAllSV(themeVo.getIsAllSV());
			}else{
				theme.setPlatform("00");
				theme.setIsAllSV(0);
			}
			theme.setLotteryCode(themeVo.getLotteryCode());
			theme.setStyle(themeVo.getStyle());
			theme.setPictureUrl(themeVo.getPictureUrl());
			theme.setDescribe(themeVo.getDescribe());
			theme.setProgramType(themeVo.getProgramType());
			theme.setHeadStyle(themeVo.getHeadStyle());
			if (themeVo.getProgramType() != 1 && themeVo.getProgramType() != 2
					&& themeVo.getProgramType() != 3) {
				theme.setHtmlCode(htmlCode);
			} else {
				theme.setHtmlCode(themeVo.getHtmlCode());
			}
			theme.setParent_Code(themeVo.getParent_Code());
			if (themeVo.getTheme_code() != null
					&& !themeVo.getTheme_code().equals("")) {
				if (themeVo.getProgramType() != 1
						&& themeVo.getProgramType() != 2
						&& themeVo.getProgramType() != 3) {
					if (themeVo.getIsRecommend() != 1) {
						htmlContentDao.update(htmlContent);
					}
				} else {
					if (!oldHtmlCode.equals(themeVo.getHtmlCode())) {
						HtmlContent html4State1 = htmlContentDao.get(
								"from HtmlContent where htmlCode=?",
								themeVo.getHtmlCode());
						html4State1.setHtmlState(1);
						htmlContentDao.update(html4State1);

						List<Object> param = new ArrayList<Object>();
						param.add(oldHtmlCode);
						long num = themeDao
								.countBySql(
										"select count(*) from INFO_THEME where HTML_CODE=?",
										param).longValue();
						if (num == 1) {
							HtmlContent html4State2 = htmlContentDao.get(
									"from HtmlContent where htmlCode=?",
									oldHtmlCode);
							html4State2.setHtmlState(0);
							htmlContentDao.update(html4State2);
						}
					}

				}
				themeDao.update(theme);
			} else {
				if (themeVo.getProgramType() != 1
						&& themeVo.getProgramType() != 2
						&& themeVo.getProgramType() != 3) {
					if (themeVo.getIsRecommend() != 1) {
						htmlContentDao.save(htmlContent);
					}
				} else {
					HtmlContent html4State = htmlContentDao.get(
							"from HtmlContent where htmlCode=?",
							themeVo.getHtmlCode());
					html4State.setHtmlState(1);
					htmlContentDao.update(html4State);
				}
				themeDao.save(theme);
			}
			List<Object> pr = new ArrayList<Object>();
			pr.add(themeCode);
			themeCooperationDao.executeSql(
					"delete from INFO_THEME_COOPERATION where THEME_CODE =? ",
					pr);
			if (themeVo.getIsAllCooperation() != 1){
				String sidListstr = themeVo.getCooperationName();
				if (sidListstr != null && !sidListstr.equals("")) {
					String[] sids = sidListstr.substring(0,
							sidListstr.length() - 1).split(",");
					for (String sid : sids) {
						ThemeCooperation themeCooperation = new ThemeCooperation();
						themeCooperation.setThemeCode(themeCode);
						themeCooperation.setSid(sid);
						themeCooperation.setCreateDate(new Date());
						themeCooperationDao.save(themeCooperation);
					}
				}
			}
			
			if(themeVo.getIsRecommend()==1){
				themeSVDao.executeSql("delete from INFO_THEME_SV where THEME_CODE =? ",
						pr);
				if(themeVo.getIsAllSV()==1){
					String svListstr = themeVo.getSvStr();
					if (svListstr != null && !svListstr.equals("")) {
						String[] svCodes = svListstr.substring(0,
								svListstr.length() - 1).split(",");
						for (String svCode : svCodes) {
							ThemeSV sv = new ThemeSV();
							sv.setSvCode(svCode);
							sv.setThemeCode(themeCode);
							themeSVDao.save(sv);
						}
					}
				}
			}
		} else if (flag == 2) {
			if (htmlContentVo.getHtmlCode() != null
					&& !htmlContentVo.getHtmlCode().equals("")) {
				htmlContentDao.update(htmlContent);
			} else {
				htmlContentDao.save(htmlContent);
			}
		}

		return themeCode;
	}

	@Override
	public void updateState(ThemeVo themeVo) {
		Theme theme = themeDao.get("from Theme where theme_code=? ",
				themeVo.getTheme_code());
		theme.setState(themeVo.getState());
		if (themeVo.getState() == 1) {
			theme.setPublishDate(new Date());
		} else if (themeVo.getState() == 2) {
			if(themeVo.getProgramType()==2){
				theme.setWeight(0);
			}
			List<Object> uparam = new ArrayList<Object>();
			uparam.add(themeVo.getTheme_code());
			themeUserReadDao.executeSql(
					"delete from info_theme_userread t where  t.theme_code=?",
					uparam);
		}
		themeDao.update(theme);
	}

	@Override
	public void updatehidden(ThemeVo themeVo) {
		Theme theme = themeDao.get("from Theme where theme_code=? ",
				themeVo.getTheme_code());
		theme.setHidden_flag(themeVo.getHidden_flag());
		themeDao.update(theme);

	}

	@Override
	public void updateWeight(ThemeVo themeVo) {
		Theme theme = themeDao.get("from Theme where theme_code=? ",
				themeVo.getTheme_code());
		theme.setWeight(themeVo.getWeight());
		themeDao.update(theme);

	}

	@Override
	public List<ThemeCooperation> selectThemeCooperationByThemeCode(
			String theme_code) {
		return themeCooperationDao.find(
				"from ThemeCooperation where themeCode=?", theme_code);
	}

	@Override
	public List<HtmlContentVo> selectHtmlContent10ByType(int type) {
		String sql = "select q.hid,q.html_code,q.html_title from info_html_content q where q.businesstype=? order by q.htmlcreatedate desc";
		List<Object> param = new ArrayList<Object>();
		param.add(type);
		List<HtmlContentVo> htmlContentVos = new ArrayList<HtmlContentVo>();
		List<Map> maps = themeCooperationDao.findBySql(sql, param, 1, 10);
		for (Map map : maps) {
			HtmlContentVo htmlContentVo = new HtmlContentVo();
			htmlContentVo.setHtmlCode((String) map.get("HTML_CODE"));
			htmlContentVo.setHtmlTitle((String) map.get("HTML_TITLE"));
			htmlContentVos.add(htmlContentVo);
		}
		return htmlContentVos;
	}

	@Override
	public EasyuiDataGridJson selectHtmlContentList(EasyuiDataGrid dg,
			HtmlContentVo htmlContentVo) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder(
				" select q.hid,q.html_code,q.html_title,q.htmlstate,q.businesstype,q.is_outlink,q.html_url,q.htmlcreatedate from info_html_content q  where q.businesstype!=0 ");
		List<Object> values = new ArrayList<Object>();
		if (htmlContentVo != null) {// 添加查询条件
			if (CommonUtil.isNotEmpty(htmlContentVo.getHtmlTitle())) {
				sb.append(" and q.HTML_TITLE = ? ");
				values.add(htmlContentVo.getHtmlTitle());
			}
			// 栏目标题
			if (htmlContentVo.getBusinessType() == 4) {
				sb.append(" and q.BUSINESSTYPE in (1,2,3) ");
			} else {
				sb.append(" and q.BUSINESSTYPE = ? ");
				values.add(htmlContentVo.getBusinessType());
			}
			// 发布状态
			if (htmlContentVo.getHtmlState() != 3) {
				sb.append(" and q.HTMLSTATE =? ");
				values.add(htmlContentVo.getHtmlState());
			}
			// 创建时间
			if (CommonUtil.isNotEmpty(htmlContentVo.getCreateDateStart())) {
				sb.append(" and q.createdate >= ?");
				values.add(DateUtil.format(htmlContentVo.getCreateDateStart(),
						"yy-MM-dd"));
			}
			if (CommonUtil.isNotEmpty(htmlContentVo.getCreateDateEnd())) {
				sb.append(" and q.createdate < ?");
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtil.format(htmlContentVo.getCreateDateEnd(),
						"yy-MM-dd"));

				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0); // 设置时分秒都为0
				cal.add(Calendar.DAY_OF_YEAR, 1); // 小时加1
				values.add(cal.getTime());
			}

		}
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(themeDao.countBySql(totalHql, values).longValue());// 设置总记录数

		if (dg.getSort() != null) {// 设置排序
			String sort = "";
			if (dg.getSort().toString().equalsIgnoreCase("htmlCreateDate")) {
				sort = "q.HTMLCREATEDATE";
			}

			if (dg.getSort().toString().equalsIgnoreCase("htmlState")) {
				sort = "q.HTMLSTATE";
			}
			if (!sort.equals("")) {
				sb.append(" order by " + sort + " " + dg.getOrder());
			}
		}

		List<Map> themeMapList = themeDao.findBySql(sb.toString(), values,
				dg.getPage(), dg.getRows());// 查询分页
		List<HtmlContentVo> htmlContentVoList = new ArrayList<HtmlContentVo>();
		if (themeMapList != null && themeMapList.size() > 0) {// 转换模型
			for (Map map : themeMapList) {
				HtmlContentVo htmlContentVo2 = new HtmlContentVo();
				htmlContentVo2.setHtmlCode((String) map.get("HTML_CODE"));
				htmlContentVo2.setHtmlTitle((String) map.get("HTML_TITLE"));
				htmlContentVo2.setHtmlState(((BigDecimal) map.get("HTMLSTATE"))
						.intValue());
				htmlContentVo2.setHtmlCreateDate(DateUtil.format((Date) map
						.get("HTMLCREATEDATE")));
				htmlContentVo2.setBusinessType(((BigDecimal) map
						.get("BUSINESSTYPE")).intValue());
				htmlContentVoList.add(htmlContentVo2);
			}
		}
		j.setRows(htmlContentVoList);// 设置返回的行
		return j;
	}

	@Override
	public ThemeSum getNumHtml(HtmlContentVo htmlContentVo) {
		String sb = "select q.hid,q.html_code,q.html_title,q.htmlstate,q.businesstype,q.is_outlink,q.html_url,q.htmlcreatedate from info_html_content q  where q.businesstype!=0 ";
		List<Object> values = new ArrayList<Object>();
		ThemeSum sum = new ThemeSum();
		if (htmlContentVo != null) {// 添加查询条件

			String allSql = " select count(*)  from (" + sb + ")";
			String publishedSql = " select count(*)  from (" + sb
					+ " and q.htmlstate > 0 )";
			String unpublishSql = " select count(*)  from (" + sb
					+ " and q.htmlstate = 0 )";
			long all = themeDao.countBySql(allSql, values).longValue();
			long published = themeDao.countBySql(publishedSql, values)
					.longValue();
			long unPublish = themeDao.countBySql(unpublishSql, values)
					.longValue();
			sum.setAll(all);
			sum.setPublished(published);
			sum.setUnPublish(unPublish);
		}
		return sum;
	}

	@Override
	public List<Url> selectUrlByType(String type) {
		List<Url> urls = null;
		if (type != null && !type.equals("")) {
			urls = urlDao.find("from  Url where type =?", type);
		} else {
			urls = urlDao.find("from Url");
		}
		return urls;
	}

	@Override
	public Url addUrl(Url url) {
		SimpleDateFormat dff = new SimpleDateFormat("yyyyMMddHHHmmssS");
		String ymdhms = dff.format(new Date());
		int max = 2000;
		int min = 1000;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		String urlCode = ymdhms + s;
		url.setCode(urlCode);
		long id = (Long) urlDao.save(url);
		url.setId(id);
		return url;
	}

	@Override
	public void delUrl(Url url) {
		urlDao.delete(url);
	}

	@Override
	public void editUrl(Url url) {
		urlDao.update(url);
	}

	@Override
	public List<ThemeVo> selectThemeVoByParent(String themeCode) {
		List<Theme> list = themeDao.find("from Theme where parent_Code=?",
				themeCode);
		List<ThemeVo> themeVos = new ArrayList<ThemeVo>();
		for (Theme theme : list) {
			ThemeVo themeVo = new ThemeVo();
			themeVo = new ThemeVo();
			themeVo.setTid(theme.getTid());
			themeVo.setTheme_code(theme.getTheme_code());
			themeVo.setIsTop(theme.getIsTop());
			themeVo.setDescribe(theme.getDescribe());
			themeVo.setHtmlCode(theme.getHtmlCode());
			themeVo.setThemeTitle(theme.getThemeTitle());
			themeVo.setPictureUrl(theme.getPictureUrl());
			themeVos.add(themeVo);
		}
		return themeVos;
	}

	@Override
	public void updateActivityState(ThemeVo themeVo) {
		Theme theme = themeDao.get("from Theme where theme_code=? ",
				themeVo.getTheme_code());
		theme.setActivityState(themeVo.getActivityState());
		themeDao.update(theme);

	}

	@Override
	public String getTowPic(String htmlCode) {
		HtmlContent content = htmlContentDao.get(
				"from HtmlContent where htmlCode=?", htmlCode);
		String url = null;
		String urlHead = ConfigUtils.getValue("HTML_URL");
		if (content != null) {
			if(content.getIsOutLink()==1){
				url = content.getHtmlUrl();
			}else{
				url=urlHead+content.getHtmlUrl();
			}
		}
		return url;
	}

	@Override
	public void deleteTheme(ThemeVo themeVo) {
		int type = themeVo.getProgramType();
		if (type != 1 && type != 2 && type != 3) {
			Theme theme = themeDao.get("from Theme where theme_code=? ",
					themeVo.getTheme_code());
			themeDao.delete(theme);
			if (themeVo.getIsRecommend() != 1) {
				HtmlContent htmlContent = htmlContentDao.get(
						"from HtmlContent where htmlCode=?",
						theme.getHtmlCode());
				htmlContentDao.delete(htmlContent);
			}

		} else if (type == 1) {
			Theme theme = themeDao.get("from Theme where theme_code=? ",
					themeVo.getTheme_code());
			List<Object> param = new ArrayList<Object>();
			param.add(theme.getHtmlCode());
			long num = themeDao.countBySql(
					"select count(*) from INFO_THEME where HTML_CODE=?", param)
					.longValue();
			if (num == 1) {
				HtmlContent html4State2 = htmlContentDao.get(
						"from HtmlContent where htmlCode=?",
						theme.getHtmlCode());
				if (html4State2 != null) {
					html4State2.setHtmlState(0);
					htmlContentDao.update(html4State2);
				}
			}
			themeDao.delete(theme);
			List<Theme> themes = themeDao.find(
					"from Theme where parent_Code=? ", theme.getTheme_code());
			if (themes != null && themes.size() != 0) {
				for (Theme theme2 : themes) {
					themeDao.delete(theme2);
				}
			}
		} else {
			Theme theme = themeDao.get("from Theme where theme_code=? ",
					themeVo.getTheme_code());
			List<Object> param = new ArrayList<Object>();
			param.add(theme.getHtmlCode());
			long num = themeDao.countBySql(
					"select count(*) from INFO_THEME where HTML_CODE=?", param)
					.longValue();
			if (num == 1) {
				HtmlContent html4State2 = htmlContentDao.get(
						"from HtmlContent where htmlCode=?",
						theme.getHtmlCode());
				html4State2.setHtmlState(0);
				htmlContentDao.update(html4State2);
			}
			themeDao.delete(theme);
		}
		List<Object> uparam = new ArrayList<Object>();
		uparam.add(themeVo.getTheme_code());
		themeUserReadDao.executeSql(
				"delete from info_theme_userread t where  t.theme_code=?",
				uparam);
	}

	@Override
	public void deleteHtmlContent(HtmlContentVo htmlContentVo) {
		HtmlContent htmlContent = htmlContentDao.get(
				"from HtmlContent where htmlCode=?",
				htmlContentVo.getHtmlCode());
		htmlContentDao.delete(htmlContent);
	}



}

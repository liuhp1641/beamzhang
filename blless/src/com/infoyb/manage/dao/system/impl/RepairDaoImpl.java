package com.cm.manage.dao.system.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.system.IRepairDao;
import com.cm.manage.model.system.Symenu;
import com.cm.manage.model.system.Syportal;
import com.cm.manage.model.system.Syresources;
import com.cm.manage.model.system.Syrole;
import com.cm.manage.model.system.Syuser;
import com.cm.manage.util.base.Encrypt;


@Repository("repairDao")
public class RepairDaoImpl  implements IRepairDao {

	@Autowired
	private IBaseDao<Syrole> roleDao;
	@Autowired
	private IBaseDao<Syresources> resourcesDao;
	@Autowired
	private IBaseDao<Symenu> menuDao;
	@Autowired
	private IBaseDao<Syuser> userDao;
	@Autowired
	private IBaseDao<Syportal> portalDao;
	
	public void repair() {

		// 修复菜单
		deleteMenuParent();
		repairMenu();

		// 修复资源
		deleteResourcesParent();
		repairResources();

		// 修复角色
		deleteRoleParent();
		repairRole();

		// 修复用户
		repairUser();

		// 修复门户
		repairPortal();

	}

	public void repairPortal() {
		Syportal syportal1 = new Syportal();
		syportal1.setId("notice");
		syportal1.setTitle("通知");
		syportal1.setSrc("/jsp/portal/use.jsp");
		syportal1.setHeight(BigDecimal.valueOf(150));
		syportal1.setClosable("1");
		syportal1.setCollapsible("1");
		syportal1.setSeq(BigDecimal.valueOf(1));
		portalDao.saveOrUpdate(syportal1);
		
	}

	public void deleteMenuParent() {
		menuDao.executeHql("update Symenu t set t.symenu = null ");
	}

	public void deleteResourcesParent() {
		resourcesDao.executeHql("update Syresources t set t.syresources = null ");
	}

	public void deleteRoleParent() {
		roleDao.executeHql("update Syrole t set t.syrole = null ");
	}

	private void repairRole() {
		Syrole admin = new Syrole();
		admin.setId("0");
		admin.setText("超管角色");
		admin.setDescript("超级管理员角色");
		admin.setSyrole(null);
		admin.setSeq(BigDecimal.valueOf(0));
		roleDao.saveOrUpdate(admin);
	}

	private void repairResources() {
		
		Syresources xtgl = new Syresources();
		xtgl.setId("xtgl");
		xtgl.setText("系统管理");
		xtgl.setSrc("");
		xtgl.setDescript("系统管理");
		xtgl.setSyresources(null);
		xtgl.setSeq(BigDecimal.valueOf(1));
		resourcesDao.saveOrUpdate(xtgl);
		repairResourcesXtgl(xtgl);// 修复系统管理子菜单

		Syresources kfgl = new Syresources();
		kfgl.setId("kfgl");
		kfgl.setText("客服管理");
		kfgl.setSrc("");
		kfgl.setDescript("客服管理");
		kfgl.setSyresources(null);
		kfgl.setSeq(BigDecimal.valueOf(1));
		kfgl.setOnoff("0");
		resourcesDao.saveOrUpdate(kfgl);
		repairSyresourcesKfgl(kfgl);
		
		Syresources yhgl = new Syresources();
		yhgl.setId("yhgl");
		yhgl.setText("用户管理");
		yhgl.setSrc("");
		yhgl.setDescript("用户管理");
		yhgl.setSyresources(null);
		yhgl.setSeq(BigDecimal.valueOf(1));
		yhgl.setOnoff("0");
		resourcesDao.saveOrUpdate(yhgl);
		repairSyresourcesYhgl(yhgl);
		
		Syresources yygl = new Syresources();
		yygl.setId("yygl");
		yygl.setText("运营管理");
		yygl.setSrc("");
		yhgl.setDescript("运营管理");
		yygl.setSyresources(null);
		yhgl.setSeq(BigDecimal.valueOf(1));
		yygl.setOnoff("0");
		resourcesDao.saveOrUpdate(yygl);
		repairSyresourcesYygl(yygl);
		
		Syresources hdxt = new Syresources();
		hdxt.setId("hdxt");
		hdxt.setText("互动系统");
		hdxt.setSrc("");
		hdxt.setDescript("互动系统");
		hdxt.setSyresources(null);
		hdxt.setSeq(BigDecimal.valueOf(1));
		hdxt.setOnoff("0");
		resourcesDao.saveOrUpdate(hdxt);
		repairSyresourcesHdxt(hdxt);
		
		
		Syresources xtsz = new Syresources();
		xtsz.setId("xtsz");
		xtsz.setText("系统设置");
		hdxt.setSrc("");
		xtsz.setDescript("系统设置");
		xtsz.setSyresources(null);
		xtsz.setSeq(BigDecimal.valueOf(1));
		xtsz.setOnoff("0");
		resourcesDao.saveOrUpdate(xtsz);
		repairSyresourcesXtsz(xtsz);
		
		Syresources zjgl = new Syresources();
		zjgl.setId("zjgl");
		zjgl.setText("资金管理");
		zjgl.setSrc("");
		zjgl.setDescript("资金管理");
		zjgl.setSyresources(null);
		zjgl.setSeq(BigDecimal.valueOf(1));
		zjgl.setOnoff("0");
		resourcesDao.saveOrUpdate(zjgl);
		repairSyresourcesZjgl(zjgl);
		
		Syresources ddgl = new Syresources();
		ddgl.setId("ddgl");
		ddgl.setText("订单管理");
		ddgl.setSrc("");
		ddgl.setDescript("订单管理");
		ddgl.setSyresources(null);
		ddgl.setSeq(BigDecimal.valueOf(1));
		ddgl.setOnoff("0");
		resourcesDao.saveOrUpdate(ddgl);
		repairSyresourcesDdgl(ddgl);
	}

	private void repairSyresourcesSy(Syresources sy) {
		Syresources north = new Syresources();
		north.setId("north");
		north.setText("north");
		north.setSrc("/userController.do?north");
		north.setDescript("north");
		north.setSyresources(sy);
		north.setSeq(BigDecimal.valueOf(1));
		north.setOnoff("0");
		resourcesDao.saveOrUpdate(north);

		Syresources west = new Syresources();
		west.setId("west");
		west.setText("west");
		west.setSrc("/userController.do?west");
		west.setDescript("west");
		west.setSyresources(sy);
		west.setSeq(BigDecimal.valueOf(1));
		west.setOnoff("0");
		resourcesDao.saveOrUpdate(west);

		
		Syresources center = new Syresources();
		center.setId("center");
		center.setText("center");
		center.setSrc("/userController.do?center");
		center.setDescript("center");
		center.setSyresources(sy);
		center.setSeq(BigDecimal.valueOf(1));
		center.setOnoff("0");
		resourcesDao.saveOrUpdate(center);

		Syresources south = new Syresources();
		south.setId("south");
		south.setText("south");
		south.setSrc("/userController.do?south");
		south.setDescript("south");
		south.setSyresources(sy);
		south.setSeq(BigDecimal.valueOf(1));
		south.setOnoff("0");
		resourcesDao.saveOrUpdate(south);
	}

	private void repairResourcesXtgl(Syresources xtgl) {
		Syresources sy = new Syresources();
		sy.setId("sy");
		sy.setText("首页");
		sy.setSrc("/userController.do?home");
		sy.setDescript("首页");
		sy.setSyresources(xtgl);
		sy.setSeq(BigDecimal.valueOf(1));
		sy.setOnoff("0");
		resourcesDao.saveOrUpdate(sy);
		repairSyresourcesSy(sy);
		Syresources zygl = new Syresources();
		zygl.setId("zygl");
		zygl.setText("资源管理");
		zygl.setSrc("");
		zygl.setSyresources(xtgl);
		zygl.setSeq(BigDecimal.valueOf(3));
		zygl.setOnoff("0");
		resourcesDao.saveOrUpdate(zygl);
		repairZyglResources(zygl);// 修复资源管理下级

		Syresources czygl = new Syresources();
		czygl.setId("czygl");
		czygl.setText("操作员管理");
		czygl.setSrc("");
		czygl.setSyresources(xtgl);
		czygl.setSeq(BigDecimal.valueOf(2));
		czygl.setOnoff("0");
		resourcesDao.saveOrUpdate(czygl);
		repairResourcesUser(czygl);

		Syresources cdgl = new Syresources();
		cdgl.setId("cdgl");
		cdgl.setText("菜单管理");
		cdgl.setSrc("");
		cdgl.setSyresources(xtgl);
		cdgl.setSeq(BigDecimal.valueOf(0));
		cdgl.setOnoff("0");
		resourcesDao.saveOrUpdate(cdgl);
		repairMenuResources(cdgl);

		Syresources jsgl = new Syresources();
		jsgl.setId("role");
		jsgl.setText("角色管理");
		jsgl.setSrc("");
		jsgl.setSyresources(xtgl);
		jsgl.setSeq(BigDecimal.valueOf(0));
		jsgl.setOnoff("0");
		resourcesDao.saveOrUpdate(jsgl);
		repairRoleResources(jsgl);

		Syresources portal = new Syresources();
		portal.setId("portal");
		portal.setText("门户管理");
		portal.setSrc("");
		portal.setSyresources(xtgl);
		portal.setSeq(BigDecimal.valueOf(0));
		portal.setOnoff("0");
		resourcesDao.saveOrUpdate(portal);
		repairPortalResources(portal);
	}

	private void repairSyresourcesYhgl(Syresources yhgl) {
		
		Syresources yhgl_query = new Syresources();
		yhgl_query.setId("yhgl_query");
		yhgl_query.setText("用户首页");
		yhgl_query.setSrc("/memberController.do?main");
		yhgl_query.setSyresources(yhgl);
		yhgl_query.setSeq(BigDecimal.valueOf(0));
		yhgl_query.setOnoff("0");
		resourcesDao.saveOrUpdate(yhgl_query);
		
		Syresources yhgl_datagrid = new Syresources();
		yhgl_datagrid.setId("yhgl_datagrid");
		yhgl_datagrid.setText("用户表格");
		yhgl_datagrid.setSrc("/memberController.do?members");
		yhgl_datagrid.setSyresources(yhgl);
		yhgl_datagrid.setSeq(BigDecimal.valueOf(1));
		yhgl_datagrid.setOnoff("0");
		resourcesDao.saveOrUpdate(yhgl_datagrid);
		
		Syresources yhgl_detail = new Syresources();
		yhgl_detail.setId("yhgl_detail");
		yhgl_detail.setText("用户详情");
		yhgl_detail.setSrc("/memberController.do?detail");
		yhgl_detail.setSyresources(yhgl);
		yhgl_detail.setSeq(BigDecimal.valueOf(1));
		yhgl_detail.setOnoff("0");
		resourcesDao.saveOrUpdate(yhgl_detail);
		
		Syresources yhgl_detailByMobile = new Syresources();
		yhgl_detailByMobile.setId("yhgl_detailByMobile");
		yhgl_detailByMobile.setText("用户详情");
		yhgl_detailByMobile.setSrc("/memberController.do?detailByMobile");
		yhgl_detailByMobile.setSyresources(yhgl);
		yhgl_detailByMobile.setSeq(BigDecimal.valueOf(1));
		yhgl_detailByMobile.setOnoff("0");
		resourcesDao.saveOrUpdate(yhgl_detailByMobile);
		
		Syresources yhgl_basic = new Syresources();
		yhgl_basic.setId("yhgl_basic");
		yhgl_basic.setText("基本信息");
		yhgl_basic.setSrc("/memberController.do?basic");
		yhgl_basic.setSyresources(yhgl);
		yhgl_basic.setSeq(BigDecimal.valueOf(1));
		yhgl_basic.setOnoff("0");
		resourcesDao.saveOrUpdate(yhgl_basic);
		
		Syresources yhgl_edit = new Syresources();
		yhgl_edit.setId("yhgl_edit");
		yhgl_edit.setText("用户信息修改");
		yhgl_edit.setSrc("/memberController.do?edit");
		yhgl_edit.setSyresources(yhgl);
		yhgl_edit.setSeq(BigDecimal.valueOf(1));
		yhgl_edit.setOnoff("0");
		resourcesDao.saveOrUpdate(yhgl_edit);
		
		Syresources clearMobile = new Syresources();
		clearMobile.setId("clearMobile");
		clearMobile.setText("用户手机号清空");
		clearMobile.setSrc("/memberController.do?clearMobile");
		clearMobile.setSyresources(yhgl);
		clearMobile.setSeq(BigDecimal.valueOf(1));
		clearMobile.setOnoff("0");
		resourcesDao.saveOrUpdate(clearMobile);
		
		Syresources basic_save = new Syresources();
		basic_save.setId("basic_save");
		basic_save.setText("用户基本信息保存");
		basic_save.setSrc("/memberController.do?basicSave");
		basic_save.setSyresources(yhgl);
		basic_save.setSeq(BigDecimal.valueOf(1));
		basic_save.setOnoff("0");
		resourcesDao.saveOrUpdate(basic_save);
		
		
		Syresources charge_save = new Syresources();
		charge_save.setId("charge_save");
		charge_save.setText("充值银行信息保存");
		charge_save.setSrc("/memberController.do?chargeSave");
		charge_save.setSyresources(yhgl);
		charge_save.setSeq(BigDecimal.valueOf(1));
		charge_save.setOnoff("0");
		resourcesDao.saveOrUpdate(charge_save);
		
		Syresources fillBank_save = new Syresources();
		fillBank_save.setId("fillBank_save");
		fillBank_save.setText("提现银行信息保存");
		fillBank_save.setSrc("/memberController.do?fillBankSave");
		fillBank_save.setSyresources(yhgl);
		fillBank_save.setSeq(BigDecimal.valueOf(1));
		fillBank_save.setOnoff("0");
		resourcesDao.saveOrUpdate(fillBank_save);
		
		Syresources edit_pwd = new Syresources();
		edit_pwd.setId("edit_pwd");
		edit_pwd.setText("密码修改");
		edit_pwd.setSrc("/memberController.do?pwdopen");
		edit_pwd.setSyresources(yhgl);
		yhgl_edit.setSeq(BigDecimal.valueOf(1));
		yhgl_edit.setOnoff("0");
		resourcesDao.saveOrUpdate(edit_pwd);
		
		Syresources yhgl_focus = new Syresources();
		yhgl_focus.setId("yhgl_focus");
		yhgl_focus.setText("我的关注");
		yhgl_focus.setSrc("/memberController.do?focus");
		yhgl_focus.setSyresources(yhgl);
		yhgl_focus.setSeq(BigDecimal.valueOf(2));
		yhgl_focus.setOnoff("0");
		resourcesDao.saveOrUpdate(yhgl_focus);
		
		Syresources yhgl_groupgrid = new Syresources();
		yhgl_groupgrid.setId("yhgl_groupgrid");
		yhgl_groupgrid.setText("组列表");
		yhgl_groupgrid.setSrc("/memberController.do?groupgrid");
		yhgl_groupgrid.setSyresources(yhgl);
		yhgl_groupgrid.setSeq(BigDecimal.valueOf(2));
		yhgl_groupgrid.setOnoff("0");
		resourcesDao.saveOrUpdate(yhgl_groupgrid);
		
		Syresources yhgl_level = new Syresources();
		yhgl_level.setId("yhgl_level");
		yhgl_level.setText("等级管理");
		yhgl_level.setSrc("/memberController.do?level");
		yhgl_level.setSyresources(yhgl);
		yhgl_level.setSeq(BigDecimal.valueOf(3));
		yhgl_level.setOnoff("0");
		resourcesDao.saveOrUpdate(yhgl_level);
		
		Syresources yhgl_device = new Syresources();
		yhgl_device.setId("yhgl_device");
		yhgl_device.setText("设备查询");
		yhgl_device.setSrc("/memberController.do?device");
		yhgl_device.setSyresources(yhgl);
		yhgl_device.setSeq(BigDecimal.valueOf(4));
		yhgl_device.setOnoff("0");
		resourcesDao.saveOrUpdate(yhgl_device);
		
		Syresources yhgl_blacklist = new Syresources();
		yhgl_blacklist.setId("yhgl_blacklist");
		yhgl_blacklist.setText("黑名单");
		yhgl_blacklist.setSrc("/memberController.do?blacklist");
		yhgl_blacklist.setSyresources(yhgl);
		yhgl_blacklist.setSeq(BigDecimal.valueOf(4));
		yhgl_blacklist.setOnoff("0");
		resourcesDao.saveOrUpdate(yhgl_blacklist);
		
		Syresources yhgl_monitor = new Syresources();
		yhgl_monitor.setId("yhgl_monitor");
		yhgl_monitor.setText("账户监控");
		yhgl_monitor.setSrc("/memberController.do?monitor");
		yhgl_monitor.setSyresources(yhgl);
		yhgl_monitor.setSeq(BigDecimal.valueOf(4));
		yhgl_monitor.setOnoff("0");
		resourcesDao.saveOrUpdate(yhgl_monitor);
	}
	
	private void repairSyresourcesKfgl(Syresources kfgl) {
		Syresources kfgl_home = new Syresources();
		kfgl_home.setId("kfgl_home");
		kfgl_home.setText("客服首页");
		kfgl_home.setSrc("/cmsController.do?home");
		kfgl_home.setSyresources(kfgl);
		kfgl_home.setSeq(BigDecimal.valueOf(0));
		kfgl_home.setOnoff("0");
		resourcesDao.saveOrUpdate(kfgl_home);
		Syresources kfgl_page = new Syresources();
		kfgl_page.setId("kfgl_page");
		kfgl_page.setText("基本查询");
		kfgl_page.setSrc("/cmsController.do?query");
		kfgl_page.setSyresources(kfgl);
		kfgl_page.setSeq(BigDecimal.valueOf(0));
		kfgl_page.setOnoff("0");
		resourcesDao.saveOrUpdate(kfgl_page);
		
		Syresources kfgl_message = new Syresources();
		kfgl_message.setId("kfgl_message");
		kfgl_message.setText("留言管理");
		kfgl_message.setSrc("/cmsController.do?message");
		kfgl_message.setSyresources(kfgl);
		kfgl_message.setSeq(BigDecimal.valueOf(0));
		kfgl_message.setOnoff("0");
		resourcesDao.saveOrUpdate(kfgl_message);
		
		Syresources kfgl_widthdraw = new Syresources();
		kfgl_widthdraw.setId("kfgl_widthdraw");
		kfgl_widthdraw.setText("提现审核");
		kfgl_widthdraw.setSrc("/cmsController.do?widthdraw");
		kfgl_widthdraw.setSyresources(kfgl);
		kfgl_widthdraw.setSeq(BigDecimal.valueOf(0));
		kfgl_widthdraw.setOnoff("0");
		resourcesDao.saveOrUpdate(kfgl_widthdraw);
	}
	
	private void repairSyresourcesYygl(Syresources yygl) {
		
		Syresources yygl_lottery = new Syresources();
		yygl_lottery.setId("yygl_kind");
		yygl_lottery.setText("彩种管理");
		yygl_lottery.setSrc("/operateController.do?lottery");
		yygl_lottery.setSyresources(yygl);
		yygl_lottery.setSeq(BigDecimal.valueOf(0));
		yygl_lottery.setOnoff("0");
		resourcesDao.saveOrUpdate(yygl_lottery);
		
		Syresources yygl_query = new Syresources();
		yygl_query.setId("yygl_query");
		yygl_query.setText("彩种查询");
		yygl_query.setSrc("/operateController.do?invoker");
		yygl_query.setSyresources(yygl);
		yygl_query.setSeq(BigDecimal.valueOf(0));
		yygl_query.setOnoff("0");
		resourcesDao.saveOrUpdate(yygl_query);
		
		
		Syresources lottery_save = new Syresources();
		lottery_save.setId("lottery_save");
		lottery_save.setText("彩种保存");
		lottery_save.setSrc("/operateController.do?editLottery");
		lottery_save.setSyresources(yygl);
		lottery_save.setSeq(BigDecimal.valueOf(0));
		lottery_save.setOnoff("0");
		resourcesDao.saveOrUpdate(lottery_save);
		
		Syresources yygl_recharge = new Syresources();
		yygl_recharge.setId("yygl_recharge");
		yygl_recharge.setText("充值管理");
		yygl_recharge.setSrc("/operateController.do?recharge");
		yygl_recharge.setSyresources(yygl);
		yygl_recharge.setSeq(BigDecimal.valueOf(0));
		yygl_query.setOnoff("0");
		resourcesDao.saveOrUpdate(yygl_recharge);
	}
	
   private void repairSyresourcesHdxt(Syresources hdxt) {
		
		Syresources hdxt_dxgl = new Syresources();
		hdxt_dxgl.setId("hdxt_dxgl");
		hdxt_dxgl.setText("短信管理");
		hdxt_dxgl.setSrc("");
		hdxt_dxgl.setSyresources(hdxt);
		hdxt_dxgl.setSeq(BigDecimal.valueOf(0));
		hdxt_dxgl.setOnoff("0");
		resourcesDao.saveOrUpdate(hdxt_dxgl);
		repairSyresourcesDxgl(hdxt_dxgl);
		
		
	}
   
   private void repairSyresourcesDxgl(Syresources hdxt_dxgl) {
		
		Syresources dxgl_fsdx = new Syresources();
		dxgl_fsdx.setId("dxgl_fsdx");
		dxgl_fsdx.setText("发送短信");
		dxgl_fsdx.setSrc("/smsController.do?sendPage");
		dxgl_fsdx.setSyresources(hdxt_dxgl);
		dxgl_fsdx.setSeq(BigDecimal.valueOf(0));
		dxgl_fsdx.setOnoff("0");
		resourcesDao.saveOrUpdate(dxgl_fsdx);
		
		Syresources fsdx_fs = new Syresources();
		fsdx_fs.setId("fsdx_fs");
		fsdx_fs.setText("发送按钮");
		fsdx_fs.setSrc("/smsController.do?sendSms");
		fsdx_fs.setSyresources(hdxt_dxgl);
		fsdx_fs.setSeq(BigDecimal.valueOf(0));
		fsdx_fs.setOnoff("0");
		resourcesDao.saveOrUpdate(fsdx_fs);
		
		Syresources dxgl_ckdx = new Syresources();
		dxgl_ckdx.setId("dxgl_ckdx");
		dxgl_ckdx.setText("查看短信");
		dxgl_ckdx.setSrc("/smsController.do?smsView");
		dxgl_ckdx.setSyresources(hdxt_dxgl);
		dxgl_ckdx.setSeq(BigDecimal.valueOf(1));
		dxgl_ckdx.setOnoff("0");
		resourcesDao.saveOrUpdate(dxgl_ckdx);
		
		Syresources ckdx_query = new Syresources();
		ckdx_query.setId("ckdx_query");
		ckdx_query.setText("短信查询");
		ckdx_query.setSrc("/smsController.do?smsDatagrid");
		ckdx_query.setSyresources(hdxt_dxgl);
		ckdx_query.setSeq(BigDecimal.valueOf(2));
		ckdx_query.setOnoff("0");
		resourcesDao.saveOrUpdate(ckdx_query);
		
	}
   

   private void repairSyresourcesXtsz(Syresources xtsz) {
		
 		Syresources zdgl_account = new Syresources();
 		zdgl_account.setId("zdgl_account");
 		zdgl_account.setText("账户字典");
 		zdgl_account.setSrc("/dictController.do?accountDict");
 		zdgl_account.setSyresources(xtsz);
 		zdgl_account.setSeq(BigDecimal.valueOf(0));
 		zdgl_account.setOnoff("0");
 		resourcesDao.saveOrUpdate(zdgl_account);
 		
 		
 		Syresources zdgl_tms = new Syresources();
 		zdgl_tms.setId("zdgl_tms");
 		zdgl_tms.setText("订单字典");
 		zdgl_tms.setSrc("/dictController.do?tmsDict");
 		zdgl_tms.setSyresources(xtsz);
 		zdgl_tms.setSeq(BigDecimal.valueOf(1));
 		zdgl_tms.setOnoff("0");
 		resourcesDao.saveOrUpdate(zdgl_tms);
 		
 		Syresources zdgl_user = new Syresources();
 		zdgl_user.setId("zdgl_user");
 		zdgl_user.setText("用户字典");
 		zdgl_user.setSrc("/dictController.do?userDict");
 		zdgl_user.setSyresources(xtsz);
 		zdgl_user.setSeq(BigDecimal.valueOf(2));
 		zdgl_user.setOnoff("0");
 		resourcesDao.saveOrUpdate(zdgl_user);
 		
 		Syresources zdgl_type = new Syresources();
 		zdgl_type.setId("zdgl_type");
 		zdgl_type.setText("字典类型");
 		zdgl_type.setSrc("/dictController.do?dictType");
 		zdgl_type.setSyresources(xtsz);
 		zdgl_type.setSeq(BigDecimal.valueOf(3));
 		zdgl_type.setOnoff("0");
 		resourcesDao.saveOrUpdate(zdgl_type);
 		
 		
 		Syresources zdgl_detail = new Syresources();
 		zdgl_detail.setId("zdgl_detail");
 		zdgl_detail.setText("字典详细");
 		zdgl_detail.setSrc("/dictController.do?dictDetail");
 		zdgl_detail.setSyresources(xtsz);
 		zdgl_detail.setSeq(BigDecimal.valueOf(4));
 		zdgl_detail.setOnoff("0");
 		resourcesDao.saveOrUpdate(zdgl_detail);
 		
 		
 		Syresources type_add = new Syresources();
 		type_add.setId("type_add");
 		type_add.setText("类型新增");
 		type_add.setSrc("/dictController.do?typeAdd");
 		type_add.setSyresources(xtsz);
 		type_add.setSeq(BigDecimal.valueOf(5));
 		type_add.setOnoff("0");
 		resourcesDao.saveOrUpdate(type_add);
 		
 		Syresources type_del = new Syresources();
 		type_del.setId("type_del");
 		type_del.setText("类型删除");
 		type_del.setSrc("/dictController.do?typeDel");
 		type_del.setSyresources(xtsz);
 		type_del.setSeq(BigDecimal.valueOf(5));
 		type_del.setOnoff("0");
 		resourcesDao.saveOrUpdate(type_del);
 		
 		Syresources type_info = new Syresources();
 		type_info.setId("type_info");
 		type_info.setText("类型信息");
 		type_info.setSrc("/dictController.do?typeInfo");
 		type_info.setSyresources(xtsz);
 		type_info.setSeq(BigDecimal.valueOf(5));
 		type_info.setOnoff("0");
 		resourcesDao.saveOrUpdate(type_info);
 		
 		
 		
 		Syresources detail_add = new Syresources();
 		detail_add.setId("detail_add");
 		detail_add.setText("字典详细新增");
 		detail_add.setSrc("/dictController.do?detailAdd");
 		detail_add.setSyresources(xtsz);
 		detail_add.setSeq(BigDecimal.valueOf(5));
 		detail_add.setOnoff("0");
 		resourcesDao.saveOrUpdate(detail_add);
 		
 		Syresources detail_del = new Syresources();
 		detail_del.setId("detail_del");
 		detail_del.setText("字典详细删除");
 		detail_del.setSrc("/dictController.do?detailDel");
 		detail_del.setSyresources(xtsz);
 		detail_del.setSeq(BigDecimal.valueOf(5));
 		detail_del.setOnoff("0");
 		resourcesDao.saveOrUpdate(detail_del);
 		
 	}
    
   private void repairSyresourcesZjgl(Syresources zjgl) {
		
		Syresources memberAccount = new Syresources();
		memberAccount.setId("memberAccount");
		memberAccount.setText("用户资金");
		memberAccount.setSrc("/accountController.do?memberAccount");
		memberAccount.setSyresources(zjgl);
		memberAccount.setSeq(BigDecimal.valueOf(0));
		memberAccount.setOnoff("0");
		resourcesDao.saveOrUpdate(memberAccount);
		
		Syresources accountData = new Syresources();
		accountData.setId("accountData");
		accountData.setText("用户资金查询");
		accountData.setSrc("/accountController.do?accountData");
		accountData.setSyresources(zjgl);
		accountData.setSeq(BigDecimal.valueOf(0));
		accountData.setOnoff("0");
		resourcesDao.saveOrUpdate(accountData);
		
		Syresources accountDetail = new Syresources();
		accountDetail.setId("accountDetail");
		accountDetail.setText("账户明细");
		accountDetail.setSrc("/accountController.do?accountDetail");
		accountDetail.setSyresources(zjgl);
		accountDetail.setSeq(BigDecimal.valueOf(0));
		accountDetail.setOnoff("0");
		resourcesDao.saveOrUpdate(accountDetail);
		
		Syresources detailData = new Syresources();
		detailData.setId("detailData");
		detailData.setText("账户明细查询");
		detailData.setSrc("/accountController.do?detailData");
		detailData.setSyresources(zjgl);
		detailData.setSeq(BigDecimal.valueOf(0));
		detailData.setOnoff("0");
		resourcesDao.saveOrUpdate(detailData);
		
		Syresources rechargeManage = new Syresources();
		rechargeManage.setId("rechargeManage");
		rechargeManage.setText("充值管理");
		rechargeManage.setSrc("/accountController.do?rechargeManage");
		rechargeManage.setSyresources(zjgl);
		rechargeManage.setSeq(BigDecimal.valueOf(0));
		rechargeManage.setOnoff("0");
		resourcesDao.saveOrUpdate(rechargeManage);
		
		Syresources rechargeData = new Syresources();
		rechargeData.setId("rechargeData");
		rechargeData.setText("充值管理查询");
		rechargeData.setSrc("/accountController.do?rechargeData");
		rechargeData.setSyresources(zjgl);
		rechargeData.setSeq(BigDecimal.valueOf(0));
		rechargeData.setOnoff("0");
		resourcesDao.saveOrUpdate(rechargeData);
		
		Syresources draw = new Syresources();
		draw.setId("draw");
		draw.setText("提现管理");
		draw.setSrc("/drawController.do?draw");
		draw.setSyresources(zjgl);
		draw.setSeq(BigDecimal.valueOf(0));
		draw.setOnoff("0");
		resourcesDao.saveOrUpdate(draw);
		
		Syresources drawList = new Syresources();
		drawList.setId("drawList");
		drawList.setText("提现列表");
		drawList.setSrc("/drawController.do?drawList");
		drawList.setSyresources(zjgl);
		drawList.setSeq(BigDecimal.valueOf(0));
		drawList.setOnoff("0");
		resourcesDao.saveOrUpdate(drawList);
		
		Syresources drawCount = new Syresources();
		drawCount.setId("drawCount");
		drawCount.setText("提现统计");
		drawCount.setSrc("/drawController.do?drawCount");
		drawCount.setSyresources(zjgl);
		drawCount.setSeq(BigDecimal.valueOf(0));
		drawCount.setOnoff("0");
		resourcesDao.saveOrUpdate(drawCount);
		
		
		Syresources drawTransfer = new Syresources();
		drawTransfer.setId("drawTransfer");
		drawTransfer.setText("提现转账");
		drawTransfer.setSrc("/accountController.do?drawTransfer");
		drawTransfer.setSyresources(zjgl);
		drawTransfer.setSeq(BigDecimal.valueOf(0));
		rechargeManage.setOnoff("0");
		resourcesDao.saveOrUpdate(drawTransfer);
		
		Syresources access = new Syresources();
		access.setId("access");
		access.setText("内部存取");
		access.setSrc("/accountController.do?access");
		access.setSyresources(zjgl);
		access.setSeq(BigDecimal.valueOf(0));
		rechargeManage.setOnoff("0");
		resourcesDao.saveOrUpdate(access);
		
	}
   
   private void repairSyresourcesDdgl(Syresources ddgl) {
		
		Syresources toOrderList = new Syresources();
		toOrderList.setId("toOrderList");
		toOrderList.setText("投注查询");
		toOrderList.setSrc("/orderController.do?toOrderList");
		toOrderList.setSyresources(ddgl);
		toOrderList.setSeq(BigDecimal.valueOf(0));
		toOrderList.setOnoff("0");
		resourcesDao.saveOrUpdate(toOrderList);
		
		Syresources orderList = new Syresources();
		orderList.setId("orderList");
		orderList.setText("投注列表");
		orderList.setSrc("/orderController.do?orderList");
		orderList.setSyresources(ddgl);
		orderList.setSeq(BigDecimal.valueOf(0));
		orderList.setOnoff("0");
		resourcesDao.saveOrUpdate(orderList);
		
		Syresources toAutoOrderList = new Syresources();
		toAutoOrderList.setId("toAutoOrderList");
		toAutoOrderList.setText("追号方案");
		toAutoOrderList.setSrc("/orderController.do?toAutoOrderList");
		toAutoOrderList.setSyresources(ddgl);
		toAutoOrderList.setSeq(BigDecimal.valueOf(0));
		toAutoOrderList.setOnoff("0");
		resourcesDao.saveOrUpdate(toAutoOrderList);
		
	}
   
	private void repairPortalResources(Syresources portal) {
		Syresources portal_page = new Syresources();
		portal_page.setId("portal_page");
		portal_page.setText("门户管理页面");
		portal_page.setSrc("/portalController.do?portal");
		portal_page.setSyresources(portal);
		portal_page.setSeq(BigDecimal.valueOf(0));
		portal_page.setOnoff("1");
		resourcesDao.saveOrUpdate(portal_page);

		Syresources portal_datagrid = new Syresources();
		portal_datagrid.setId("portal_datagrid");
		portal_datagrid.setText("查询门户表格");
		portal_datagrid.setSrc("/portalController.do?datagrid");
		portal_datagrid.setSyresources(portal);
		portal_datagrid.setSeq(BigDecimal.valueOf(3));
		portal_datagrid.setOnoff("1");
		resourcesDao.saveOrUpdate(portal_datagrid);

		Syresources portal_show = new Syresources();
		portal_show.setId("portal_show");
		portal_show.setText("显示门户内容");
		portal_show.setSrc("/portalController.do?show");
		portal_show.setSyresources(portal);
		portal_show.setSeq(BigDecimal.valueOf(3));
		portal_show.setOnoff("0");
		resourcesDao.saveOrUpdate(portal_show);

		Syresources portal_del = new Syresources();
		portal_del.setId("portal_del");
		portal_del.setText("删除门户内容");
		portal_del.setSrc("/portalController.do?del");
		portal_del.setSyresources(portal);
		portal_del.setSeq(BigDecimal.valueOf(3));
		portal_del.setOnoff("1");
		resourcesDao.saveOrUpdate(portal_del);

		Syresources portal_add = new Syresources();
		portal_add.setId("portal_add");
		portal_add.setText("添加门户内容");
		portal_add.setSrc("/portalController.do?add");
		portal_add.setSyresources(portal);
		portal_add.setSeq(BigDecimal.valueOf(3));
		portal_add.setOnoff("1");
		resourcesDao.saveOrUpdate(portal_add);

		Syresources portal_edit = new Syresources();
		portal_edit.setId("portal_edit");
		portal_edit.setText("编辑门户内容");
		portal_edit.setSrc("/portalController.do?edit");
		portal_edit.setSyresources(portal);
		portal_edit.setSeq(BigDecimal.valueOf(3));
		portal_edit.setOnoff("1");
		resourcesDao.saveOrUpdate(portal_edit);
	}

	private void repairRoleResources(Syresources jsgl) {
		Syresources jsgl_page = new Syresources();
		jsgl_page.setId("jsgl_page");
		jsgl_page.setText("角色管理页面");
		jsgl_page.setSrc("/roleController.do?role");
		jsgl_page.setSyresources(jsgl);
		jsgl_page.setSeq(BigDecimal.valueOf(0));
		jsgl_page.setOnoff("1");
		resourcesDao.saveOrUpdate(jsgl_page);

		Syresources jsgl_add = new Syresources();
		jsgl_add.setId("jsgl_add");
		jsgl_add.setText("添加角色");
		jsgl_add.setSrc("/roleController.do?add");
		jsgl_add.setSyresources(jsgl);
		jsgl_add.setSeq(BigDecimal.valueOf(3));
		jsgl_add.setOnoff("1");
		resourcesDao.saveOrUpdate(jsgl_add);

		Syresources jsgl_del = new Syresources();
		jsgl_del.setId("jsgl_del");
		jsgl_del.setText("删除角色");
		jsgl_del.setSrc("/roleController.do?del");
		jsgl_del.setSyresources(jsgl);
		jsgl_del.setSeq(BigDecimal.valueOf(3));
		jsgl_del.setOnoff("1");
		resourcesDao.saveOrUpdate(jsgl_del);

		Syresources jsgl_edit = new Syresources();
		jsgl_edit.setId("jsgl_edit");
		jsgl_edit.setText("修改角色");
		jsgl_edit.setSrc("/roleController.do?edit");
		jsgl_edit.setSyresources(jsgl);
		jsgl_edit.setSeq(BigDecimal.valueOf(3));
		jsgl_edit.setOnoff("1");
		resourcesDao.saveOrUpdate(jsgl_edit);

		Syresources jsgl_treegrid = new Syresources();
		jsgl_treegrid.setId("jsgl_treegrid");
		jsgl_treegrid.setText("查询角色表格");
		jsgl_treegrid.setSrc("/roleController.do?treegrid");
		jsgl_treegrid.setSyresources(jsgl);
		jsgl_treegrid.setSeq(BigDecimal.valueOf(3));
		jsgl_treegrid.setOnoff("1");
		resourcesDao.saveOrUpdate(jsgl_treegrid);

		Syresources jsgl_tree = new Syresources();
		jsgl_tree.setId("jsgl_tree");
		jsgl_tree.setText("查询角色树");
		jsgl_tree.setSrc("/roleController.do?tree");
		jsgl_tree.setSyresources(jsgl);
		jsgl_tree.setSeq(BigDecimal.valueOf(3));
		jsgl_tree.setOnoff("0");
		resourcesDao.saveOrUpdate(jsgl_tree);

	}

	private void repairMenuResources(Syresources cdgl) {
		Syresources cdgl_page = new Syresources();
		cdgl_page.setId("cdgl_page");
		cdgl_page.setText("菜单管理页面");
		cdgl_page.setSrc("/menuController.do?menu");
		cdgl_page.setSyresources(cdgl);
		cdgl_page.setSeq(BigDecimal.valueOf(0));
		cdgl_page.setOnoff("1");
		resourcesDao.saveOrUpdate(cdgl_page);
		/**
		 * begin
		 */
		Syresources usermodel = new Syresources();
		usermodel.setId("usermodel");
		usermodel.setText("用户模块");
		usermodel.setSrc("/menuController.do?model");
		usermodel.setSyresources(cdgl);
		usermodel.setSeq(BigDecimal.valueOf(1));
		usermodel.setOnoff("1");
		resourcesDao.saveOrUpdate(usermodel);
		
		Syresources usertree = new Syresources();
		usertree.setId("usertree");
		usertree.setText("用户树");
		usertree.setSrc("/menuController.do?usertree");
		usertree.setSyresources(cdgl);
		usertree.setSeq(BigDecimal.valueOf(1));
		usertree.setOnoff("1");
		resourcesDao.saveOrUpdate(usertree);
		
		/****/
		Syresources cdgl_add = new Syresources();
		cdgl_add.setId("cdgl_add");
		cdgl_add.setText("添加菜单");
		cdgl_add.setSrc("/menuController.do?add");
		cdgl_add.setSyresources(cdgl);
		cdgl_add.setSeq(BigDecimal.valueOf(3));
		cdgl_add.setOnoff("1");
		resourcesDao.saveOrUpdate(cdgl_add);

		Syresources cdgl_del = new Syresources();
		cdgl_del.setId("cdgl_del");
		cdgl_del.setText("删除菜单");
		cdgl_del.setSrc("/menuController.do?del");
		cdgl_del.setSyresources(cdgl);
		cdgl_del.setSeq(BigDecimal.valueOf(3));
		cdgl_del.setOnoff("1");
		resourcesDao.saveOrUpdate(cdgl_del);

		Syresources cdgl_edit = new Syresources();
		cdgl_edit.setId("cdgl_edit");
		cdgl_edit.setText("修改菜单");
		cdgl_edit.setSrc("/menuController.do?edit");
		cdgl_edit.setSyresources(cdgl);
		cdgl_edit.setSeq(BigDecimal.valueOf(3));
		cdgl_edit.setOnoff("1");
		resourcesDao.saveOrUpdate(cdgl_edit);

		Syresources cdgl_treegrid = new Syresources();
		cdgl_treegrid.setId("cdgl_treegrid");
		cdgl_treegrid.setText("查询菜单表格");
		cdgl_treegrid.setSrc("/menuController.do?treegrid");
		cdgl_treegrid.setSyresources(cdgl);
		cdgl_treegrid.setSeq(BigDecimal.valueOf(3));
		cdgl_treegrid.setOnoff("1");
		resourcesDao.saveOrUpdate(cdgl_treegrid);

		Syresources cdgl_tree = new Syresources();
		cdgl_tree.setId("cdgl_tree");
		cdgl_tree.setText("查询菜单树");
		cdgl_tree.setSrc("/menuController.do?tree");
		cdgl_tree.setSyresources(cdgl);
		cdgl_tree.setSeq(BigDecimal.valueOf(3));
		cdgl_tree.setOnoff("0");
		resourcesDao.saveOrUpdate(cdgl_tree);
	}

	private void repairResourcesUser(Syresources czygl) {
		Syresources czygl_page = new Syresources();
		czygl_page.setId("czygl_page");
		czygl_page.setText("操作员管理页面");
		czygl_page.setSrc("/userController.do?user");
		czygl_page.setSyresources(czygl);
		czygl_page.setSeq(BigDecimal.valueOf(0));
		czygl_page.setOnoff("1");
		resourcesDao.saveOrUpdate(czygl_page);

		Syresources czygl_add = new Syresources();
		czygl_add.setId("czygl_add");
		czygl_add.setText("添加操作员");
		czygl_add.setSrc("/userController.do?add");
		czygl_add.setSyresources(czygl);
		czygl_add.setSeq(BigDecimal.valueOf(2));
		czygl_add.setOnoff("1");
		resourcesDao.saveOrUpdate(czygl_add);

		Syresources czygl_edit = new Syresources();
		czygl_edit.setId("czygl_edit");
		czygl_edit.setText("编辑操作员");
		czygl_edit.setSrc("/userController.do?edit");
		czygl_edit.setSyresources(czygl);
		czygl_edit.setSeq(BigDecimal.valueOf(2));
		czygl_edit.setOnoff("1");
		resourcesDao.saveOrUpdate(czygl_edit);

		Syresources czygl_editUsersRole = new Syresources();
		czygl_editUsersRole.setId("czygl_editUsersRole");
		czygl_editUsersRole.setText("批量编辑操作员角色");
		czygl_editUsersRole.setSrc("/userController.do?editUsersRole");
		czygl_editUsersRole.setSyresources(czygl);
		czygl_editUsersRole.setSeq(BigDecimal.valueOf(2));
		czygl_editUsersRole.setOnoff("1");
		resourcesDao.saveOrUpdate(czygl_editUsersRole);

		Syresources czygl_userInfo = new Syresources();
		czygl_userInfo.setId("czygl_userInfo");
		czygl_userInfo.setText("个人信息页面");
		czygl_userInfo.setSrc("/userController.do?userInfo");
		czygl_userInfo.setSyresources(czygl);
		czygl_userInfo.setSeq(BigDecimal.valueOf(2));
		czygl_userInfo.setOnoff("0");
		resourcesDao.saveOrUpdate(czygl_userInfo);

		Syresources czygl_getUserInfo = new Syresources();
		czygl_getUserInfo.setId("czygl_getUserInfo");
		czygl_getUserInfo.setText("获得个人信息");
		czygl_getUserInfo.setSrc("/userController.do?getUserInfo");
		czygl_getUserInfo.setSyresources(czygl);
		czygl_getUserInfo.setSeq(BigDecimal.valueOf(2));
		czygl_getUserInfo.setOnoff("0");
		resourcesDao.saveOrUpdate(czygl_getUserInfo);

		Syresources czygl_editUserInfo = new Syresources();
		czygl_editUserInfo.setId("czygl_editUserInfo");
		czygl_editUserInfo.setText("编辑个人信息");
		czygl_editUserInfo.setSrc("/userController.do?editUserInfo");
		czygl_editUserInfo.setSyresources(czygl);
		czygl_editUserInfo.setSeq(BigDecimal.valueOf(2));
		czygl_editUserInfo.setOnoff("0");
		resourcesDao.saveOrUpdate(czygl_editUserInfo);

		Syresources czygl_del = new Syresources();
		czygl_del.setId("czygl_del");
		czygl_del.setText("删除操作员");
		czygl_del.setSrc("/userController.do?del");
		czygl_del.setSyresources(czygl);
		czygl_del.setSeq(BigDecimal.valueOf(2));
		czygl_del.setOnoff("1");
		resourcesDao.saveOrUpdate(czygl_del);

		Syresources czygl_datagrid = new Syresources();
		czygl_datagrid.setId("czygl_datagrid");
		czygl_datagrid.setText("操作员表格");
		czygl_datagrid.setSrc("/userController.do?datagrid");
		czygl_datagrid.setSyresources(czygl);
		czygl_datagrid.setSeq(BigDecimal.valueOf(2));
		czygl_datagrid.setOnoff("1");
		resourcesDao.saveOrUpdate(czygl_datagrid);

		Syresources czygl_loginCombobox = new Syresources();
		czygl_loginCombobox.setId("czygl_loginCombobox");
		czygl_loginCombobox.setText("操作员登录");
		czygl_loginCombobox.setSrc("/userController.do?loginCombobox");
		czygl_loginCombobox.setSyresources(czygl);
		czygl_loginCombobox.setSeq(BigDecimal.valueOf(2));
		czygl_loginCombobox.setOnoff("0");
		resourcesDao.saveOrUpdate(czygl_loginCombobox);

		Syresources czygl_loginDatagrid = new Syresources();
		czygl_loginDatagrid.setId("czygl_loginDatagrid");
		czygl_loginDatagrid.setText("操作员登录");
		czygl_loginDatagrid.setSrc("/userController.do?loginDatagrid");
		czygl_loginDatagrid.setSyresources(czygl);
		czygl_loginDatagrid.setSeq(BigDecimal.valueOf(2));
		czygl_loginDatagrid.setOnoff("0");
		resourcesDao.saveOrUpdate(czygl_loginDatagrid);

		Syresources czygl_login = new Syresources();
		czygl_login.setId("czygl_login");
		czygl_login.setText("操作员登录");
		czygl_login.setSrc("/userController.do?login");
		czygl_login.setSyresources(czygl);
		czygl_login.setSeq(BigDecimal.valueOf(2));
		czygl_login.setOnoff("0");
		resourcesDao.saveOrUpdate(czygl_login);

		Syresources czygl_reg = new Syresources();
		czygl_reg.setId("czygl_reg");
		czygl_reg.setText("操作员注册");
		czygl_reg.setSrc("/userController.do?reg");
		czygl_reg.setSyresources(czygl);
		czygl_reg.setSeq(BigDecimal.valueOf(2));
		czygl_reg.setOnoff("0");
		resourcesDao.saveOrUpdate(czygl_reg);

		Syresources czygl_logout = new Syresources();
		czygl_logout.setId("czygl_logout");
		czygl_logout.setText("注销");
		czygl_logout.setSrc("/userController.do?logout");
		czygl_logout.setSyresources(czygl);
		czygl_logout.setSeq(BigDecimal.valueOf(2));
		czygl_logout.setOnoff("0");
		resourcesDao.saveOrUpdate(czygl_logout);
	}

	private void repairZyglResources(Syresources zygl) {
		Syresources zygl_page = new Syresources();
		zygl_page.setId("zygl_page");
		zygl_page.setText("资源管理页面");
		zygl_page.setSrc("/resourcesController.do?resources");
		zygl_page.setSyresources(zygl);
		zygl_page.setSeq(BigDecimal.valueOf(0));
		zygl_page.setOnoff("1");
		resourcesDao.saveOrUpdate(zygl_page);

		Syresources zygl_add = new Syresources();
		zygl_add.setId("zygl_add");
		zygl_add.setText("添加资源");
		zygl_add.setSrc("/resourcesController.do?add");
		zygl_add.setSyresources(zygl);
		zygl_add.setSeq(BigDecimal.valueOf(3));
		zygl_add.setOnoff("1");
		resourcesDao.saveOrUpdate(zygl_add);

		Syresources zygl_del = new Syresources();
		zygl_del.setId("zygl_del");
		zygl_del.setText("删除资源");
		zygl_del.setSrc("/resourcesController.do?del");
		zygl_del.setSyresources(zygl);
		zygl_del.setSeq(BigDecimal.valueOf(3));
		zygl_del.setOnoff("1");
		resourcesDao.saveOrUpdate(zygl_del);

		Syresources zygl_edit = new Syresources();
		zygl_edit.setId("zygl_edit");
		zygl_edit.setText("修改资源");
		zygl_edit.setSrc("/resourcesController.do?edit");
		zygl_edit.setSyresources(zygl);
		zygl_edit.setSeq(BigDecimal.valueOf(3));
		zygl_edit.setOnoff("1");
		resourcesDao.saveOrUpdate(zygl_edit);

		Syresources zygl_treegrid = new Syresources();
		zygl_treegrid.setId("zygl_treegrid");
		zygl_treegrid.setText("查询资源表格");
		zygl_treegrid.setSrc("/resourcesController.do?treegrid");
		zygl_treegrid.setSyresources(zygl);
		zygl_treegrid.setSeq(BigDecimal.valueOf(3));
		zygl_treegrid.setOnoff("1");
		resourcesDao.saveOrUpdate(zygl_treegrid);

		Syresources zygl_tree = new Syresources();
		zygl_tree.setId("zygl_tree");
		zygl_tree.setText("查询资源树");
		zygl_tree.setSrc("/resourcesController.do?tree");
		zygl_tree.setSyresources(zygl);
		zygl_tree.setSeq(BigDecimal.valueOf(3));
		zygl_tree.setOnoff("1");
		resourcesDao.saveOrUpdate(zygl_tree);
	}

	private void repairMenu() {
		
		Symenu xtgl = new Symenu();
		xtgl.setId("xtgl");
		xtgl.setText("系统管理");
		xtgl.setSrc("");
		xtgl.setSymenu(null);
		xtgl.setSeq(BigDecimal.valueOf(10));
		menuDao.saveOrUpdate(xtgl);
		repairMenuXtgl(xtgl);// 修复系统管理子菜单

		Symenu yhgl = new Symenu();
		yhgl.setId("yhgl");
		yhgl.setText("用户管理");
		yhgl.setSrc("");
		yhgl.setSymenu(null);
		yhgl.setSeq(BigDecimal.valueOf(0));
		menuDao.saveOrUpdate(yhgl);
		repairMenuYhgl(yhgl);//修复用户管理子菜单
		
		Symenu zjgl = new Symenu();
		zjgl.setId("zjgl");
		zjgl.setText("资金管理");
		zjgl.setSrc("");
		zjgl.setSymenu(null);
		zjgl.setSeq(BigDecimal.valueOf(1));
		menuDao.saveOrUpdate(zjgl);
		repairMenuZjgl(zjgl);
		
		Symenu ddgl = new Symenu();
		ddgl.setId("ddgl");
		ddgl.setText("订单管理");
		ddgl.setSrc("");
		ddgl.setSymenu(null);
		ddgl.setSeq(BigDecimal.valueOf(2));
		menuDao.saveOrUpdate(ddgl);
		repairMenuDdgl(ddgl);
		
		Symenu kfgl = new Symenu();
		kfgl.setId("kfgl");
		kfgl.setText("客服管理");
		kfgl.setSrc("/cmsController.do?home");
		kfgl.setSymenu(null);
		kfgl.setSeq(BigDecimal.valueOf(8));
		menuDao.saveOrUpdate(kfgl);
		repairMenuKfgl(kfgl);
		
		Symenu yygl = new Symenu();
		yygl.setId("yygl");
		yygl.setText("运营系统");
		yygl.setSrc("");
		yygl.setSymenu(null);
		yygl.setSeq(BigDecimal.valueOf(4));
		menuDao.saveOrUpdate(yygl);
		repairMenuYygl(yygl);
		
		Symenu hdxt = new Symenu();
		hdxt.setId("hdxt");
		hdxt.setText("互动系统");
		hdxt.setSrc("");
		hdxt.setSymenu(null);
		hdxt.setSeq(BigDecimal.valueOf(5));
		menuDao.saveOrUpdate(hdxt);
		repairMenuHdxt(hdxt);
		
		Symenu xtsz = new Symenu();
		xtsz.setId("xtsz");
		xtsz.setText("系统设置");
		xtsz.setSrc("");
		xtsz.setSymenu(null);
		xtsz.setSeq(BigDecimal.valueOf(9));
		menuDao.saveOrUpdate(xtsz);
		repairMenuXtsz(xtsz);
		
	}
	private void repairMenuXtgl(Symenu xtgl) {
		Symenu root = new Symenu();
		root.setId("sy");
		root.setText("首页");
		root.setIconcls("icon-tip");
		root.setSrc("/userController.do?home");
		root.setSymenu(xtgl);
		root.setSeq(BigDecimal.valueOf(0));
		menuDao.saveOrUpdate(root);
		
		Symenu zygl = new Symenu();
		zygl.setId("zygl");
		zygl.setText("资源管理");
		zygl.setSrc("/resourcesController.do?resources");
		zygl.setSymenu(xtgl);
		zygl.setSeq(BigDecimal.valueOf(1));
		menuDao.saveOrUpdate(zygl);

		Symenu czygl = new Symenu();
		czygl.setId("czygl");
		czygl.setText("操作员管理");
		czygl.setSrc("/userController.do?user");
		czygl.setSymenu(xtgl);
		czygl.setSeq(BigDecimal.valueOf(2));
		menuDao.saveOrUpdate(czygl);

		Symenu cdgl = new Symenu();
		cdgl.setId("cdgl");
		cdgl.setText("菜单管理");
		cdgl.setSrc("/menuController.do?menu");
		cdgl.setSymenu(xtgl);
		cdgl.setSeq(BigDecimal.valueOf(3));
		menuDao.saveOrUpdate(cdgl);

		Symenu jsgl = new Symenu();
		jsgl.setId("role");
		jsgl.setText("角色管理");
		jsgl.setSrc("/roleController.do?role");
		jsgl.setSymenu(xtgl);
		jsgl.setSeq(BigDecimal.valueOf(4));
		menuDao.saveOrUpdate(jsgl);

		Symenu mhgl = new Symenu();
		mhgl.setId("portal");
		mhgl.setText("门户管理");
		mhgl.setSrc("/portalController.do?portal");
		mhgl.setSymenu(xtgl);
		mhgl.setSeq(BigDecimal.valueOf(5));
		menuDao.saveOrUpdate(mhgl);
		
		Symenu userInfo = new Symenu();
		userInfo.setId("userInfo");
		userInfo.setText("个人信息");
		userInfo.setSrc("/userController.do?userInfo");
		userInfo.setSymenu(xtgl);
		userInfo.setSeq(BigDecimal.valueOf(6));
		menuDao.saveOrUpdate(userInfo);
	}
	private void repairMenuYhgl(Symenu yhgl) {
		Symenu yhsy = new Symenu();
		yhsy.setId("yhsy");
		yhsy.setText("用户首页");
		yhsy.setSrc("/memberController.do?main");
		yhsy.setSymenu(yhgl);
		yhsy.setSeq(BigDecimal.valueOf(0));
		menuDao.saveOrUpdate(yhsy);

		Symenu wdgz = new Symenu();
		wdgz.setId("wdgz");
		wdgz.setText("我的关注");
		wdgz.setSrc("/memberController.do?focus");
		wdgz.setSymenu(yhgl);
		wdgz.setSeq(BigDecimal.valueOf(1));
		menuDao.saveOrUpdate(wdgz);

		Symenu djgl = new Symenu();
		djgl.setId("djgl");
		djgl.setText("等级管理");
		djgl.setSrc("/memberController.do?level");
		djgl.setSymenu(yhgl);
		djgl.setSeq(BigDecimal.valueOf(2));
		menuDao.saveOrUpdate(djgl);

		Symenu sbcx = new Symenu();
		sbcx.setId("sbcx");
		sbcx.setText("设备查询");
		sbcx.setSrc("/memberController.do?device");
		sbcx.setSymenu(yhgl);
		sbcx.setSeq(BigDecimal.valueOf(3));
		menuDao.saveOrUpdate(sbcx);

		Symenu hmd = new Symenu();
		hmd.setId("hmd");
		hmd.setText("黑名单");
		hmd.setSrc("/memberController.do?blacklist");
		hmd.setSymenu(yhgl);
		hmd.setSeq(BigDecimal.valueOf(4));
		menuDao.saveOrUpdate(hmd);
		
		Symenu zhjk = new Symenu();
		zhjk.setId("zhjk");
		zhjk.setText("账户监控");
		zhjk.setSrc("/memberController.do?monitor");
		zhjk.setSymenu(yhgl);
		zhjk.setSeq(BigDecimal.valueOf(5));
		menuDao.saveOrUpdate(zhjk);
	}

	private void repairMenuKfgl(Symenu kfgl) {
		Symenu kfsy = new Symenu();
		kfsy.setId("kfsy");
		kfsy.setText("基本查询");
		kfsy.setSrc("/cmsController.do?query");
		kfsy.setSymenu(kfgl);
		kfsy.setSeq(BigDecimal.valueOf(0));
		menuDao.saveOrUpdate(kfsy);

		Symenu lygl = new Symenu();
		lygl.setId("lygl");
		lygl.setText("留言管理");
		lygl.setSrc("/cmsController.do?message");
		lygl.setSymenu(kfgl);
		lygl.setSeq(BigDecimal.valueOf(1));
		menuDao.saveOrUpdate(lygl);

		Symenu txsh = new Symenu();
		txsh.setId("txsh");
		txsh.setText("提现审核");
		txsh.setSrc("/cmsController.do?widthdraw");
		txsh.setSymenu(kfgl);
		txsh.setSeq(BigDecimal.valueOf(2));
		menuDao.saveOrUpdate(txsh);
	}
	
	

	private void repairMenuYygl(Symenu yygl) {
		Symenu yygl_czgl = new Symenu();
		yygl_czgl.setId("yygl_czgl");
		yygl_czgl.setText("彩种管理");
		yygl_czgl.setSrc("/operateController.do?lottery");
		yygl_czgl.setSymenu(yygl);
		yygl_czgl.setSeq(BigDecimal.valueOf(1));
		menuDao.saveOrUpdate(yygl_czgl);

		Symenu yygl_recharge = new Symenu();
		yygl_recharge.setId("yygl_recharge");
		yygl_recharge.setText("充值管理");
		yygl_recharge.setSrc("/operateController.do?recharge");
		yygl_recharge.setSymenu(yygl);
		yygl_recharge.setSeq(BigDecimal.valueOf(2));
		menuDao.saveOrUpdate(yygl_recharge);
	}
	
	private void repairMenuHdxt(Symenu hdxt) {
		Symenu dxgl = new Symenu();
		dxgl.setId("dxgl");
		dxgl.setText("短信管理");
		dxgl.setSrc("");
		dxgl.setSymenu(hdxt);
		dxgl.setSeq(BigDecimal.valueOf(0));
		menuDao.saveOrUpdate(dxgl);
		repairMenuDxgl(dxgl);
	
	}
	
	private void repairMenuDxgl(Symenu dxgl) {
		Symenu fsdx = new Symenu();
		fsdx.setId("fsdx");
		fsdx.setText("发送短信");
		fsdx.setSrc("/smsController.do?sendPage");
		fsdx.setSymenu(dxgl);
		fsdx.setSeq(BigDecimal.valueOf(0));
		menuDao.saveOrUpdate(fsdx);
		
		Symenu ckdx = new Symenu();
		ckdx.setId("ckdx");
		ckdx.setText("查看短信");
		ckdx.setSrc("/smsController.do?smsView");
		ckdx.setSymenu(dxgl);
		ckdx.setSeq(BigDecimal.valueOf(1));
		menuDao.saveOrUpdate(ckdx);
		
		Symenu dxmb = new Symenu();
		dxmb.setId("dxmb");
		dxmb.setText("短信模板");
		dxmb.setSrc("");
		dxmb.setSymenu(dxgl);
		dxmb.setSeq(BigDecimal.valueOf(2));
		menuDao.saveOrUpdate(dxmb);
	
	}
	
	private void repairMenuZjgl(Symenu zjgl) {
		Symenu yhzj = new Symenu();
		yhzj.setId("yhzj");
		yhzj.setText("用户资金");
		yhzj.setSrc("/accountController.do?memberAccount");
		yhzj.setSymenu(zjgl);
		yhzj.setSeq(BigDecimal.valueOf(0));
		menuDao.saveOrUpdate(yhzj);
		
		Symenu zhmx = new Symenu();
		zhmx.setId("zhmx");
		zhmx.setText("账户明细");
		zhmx.setSrc("/accountController.do?accountDetail");
		zhmx.setSymenu(zjgl);
		zhmx.setSeq(BigDecimal.valueOf(1));
		menuDao.saveOrUpdate(zhmx);
		
		Symenu chzgl = new Symenu();
		chzgl.setId("chzgl");
		chzgl.setText("充值管理");
		chzgl.setSrc("/accountController.do?rechargeManage");
		chzgl.setSymenu(zjgl);
		chzgl.setSeq(BigDecimal.valueOf(2));
		menuDao.saveOrUpdate(chzgl);
		
		Symenu txgl = new Symenu();
		txgl.setId("txgl");
		txgl.setText("提现管理");
		txgl.setSrc("/drawController.do?draw");
		txgl.setSymenu(zjgl);
		txgl.setSeq(BigDecimal.valueOf(3));
		menuDao.saveOrUpdate(txgl);
		
		Symenu txzz = new Symenu();
		txzz.setId("txzz");
		txzz.setText("提现转账");
		txzz.setSrc("/accountController.do?drawTransfer");
		txzz.setSymenu(zjgl);
		txzz.setSeq(BigDecimal.valueOf(4));
		menuDao.saveOrUpdate(txzz);
		
		Symenu nbcq = new Symenu();
		nbcq.setId("nbcq");
		nbcq.setText("内部存取");
		nbcq.setSrc("/accountController.do?access");
		nbcq.setSymenu(zjgl);
		nbcq.setSeq(BigDecimal.valueOf(4));
		menuDao.saveOrUpdate(nbcq);
	
	}
	
	private void repairMenuDdgl(Symenu ddgl) {
		Symenu tzcx = new Symenu();
		tzcx.setId("tzcx");
		tzcx.setText("投注查询");
		tzcx.setSrc("/orderController.do?toOrderList");
		tzcx.setSymenu(ddgl);
		tzcx.setSeq(BigDecimal.valueOf(0));
		menuDao.saveOrUpdate(tzcx);
		
		Symenu zhfa = new Symenu();
		zhfa.setId("zhfa");
		zhfa.setText("追号方案");
		zhfa.setSrc("/orderController.do?toAutoOrderList");
		zhfa.setSymenu(ddgl);
		zhfa.setSeq(BigDecimal.valueOf(0));
		menuDao.saveOrUpdate(zhfa);
	}
	
	private void repairMenuXtsz(Symenu xtsz) {
		Symenu zhzd = new Symenu();
		zhzd.setId("zhzd");
		zhzd.setText("账户字典");
		zhzd.setSrc("/dictController.do?accountDict");
		zhzd.setSymenu(xtsz);
		zhzd.setSeq(BigDecimal.valueOf(0));
		menuDao.saveOrUpdate(zhzd);
		
		Symenu yhzd = new Symenu();
		yhzd.setId("yhzd");
		yhzd.setText("用户字典");
		yhzd.setSrc("/dictController.do?userDict");
		yhzd.setSymenu(xtsz);
		yhzd.setSeq(BigDecimal.valueOf(1));
		menuDao.saveOrUpdate(yhzd);
		
		Symenu ddzd = new Symenu();
		ddzd.setId("ddzd");
		ddzd.setText("订单字典");
		ddzd.setSrc("/dictController.do?tmsDict");
		ddzd.setSymenu(xtsz);
		ddzd.setSeq(BigDecimal.valueOf(2));
		menuDao.saveOrUpdate(ddzd);
	
	}
	
	private void repairUser() {
		Syuser admin = new Syuser();
		admin.setId("0");
		admin.setName("admin");
		admin.setPassword(Encrypt.e("admin"));
		admin.setCreatedatetime(new Date());
		admin.setModifydatetime(admin.getCreatedatetime());
		userDao.saveOrUpdate(admin);
	}

}

package com.cm.scoreMall.http;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cm.scoreMall.http.bean.AwardProbability4Back;
import com.cm.scoreMall.http.bean.CommodityAndPro4Back;
import com.cm.scoreMall.http.bean.CommodityVo4Back;

public class TestCase {
	@Resource(name="scoreMallInterface")
	private IScoreMall4BackHttpService scoreMallInterface;
	private static ApplicationContext context;
	@Test
	public void testAdd() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		scoreMallInterface = (IScoreMall4BackHttpService)context.getBean("scoreMallInterface");
		CommodityVo4Back cm = new CommodityVo4Back();
		AwardProbability4Back aw = new AwardProbability4Back();
		AwardProbability4Back aw1 = new AwardProbability4Back();
		AwardProbability4Back aw2 = new AwardProbability4Back();
		AwardProbability4Back aw3 = new AwardProbability4Back();
		CommodityAndPro4Back cmp = new CommodityAndPro4Back();
		List<AwardProbability4Back> list = new ArrayList<AwardProbability4Back>();
//		cm.setCreateDate(new Date());
		cm.setPublishDate(new Date());
		cm.setBeginDate(new Date());
		cm.setEndDate(new Date());
		cm.setCommodityName("每日一刮2");
		cm.setPayNum(5);
		cm.setAwardNumMin(1);
		cm.setAwardNumMax(10);
		cm.setAwardType(0);
		cm.setState(0);
		cm.setPartakeNum(0);
		cm.setPartakeDateType(2);
		cm.setWeight(1);
		cm.setTermFlag(1);
		cm.setAccountCode("20141114115038083757");
		aw.setAwardNum(2);
		aw.setAwardProbability(35);
		aw1.setAwardNum(4);
		aw1.setAwardProbability(15);
		aw2.setAwardNum(6);
		aw2.setAwardProbability(20);
		aw3.setAwardNum(8);
		aw3.setAwardProbability(30);
		list.add(aw);
		list.add(aw1);
		list.add(aw2);
		list.add(aw3);
		cmp.setAwardProbability4BackList(list);
		cmp.setCommodityVo4Back(cm);
		scoreMallInterface.saveCommodityAndPro(cmp);
	}
	@Test
	public void testUpdate() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		scoreMallInterface = (IScoreMall4BackHttpService)context.getBean("scoreMallInterface");
		CommodityVo4Back cm = new CommodityVo4Back();
		AwardProbability4Back aw = new AwardProbability4Back();
		AwardProbability4Back aw1 = new AwardProbability4Back();
		CommodityAndPro4Back cmp = new CommodityAndPro4Back();
		List<AwardProbability4Back> list = new ArrayList<AwardProbability4Back>();
		cm.setCommodityCode("2014120401857376766349");
//		cm.setCreateDate(new Date());
		cm.setPublishDate(new Date());
		cm.setBeginDate(new Date());
		cm.setEndDate(new Date());
		cm.setCommodityName("福星高照");
		cm.setPayNum(5);
		cm.setAwardNumMin(1);
		cm.setAwardNumMax(10);
		cm.setAwardType(0);
		cm.setState(0);
		cm.setPartakeNum(0);
		cm.setPartakeDateType(2);
		cm.setWeight(1);
		cm.setTermFlag(1);
		cm.setAccountCode("");
		aw.setApCode("2014120401857377723051");
		aw.setAwardNum(10);
		aw.setAwardProbability(15);
		aw1.setApCode("2014120401857377752971");
		aw1.setAwardNum(12);
		aw1.setAwardProbability(10);
		list.add(aw);
		list.add(aw1);
		cmp.setAwardProbability4BackList(list);
		cmp.setCommodityVo4Back(cm);
		scoreMallInterface.updateCommodityAndPro(cmp);
	}
	@Test
	public void queryCommodityMsg4BackList() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		scoreMallInterface = (IScoreMall4BackHttpService)context.getBean("scoreMallInterface");
//		scoreMallInterface.queryCommodityLogMsg4BackList(1,, commodityLogVo4Back);
	}
	@Test
	public void queryCommoditVo4Back() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		scoreMallInterface = (IScoreMall4BackHttpService)context.getBean("scoreMallInterface");
		scoreMallInterface.queryCommoditVo4Back("2014120401906036167155");
	}
}

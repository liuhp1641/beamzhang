package com.cm.manage.util.base;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.vo.account.DrawVO;

public class CommonBusiUtil {

	//活动ID
	 public synchronized static String getActiveId(){
		 Date date = new Date();
		 String nowStr = DateUtil.format(date, "yyyyMMddHHmmss");
		 Random rd = new Random();
		 String randomStr = "";
		 for(int i=0;i<6;i++){
			 randomStr += rd.nextInt(10);
		 }
		 return nowStr + randomStr;
	 }
	//活动明细ID
	 public synchronized static String getActiveItemId(){
		 Date date = new Date();
		 String nowStr = DateUtil.format(date, "yyyyMMddHHmmss");
		 Random rd = new Random();
		 String randomStr = "";
		 for(int i=0;i<6;i++){
			 randomStr += rd.nextInt(10);
		 }
		 return nowStr + randomStr;
	 }
	 //任务ID 
	 public synchronized static String getTaskId(){
		 Date date = new Date();
		 String nowStr = DateUtil.format(date, "yyyyMMddHHmmss");
		 Random rd = new Random();
		 String randomStr = "";
		 for(int i=0;i<6;i++){
			 randomStr += rd.nextInt(10);
		 }
		 return nowStr + randomStr;
	 }
	 /***
	  * @describe 导出批次号码
	  * @return
	  */
	 public synchronized static String getExportNo(){
		 Date date = new Date();
		 String nowStr = DateUtil.format(date, "yyyyMMddHHmmss");
		 Random rd = new Random();
		 String randomStr = "";
		 for(int i=0;i<6;i++){
			 randomStr += rd.nextInt(10);
		 }
		 return nowStr + randomStr;
	 }
	 public static String formatAmount(Double d){
		 DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
		 df.applyPattern("#0.0#");
		 return df.format(d);
	 }
	 public static String formatAmountToInt(Double d){
		 DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
		 df.applyPattern("#");
		 return df.format(d);
	 }
	 public static double add(double v1, double v2) {
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			return b1.add(b2).doubleValue();
		}

		/**
		 * 提供精确的减法运算。
		 * @param v1 被减数
		 * @param v2 减数
		 * @return 两个参数的差
		 */

		public static double sub(double v1, double v2) {
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			return b1.subtract(b2).doubleValue();
		}    
		
		public static double mul(double v1,double v2) {
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			return b1.multiply(b2).doubleValue();
		}
		/***
		 * 
		 * @param rechargeTotal 充值金总额
		 * @param rechargeDrawTotal 已提出充值金
		 * @param bonusAmount 可用的奖金
		 * @return
		 */
		public static double getDrawBeforeLimit(double rechargeTotal,double rechargeDrawTotal,double bonusAmount){
			double ratio = 0.7;//比率
			double step = mul(ratio,rechargeTotal);
			step = add(step,bonusAmount);
			return sub(step,rechargeDrawTotal);
		}
		
		public static String getFunddingTypeName(Integer funddingType){
			if(funddingType == CommonConstants.FUNDINGTYPE_PRESIENT_FLAG){
				return CommonConstants.FUNDINGTYPE_PRESIENT_NAME;
			}else if(funddingType == CommonConstants.FUNDINGTYPE_SCORE_FLAG){
				return CommonConstants.FUNDINGTYPE_SCORE_NAME;
			}else if(funddingType == CommonConstants.FUNDINGTYPE_GOLD_FLAG){
				return CommonConstants.FUNDINGTYPE_GOLD_NAME;
			}else{
				return "";
			}
		}
		
		public static String getVipRankName(Integer vipRank){
			String rankName = "普通用户";
			if(vipRank != null && vipRank > 0){
				return "VIP" + vipRank.intValue();
			}else{
				return rankName;
			}
		}
		
		public static String getDrawStatusName(Integer status){
			if(status == CommonConstants.DRAW_FAIL){
				return "失败";
			}else if(status == CommonConstants.DRAW_SUCCESS){
				return "成功";
			}else if(status == CommonConstants.DRAW_TRANSFERING){
				return "转账中";
			}else if(status == CommonConstants.DRAW_WAIT_REVIEW){
				return "待审核";
			}else if(status == CommonConstants.DRAW_HAS_REVIEW){
				return "已审核";
			}else if(status == CommonConstants.DRAW_HAS_REJECT){
				return "已驳回";
			}
			return " ";
		}
		/***
		 * @describe 创建提现Excel数据
		 * @param drawMap
		 * @param workbook
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public static HSSFSheet buildDrawExcelData(Map<String,Object> drawMap,HSSFWorkbook workbook){
			HSSFSheet sheet = workbook.createSheet("提现");
			HSSFRow header0 = sheet.createRow(0);//第一行
			header0.createCell(0).setCellValue("#彩米后台系统账务明细查询");
			HSSFRow header1 = sheet.createRow(1);//第二行
			String accountNo = "";
			if(drawMap.get("accountNo") != null){
				accountNo = (String)drawMap.get("accountNo");
			}
			header1.createCell(0).setCellValue("#账号:[" + accountNo + "]");
			HSSFRow header2 = sheet.createRow(2);//第三行
			String createStartTime = formatDateString((String) drawMap.get("createStartTime"));
			String createEndTime = formatDateString((String) drawMap.get("createEndTime"));
			String acceptStartTime = formatDateString((String) drawMap.get("acceptStartTime"));
			String acceptEndTime = formatDateString((String) drawMap.get("acceptEndTime"));
			String dateDesc = "#申请时间(起始时间:[" + createStartTime + "]终止时间:[" + createEndTime + "])   " +
							  "处理时间(起始时间:[" + acceptStartTime + "]终止时间:[" + acceptEndTime + "])";
			header2.createCell(0).setCellValue(dateDesc);
			HSSFRow header3 = sheet.createRow(3);//第四行
			header3.createCell(0).setCellValue("#-----------------------------------------账务明细列表----------------------------------------");
			HSSFRow header4 = sheet.createRow(4);//第五行
			header4.createCell(0).setCellValue("账务流水号");
			header4.createCell(1).setCellValue("用户名（对方账号）");
			header4.createCell(2).setCellValue("用户等级");
			header4.createCell(3).setCellValue("姓名");
			header4.createCell(4).setCellValue("发起时间");
			header4.createCell(5).setCellValue("受理时间");
			header4.createCell(6).setCellValue("提现限额(元) ");
			header4.createCell(7).setCellValue("申请金额（元）");
			header4.createCell(8).setCellValue("应扣手续费(元) ");
			header4.createCell(9).setCellValue("实扣手续费（元）");
			header4.createCell(10).setCellValue("实际转账金额（元）");
			header4.createCell(11).setCellValue("状态 ");
			header4.createCell(12).setCellValue("处理描述 ");
			header4.createCell(13).setCellValue("操作员 ");
			header4.createCell(14).setCellValue("备注");
			List<DrawVO> drawVOList = (List<DrawVO>)drawMap.get("drawList");
			int rownum = 5;
			if(drawVOList != null && drawVOList.size() > 0){
				for(DrawVO drawVO : drawVOList){
					HSSFRow row = sheet.createRow(rownum++);
					row.createCell(0).setCellValue(drawVO.getOrderId());
					row.createCell(1).setCellValue(drawVO.getUserName());
					row.createCell(2).setCellValue(drawVO.getVipName());
					row.createCell(3).setCellValue(drawVO.getRealName());
					row.createCell(4).setCellValue(drawVO.getCreateTime());
					row.createCell(5).setCellValue(drawVO.getAcceptTime());
					row.createCell(6).setCellValue(drawVO.getDrawBeforeLimit());
					row.createCell(7).setCellValue(drawVO.getAmount());
					row.createCell(8).setCellValue(drawVO.getFee());
					row.createCell(9).setCellValue(drawVO.getRealFee());
					row.createCell(10).setCellValue(drawVO.getRealAmount());
					row.createCell(11).setCellValue(drawVO.getStatusName());
					row.createCell(12).setCellValue(drawVO.getErrorMsg());
					row.createCell(13).setCellValue(drawVO.getPeopleAccepted());
					row.createCell(14).setCellValue(drawVO.getMemo());
				}
			}
			HSSFRow nextRow = sheet.createRow(rownum++);
			nextRow.createCell(0).setCellValue("#-----------------------------------------账务明细列表结束------------------------------------");
			int totalNum = 0;
			Double totalAmount = 0.0;
			if(drawMap.get("totalNum") != null){
				totalNum = (Integer)drawMap.get("totalNum");
			}
			if(drawMap.get("totalAmount") != null){
				totalAmount = (Double) drawMap.get("totalAmount");
			}
			nextRow = sheet.createRow(rownum++);
			nextRow.createCell(0).setCellValue("#支出合计：" + totalNum + "笔，共" + totalAmount + "元");
			Date date = new Date();
			String now = DateUtil.format(date,"yyyy年MM月dd日 HH:mm:ss");
			nextRow = sheet.createRow(rownum++);
			nextRow.createCell(0).setCellValue("#导出时间：[" + now + "]");
			return sheet;
		}
		
		public static String formatDateString(String datestr){
			try{
				if(datestr != null && ! "".equals(datestr)){
					Date date = DateUtil.format(datestr);
					return DateUtil.format(date,"yyyy年MM月dd日 HH:mm:ss");
				}
				return datestr;
			}catch(Exception e){
				return datestr;
			}
		}
		
		public static void main(String[] args){
			Double d =0.5;
			System.out.println(formatAmount(d));
		}
	
}

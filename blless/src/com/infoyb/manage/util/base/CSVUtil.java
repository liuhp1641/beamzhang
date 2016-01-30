package com.cm.manage.util.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.cm.manage.util.alipay.AlipayBatchOrder;
import com.cm.manage.util.alipay.AlipayOrderItem;
import com.cm.manage.vo.account.DrawTransferVO;
public class CSVUtil {
	
	private static ResourceBundle bundle = java.util.ResourceBundle.getBundle("alipaydraw");
	private static Logger logger = Logger.getLogger(CSVUtil.class);
	

	/***
	 * @describe 生成批量付款到支付宝的csv文件
	 * @param fileName
	 * @param outputPath
	 * @param headListData
	 * @param dataArray
	 * @throws IOException
	 */
	 public static void createCSVFile(String fileName,String outputPath,List<String> headListData,List<DrawTransferVO> transferVOList) throws Exception{
		 File file = new File(outputPath + File.separator + fileName + ".csv");
		 if(! file.getParentFile().exists()){
			 file.getParentFile().mkdirs();
		 }
		 BufferedWriter bw = null;
		 try{
			 file.createNewFile();
			 if(file.exists()){
				 bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"GBK"));
				 //写入抬头：日期	总金额	总笔数	支付宝账号(Email)
				 bw.write(createCSVHeadStr(headListData));
				 //商户流水号	收款银行户名	收款银行账号	收款卡户银行	收款银行所在省份	收款银行所在市	收款支行名称	金额	对公对私标志	备注
				 //1	王建斌	6.22589E+15	招商银行	浙江省	杭州市	招商银行浙江支行	0.01	2	测试
				 //2	王建斌	6.22589E+15	中国工商银行	浙江省	杭州市	中国工商银行浙江支行	0.01	2	测试
				 bw.write(createCSVItemStr(transferVOList));
				 bw.flush();
			 }
		 }catch(Exception e){
			 logger.error(e);
			 throw e;
		 }finally{
			 if(bw != null){
				 bw.close();
			 }		
		 }				 
	 }
	 
//	 public static void createCSVFile(String fileName,String outputPath,List<String> headListData,List<DrawTransferVO> transferVOList) throws IOException{
//		 BufferedWriter bw = null;
//		 try{
//			 File file = new File(outputPath + File.separator + fileName + ".csv");
//			 if(! file.getParentFile().exists()){
//				 file.getParentFile().mkdirs();
//			 }
//			 
//			 file.createNewFile();
//			 if(file.exists()){
//				 bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"GBK"));
//				 //写入抬头：日期	总金额	总笔数	支付宝账号(Email)
//				 bw.write("\"日期\",\"总金额\",\"总笔数\",\"支付宝账号(Email)\"");
//				 bw.newLine();
//				 for(int i=0;i<headListData.size();i++){
//					 bw.write("\"" + headListData.get(i) + "\"");
//					 if(i != headListData.size() - 1){
//						 bw.write(",");
//					 }
//				 }
//				 bw.newLine();
//				 //商户流水号	收款银行户名	收款银行账号	收款卡户银行	收款银行所在省份	收款银行所在市	收款支行名称	金额	对公对私标志	备注
//				 //1	王建斌	6.22589E+15	招商银行	浙江省	杭州市	招商银行浙江支行	0.01	2	测试
//				 //2	王建斌	6.22589E+15	中国工商银行	浙江省	杭州市	中国工商银行浙江支行	0.01	2	测试
//				 bw.write("\"商户流水号\",\"收款银行户名\",\"收款银行账号\",\"收款卡户银行\",\"收款银行所在省份\",\"收款银行所在市\",\"收款支行名称\",\"金额\",\"对公对私标志\",\"备注\"");
//				 bw.newLine();
//				 DrawTransferVO vo = null;
//				 StringBuilder sb = null;
//				 if(transferVOList != null && ! transferVOList.isEmpty()){
//					 for(int i=0;i<transferVOList.size();i++){
//						 sb = new StringBuilder();
//						 vo = transferVOList.get(i);
//						 sb.append("\"");
//						 sb.append(vo.getOrderId());
//						 sb.append("\",\"");
//						 sb.append(vo.getRealName());
//						 sb.append("\",\"");
//						 sb.append(vo.getBankCardNo());
//						 sb.append("\",\"");
//						 sb.append(vo.getBankName());
//						 sb.append("\",\"");
//						 sb.append(vo.getProvince());
//						 sb.append("\",\"");
//						 sb.append(vo.getCity());
//						 sb.append("\",\"");
//						 sb.append(vo.getSubBankName());
//						 sb.append("\",\"");
//						 sb.append(CommonUtil.formatDouble(vo.getAmount(),"#.00"));
//						 sb.append("\",\"");
//						 sb.append(vo.getCustomerType());
//						 sb.append("\",\"");
//						 sb.append(vo.getNotes());
//						 sb.append("\"");
//						 bw.write(sb.toString());
//						 if(i != transferVOList.size() - 1){
//							 bw.newLine();
//						 }
//					 }
//				 }
//				 bw.flush();
//			 }
//		 }catch(IOException e){
//			 logger.error(e);
//			 throw e;
//		 }finally{
//			 if(bw != null){
//				 bw.close();
//			 }	
//		 }
//	 }
	 
	 public static String createCSVHeadStr(List<String> headListData){
		 StringBuilder sb = new StringBuilder("\"日期\",\"总金额\",\"总笔数\",\"支付宝账号(Email)\"");
		 sb.append("\n");
		 for(int i=0;i<headListData.size();i++){
			 sb.append("\"" + headListData.get(i) + "\"");
			 if(i != headListData.size() - 1){
				 sb.append(",");
			 }
		 }
		 sb.append("\n");
		 return sb.toString();
	 }
	 public static String createCSVItemStr(List<DrawTransferVO> transferVOList){
		 StringBuilder sb = new StringBuilder();
		 
		 sb.append("\"商户流水号\",\"收款银行户名\",\"收款银行账号\",\"收款卡户银行\",\"收款银行所在省份\",\"收款银行所在市\",\"收款支行名称\",\"金额\",\"对公对私标志\",\"备注\"");
		 sb.append("\n");
		 DrawTransferVO vo = null;
		 if(transferVOList != null && ! transferVOList.isEmpty()){
			 for(int i=0;i<transferVOList.size();i++){
				 vo = transferVOList.get(i);
				 sb.append("\"");
				 sb.append(vo.getOrderId());
				 sb.append("\",\"");
				 sb.append(vo.getRealName());
				 sb.append("\",\"");
				 sb.append(vo.getBankCardNo());
				 sb.append("\",\"");
				 sb.append(vo.getBankName());
				 sb.append("\",\"");
				 sb.append(vo.getProvince());
				 sb.append("\",\"");
				 sb.append(vo.getCity());
				 sb.append("\",\"");
				 sb.append(vo.getSubBankName());
				 sb.append("\",\"");
				 sb.append(vo.getAmount());
				 sb.append("\",\"");
				 sb.append(vo.getCustomerType());
				 sb.append("\",\"");
				 sb.append(vo.getNotes());
				 sb.append("\"");
				 if(i != transferVOList.size() - 1){
					 sb.append("\n");
				 }
			 }
		 }
		 return sb.toString();
	 }
		
	 
	 public static List<String> parseCSVFile(String fileName) throws Exception{
		 if(fileName == null || fileName.lastIndexOf(".csv") < 0){
			 throw new BusiException("",fileName + "支付结果文件格式不正确");
		 }
		 File csvFile = new File(fileName);
		 if(! csvFile.exists()){
			 throw new BusiException("",fileName + "支付结果文件不存在");
		 }
		 BufferedReader br = null;
		 List<String> resultList = new ArrayList<String>();
		 try{
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile),"GBK"));
			 String line = null;
			 while((line = br.readLine()) != null){
				 if(line.trim().equals("")){
					 continue;
				 }
				 resultList.add(line);
			 }
		 }catch(Exception e){
			 logger.error(e);
			 throw e;
		 }finally{
			 if(br != null){
				 br.close();
			 }
		 }
		return resultList;
		 
	 }
	 
	 public static AlipayBatchOrder getBatchOrderFromList(List<String> resultList) throws Exception{
		 if(resultList != null && resultList.size() > 0){
			 String batchOrderResult = trim(resultList.get(1));
			 String[] temp = batchOrderResult.split(",");
			 if(temp == null || temp.length < 8){
				 throw new BusiException("","支付宝返回的支付结果文件中头部数据不全");
			 }
			 if(! isValidDate(trim(temp[0])) || ! isValidDate(trim(temp[1]))){
				 throw new BusiException("","支付宝返回的支付结果文件中头部日期格式错误");
			 }
			 if(! isNumber(trim(temp[2])) || ! isNumber(trim(temp[3])) || ! isNumber(trim(temp[4]))){
				 throw new BusiException("","支付宝返回的支付结果文件中交易记录数格式错误");
			 }
			 if(! isDouble(trim(temp[5])) || !isDouble(trim(temp[6]))){
				 throw new BusiException("","支付宝返回的支付结果文件中交易金额格式错误");
			 }
			 AlipayBatchOrder abo = new AlipayBatchOrder();
			 abo.setPaymentDate(trim(temp[0]));
			 abo.setProcessDate(trim(temp[1]));
			 abo.setTotalNumber(Integer.parseInt(trim(temp[2])));
			 abo.setSuccessNumber(Integer.parseInt(trim(temp[3])));
			 abo.setFailNumber(Integer.parseInt(trim(temp[4])));
			 abo.setSuccessAmount(Double.valueOf(trim(temp[5])));
			 abo.setFailAmount(Double.valueOf(trim(temp[6])));
			 abo.setPayAccountNo(trim(temp[7]));
			 String result = "";
			 AlipayOrderItem item = null;
			 for(int i=3;i<resultList.size();i++){
				 result = trim(resultList.get(i));
				 if(result == null || result.length() < 13){
					 throw new BusiException("","支付宝返回的支付结果文件中交易记录数据信息不全");
				 }
				 String[] record = result.split(",");
				 String amount = trim(record[6]);
				 if(! isDouble(amount)){
					 throw new BusiException("","支付宝返回的支付结果文件中交易条目中金额格式错误");
				 }
				 item = new AlipayOrderItem();
				 item.setRealName(trim(record[0]));
				 item.setBankCardNo(trim(record[1]));
				 item.setBankName(trim(record[2]));
				 item.setBankProvince(trim(record[3]));
				 item.setBankCity(trim(record[4]));
				 item.setSubBankName(trim(record[5]));
				 item.setAmount(amount);
				 item.setCustomerType(trim(record[7]));
				 item.setStatus(trim(record[8]));
				 item.setInstructionId(trim(record[9]));
				 item.setBankFlag(trim(record[10]));
				 item.setDealMemo(trim(record[11]));
				 item.setUserMemo(trim(record[12]));
				 abo.getOrderItem().add(item);
			 }
			 return abo;
		 }
		 return null;
	 }
	 
	 public static boolean isNumber(String str) {
			String regex = "^[0-9]+$";
			return str.matches(regex);
		}
	 public static boolean isDouble(String str) {
		 try{
			 double d = Double.parseDouble(str);
			 if(d < 0){
				 return false;
			 }
			 return true;
		 }catch(Exception e){
			 return false;
		 }
	 }

	 /**
	     * 检查传入的字符串时否符合YYYYMMDD的日期格式。
	     * @param aString 需要检查的字符串。
	     * @return 检查结果，true:正确   false:不正确
	     */
	    public static boolean isValidDate(String aString) {
	        boolean tResult = false;

	        if (aString.length() != 8)
	            return false;
	        try {
	            int tYYYY  = Integer.parseInt(aString.substring(0,  4));
	            int tMM    = Integer.parseInt(aString.substring(4,  6));
	            int tDD    = Integer.parseInt(aString.substring(6,  8));
	            if ((tMM < 1) || (tMM > 12))
	                return false;
	            if ((tDD < 1) || (tDD > 31))
	                return false;
	            tResult = true;
	        }
	        catch (Exception e) {
	            System.out.println(e);
	        }
	        return tResult;
	    }
	 

	 public synchronized static String getBatchNo(){
		 Date date = new Date();
		 String nowStr = DateUtil.format(date, "yyyyMMddHHmmss");
		 Random rd = new Random();
		 String randomStr = "";
		 for(int i=0;i<6;i++){
			 randomStr += rd.nextInt(10);
		 }
		 return nowStr + randomStr;
	 }
	 
	 public static String trim(String str){
		 if(str == null){
			 return "";
		 }
		 return str.trim();
	 }
	 
	 public static String getPropertyValue(String propertyName){
		String value =  bundle.getString(propertyName);
		if(value == null){
			return "";
		}
		return value;
	}
	 
	 public static void main(String[] args) throws IOException {  
		System.out.println("OK"); 
	  }  
}

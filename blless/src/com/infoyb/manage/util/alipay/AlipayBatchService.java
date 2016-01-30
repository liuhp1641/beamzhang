package com.cm.manage.util.alipay;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.util.alipay.config.AlipayConfig;
import com.cm.manage.util.alipay.util.AlipayCore;
import com.cm.manage.util.alipay.util.AlipaySubmit;
import com.cm.manage.util.base.BusiException;
import com.cm.manage.util.base.ZipUtil;


public class AlipayBatchService {
	/***
	 * @describe 上传批量支付文件到支付宝
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String batchFileToAlipayReq(String filePath) throws Exception{
		String partner = AlipayConfig.partner;
		//文件完整路径
		String bptb_pay_file = new String(filePath.getBytes("ISO-8859-1"),"UTF-8");
		//文件摘要
		String digest_bptb_pay_file = AlipayCore.getAbstract(bptb_pay_file,AlipayConfig.sign_type);
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "bptb_pay_file_confirm");
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("file_digest_type", AlipayConfig.sign_type);
		sParaTemp.put("digest_bptb_pay_file", digest_bptb_pay_file);
		sParaTemp.put("bussiness_type", CommonConstants.BUSSINESS_TYPE_T0);//当天到账
		
		String res = AlipaySubmit.buildRequest("bptb_pay_file", bptb_pay_file, sParaTemp);
		
		return res;
	}
	/***
	 * @throws UnsupportedEncodingException 
	 * @describe 发送复核文件请求到支付宝
	 */
	public static String reViewFileToAlipayReq(String fileName) throws Exception{
		//付款人支付宝账户
		String account = new String(AlipayConfig.account.getBytes("ISO-8859-1"),"UTF-8");
		String file_name = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "bptb_user_confirm");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
//		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("email", account);
		sParaTemp.put("file_name", file_name);
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		return sHtmlText;
		
	}
	/****
	 * @describe 支付宝批量支付无密
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String batchFileToAlipayNoPass(String filePath) throws Exception{
		String partner = AlipayConfig.partner;
		//文件完整路径
		String bptb_pay_file = new String(filePath.getBytes("ISO-8859-1"),"UTF-8");
		//文件摘要
		String digest_bptb_pay_file = AlipayCore.getAbstract(bptb_pay_file,AlipayConfig.sign_type);
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "bptb_pay_file");
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("file_digest_type", AlipayConfig.sign_type);
		sParaTemp.put("digest_bptb_pay_file", digest_bptb_pay_file);
		
		String res = AlipaySubmit.buildRequest("bptb_pay_file", bptb_pay_file, sParaTemp);
		
		return res;
	}
	
	/***
	 * @describe 向支付宝发送明细查询请求
	 */
	public static String queryBatchDetail(Map<String,String> params) throws Exception{
		String fileName = params.get("file_name");
		String serialNo = params.get("serial_no");
		String status = params.get("status");
		String bankAccountNo = params.get("bank_account_no");
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.batchpay.bptb.detail.query");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("file_name", fileName);
		if(serialNo != null && ! "".equals(serialNo)){
			sParaTemp.put("serial_no", serialNo);
		}else{
			sParaTemp.put("status", status);
			sParaTemp.put("bank_account_no", bankAccountNo);
		}		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);
		return sHtmlText;
	}
	/***
	 * @describe 下载支付结果文件
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String getResultFile(String fileName) throws Exception{
		String file_name = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
		String preName = file_name.substring(0, file_name.lastIndexOf(CommonConstants.TRANSFER_FILE_CSV_SUFFIX));
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "bptb_result_file");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("file_name", file_name);
		Map<String,Object> res = AlipaySubmit.buildRequestNew("","",sParaTemp);
		if(res == null || res.size() == 0){
			throw new BusiException("","文件下载请求失败");
		}
		Object resValue = (Object) res.get("fail");
		if(resValue == null){
			byte[] resultBytes = (byte[])res.get("success");
			String destName = AlipayConfig.payment_file_directory + File.separator + preName + CommonConstants.PAYMENT_RESULTFILE_ZIP_SUFFIX;
			ZipUtil.createFileFromByteArray(resultBytes, destName);
			return "fileOK";
		}else{
			return (String)resValue;
		}
		
	}
	/***
	 * @describe 查看上传文件是否成功
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String queryPayFile(String fileName) throws Exception{
		String file_name = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "bptb_file_query");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("file_name", file_name);
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);
		return sHtmlText;
	}
	
	public static AlipayOrder test() throws Exception{
		String file_name = "20141023181609717952.csv";
		Map<String,String> params = new HashMap<String,String>();
		params.put("file_name", file_name);
		AlipayOrder order = new AlipayOrder();
		String xml = queryBatchDetail(params);
		order.fromXml(xml);
		return order;
	}
	public static void main(String[] args) throws Exception{
		String file_name = "20141025152538724306.csv";
		Map<String,String> params = new HashMap<String,String>();
		params.put("file_name", file_name);
		AlipayOrder order = new AlipayOrder();
		String xml = queryBatchDetail(params);
		order.fromXml(xml);
		System.out.println(xml);
		/*String res = getResultFile(file_name);
		if("fileOK".equals(res)){
			String batchNo = file_name.substring(0, file_name.lastIndexOf(CommonConstants.TRANSFER_FILE_CSV_SUFFIX));
			String destName = AlipayConfig.payment_file_directory + File.separator + batchNo + CommonConstants.PAYMENT_RESULTFILE_ZIP_SUFFIX;
			String outputDirectory = "e:/test/sunjinfu";
			ZipUtil.unzip(destName, outputDirectory);
		}*/
	}

}

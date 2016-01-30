
package com.cm.manage.util.alipay;

import java.util.ArrayList;

/**
 * 商户端接口软件包工具类，代表XML文件，负责XML报文的相关处理。
 */
public class XMLDocument {
    /**
     * XML字符串
     */
    private String iXMLString = "";

    /**
     * 构造XMLDocument对象。
     */
    public XMLDocument() {
    }

    /**
     * 构造XMLDocument对象，并给定初始对象的XML字符串。
     */
    public XMLDocument(String aXMLString) {
        iXMLString = aXMLString;
    }

    /**
     * 回传XML字符串
     * @return XML字符串
     */
    public String toString() {
        return iXMLString;
    }

    /**
     * 回传XML文件中指定域的值
     * @param aTag 域名
     * @return 指定域的值
     */
    public XMLDocument getValue(String aTag) {
        XMLDocument tXMLDocument = null;
        int tStartIndex = iXMLString.indexOf("<" + aTag.trim() + ">");
        int tEndIndex = iXMLString.indexOf("</" + aTag.trim() + ">");
        if ((tStartIndex >= 0) && (tEndIndex >= 0) && (tStartIndex < tEndIndex))
            tXMLDocument = new XMLDocument(iXMLString.substring(tStartIndex + aTag.length() + 2, tEndIndex));
        return tXMLDocument;
    }

    /**
     * 回传XML文件中指定域的值
     * @param aTag 域名
     * @return 指定域的值，若找不到该域则回传空字符串。
     */
    public String getValueNoNull(String aTag) {
        String tValue = "";
        XMLDocument tXML = getValue(aTag);
        if (tXML != null)
            tValue = tXML.toString();
        return tValue.replaceAll("\r", "").replaceAll("\n", "");
    }

    /**
     * 回传XML文件中指定域名的集合
     * @param aTag 域名
     * @return XMLDocument对象的ArrayList集合。
     */
    public ArrayList getDocuments(String aTag) {
        String tXMLString = iXMLString;
        ArrayList tValues = new ArrayList();

        while (true) {
            XMLDocument tXMLDocument = null;
            int tStartIndex = tXMLString.indexOf("<" + aTag.trim() + ">");
            int tEndIndex = tXMLString.indexOf("</" + aTag.trim() + ">");
            if ((tStartIndex == -1) || (tEndIndex == -1) || (tStartIndex > tEndIndex))
                break;
            tXMLDocument = new XMLDocument(tXMLString.substring(tStartIndex, tEndIndex + aTag.length() + 3));
            tValues.add(tXMLDocument);
            tXMLString = tXMLString.substring(tEndIndex + 1);
        }
        return tValues;
    }

    /**
     * 回传经过格式化排版的XML文件
     * @param  aTag 域名
     * @return 经过格式化排版的XML文件
     */
    public XMLDocument getFormatDocument(String aSpace) {
        return getFormatDocument(0, aSpace);
    }
    
    /**
     * 回传经过格式化排版的XML文件
     * @param  aTag 域名
     * @return 经过格式化排版的XML文件
     * added by qidp 2012-6-25 默认以空格缩进
     */
    public XMLDocument getFormatDocument() {
        return getFormatDocument(0, "  ");
    }

    
    /**
     * 回传经过格式化排版的XML文件
     * @param  aTag 域名
     * @return 经过格式化排版的XML文件
     */
    private XMLDocument getFormatDocument(int aLevel, String aSpace) {
        String tSpace1 = aSpace;
        for (int i = 0; i < aLevel; i++)
            tSpace1 += aSpace;
        String tTagName = getFirstTagName();
        if (tTagName == null)
            return this;
        String tXMLString = "\n";
        XMLDocument tXMLDocument = new XMLDocument(iXMLString);
        while ((tTagName = tXMLDocument.getFirstTagName()) != null) {
            XMLDocument tTemp = tXMLDocument.getValue(tTagName);
            String tSpace = "";
            if (tTemp.getFirstTagName() != null)
                tSpace = tSpace1;
            tXMLString = tXMLString + tSpace1 + "<" + tTagName + ">" + tTemp.getFormatDocument(aLevel + 1, aSpace) + tSpace + "</" + tTagName + ">\n";
            tXMLDocument = tXMLDocument.deleteFirstTagDocument();
        }
        return new XMLDocument(tXMLString);
    }

    /**
     * 回传XML文件的第一个Tag名称
     * @return String Tag名称
     */
    public String getFirstTagName() {
        String tTagName = null;
        int tStartIndex = iXMLString.indexOf('<');
        int tEndIndex   = iXMLString.indexOf('>');
        if (tEndIndex > tStartIndex)
            tTagName = iXMLString.substring(tStartIndex + 1, tEndIndex);
        return tTagName;
    }

    /**
     * 删除XML文件中第一个Tab的资料段
     * @return String Tag名称
     */
    public XMLDocument deleteFirstTagDocument() {
        String tTagName = this.getFirstTagName();
        int tStartIndex = iXMLString.indexOf("<" + tTagName + ">");
        int tEndIndex   = iXMLString.indexOf("</" + tTagName + ">");
        if (tEndIndex > tStartIndex)
            iXMLString = iXMLString.substring(tEndIndex + tTagName.length() + 3);
        return this;
    }

    public static void main(String[] argc) {
//        XMLDocument tXMLDocument = new XMLDocument("<Message><Merchant><ECMerchantType>B2C</ECMerchantType><MerchantID>123456789012345</MerchantID></Merchant><TrxRequest><TrxType>PayReq</TrxType><Order><OrderNo>ON200306300001</OrderNo><OrderAmount>280.0</OrderAmount><OrderDesc>Game Card Order</OrderDesc><OrderDate>2003/11/12</OrderDate><OrderTime>23:55:30</OrderTime><OrderURL>http://127.0.0.1/Merchant/MerchantQueryOrder.jsp?ON=ON200306300001&QueryType=1</OrderURL><OrderItems><OrderItem><ProductID>IP000001</ProductID><ProductName>中国移动IP卡</ProductName><UnitPrice>100.0</UnitPrice><Qty>1</Qty></OrderItem><OrderItem><ProductID>IP000002</ProductID><ProductName>网通IP卡</ProductName><UnitPrice>90.0</UnitPrice><Qty>2</Qty></OrderItem></OrderItems></Order><ProductType>0</ProductType><ResultNotifyURL>http://127.0.0.1/Merchant/MerchantResult.jsp</ResultNotifyURL><MerchantRemarks>Hi!</MerchantRemarks></TrxRequest></Message><Signature-Algorithm>SHA1withRSA</Signature-Algorithm><Signature>PFCTbaHQ/xml2wOtI1u2/q9UV4IwKW5C2Cc89TRRUW0l5BMK21qgrpltFlOTDrZ88r69J9j9oTtQDp+ySEU9ep/diiPTHRl0+N6OFcXp+SiTcn78DlUYAWmobSDziLnZcQu903kxU2mJvBX39Ua1YsQPuHv5z9LPKdwLJhtNuqA=</Signature>");
        XMLDocument tXMLDocument = new XMLDocument("<Loops><Loop><name>name1</name><age>age1</age></Loop><Loop><name>name2</name><age>age2</age></Loop></Loops>");
        ArrayList sons=tXMLDocument.getDocuments("Loop");
        System.out.println(sons);
        String newline = System.getProperty("line.separator");
        System.out.println("========"+newline+"=======");
//        System.out.println("Formated\n" + tXMLDocument.getFormatDocument("    ").toString());
    }
}

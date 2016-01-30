package com.cm.message.http;


import com.cm.message.http.bean.*;

import java.util.List;

public interface IMessageManagesHttpService {

    /**
     * 消息模板列表
     * @return
     */
    public List<MessageTemplateBean> getMessageTemplateList();

    /**
     * 消息类型
     * @return
     */
    public List<MessageTypeBean> getMessageTypeList();

    /**
     * 更新消息类型优先级
     * @param messageTypeBean
     * @return
     */
    public boolean updateMessageType(MessageTypeBean messageTypeBean);

    /**
     * 系统消息列表
     * @param messageInfoBean
     * @param page
     * @param pageSize
     * @return
     */
    public HttpPageBean<MessageInfoBean> getMessageInfoList(MessageInfoBean messageInfoBean, Integer page, Integer pageSize);

    /**
     * 保存定制消息
     * @param messageCustomInfoBean
     * @return
     */
    public boolean saveCustomMessage(MessageCustomInfoBean messageCustomInfoBean);

    /**
     * 编辑定制消息
     * @param messageCustomInfoBean
     * @return
     */
    public boolean editCustomMessage(MessageCustomInfoBean messageCustomInfoBean);


    /**
     * 发送消息
     * @param messageCustomInfoBean
     *  messageCode  消息编码
     *  quartzTime  定时时间
     *
     * @return
     */
    public boolean sendMessage(MessageCustomInfoBean messageCustomInfoBean);

    /**
     * 再次发送
     * @param messageCode 消息编码
     * @return
     */
    public boolean reSendMessage(String messageCode);

    /**
     * 推送消息列表
     * @param messageInfoQueryBean
     * @param page
     * @param pageSize
     * @return
     */
    public HttpPageBean<MessageCustomInfoBean> getMessageInfoList(MessageInfoQueryBean messageInfoQueryBean, Integer page, Integer pageSize);
}

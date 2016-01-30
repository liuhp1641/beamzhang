package com.cm.manage.jms.producer;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Map;

@Component
public class DrawMessageProducer {

    private Logger logger = Logger.getLogger(getClass());


    @Resource(name = "draw_topic_msg")
    private Destination destination;

    @Resource(name = "drawJmsTemplate")
    private JmsTemplate drawJmsTemplate;

    public void sendMessage(final String action, final Map<String, Object> paramMap) {

        drawJmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("action", action);
                message.setObject("draw", paramMap);
                return message;
            }
        });
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public JmsTemplate getDrawJmsTemplate() {
        return drawJmsTemplate;
    }

    public void setDrawJmsTemplate(JmsTemplate drawJmsTemplate) {
        this.drawJmsTemplate = drawJmsTemplate;
    }
}

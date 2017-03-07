/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 BeiJing Baidu Netcom Science Technology Co., Ltd
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.baidu.cloud.kafka;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;

class Producer {

    static void run(String topic, int numOfRecords) throws IOException {
        String accountId = topic.split("__")[0];

        Properties properties = new Properties();
        properties.load(Consumer.class.getClassLoader().getResourceAsStream("client.properties"));
        properties.setProperty(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "kafka.bj.baidubce.com:9091");
        properties.setProperty(CommonClientConfigs.CLIENT_ID_CONFIG, accountId + "kafka-samples-java-producer");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "1");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        try {
            for (int i = 0; i < numOfRecords; i++) {
                String key = String.valueOf(i);
                String value = i + "-hello kafka";
                ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);
                producer.send(record);
            }
        } finally {
            producer.close();
        }
    }
}

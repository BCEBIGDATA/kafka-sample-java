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
import org.apache.kafka.common.serialization.ByteArraySerializer;

import java.io.IOException;
import java.util.Properties;

class Producer {

    static void run(String topic, int numOfRecords) throws IOException {
        Properties properties = new Properties();
        properties.load(Consumer.class.getClassLoader().getResourceAsStream("client.properties"));
        properties.setProperty(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "kafka.bj.baidubce.com:9091");
        properties.setProperty(CommonClientConfigs.CLIENT_ID_CONFIG, "kafka-samples-java-producer");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());

        KafkaProducer<byte[], byte[]> producer = new KafkaProducer<byte[], byte[]>(properties);

        for (int i = 0; i < numOfRecords; i++) {
            byte[] key = Integer.toString(i).getBytes("UTF-8");
            byte[] value = "hello, kafka".getBytes("UTF-8");
            ProducerRecord<byte[], byte[]> record = new ProducerRecord<byte[], byte[]>(topic, key, value);
            producer.send(record);
        }

        producer.close();
    }
}

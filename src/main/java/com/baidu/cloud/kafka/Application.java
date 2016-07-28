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

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Application {

    private static final int NUM_OF_RECORD = 10;

    public static void main(String[] args) throws IOException {
        if (args.length < 1 || "".equals(args[0])) {
            System.out.println("argument topic_name missing");
            System.out.println("");
            System.out.println("Usage: run.bat/run.sh topic_name, where topic_name looks like 'prefix__topic'");
            System.out.println("Please follow guide on https://cloud.baidu.com/product/kafka.html to: ");
            System.out.println("  1. Create/Get client.keystore.jks, client.truststore.jks files.");
            System.out.println("  2. Create/Get client.properties file.");
            System.out.println("  3. Create/Get topic_name.");
            return;
        }

        if (new File("client.keystore.jks").length() == 0 || new File("client.truststore.jks").length() == 0) {
            System.out.println("Please replace *.jks with your own.");
            return;
        }

        Properties properties = new Properties();
        properties.load(Application.class.getClassLoader().getResourceAsStream("client.properties"));
        if ("your_keystore_password".equals(properties.getProperty("ssl.keystore.password"))) {
            System.out.println("Please replace resources/client.properties with your own.");
            return;
        }

        String topic = args[0];
        Producer.run(topic, NUM_OF_RECORD);
        Consumer.run(topic, NUM_OF_RECORD);
    }
}

package com.cxk.storm;

import org.apache.storm.topology.OutputFieldsDeclarer;
import java.util.Map;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestWordSpout extends BaseRichSpout {
    public static Logger LOG = LoggerFactory.getLogger(TestWordSpout.class);
    SpoutOutputCollector _collector;

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        _collector = collector;
    }

    public void nextTuple() {

        String line="192.168.1.57  8.348 [21/Mar/2018:15:28:58 +0800] \"POST /CHECK/getCheckIllegalCase.do HTTP/1.1\" 200 ";
        String json="{\n" +
                "\t\"score\": \"10\",\n" +
                "\t\"signname\": \"张三\",\n" +
                "\t\"desc\": \"描述xxxxxxxxo\",\n" +
                "\t\"filepath\": \"c:/a/b/c/a.txt\",\n" +
                "\t\"size\": \"110KB\"\n" +
                "}";
      //  final Random rand = new Random();
       // final String word = words[rand.nextInt(words.length)];
            for(int i=0;i<5;i++){
                _collector.emit(new Values(json));
            }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("line"));
    }
    @Override
    public void ack(Object msgId) {
        System.out.println("任务"+msgId+"完成");
    }
}
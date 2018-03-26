package com.cxk.storm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.JDBCUtil;


public class PrintBolt extends BaseRichBolt {
    private static Logger logger = LoggerFactory.getLogger(PrintBolt.class);
    OutputCollector _collector;

    public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
        _collector = collector;
    }

    public void execute(Tuple tuple) {
        logger.error("xxxxt"); logger.info("xxxxt");
        try {
            String json=tuple.getString(0);
            JSONObject obj=   JSONObject.parseObject(json);
            String  score=obj.getString("score");
            String  signname=obj.getString("signname");
            String  desc=obj.getString("desc");
            String  filepath=obj.getString("filepath");
            String  size=obj.getString("size");
            logger.error("xxxx"); logger.info("xxxx");
            create(score,filepath);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("",e);
        }
        _collector.ack(tuple);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }


    public static void create(String score,String filepath) throws SQLException{
        Connection conn = JDBCUtil.getInstance().getConnection();
        String sql = "insert into test(score,filepath)values(?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,score);
        ps.setString(2,filepath);

        ps.executeUpdate();
    }



}
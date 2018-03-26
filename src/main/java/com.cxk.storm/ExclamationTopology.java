package com.cxk.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.utils.Utils;

public class ExclamationTopology {
    public static void main(String[] args) throws Exception {
        TopologyBuilder builder = new TopologyBuilder();


        // 第一个参数是id 第二个是包含处理逻辑的对象(spout或者bolt), 第三个参数是你所需要的并行度
      //  builder.setSpout("word", new TestWordSpout(), 1);
        builder.setSpout("word", new TestWordSpout(), 1);
        builder.setBolt("addGTH", new ExclamationBolt(), 1).shuffleGrouping("word");
        builder.setBolt("print", new PrintBolt(), 1).shuffleGrouping("addGTH");

        Config conf = new Config();
        conf.setDebug(true);

        if (args != null && args.length > 0) {
            conf.setNumWorkers(1);


            StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
        }
        else {

            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("test3", conf, builder.createTopology());
            Utils.sleep(20000);
            cluster.killTopology("test3");
            cluster.shutdown();
        }
    }
}

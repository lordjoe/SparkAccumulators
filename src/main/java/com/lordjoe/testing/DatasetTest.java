package com.lordjoe.testing;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SQLContext;
import scala.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This code creates a list of objects containing MyBean -
 * a Jaba Bean containing one field which is not bean
 */
public class DatasetTest {
    public static final Random RND = new Random();
    public static final int LIST_SIZE = 100;

    public static String makeName() {
        return Integer.toString(RND.nextInt());
    }

    public static MyUnBean makeUnBean() {
        return new MyUnBean(RND.nextInt(), makeName());
    }

    public static MyBean makeBean() {
        return new MyBean(RND.nextInt(), makeName(), makeUnBean());
    }

    /**
     * Make a list of MyBeans
     * @return
     */
    public static List<MyBean> makeBeanList() {
        List<MyBean> holder = new ArrayList<MyBean>();
        for (int i = 0; i < LIST_SIZE; i++) {
            holder.add(makeBean());
        }
        return holder;
    }

    public static SQLContext getSqlContext() {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("BeanTest") ;
        Option<String> option = sparkConf.getOption("spark.master");
        if (!option.isDefined())    // use local over nothing
            sparkConf.setMaster("local[*]");
        JavaSparkContext ctx = new JavaSparkContext(sparkConf) ;
        return new SQLContext(ctx);
    }


    public static void main(String[] args) {
        SQLContext sqlContext = getSqlContext();

        Encoder<MyBean> evidence = Encoders.bean(MyBean.class);
        Encoder<MyUnBean> evidence2 = Encoders.javaSerialization(MyUnBean.class);

        List<MyBean> holder = makeBeanList();
        Dataset<MyBean> beanSet  = sqlContext.createDataset( holder, evidence);

        long count = beanSet.count();
        if(count != LIST_SIZE)
            throw new IllegalStateException("bad count");

    }



}

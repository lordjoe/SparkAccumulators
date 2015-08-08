package com.lordjoe.distributed.spark.accumulators;

import org.apache.spark.api.java.function.*;
import scala.*;

import java.io.Serializable;

/**
 * org.apache.spark.api.java.function.AbstraceLoggingFunction
 * superclass for defined functions that will log on first call making it easier to see
 * do work in doCall
 * User: Steve
 * Date: 10/23/2014
 */
public abstract class AbstractLoggingPairFunction<T extends Serializable, K extends Serializable, V extends Serializable>
        extends AbstractLoggingFunctionBase implements PairFunction<T, K, V> {


    /**
     * NOTE override doCall not this
     *
     * @param t
     * @return
     */
    @Override
    public final Tuple2<K, V> call(final T t) throws Exception {
        reportCalls();
        long startTime = System.nanoTime();
        Tuple2<K, V> ret = doCall(t);
        long estimatedTime = System.nanoTime() - startTime;
        incrementAccumulatedTime(estimatedTime);
         return ret;
    }

    /**
     * do work here
     *
     * @param v1
     * @return
     */

    public abstract Tuple2<K, V> doCall(final T t) throws Exception;

}
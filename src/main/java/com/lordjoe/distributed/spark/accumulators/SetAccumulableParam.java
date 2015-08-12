package com.lordjoe.distributed.spark.accumulators;

import org.apache.spark.*;

import java.io.*;
import java.util.*;

/**
* com.lordjoe.distributed.spark.accumulators.Set<String>AccumulableParam
 * Usage
 *      Accumulator<Set<String>> myCount = context.accumulator(0L,"MyAccumulatorName",Set<String>AccumulableParam.INSTANCE);
 *      myCount.add(1L);
* User: Steve
* Date: 11/12/2014
*/
public class SetAccumulableParam implements AccumulatorParam<Set<String>>,Serializable {
    public static final SetAccumulableParam INSTANCE = new SetAccumulableParam();
    private SetAccumulableParam() {}
     @Override
    public Set<String> addAccumulator(final Set<String> r, final Set<String> t) {
         HashSet<String> ret = new HashSet<String>(r);
         ret.addAll(t);
        return ret;
    }
     @Override
    public Set<String> addInPlace(final Set<String> r1, final Set<String> r2) {
        return  addAccumulator(r1,r2);
    }
     @Override
    public Set<String> zero(final Set<String> initialValue) {
        return initialValue;
    }
}

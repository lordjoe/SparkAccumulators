package com.lordjoe.distributed.spark.accumulators;

import org.apache.spark.*;

import java.io.*;
import java.util.*;

/**
 * com.lordjoe.distributed.spark.accumulators.ISparkAccumulators
 * User: Steve
 * Date: 8/7/2015
 */
public interface ISparkAccumulators extends Serializable {


    /**
     * return all registerd aaccumlators
     *
     * @return
     */
    public List<String> getAccumulatorNames();

    /**
     * return all registerd accumulators
     *
     * @return
     */
    public List<String> getFunctionAccumulatorNames();

    /**
     * return all special accumulators
     *
     * @return
     */
    public List<String> getSpecialAccumulatorNames();

    /**
     * true is an accumulator exists
     */
    public boolean isAccumulatorRegistered(String acc);

    /**
     * @param acc name of am existing accumulator
     * @return !null existing accumulator
     */
    public Accumulator<Long> getAccumulator(String acc);

    /**
     * @param acc name of am existing accumulator
     * @return !null existing accumulator
     */
    public Accumulator<MachineUseAccumulator> getFunctionAccumulator(String acc);

    /**
     * @param acc name of am existing special accumulator
     * @return !null existing accumulator
     */
    public Accumulator getSpecialAccumulator(String acc);

    /**
     * add one to an existing accumulator
     *
     * @param acc
     */
    public void incrementAccumulator(String acc);

    /**
     * add added to an existing accumulator
     *
     * @param acc   name of am existing accumulator
     * @param added amount to add
     */
    public void incrementAccumulator(String acc, long added);

    /**
     * add 1 for use and add time
     * @param acc  name
     * @param totalTme  added time
     */
    public void incrementFunctionAccumulator(String acc, long totalTme);

    /**
     * make a special acculumator to look at use of machines
     * @param acc  name of the accumulator
     * @return  the accululator
     */
    public  Accumulator<MachineUseAccumulator> createFunctionAccumulator(String acc);

    /**
     *
     * @param id  name of the accumulator
     * @param param  object needed for construction
     * @param initialValue  how to initialize
     * @param <K>  type to accumulate
     * @return  the accululator
     */
    public  <K> Accumulator<K> createSpecialAccumulator(String id, AccumulatorParam<K> param, K initialValue);

    /**
     * make an accumulator of type long
     * @param acc name of the accumulator
     * @return the acculumator
     */
    public Accumulator<Long> createAccumulator(String acc);
    /**
     * add added to an existing accumulator
     *
     * @param acc   name of am existing accumulator
     * @param added amount to add
     */
    void incrementFunctionAccumulator(String acc, long totalTme, int added);
}

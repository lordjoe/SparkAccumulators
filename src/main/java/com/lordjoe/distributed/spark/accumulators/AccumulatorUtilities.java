package com.lordjoe.distributed.spark.accumulators;

import java.io.*;

/**
 * com.lordjoe.distributed.spark.accumulators.AccumulatorUtilities
 * Holds the only implementation of  ISparkAccumulators
 * also logging flag - this allows alternate implementations to be installed
 * User: Steve
 * Date: 8/7/2015
 */
public class AccumulatorUtilities implements Serializable {
   private static ISparkAccumulators onlyInstance;

    private static boolean functionsLoggedByDefault = true;

    public static boolean isFunctionsLoggedByDefault() {
        return functionsLoggedByDefault;
    }

    public static void setFunctionsLoggedByDefault(final boolean pFunctionsLoggedByDefault) {
        functionsLoggedByDefault = pFunctionsLoggedByDefault;
    }

    public static ISparkAccumulators getInstance() {
        return onlyInstance;
    }

    public static void setInstance(final ISparkAccumulators pOnlyInstance) {
        if(onlyInstance != null) {
            if(pOnlyInstance == onlyInstance)
                return;
            throw new IllegalStateException("onlyInstance cna only be set once");
        }
        onlyInstance = pOnlyInstance;
    }
}

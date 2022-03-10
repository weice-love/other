package org.example.agent;

import java.lang.instrument.Instrumentation;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/3/10 20:47
 */
public class PreAgent {

    static private Instrumentation inst = null;

    public static void premain(String agentArgs, Instrumentation _inst) {
        System.out.println("PreAgent.premain was called.");
        inst = _inst;
        PerfMonXformer perfMonXformer = new PerfMonXformer();
        System.out.println("Adding a PerfMonXformer instance to the jvm.");
        inst.addTransformer(perfMonXformer);
    }
}

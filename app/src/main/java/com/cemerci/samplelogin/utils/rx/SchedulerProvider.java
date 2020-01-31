package com.cemerci.samplelogin.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-21.
 */
public interface SchedulerProvider {
    Scheduler io();

    Scheduler ui();
}

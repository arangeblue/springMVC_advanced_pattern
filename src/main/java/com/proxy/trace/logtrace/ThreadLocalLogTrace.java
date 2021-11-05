package com.proxy.trace.logtrace;

import com.proxy.trace.TraceId;
import com.proxy.trace.TraceStatus;

import lombok.extern.slf4j.Slf4j;


/**
 * @title : ThreadLocalLogTrace
 * @author : wikyubok
 * @date : "2021-10-28 14:54:56"
 * @description : 필드 동기화 방법에서 동시성문제를 해결하기 위해 ThreadLocal이라는 객체를 사용.
 */

@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    
    // private TraceId traceIdHolder; // traceId 동기화, 동시성 문제 발생 나중에 처리
    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();



    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceIdHolder.get();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    private void syncTraceId() {
        TraceId traceId = traceIdHolder.get();
        if (traceId == null) {
            traceIdHolder.set(new TraceId());
        } else {
            traceIdHolder.set(traceId.createNextId());
        }
    }


    @Override
    public void end(TraceStatus status) {
        complete(status, null);
        
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }
    
    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()),
                    status.getMessage(), resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()),
                    status.getMessage(), resultTimeMs, e.toString());
        }

        releaseTraceId();
    }

    private void releaseTraceId() {
        TraceId traceId = traceIdHolder.get();
        if (traceId.isFirstLevel()) {
            traceIdHolder.remove(); //destroy, thread 해당 데이터만, 스프링은 thread 풀을 사용하는데 마지막에 데이터를 제거해주지 않으면 요청 때 다른 사용자의 정보가 보일 수 있음
        } else {
            traceIdHolder.set(traceId.createPreviousId());
        }
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "| ");
        }
        return sb.toString();
    }

}

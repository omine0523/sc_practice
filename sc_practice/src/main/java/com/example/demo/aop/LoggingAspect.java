package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


/**
 * Service層のメソッド実行時間を計測してログ出力するAOP
 *
 * ・Serviceメソッドの「実行開始〜終了」までを囲む
 * ・処理時間(ms)をINFOログで出力
 * ・例外が発生しても必ず処理時間をログに残す
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {
	/**
     * Service配下の全メソッドを対象に処理時間を計測する
     *
     * execution(* com.example.service..*(..))
     *  └ com.example.service 配下（サブパッケージ含む）
     *  └ 戻り値・引数はすべて対象
     */
    @Around("execution(* com.example.demo.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {

    	// メソッド実行前の時刻を取得する
        long start = System.currentTimeMillis();
        try {
        	// 対象メソッドを実行する
            return pjp.proceed();
        } finally {
        	// メソッド終了後（例外発生時も必ず実行）する
            long time = System.currentTimeMillis() - start;
            // メソッドシグネチャ（クラス名.メソッド名）と処理時間をログとして出力する
            log.info("{} executed in {}ms", pjp.getSignature(), time);
        }
    }
}

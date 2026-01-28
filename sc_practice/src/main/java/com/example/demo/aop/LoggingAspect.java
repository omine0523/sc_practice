package com.example.demo.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


/**
 * Service層のメソッド実行時間を計測してログ出力するアドバイザークラス
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
     * Service配下の全メソッドを対象に処理時間を計測・ログ出力する
     *
     * execution(* com.example.service..*(..))
     *  └ com.example.service 配下（サブパッケージ含む）
     *  └ 戻り値・引数はすべて対象
     */
    @Around("execution(* com.example.demo.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
    	
    	// メソッドシグネチャ（クラス名.メソッド名）とを文字列にする
        String methodName = pjp.getSignature().toShortString();   	
        // メソッド開始をログ出力する
        log.info("【START】 {}", methodName);
        
        // メソッド実行前の時刻を取得する
        long start = System.currentTimeMillis();

        try {
        	// 対象メソッドを実行する
            return pjp.proceed();
            
        } finally {
        	// メソッド終了後（例外発生時も）必ず実行する。	
        	// 処理にかかった時間を算出する。
        	long time = System.currentTimeMillis() - start;      
        	
            // ログ出力レベルがDEBUGで設定されている場合、メソッド内で使用された引数の値を出力する
            if (log.isDebugEnabled()) {
                log.debug("args={}", Arrays.toString(pjp.getArgs()));
            }
            
            // メソッド終了を出力し処理時間をログ出力する。
            log.info("【END】 {} executed in time={}ms", methodName, time);           
        }
    }
}

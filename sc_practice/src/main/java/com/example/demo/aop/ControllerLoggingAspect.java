package com.example.demo.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller層のメソッド実行時間を計測してログ出力するAspectクラス
 * 
 * ・Controllerメソッドの「実行開始〜終了」までの経過時間を割り出す
 * ・処理時間(ms)をINFOログで出力
 * ・例外が発生しても必ず処理時間をログに残す
 */
@Aspect
@Component
@Slf4j
public class ControllerLoggingAspect {
	/**
     * Controller配下の全メソッドを対象に処理時間を計測・ログ出力する
     *
     * execution(* com.example.controller..*(..))
     *  └ com.example.controller 配下（サブパッケージ含む）
     *  └ 戻り値・引数はすべて対象
     */
	@Around("execution(* com.example.demo.controller..*(..))")
	public Object logControllerExecutionTime(ProceedingJoinPoint pjp) throws Throwable{
		
		// メソッドシグネチャ（クラス名.メソッド名）とを文字列にする
		String methodName = pjp.getSignature().toShortString();
		// メソッド開始をログ出力する
		log.info("【CONTROLLER START】 {}", methodName);
		
		// メソッドの実行前の時刻を取得する
		long start = System.currentTimeMillis();
		
		try {
			// 対象のメソッドを実行する
			return pjp.proceed();
			
		}finally {
			// メソッド終了後（例外発生時も）必ず実行する。	
        	// 処理にかかった時間を算出する。
			long time = System.currentTimeMillis() - start;
			
			// ログ出力レベルがDEBUGで設定されている場合、メソッド内で使用された引数の値を出力する
            if (log.isDebugEnabled()) {
                log.debug("args={}", Arrays.toString(pjp.getArgs()));
            }
            
            // メソッドが終了したことと、処理時間をログ出力する。
            log.info("【CONTROLLER END】 {} executed in time={}ms", methodName ,time);
			
			
		}
	};
	

}

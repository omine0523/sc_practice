package com.example.demo.common.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * トランザクション管理に関する共通設定クラス。
 *
 * <p>
 * 分離レベル（Isolation Level）については本クラスでは指定せず、
 * 各サービスメソッドの {@Transactional} アノテーションで
 * 個別に指定する。
 * </p>
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfig {

	@Bean
	public PlatformTransactionManager transactionManager(
			DataSource dataSource) {

		// MyBatis向けのトランザクションマネージャを用意する。
		DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);

		// トランザクションのデフォルトタイムアウト（秒）
        // 指定時間を超えた場合、トランザクションはロールバックされる。
		txManager.setDefaultTimeout(30);

		return txManager;
	}
}

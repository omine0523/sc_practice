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

	/**
	 * MyBatisで実行された複数SQLを1トランザクションでまとめ例外時にロールバックされるよう管理する
	 * <p>
	 * 例外発生時に自動的にロールバックし、デフォルトのタイムアウト時間（30秒）を超えた場合にもロールバックされる。
	 * </p>
	 * @param dataSource トランザクション管理に使用するデータソース
	 * @return トランザクション管理を行う
	 */
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {

		// MyBatis向けのトランザクションマネージャを用意する。
		DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);

		// トランザクションのデフォルトタイムアウト（秒）
		// 指定時間を超えた場合、トランザクションはロールバックされる。
		txManager.setDefaultTimeout(30);

		return txManager;
	}
}

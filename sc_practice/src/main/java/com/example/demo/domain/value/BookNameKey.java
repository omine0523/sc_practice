package com.example.demo.domain.value;

import java.text.Normalizer;

import org.springframework.util.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 書籍名を安全に業務ロジックで扱うため値を加工するオブジェクト
 * 
 * 画面入力された書籍名に対して以下の正規化・加工を行う
 * このクラスを通した値のみをDB検索条件として使用する。
 * <ul>
 *   <li>null・空文字の場合は null として扱う</li>
 *   <li>全角文字を半角に正規化する（NFKC）</li>
 *   <li>前後の空白を削除する（trim）</li>
 *   <li>全角スペースを除去する</li>
 *   <li>LIKE検索用ワイルドカード（%, _ , \）をエスケープする</li>
 * </ul>
 */
@Getter
@EqualsAndHashCode
@ToString
public class BookNameKey {
	
	/** 正規化・加工後の書籍名（業務ロジックで使用する値）*/
	private final String value;

	/**
	 * 画面で入力された書籍名を正規化・空白除去・ワイルドカード対策を行い
	 * 値を加工しコンストラクタ。
	 * 
	 * @param input 画面で入力された書籍名
	 */
	public BookNameKey(String input) {
		// 未入力（null または 空文字）の場合は検索条件なしとして扱う
		if (!StringUtils.hasText(input)) {
            this.value = null;
            return;
        }

        // 正規化（全角→半角）する
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFKC);

        // 不要な前後空白を削除する
        normalized = normalized.trim();

        // 不要な全角スペースを削除する
        normalized = normalized.replace("　", "");

        // LIKE句で使用する時にワイルドカードが使用されないようにエスケープ処理をする。
        normalized = normalized
                .replace("\\", "\\\\")
                .replace("%", "\\%")
                .replace("_", "\\_");

        this.value = normalized;
	}

}

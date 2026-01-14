package com.example.demo.domain.value;

import java.text.Normalizer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
/**
 * 書籍ID表すオブジェクト
 * 
 * 画面入力された文字列の書籍IDを受け取り、
 *  以下を保証した Integer 値に変換する。
 * <ul>
 *   <li>未入力でないこと</li>
 *   <li>数字のみであること</li>
 *   <li>全角数字を半角に正規化していること</li>
 * </ul>
 */
@Getter
@EqualsAndHashCode
@ToString
public class BookId {
	
	// 正規化・変換済みの書籍ID（業務ロジックで使用する値）
	private final Integer value;
	
	/**
	 * 検索条件で入力した書籍ID（文字列）を業務ロジック用に正規化し、数値に変換する。
	 * 
	 * @param input 画面で入力された書籍ID
	 * @throws IllegalArgumentException 書籍IDが未入力、または正規化後の値が数字のみでない場合
	 */
	public BookId(String input) {
		// 入力した書籍IDがもし全角だった場合、半角に正規化する
		String normalized = Normalizer.normalize(input, Normalizer.Form.NFKC);
		
		// 正規化後の書籍IDに数字以外の文字が含まれている場合は不正とする
		if(!normalized.matches("\\d+")) {
			throw new IllegalArgumentException("書籍IDは数字のみです");
		}
		// 入力された文字列を業務ロジックで安全に使用できるよう数値に変換する
		this.value = Integer.valueOf(normalized);
	}		
}

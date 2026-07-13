
/**
 * 書籍削除画面の削除ボタンに対する確認ダイアログ処理
 *
 * - 各行の削除フォームに submit イベントを登録
 * - ユーザーがキャンセルした場合は送信を中止する
 * - DOM が完全に読み込まれてから処理する
 */
document.addEventListener('DOMContentLoaded', () => {
	// 全ての削除フォームを取得し、submitイベントを登録
	document.querySelectorAll('.delete-form').forEach(form => {
		form.addEventListener('submit', function(e) {
			// 削除確認モーダルを表示する
			if (!confirm('この書籍を削除しますか？')) {
				e.preventDefault(); // キャンセルしたら送信中止する
			}
		});
	});
});
# KeepTheBallUpPlugin
ボールを落とさずにどれだけ続けられるかゲーム
## コマンド
- /ktbu spawn : 基準座標にアーマースタンドを1体出現させる
- /ktbu spawn <num> : 基準座標にアーマースタンドを<num>体出現させる
- /ktbu setlocation : アーマースタンドを召喚する基準座標の設定を現在位置に設定
- /ktbu setR_max <value> : スポーン範囲の設定(半径)を設定
- /ktbu setMultiply <value> : 打ち出しの初速の大きさを設定
- /ktbu setPlayerOnly <true/false> : アーマースタンドを飛ばせるのをプレイヤー限定にするかどうかを設定
- /ktbu info : コマンド一覧を表示
- /ktbu showConfig : コンフィグの値を表示
## コンフィグ
|  変数 |  説明  |
| ---- | ---- |
|   R_max |  スポーン範囲の設定(半径)  |
|  multiply  |  打ち出しの初速の大きさ  |
| player_only | プレイヤー限定にするか(投射物に対応させるか) |
| ref_coordinate | 基準座標 |

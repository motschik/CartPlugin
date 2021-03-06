# CartPlugin
トロッコの高速化をはじめとした、spigot用プラグイン  
4つの機能が含まれています

# トロッコ高速化

線路の横に看板を配置することで看板の周囲8マスを通過するトロッコの速度制限を変更する。  
看板を連続で置く場合は2マス間を開けることで安定して動作する。

1. **速度制限設定**  
  - 1行目-コマンド：[speedlimit]または[speed limit]  
    2行目-制限速度：0.0 ~ 2.0(0.4が標準)  
  
  - 0.4を超える速度ではトロッコが曲がり角を曲がり切れず脱線することがあります。  
  
  - マイクラの仕様で制限速度を超過してもトロッコの移動速度はそれ以上上がらない  
  ただし、内部では超過している速度の情報を持っているため、制限を解除すると  
  その瞬間に内部で持っていた速度に達する。  
  
2. **空中移動**  
  - 1行目-コマンド：[fly]  
  トロッコが線路を離れても落下しなくなります  
  
  - 1行目-コマンド：[!fly]  
  トロッコが自然落下するようになります  
  
  - 低コストで長距離移動ができるようになります  
  景観を崩したくない場合も便利です
  
  - 空中に浮くようになったトロッコは移動した後  
  レールに乗るかブロックにぶつかるまで止まらなくなるので注意
  
# 駅名表示

駅到着時に駅名などを画面上に表示します

- **看板設置位置**  
トロッコが**ブロックに衝突して**停車する位置の上下に設置することで作動

  - パターン1  
■←看板  
□←任意のブロック  
＿←線路

  - パターン2  
＿←線路  
□←任意のブロック  
■←看板  

- **記述内容**  

  - 1行目：画面中央に表示される大きなタイトル  
  - 2行目：画面中央の文字の下に表示されるサブタイトル  
  - 3行目：チャット欄に表示される文章1行目  
  - 4行目：チャット欄に表示される文章2行目  
  
  看板に記入しきれない場合は以下のコマンドを利用して文章を登録する。  
  登録した文章は"/キー"と看板に書くことで呼び出すことができる。  
  - 追加 /message add [キー] [文章]  
  - 更新 /message update [キー] [文章]  
  - 削除 /message delete [キー]  
  - 取得 /message get [キー]  
  - 全取得 /message list  

# SPSU

適度にスペクテイターモードになれーる
地下の作業をするときに安全確認やほかの装置との競合を確認する目的です。
スペクテイターモードのままログアウトすると元の位置に戻り、サバイバルモードになります。

- /sp スペクテイターモードに変更

- /su サバイバルモードに変更し、スペクテイターモードへ切り替えた位置に戻る

# 睡眠を求めて

寝るときは「zzz」とおしゃべりします  
これを見たら、寝るか観客モードに入って夜を過ごしましょう

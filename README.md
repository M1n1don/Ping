# Ping  
Get ping plugin for Bukkit/Spigot  
[ダウンロード](https://github.com/M1n1don/Ping/releases/latest)

## Features  
* 1.16.5対応
* TabComplete対応
* 非常にシンプルで使いやすい
* 権限設定や表示されるメッセージなどは全てコンフィグから変更できる
* APIがある

## Commands  
| Command | Description | Default |
| ------- | ----------- | ------- |
| /ping | 実行者のPingを取得 | ALL |
| /ping [player] | ターゲットのPingを取得 | OP |
| /ping reload | コンフィグをリロード | OP |

## API  
このプラグインを開発ソフトで読み込ませてください。

## Usage  
- onEnable
```java
private Ping ping;

@Override
public void onEnable()
{
    Plugin plugin = Bukkit.getPluginManager().getPlugin("Ping");
    if (plugin == null || !plugin.isEnabled()) getLogger().warning("Ping（プラグイン）が存在しません。");
    this.ping = (Ping) plugin;
}
```

- Get ping
```java
// onCommand内の場合
Player player = (Player) sender; 
sender.sendMessage(ping.getAPI().getPing(player));
```

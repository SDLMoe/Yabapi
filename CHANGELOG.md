# Change Log

本文件自 0.0.4 版本開始記錄

## 2022-01

### 0.0.8

#### ADD

- 爲 `getVideoApi` 添加 `cid` 參數, 可獲取如字幕等依據分 P 不同的內容
- 字幕獲取 `getSubtitleContent` 以及 `toSrt` 工具方法
- `getBangumiReviewInfo` 可根據 MD 號獲取 SS 號碼

#### CHANGE

- 重命名 `A64K` `A128K` 等爲 `AUDIO_LOW` `AUDIO_MEIDUM` `AUDIO_HIGH` 並新增 `AUDIO_DOLBY` 

#### FIX

- `buildUrl` 行爲
- 清除無用依賴

#### MISC

- 新增 `SNAPSHOT` 倉庫

### 0.0.7

#### ADD

- 動態
    - 獲取動態更新了的關注用戶
    - 獲取正在直播的關注用戶
- 歷史
    - 過濾及排序請求參數支持
- 收藏
    - 獲取收藏夾信息
    - 獲取收藏夾內容
- 直播
    - 根據房間號獲取直播信息
- 視頻流
    - AV1 請求

#### FIX

- 登錄
    - 短信登錄
    - 密碼登錄

#### CHANGE

- 將 `collections` 相關項目更名爲 `favorties`, 以避免混淆(合集和收藏)

### 0.0.6

#### ADD

- 動態
    - 分享解析

#### CHANGE

- 令絕大多數外部數據可空, 默認類型安全, 因此會產生一定的兼容性問題

### 0.0.5

#### ADD

- 动态
    - 合集解析
- 个人空间
    - 稍后再看
    - 相簿
    - 视频合集
    - 订阅标签
    - 订阅番剧
    - 收藏夹
- 搜索
    - 综合搜索
    - 分类搜索
        - 结果解析
        - 过滤参数
- `VideoType` 新增 `parent` 属性

#### FIX

- 修复包名错误, `sdl.moe.yabapi` to `moe.sdl.yabapi`, 因此完全破坏了兼容性.

### 0.0.4

#### ADD

- 动态获取及其解析
- 相簿图片上传

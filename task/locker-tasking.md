### ----存包裹----
- Given locker 最大容量 20，已存包裹包裹0-19（0，19，10），when 存包裹，then 寄存成功 返回ticket
- Given locker 最大容量 20，已存包裹20，when 存包裹，then 寄存失败 返回异常locker已经满了

### ----取包裹----
- Given ticket 有效，when 取包裹，then 取包成功 ticket失效 
- Given ticket 无效，when 取包裹，then 取包失败 返回异常 无效票据


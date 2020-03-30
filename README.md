# TDD「Locker Robot」

## Tasking
### ----存包裹----
- Given locker 最大容量 20，已经使用的容量为 0，when 存包裹，then 寄存成功 返回ticket
- Given locker 最大容量 20，已经使用的容量为 10，when 存包裹，then 寄存成功 返回ticket
- Given locker 最大容量 20，已经使用的容量为 19，when 存包裹，then 寄存成功 返回ticket
- Given locker 最大容量 20，已经使用的容量为 20，when 存包裹，then 寄存失败 返回异常locker已经满了

### ----取包裹----
- Given 一个包裹 和它对应的 ticket，when 使用ticket 取包裹，then 取包裹成功 得到同一个包裹 
- Given 一个包裹 和它对应的 ticket，when 使用ticket 取包裹，then 取包裹成功 ticket失效
- Given 一个包裹 和它对应的 ticket，when 使用同一张 ticket 取两次包裹，then 取包失败 抛出异常 无效票据


